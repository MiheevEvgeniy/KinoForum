package com.MyProject.KinoForum.film.mapper;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;
import com.MyProject.KinoForum.film.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    @Mapping(target = "directorId", source = "director.id")
    @Mapping(target = "categoryId", source = "category.id")
    FilmDto toDto(Film film);
    @Mapping(ignore = true, target = "id")
    @Mapping(target = "director", source = "director")
    @Mapping(target = "category", source = "category")
    Film toEntityFromNewUser(NewFilm newFilm, Director director, Category category);
    @Mapping(ignore = true, target = "id")
    @Mapping(target = "director", source = "director")
    @Mapping(target = "category", source = "category")
    Film toEntityFromUserDto(FilmDto filmDto, Director director, Category category);
}
