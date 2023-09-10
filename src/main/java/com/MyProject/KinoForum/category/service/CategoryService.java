package com.MyProject.KinoForum.category.service;

import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.dto.NewCategory;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    CategoryDto addCategory(NewCategory newCategory);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories(int size, int from);

    void deleteCategory(Long categoryId);

    CategoryDto patchCategory(Map<String, Object> fields, Long CategoryId);
}
