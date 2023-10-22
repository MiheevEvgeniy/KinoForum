package com.MyProject.KinoForum.film.service;

import com.MyProject.KinoForum.category.service.CategoryService;
import com.MyProject.KinoForum.director.service.DirectorService;
import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;
import com.MyProject.KinoForum.film.mapper.FilmMapper;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.film.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.MyProject.KinoForum.film.model.FIlmSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class FilmService{
    private final FilmRepository repository;
    private final DirectorService directorService;
    private final CategoryService categoryService;
    private final FilmMapper mapper;
    public FilmDto addFilm(NewFilm newFilm) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewUser(
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
                                String director,
                                FilmRating rating,
                                String country,
                                String title,
                                String category) {
        PagedListHolder<FilmDto> page = new PagedListHolder<>(repository.findAll(where(withYear(releaseYear))
                        .and(withTitle(title))
                        .and(withDirector(director))
                        .and(withRating(rating))
                        .and(withCountry(country))
                        .and(withCategory(category)))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        page.setPageSize(size);
        page.setPage(from);
        return page.getPageList();
    }

    public FilmDto patchFilm(Map<String, Object> fields, long filmId) {
        Optional<Film> film = repository.findById(filmId);
        if(!film.isPresent()) throw new NotFoundException("Film not found");
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Film.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, film.get(), v);
        });
        return mapper.toDto(repository.save(film.get()));
    }

    public void deleteFilm(long filmId) {
        repository.deleteById(filmId);
    }
}
