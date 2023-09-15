package com.MyProject.KinoForum.category.service;

import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.dto.NewCategory;
import com.MyProject.KinoForum.category.mapper.CategoryMapper;
import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.category.repository.CategoryRepository;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDto addCategory(NewCategory newDirector) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewCategory(newDirector)));
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Optional<Category> user = repository.findById(categoryId);
        if(!user.isPresent()) throw new NotFoundException("Category not found");
        return mapper.toDto(user.get());
    }

    @Override
    public List<CategoryDto> getAllCategories(int size, int from) {
        PagedListHolder<CategoryDto> page = new PagedListHolder<>(repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        page.setPageSize(size);
        page.setPage(from);
        return page.getPageList();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        repository.delete(mapper.toEntityFromCategoryDto(getCategory(categoryId)));
    }

    @Override
    public CategoryDto patchCategory(Map<String, Object> fields, Long categoryId) {
        Optional<Category> user = repository.findById(categoryId);
        if(!user.isPresent()) throw new NotFoundException("Category not found");
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Category.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, user.get(), v);
        });
        return mapper.toDto(repository.save(user.get()));
    }
}
