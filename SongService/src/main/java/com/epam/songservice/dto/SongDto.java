package com.epam.songservice.dto;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

public class SongDto {

    public SongDto(String name, String artist, String album, String length, Integer resourceId, Integer year) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.resourceId = resourceId;
        this.year = year;
    }

    public SongDto() {
    }

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String artist;

    @Size(max = 255)
    private String album;

    private String length;

    private Integer resourceId;

    @Min(1399)
    private Integer year;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
