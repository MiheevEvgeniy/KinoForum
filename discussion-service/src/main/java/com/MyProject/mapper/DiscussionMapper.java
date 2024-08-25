package com.MyProject.mapper;

import com.MyProject.model.Category;
import com.MyProject.dto.DiscussionDto;
import com.MyProject.dto.NewDiscussion;
import com.MyProject.dto.UpdateDiscussionDto;
import com.MyProject.model.Discussion;
import com.MyProject.model.User;
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
    Discussion toEntity(DiscussionDto dto);
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "closedAt")
    @Mapping(ignore = true, target = "openedAt")
    @Mapping(ignore = true, target = "author")
    @Mapping(ignore = true, target = "status")
    @Mapping(target = "categories", source = "categories")
    Discussion updateDiscussion(@MappingTarget Discussion discussion, UpdateDiscussionDto dto, List<Category> categories);
    List<DiscussionDto> toDtoList(List<Discussion> discussions);
}
