package com.MyProject.mapper;

import com.MyProject.dto.FilmDto;
import com.MyProject.dto.NewFilm;
import com.MyProject.dto.UpdateFilmDto;
import com.MyProject.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FilmMapper {
    FilmDto toDto(Film film);
    @Mapping(ignore = true, target = "id")
    Film toEntityFromNewFilm(NewFilm newFilm);
    @Mapping(ignore = true, target = "id")
    Film toEntityFromFilmDto(FilmDto filmDto);
    @Mapping(ignore = true, target = "id")
    @Mapping(target = "directorIds", source = "directorIds")
    @Mapping(target = "categoryIds", source = "categoryIds")
    Film updateFilm(@MappingTarget Film film, UpdateFilmDto filmDto, List<Long> directorIds, List<Long> categoryIds);
    List<FilmDto> toDtoList(List<Film> films);

}
