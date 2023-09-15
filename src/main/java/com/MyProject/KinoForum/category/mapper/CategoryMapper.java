package com.MyProject.KinoForum.category.mapper;

import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.dto.NewCategory;
import com.MyProject.KinoForum.category.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto toDto(Category category);
    Category toEntityFromNewCategory(NewCategory newCategory);
    Category toEntityFromCategoryDto(CategoryDto categoryDto);
}
