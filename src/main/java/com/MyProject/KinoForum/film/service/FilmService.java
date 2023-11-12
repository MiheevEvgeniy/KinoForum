package com.MyProject.KinoForum.film.service;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.category.service.CategoryService;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.director.service.DirectorService;
import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;
import com.MyProject.KinoForum.film.dto.UpdateFilmDto;
import com.MyProject.KinoForum.film.mapper.FilmMapper;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.film.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.MyProject.KinoForum.film.model.FilmSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmService{
    private final FilmRepository repository;
    private final DirectorService directorService;
    private final CategoryService categoryService;
    private final FilmMapper mapper;
    public FilmDto addFilm(NewFilm newFilm) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewFilm(
                newFilm,
                directorService.getDirectorEntity(newFilm.getDirectorId()),
                categoryService.getCategoryEntity(newFilm.getCategoryId())
                )));
    }

    public FilmDto getFilm(long filmId) {
        Optional<Film> film = repository.findById(filmId);
        if(!film.isPresent()) throw new NotFoundException("Film not found");
        return mapper.toDto(film.get());
    }

    public List<FilmDto> search(int size,
                                int from,
                                Integer releaseYear,
                                Long directorId,
                                FilmRating rating,
                                String country,
                                String title,
                                Long categoryId) {
        Category category = null;
        Director director = null;
        if (categoryId != null){
            category = categoryService.getCategoryEntity(categoryId);
        }
        if (directorId != null){
            director = directorService.getDirectorEntity(directorId);
        }
        return mapper
                .toDtoList(
                        repository
                                .findAll(where(withYear(releaseYear))
                                        .and(withTitle(title))
                                        .and(withDirector(director))
                                        .and(withRating(rating))
                                        .and(withCountry(country))
                                        .and(withCategory(category)),
                                        PageRequest.of(from,size))
                                .toList());
    }
    public FilmDto patchFilm(UpdateFilmDto upd, long filmId) {
        Director director = null;
        Category category = null;

        if (upd.getDirectorId() != null){
            director = directorService.getDirectorEntity(upd.getDirectorId());
        }
        if (upd.getCategoryId() != null){
            category = categoryService.getCategoryEntity(upd.getCategoryId());
        }

        return mapper.toDto(
                repository.save( mapper.updateFilm(
                                repository
                                        .findById(filmId)
                                        .orElseThrow(()->new NotFoundException("Film for update not found")),
                                upd,
                                director,
                                category)));
    }

    public void deleteFilm(long filmId) {
        repository.deleteById(filmId);
    }
}
