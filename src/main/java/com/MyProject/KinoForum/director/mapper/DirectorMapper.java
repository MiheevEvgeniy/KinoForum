package com.MyProject.KinoForum.director.mapper;

import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.director.dto.NewDirector;
import com.MyProject.KinoForum.director.dto.DirectorDto;
import com.MyProject.KinoForum.director.dto.UpdateDirectorDto;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.user.dto.UpdateUserDto;
import com.MyProject.KinoForum.user.model.User;
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
