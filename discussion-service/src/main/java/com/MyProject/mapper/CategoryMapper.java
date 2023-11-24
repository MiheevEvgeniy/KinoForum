package com.MyProject.mapper;

import com.MyProject.dto.CategoryDto;
import com.MyProject.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntityFromCategoryDto(CategoryDto categoryDto);
    List<CategoryDto> toDtoList(List<Category> categories);
}
