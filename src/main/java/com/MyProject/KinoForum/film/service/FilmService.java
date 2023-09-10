package com.MyProject.KinoForum.film.service;

import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;

import java.util.List;
import java.util.Map;

public interface FilmService {
    FilmDto addFilm(NewFilm newFilm);
    FilmDto getFilm(long filmId);
    List<FilmDto> search(int size, int from, Integer releaseYear, String director, FilmRating rating, String country, String title, String category);
    FilmDto patchFilm(Map<String, Object> fields, long filmId);
    void deleteFilm(long filmId);
}
