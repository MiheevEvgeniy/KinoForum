package com.MyProject.KinoForum.user.mapper;

import com.MyProject.KinoForum.film.dto.UpdateFilmDto;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.user.dto.NewUser;
import com.MyProject.KinoForum.user.dto.UpdateUserDto;
import com.MyProject.KinoForum.user.dto.UserDto;
import com.MyProject.KinoForum.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(User user);
    @Mapping(ignore = true, target = "id")
    User toEntityFromNewUser(NewUser user);
    @Mapping(ignore = true, target = "id")
    User toEntityFromUserDto(UserDto user);
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "email")
    User updateUser(@MappingTarget User user, UpdateUserDto userDto);

    List<UserDto> toDtoList(List<User> user);
}
