package com.MyProject.KinoForum.film.mapper;

import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;
import com.MyProject.KinoForum.film.model.Film;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    FilmDto toDto(Film film);
    Film toEntityFromNewUser(NewFilm newFilm);
    Film toEntityFromUserDto(FilmDto filmDto);
}
