package com.epam.songservice.repository;

import com.epam.songservice.entity.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface SongRepository extends CrudRepository<Song, Integer> {
    List<Song> deleteAllByIdIn(List<Integer> ids);
}
