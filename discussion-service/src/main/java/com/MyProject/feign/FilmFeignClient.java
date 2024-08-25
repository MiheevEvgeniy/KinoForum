package com.MyProject.feign;

import com.MyProject.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "film-service")
public interface FilmFeignClient {
    @GetMapping("/categories/{id}")
    CategoryDto getCategory(@PathVariable("id") Long categoryId);
}
