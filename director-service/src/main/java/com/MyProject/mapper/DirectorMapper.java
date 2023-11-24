package com.MyProject.mapper;

import com.MyProject.dto.NewDirector;
import com.MyProject.dto.DirectorDto;
import com.MyProject.dto.UpdateDirectorDto;
import com.MyProject.model.Director;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);
    DirectorDto toDto(Director director);
    @Mapping(ignore = true, target = "id")
    Director toEntityFromNewDirector(NewDirector newDirector);
    @Mapping(ignore = true, target = "id")
    Director toEntityFromDirectorDto(DirectorDto directorDto);
    @Mapping(ignore = true, target = "id")
    Director updateDirector(@MappingTarget Director director, UpdateDirectorDto directorDto);
    List<DirectorDto> toDtoList(List<Director> directors);

}
