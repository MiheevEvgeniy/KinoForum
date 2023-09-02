package com.MyProject.KinoForum.user.mapper;

import com.MyProject.KinoForum.user.dto.NewUser;
import com.MyProject.KinoForum.user.dto.UserDto;
import com.MyProject.KinoForum.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(User user);
    User toEntityFromNewUser(NewUser user);
    User toEntityFromUserDto(UserDto user);
}
