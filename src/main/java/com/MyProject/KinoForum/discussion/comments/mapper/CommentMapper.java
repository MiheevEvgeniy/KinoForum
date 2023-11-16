package com.MyProject.KinoForum.discussion.comments.mapper;

import com.MyProject.KinoForum.discussion.comments.dto.CommentDto;
import com.MyProject.KinoForum.discussion.comments.dto.NewComment;
import com.MyProject.KinoForum.discussion.comments.dto.UpdateCommentDto;
import com.MyProject.KinoForum.discussion.comments.model.Comment;
import com.MyProject.KinoForum.discussion.model.Discussion;
import com.MyProject.KinoForum.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper {
    @Mapping(target = "discussion", source = "comment.discussion.id")
    @Mapping(target = "author", source = "comment.author.id")
    CommentDto toDto(Comment comment);
    @Mapping(ignore = true, target = "id")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "discussion", source = "discussion")
    @Mapping(ignore = true, target = "likes")
    @Mapping(ignore = true, target = "dislikes")
    Comment toEntity(NewComment newComment, User author, Discussion discussion);
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "author")
    @Mapping(ignore = true, target = "discussion")
    @Mapping(ignore = true, target = "likes")
    @Mapping(ignore = true, target = "dislikes")
    Comment updateComment(@MappingTarget Comment comment, UpdateCommentDto dto);
    @Mapping(target = "discussion", source = "comments.discussion.id")
    List<CommentDto> toDtoList(List<Comment> comments);
}
