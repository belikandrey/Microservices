package com.epam.resourceservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.epam.resourceservice.dto.ResourceItemBytesDto;
import com.epam.resourceservice.dto.ResourceItemIdDto;
import com.epam.resourceservice.entity.ResourceItem;
import com.epam.resourceservice.exception.NotFoundException;
import com.epam.resourceservice.mapper.ResourceMapper;
import com.epam.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceService {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucketname}")
    private String bucketName;

    @Autowired
    public ResourceService(AmazonS3 amazonS3, ResourceRepository repository, ResourceMapper mapper) {
        this.amazonS3 = amazonS3;
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResourceItemIdDto createResourceItem(MultipartFile file) {
        File newFile = new File(generateFileName(file));
        try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
            fileOutputStream.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Runtime...");
        }
        amazonS3.putObject(bucketName, newFile.getName(), newFile);
        final ResourceItem resourceItem = new ResourceItem(bucketName, newFile.getName());
        newFile.delete();
        return mapper.toIdDto(repository.save(resourceItem));
//        final S3Object object = amazonS3.getObject("", "");
//        final S3ObjectInputStream objectContent = object.getObjectContent();
//        final byte[] bytes = IOUtils.toByteArray(objectContent);
    }

    public ByteArrayResource getResourceItemContent(Integer id) throws NotFoundException {
        final ResourceItem resourceItem = repository.findById(id).orElseThrow(() -> new NotFoundException("Resource doesn't exist with given id"));
        S3Object object = amazonS3.getObject(resourceItem.getBucketName(), resourceItem.getFileName());
        final S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return new ByteArrayResource(IOUtils.toByteArray(objectContent));
            //return new ResourceItemBytesDto(IOUtils.toByteArray(objectContent));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public List<Integer> deleteResources(String ids) {
        final List<Integer> idsList = parseIds(ids);
        return repository.deleteAllByIdIn(idsList).stream().peek(this::deleteFromS3).map(ResourceItem::getId).collect(Collectors.toList());
    }


    private void deleteFromS3(ResourceItem resourceItem) {
        amazonS3.deleteObject(resourceItem.getBucketName(), resourceItem.getFileName());
    }

    private List<Integer> parseIds(String ids) {
        return Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private String generateFileName(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).replaceAll(".mp3", "") + "_" + System.currentTimeMillis() + ".mp3";
    }
}
