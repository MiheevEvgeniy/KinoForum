package com.MyProject.KinoForum.user.service;

import com.MyProject.KinoForum.user.dto.NewUser;
import com.MyProject.KinoForum.user.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDto addUser(NewUser newUser);

    UserDto getUser(Long userId);

    List<UserDto> getAllUsers(int size, int from);

    void deleteUser(Long userId);

    UserDto patchUser(Map<String, Object> fields, Long userId);
}
