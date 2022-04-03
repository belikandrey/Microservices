package com.epam.songservice.controller;

import com.epam.songservice.dto.SongDto;
import com.epam.songservice.dto.SongIdDto;
import com.epam.songservice.dto.SongIdsDto;
import com.epam.songservice.exception.EntityNotFoundException;
import com.epam.songservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/songs")
public class SongController {
  private final SongService songService;

  @Autowired
  public SongController(SongService songService) {
    this.songService = songService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> createSong(@RequestBody @Validated SongDto songDto) {
    final SongIdDto songIdDto = songService.save(songDto);
    return new ResponseEntity<>(songIdDto, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> getSongById(@PathVariable(name = "id") Integer id)
      throws EntityNotFoundException {
    final SongDto songDto = songService.findById(id);
    return new ResponseEntity<>(songDto, HttpStatus.OK);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> deleteSongsByIds(@RequestParam(name = "id") String ids) {
    final Collection<Integer> deletedIds = songService.deleteSongs(ids);
    return new ResponseEntity<>(new SongIdsDto(deletedIds.toArray(new Integer[0])), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public String handle400() {
    return "Validation error missing metadata";
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  public String handle404() {
    return "Resource doesn't exist with given id";
  }
}
