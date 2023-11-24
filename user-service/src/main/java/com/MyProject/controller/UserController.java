package com.MyProject.controller;


import com.MyProject.dto.NewUser;
import com.MyProject.dto.UpdateUserDto;
import com.MyProject.dto.UserDto;
import com.MyProject.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@Valid @RequestBody NewUser newUser){
        log.info("addUser started with body: {}",newUser);
        UserDto result = service.addUser(newUser);
        log.info("addUser finished: {}", result);
        return result;
    }
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long userId){
        log.info("getUser started with id: {}",userId);
        UserDto result = service.getUser(userId);
        log.info("getUser finished: {}", result);
        return result;
    }
    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(value = "size",required = false, defaultValue = "5") @Min(5) int size,
                                     @RequestParam(value = "from",required = false, defaultValue = "0") @Min(0) int from){
        log.info("getAllUsers started with params: size {}, from {}",size, from);
        List<UserDto> result = service.getAllUsers(size,from);
        log.info("getAllUsers finished: {}", result);
        return result;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long userId){
        log.info("deleteUser started with id: {}",userId);
        service.deleteUser(userId);
        log.info("deleteUser finished");
    }
    @PatchMapping("/{id}")
    public UserDto patchUser(@RequestBody UpdateUserDto upd, @PathVariable("id") Long userId){
        log.info("patchUser started with body: {} and id: {}",upd, userId);
        UserDto result = service.patchUser(upd, userId);
        log.info("patchUser finished: {}", result);
        return result;
    }
}
