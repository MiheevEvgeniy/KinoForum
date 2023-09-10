package com.MyProject.KinoForum.director.mapper;

import com.MyProject.KinoForum.director.dto.NewDirector;
import com.MyProject.KinoForum.director.dto.DirectorDto;
import com.MyProject.KinoForum.director.model.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);
    DirectorDto toDto(Director user);
    Director toEntityFromNewDirector(NewDirector user);
    Director toEntityFromDirectorDto(DirectorDto user);
}
