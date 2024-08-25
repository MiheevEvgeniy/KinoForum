package com.MyProject.controller;


import com.MyProject.dto.CategoryDto;
import com.MyProject.dto.NewCategory;
import com.MyProject.dto.UpdateCategoryDto;
import com.MyProject.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                             @RequestParam(value = "from",required = false, defaultValue = "0") @Min(0) int from){
        log.info("getAllCategories started with params: size {}, from {}",size, from);
        List<CategoryDto> result = service.getAllCategories(size,from);
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
    public CategoryDto patchCategory(@RequestBody UpdateCategoryDto upd, @PathVariable("id") Long categoryId){
        log.info("patchCategory started with body: {} and id: {}",upd, categoryId);
        CategoryDto result = service.patchCategory(upd, categoryId);
        log.info("patchCategory finished: {}", result);
        return result;
    }
}
