package com.MyProject.KinoForum.category.mapper;

import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.dto.NewCategory;
import com.MyProject.KinoForum.category.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    @Mapping(ignore = true, target = "id")
    Category toEntityFromNewCategory(NewCategory newCategory);
    @Mapping(ignore = true, target = "id")
    Category toEntityFromCategoryDto(CategoryDto categoryDto);
}
