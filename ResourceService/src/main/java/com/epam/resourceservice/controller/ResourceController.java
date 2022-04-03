package com.epam.resourceservice.controller;

import com.epam.resourceservice.dto.ResourceItemIdsDto;
import com.epam.resourceservice.exception.NotFoundException;
import com.epam.resourceservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Locale;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<?> createResourceItem(@RequestParam(name = "file") MultipartFile file) {
        if (file.getName().toLowerCase(Locale.ROOT).endsWith(".mp3")) {
            return new ResponseEntity<>("Validation error or request body is an invalid mp3", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resourceService.createResourceItem(file), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResourceById(@PathVariable(name = "id") Integer id) throws NotFoundException {
        return new ResponseEntity<>(resourceService.getResourceItemContent(id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteResource(@RequestParam(name = "id") String ids) {
        return new ResponseEntity<>(new ResourceItemIdsDto(resourceService.deleteResources(ids)), HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handle404(NotFoundException e) {
        return e.getMessage();
    }
}
