package com.epam.songservice.service;

import com.epam.songservice.dto.SongDto;
import com.epam.songservice.dto.SongIdDto;
import com.epam.songservice.entity.Song;
import com.epam.songservice.exception.EntityNotFoundException;
import com.epam.songservice.mapper.SongMapper;
import com.epam.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SongService {

  private final SongRepository songRepository;
  private final SongMapper songMapper;

  @Autowired
  public SongService(SongRepository songRepository, SongMapper songMapper) {
    this.songRepository = songRepository;
    this.songMapper = songMapper;
  }

  public SongDto findById(Integer id) throws EntityNotFoundException {
    return songMapper.toDto(songRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }

  public SongIdDto save(SongDto songDto) {
    return songMapper.toIdDto(songRepository.save(songMapper.toEntity(songDto)));
  }

  public List<Integer> deleteSongs(String ids) {
    final List<Integer> idsList = parseIds(ids);
    return songRepository.deleteAllByIdIn(idsList).stream()
        .map(Song::getId)
        .collect(Collectors.toList());
  }

  private List<Integer> parseIds(String ids) {
    return Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
  }
}
