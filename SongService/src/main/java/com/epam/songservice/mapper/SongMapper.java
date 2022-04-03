package com.epam.songservice.mapper;

import com.epam.songservice.dto.SongDto;
import com.epam.songservice.dto.SongIdDto;
import com.epam.songservice.entity.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

  public SongDto toDto(Song song) {
    return new SongDto(
        song.getName(),
        song.getArtist(),
        song.getAlbum(),
        song.getLength(),
        song.getResourceId(),
        song.getYear());
  }

  public Song toEntity(SongDto songDto) {
    return new Song(
        songDto.getName(),
        songDto.getArtist(),
        songDto.getAlbum(),
        songDto.getLength(),
        songDto.getResourceId(),
        songDto.getYear());
  }

  public SongIdDto toIdDto(Song song) {
    return new SongIdDto(song.getId());
  }
}
