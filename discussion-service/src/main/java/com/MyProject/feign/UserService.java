package com.MyProject.feign;

import com.MyProject.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("USER-SERVICE")
@RequestMapping("/users")
public interface UserService {
    @GetMapping("/{id}")
    UserDto getUser(@PathVariable("id") Long userId);
}
