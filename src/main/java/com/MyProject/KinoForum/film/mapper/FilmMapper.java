package com.MyProject.KinoForum.film.mapper;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.director.dto.DirectorDto;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;
import com.MyProject.KinoForum.film.dto.UpdateFilmDto;
import com.MyProject.KinoForum.film.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FilmMapper {
    @Mapping(target = "directorId", source = "director.id")
    @Mapping(target = "categoryId", source = "category.id")
    FilmDto toDto(Film film);
    @Mapping(ignore = true, target = "id")
    @Mapping(target = "director", source = "director")
    @Mapping(target = "category", source = "category")
    Film toEntityFromNewFilm(NewFilm newFilm, Director director, Category category);
    @Mapping(ignore = true, target = "id")
    @Mapping(target = "director", source = "director")
    @Mapping(target = "category", source = "category")
    Film toEntityFromFilmDto(FilmDto filmDto, Director director, Category category);
    @Mapping(ignore = true, target = "id")
    @Mapping(target = "director", source = "director")
    @Mapping(target = "category", source = "category")
    Film updateFilm(@MappingTarget Film film, UpdateFilmDto filmDto, Director director, Category category);
    List<FilmDto> toDtoList(List<Film> films);

}
