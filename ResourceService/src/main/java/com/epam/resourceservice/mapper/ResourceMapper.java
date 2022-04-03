package com.epam.resourceservice.mapper;

import com.epam.resourceservice.dto.ResourceItemDto;
import com.epam.resourceservice.dto.ResourceItemIdDto;
import com.epam.resourceservice.entity.ResourceItem;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {

    public ResourceItem toItem(ResourceItemDto resourceItemDto) {
        return new ResourceItem(resourceItemDto.getBucketName(), resourceItemDto.getFileName());
    }

    public ResourceItemDto toDto(ResourceItem resourceItem) {
        return new ResourceItemDto(resourceItem.getBucketName(), resourceItem.getFileName());
    }

    public ResourceItemIdDto toIdDto(ResourceItem resourceItem) {
        return new ResourceItemIdDto(resourceItem.getId());
    }

}
