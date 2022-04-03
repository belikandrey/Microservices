package com.epam.resourceservice.dto;

import javax.validation.constraints.Size;

public class ResourceItemDto {

    @Size(max = 255)
    private String bucketName;

    @Size(max = 255)
    private String fileName;

    public ResourceItemDto(String bucketName, String fileName) {
        this.bucketName = bucketName;
        this.fileName = fileName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
