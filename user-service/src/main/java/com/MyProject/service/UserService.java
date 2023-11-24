package com.MyProject.service;

import com.MyProject.exceptions.NotFoundException;
import com.MyProject.dto.NewUser;
import com.MyProject.dto.UpdateUserDto;
import com.MyProject.dto.UserDto;
import com.MyProject.mapper.UserMapper;
import com.MyProject.model.User;
import com.MyProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return mapper
                .toDtoList(
                        repository
                                .findAll(PageRequest.of(from,size))
                                .toList());

    }

    public void deleteUser(Long userId) {
        repository.deleteById(userId);
    }
    public UserDto patchUser(UpdateUserDto upd, Long userId) {
        return mapper.toDto(
                repository.save(mapper.updateUser(
                        repository
                                .findById(userId)
                                .orElseThrow(()->new NotFoundException("User for update not found")), upd)));
    }
}
