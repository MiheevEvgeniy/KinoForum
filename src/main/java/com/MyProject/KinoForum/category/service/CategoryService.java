package com.MyProject.KinoForum.category.service;

import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.dto.NewCategory;
import com.MyProject.KinoForum.category.dto.UpdateCategoryDto;
import com.MyProject.KinoForum.category.mapper.CategoryMapper;
import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.category.repository.CategoryRepository;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryDto addCategory(NewCategory newDirector) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewCategory(newDirector)));
    }

    public CategoryDto getCategory(Long categoryId) {
        return mapper.toDto(getCategoryEntity(categoryId));
    }
    public Category getCategoryEntityByName(String name) {
        return repository.findByName(name).orElseThrow(()-> new NotFoundException("Category not found"));
    }
    public Category getCategoryEntity(Long categoryId) {
        Optional<Category> category = repository.findById(categoryId);
        if(!category.isPresent()) throw new NotFoundException("Category not found");
        return category.get();
    }

    public List<CategoryDto> getAllCategories(int size, int from) {
        return mapper
                .toDtoList(
                        repository
                                .findAll(PageRequest.of(from,size))
                                .toList());
    }

    public void deleteCategory(Long categoryId) {
        repository.deleteById(categoryId);
    }
    public CategoryDto patchCategory(UpdateCategoryDto upd, Long categoryId) {
        return mapper.toDto(
                repository.save(
                        mapper.updateCategory(
                                repository
                                        .findById(categoryId)
                                        .orElseThrow(()->new NotFoundException("Category for update not found")), upd)));
    }
}
