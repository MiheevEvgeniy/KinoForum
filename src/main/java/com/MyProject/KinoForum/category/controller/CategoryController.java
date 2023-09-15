package com.MyProject.KinoForum.category.controller;


import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.dto.NewCategory;
import com.MyProject.KinoForum.category.service.CategoryService;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addCategory(@Valid @RequestBody NewCategory newCategory){
        log.info("addCategory started with body: {}",newCategory);
        CategoryDto result = service.addCategory(newCategory);
        log.info("addCategory finished: {}", result);
        return result;
    }
    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable("id") Long categoryId){
        log.info("getCategory started with id: {}",categoryId);
        CategoryDto result = service.getCategory(categoryId);
        log.info("getCategory finished: {}", result);
        return result;
    }
    @GetMapping
    public List<CategoryDto> getAllCategories(@RequestParam(value = "size",required = false, defaultValue = "5") @Min(5) int size,
                                             @RequestParam(value = "from",required = false, defaultValue = "1") @Min(1) int from){
        log.info("getAllCategories started with params: size {}, from {}",size, from-1);
        List<CategoryDto> result = service.getAllCategories(size,from-1);
        log.info("getAllCategories finished: {}", result);
        return result;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") Long categoryId){
        log.info("deleteCategory started with id: {}",categoryId);
        service.deleteCategory(categoryId);
        log.info("deleteCategory finished");
    }
    @PatchMapping("/{id}")
    public CategoryDto patchCategory(@RequestBody Map<String, Object> fields, @PathVariable("id") Long categoryId){
        log.info("patchCategory started with fields: {} and id: {}",fields, categoryId);
        CategoryDto result = service.patchCategory(fields, categoryId);
        log.info("patchCategory finished: {}", result);
        return result;
    }
}
