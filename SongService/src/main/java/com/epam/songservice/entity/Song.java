package com.epam.songservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Song {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "artist")
  private String artist;

  @Column(name = "album")
  private String album;

  @Column(name = "length")
  private String length;

  @Column(name = "resourceId")
  private Integer resourceId;

  @Column(name = "year")
  private Integer year;

  public Song() {}

  public Song(
      String name,
      String artist,
      String album,
      String length,
      Integer resourceId,
      Integer year) {
    this.name = name;
    this.artist = artist;
    this.album = album;
    this.length = length;
    this.resourceId = resourceId;
    this.year = year;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Song song = (Song) o;
    return Objects.equals(id, song.id)
        && Objects.equals(name, song.name)
        && Objects.equals(artist, song.artist)
        && Objects.equals(album, song.album)
        && Objects.equals(length, song.length)
        && Objects.equals(resourceId, song.resourceId)
        && Objects.equals(year, song.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, artist, album, length, resourceId, year);
  }

  @Override
  public String toString() {
    return "Song{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", artist='"
        + artist
        + '\''
        + ", album='"
        + album
        + '\''
        + ", length="
        + length
        + ", resourceId="
        + resourceId
        + ", year="
        + year
        + '}';
  }
}
