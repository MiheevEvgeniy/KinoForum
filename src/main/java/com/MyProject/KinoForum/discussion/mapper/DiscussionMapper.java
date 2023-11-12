package com.MyProject.KinoForum.discussion.mapper;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.discussion.dto.DiscussionDto;
import com.MyProject.KinoForum.discussion.dto.NewDiscussion;
import com.MyProject.KinoForum.discussion.dto.UpdateDiscussionDto;
import com.MyProject.KinoForum.discussion.model.Discussion;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiscussionMapper {
    DiscussionDto toDto(Discussion discussion);
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "closedAt")
    @Mapping(ignore = true, target = "openedAt")
    @Mapping(ignore = true, target = "status")
    @Mapping(ignore = true, target = "commentsAmount")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "categories", source = "categories")
    Discussion toEntity(NewDiscussion newDiscussion, User author, List<Category> categories);
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "closedAt")
    @Mapping(ignore = true, target = "openedAt")
    @Mapping(ignore = true, target = "author")
    @Mapping(ignore = true, target = "status")
    @Mapping(ignore = true, target = "commentsAmount")
    @Mapping(target = "categories", source = "categories")
    Discussion updateDiscussion(@MappingTarget Discussion discussion, UpdateDiscussionDto dto, List<Category> categories);
    List<DiscussionDto> toDtoList(List<Discussion> discussions);
}
