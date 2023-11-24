package com.MyProject.feign;

import com.MyProject.dto.CategoryDto;
import com.MyProject.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("CATEGORY-SERVICE")
@RequestMapping("/categories")
public interface CategoryService {
    @GetMapping("/{id}")
    CategoryDto getCategory(@PathVariable("id") Long categoryId);
}
