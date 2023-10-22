package com.MyProject.KinoForum.user.service;

import com.MyProject.KinoForum.exceptions.NotFoundException;
import com.MyProject.KinoForum.user.dto.NewUser;
import com.MyProject.KinoForum.user.dto.UserDto;
import com.MyProject.KinoForum.user.mapper.UserMapper;
import com.MyProject.KinoForum.user.model.User;
import com.MyProject.KinoForum.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDto addUser(NewUser newUser) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewUser(newUser)));
    }

    public UserDto getUser(Long userId) {
        return mapper.toDto(getUserEntity(userId));
    }
    public User getUserEntity(Long userId){
        Optional<User> user = repository.findById(userId);
        if(user.isEmpty()) throw new NotFoundException("User not found");
        return user.get();
    }

    public List<UserDto> getAllUsers(int size, int from) {
        PagedListHolder<UserDto> page = new PagedListHolder<>(repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        page.setPageSize(size);
        page.setPage(from);
        return page.getPageList();
    }

    public void deleteUser(Long userId) {
        repository.delete(mapper.toEntityFromUserDto(getUser(userId)));
    }

    public UserDto patchUser(Map<String, Object> fields, Long userId) {
        Optional<User> user = repository.findById(userId);
        if(!user.isPresent()) throw new NotFoundException("User not found");
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(User.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, user.get(), v);
        });
        return mapper.toDto(repository.save(user.get()));
    }
}
