package com.MyProject.KinoForum.category.mapper;

import com.MyProject.KinoForum.category.dto.CategoryDto;
import com.MyProject.KinoForum.category.dto.NewCategory;
import com.MyProject.KinoForum.category.dto.UpdateCategoryDto;
import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.director.dto.UpdateDirectorDto;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.user.dto.UserDto;
import com.MyProject.KinoForum.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

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
