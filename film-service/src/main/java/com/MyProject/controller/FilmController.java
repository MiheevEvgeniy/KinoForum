package com.MyProject.controller;

import com.MyProject.enums.FilmRating;
import com.MyProject.dto.FilmDto;
import com.MyProject.dto.NewFilm;
import com.MyProject.dto.UpdateFilmDto;
import com.MyProject.service.FilmService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
@Slf4j
@Validated
public class FilmController {
    private final FilmService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto addFilm(@RequestBody @Valid NewFilm newFilm){
        log.info("addFilm started with body: {}", newFilm);
        FilmDto result = service.addFilm(newFilm);
        log.info("addFilm finished: {}", result);
        return result;
    }
    @GetMapping("/{id}")
    public FilmDto getFilm(@PathVariable("id") long filmId){
        log.info("getFilm started with id: {}", filmId);
        FilmDto result = service.getFilm(filmId);
        log.info("getFilm finished: {}", result);
        return result;
    }
    @GetMapping
    public List<FilmDto> search(@RequestParam(value = "size",required = false, defaultValue = "5") @Min(5) int size,
                                @RequestParam(value = "from",required = false, defaultValue = "0") @Min(0) int from,
                                @RequestParam(value = "releaseYear",required = false) Integer releaseYear,
                                @RequestParam(value = "directorIds",required = false) List<Long> directorIds,
                                @RequestParam(value = "rating",required = false) FilmRating rating,
                                @RequestParam(value = "country",required = false) String country,
                                @RequestParam(value = "title",required = false) String title,
                                @RequestParam(value = "categoryIds",required = false) List<Long> categoryIds){
        log.info("search started with size: {} and from: {}", size, from);
        List<FilmDto> result = service.search(size,from,releaseYear,directorIds,rating,country,title, categoryIds);
        log.info("search finished: {}", result);
        return result;
    }
    @PatchMapping("/{id}")
    public FilmDto patchFilm(@RequestBody UpdateFilmDto upd, @PathVariable("id") long filmId){
        log.info("patchFilm started with body: {} and  id: {}",upd, filmId);
        FilmDto result = service.patchFilm(upd,filmId);
        log.info("patchFilm finished: {}", result);
        return result;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFilm(@PathVariable("id") long filmId){
        log.info("deleteFilm started with id: {}", filmId);
        service.deleteFilm(filmId);
        log.info("deleteFilm finished");
    }
}
