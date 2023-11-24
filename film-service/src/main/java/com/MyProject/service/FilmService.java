package com.MyProject.service;

import com.MyProject.enums.FilmRating;
import com.MyProject.exceptions.NotFoundException;
import com.MyProject.dto.FilmDto;
import com.MyProject.dto.NewFilm;
import com.MyProject.dto.UpdateFilmDto;
import com.MyProject.mapper.FilmMapper;
import com.MyProject.model.Film;
import com.MyProject.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.MyProject.model.FilmSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmService{
    private final FilmRepository repository;
    private final FilmMapper mapper;
    public FilmDto addFilm(NewFilm newFilm) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewFilm(newFilm)));
    }

    public FilmDto getFilm(long filmId) {
        Optional<Film> film = repository.findById(filmId);
        if(!film.isPresent()) throw new NotFoundException("Film not found");
        return mapper.toDto(film.get());
    }

    public List<FilmDto> search(int size,
                                int from,
                                Integer releaseYear,
                                List<Long> directorIds,
                                FilmRating rating,
                                String country,
                                String title,
                                List<Long> categoryIds) {
        return mapper
                .toDtoList(
                        repository
                                .findAll(where(withYear(releaseYear))
                                        .and(withTitle(title))
                                        .and(withDirector(directorIds))
                                        .and(withRating(rating))
                                        .and(withCountry(country))
                                        .and(withCategory(categoryIds)),
                                        PageRequest.of(from,size))
                                .toList());
    }
    public FilmDto patchFilm(UpdateFilmDto upd, long filmId) {
        List<Long> directorIds = null;
        List<Long> categoryIds = null;

        if (upd.getDirectorId() != null){
            directorIds = List.of(upd.getDirectorId());
        }
        if (upd.getCategoryId() != null){
            categoryIds = List.of(upd.getCategoryId());
        }

        return mapper.toDto(
                repository.save( mapper.updateFilm(
                                repository
                                        .findById(filmId)
                                        .orElseThrow(()->new NotFoundException("Film for update not found")),
                                upd,
                        directorIds,
                        categoryIds)));
    }

    public void deleteFilm(long filmId) {
        repository.deleteById(filmId);
    }
}
