package com.MyProject.KinoForum.film.controller;

import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;
import com.MyProject.KinoForum.film.dto.UpdateFilmDto;
import com.MyProject.KinoForum.film.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Map;

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
                                @RequestParam(value = "directorId",required = false) Long directorId,
                                @RequestParam(value = "rating",required = false) FilmRating rating,
                                @RequestParam(value = "country",required = false) String country,
                                @RequestParam(value = "title",required = false) String title,
                                @RequestParam(value = "categoryId",required = false) Long categoryId){
        log.info("search started with size: {} and from: {}", size, from);
        List<FilmDto> result = service.search(size,from,releaseYear,directorId,rating,country,title, categoryId);
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
