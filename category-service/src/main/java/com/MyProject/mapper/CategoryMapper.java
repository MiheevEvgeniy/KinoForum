package com.MyProject.mapper;

import com.MyProject.dto.CategoryDto;
import com.MyProject.dto.NewCategory;
import com.MyProject.dto.UpdateCategoryDto;
import com.MyProject.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    @Mapping(ignore = true, target = "id")
    Category toEntityFromNewCategory(NewCategory newCategory);
    @Mapping(ignore = true, target = "id")
    Category toEntityFromCategoryDto(CategoryDto categoryDto);
    @Mapping(ignore = true, target = "id")
    Category updateCategory(@MappingTarget Category category, UpdateCategoryDto categoryDto);
    List<CategoryDto> toDtoList(List<Category> categories);
}
