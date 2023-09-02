package com.MyProject.KinoForum.user.controller;


import com.MyProject.KinoForum.user.dto.NewUser;
import com.MyProject.KinoForum.user.dto.UserDto;
import com.MyProject.KinoForum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AdminController {
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
                                     @RequestParam(value = "from",required = false, defaultValue = "1") @Min(1) int from){
        log.info("getAllUsers started with params: size {}, from {}",size, from-1);
        List<UserDto> result = service.getAllUsers(size,from-1);
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
    public UserDto patchUser(@RequestBody Map<String, Object> fields, @PathVariable("id") Long userId){
        log.info("patchUser started with fields: {} and id: {}",fields, userId);
        UserDto result = service.patchUser(fields, userId);
        log.info("patchUser finished: {}", result);
        return result;
    }
}
