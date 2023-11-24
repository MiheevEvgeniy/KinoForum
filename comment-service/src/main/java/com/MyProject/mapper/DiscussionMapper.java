package com.MyProject.mapper;

import com.MyProject.dto.DiscussionDto;

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
    Discussion toEntity(DiscussionDto dto);
    List<DiscussionDto> toDtoList(List<Discussion> discussions);
}
