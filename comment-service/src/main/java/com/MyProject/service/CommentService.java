package com.MyProject.comments.service;

import com.MyProject.comments.dto.CommentDto;
import com.MyProject.comments.dto.NewComment;
import com.MyProject.comments.dto.UpdateCommentDto;
import com.MyProject.comments.mapper.CommentMapper;
import com.MyProject.comments.model.Comment;
import com.MyProject.comments.repository.CommentRepository;
import com.MyProject.dto.UpdateDiscussionDto;
import com.MyProject.feign.DiscussionService;
import com.MyProject.feign.UserService;
import com.MyProject.mapper.DiscussionMapper;
import com.MyProject.mapper.UserMapper;
import com.MyProject.model.Discussion;
import com.MyProject.exceptions.NotFoundException;
import com.MyProject.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final DiscussionService discussionService;
    private final DiscussionMapper discussionMapper;
    @Transactional
    public CommentDto addComment(long discId, NewComment newComment) {
        User author = userMapper.toEntityFromUserDto(userService.getUser(newComment.getAuthor()));
        Discussion discussion = discussionMapper.toEntity(discussionService.getDiscussion(discId));

        CommentDto commentDto = mapper.toDto(repository
                                .save(mapper.toEntity(
                                        newComment,
                                        author,
                                        discussion)));
        UpdateDiscussionDto upd = UpdateDiscussionDto.builder()
                .commentsAmount(discussion.getCommentsAmount() + 1)
                .build();
        discussionService.patchDiscussion(upd, discId);
        return commentDto;
    }

    public CommentDto getComment(long discId, long comId) {
        return mapper.toDto(getCommentEntity(discId, comId));
    }
    public void likeOrDislikeComment(long discId, long comId, boolean isLiked) {
        Comment comment = getCommentEntity(discId,comId);
        if (isLiked){
            comment.setLikes(comment.getLikes()+1);
            repository.save(comment);
            return;
        }
        comment.setDislikes(comment.getDislikes()+1);
        repository.save(comment);
    }
    public Comment getCommentEntity(long discId, long comId){
        Optional<Comment> comment = repository.findByIdAndDiscussionId(comId, discId);
        if(comment.isEmpty()) throw new NotFoundException("Comment not found");
        return comment.get();
    }

    public List<CommentDto> getAllComments(int size,
                                   int from,
                                   long discId) {
        return mapper
                .toDtoList(
                        repository
                                .findAllByDiscussionId(discId,
                                        PageRequest.of(from,size)));
    }
    public CommentDto patchComment(long discId, long comId, UpdateCommentDto upd) {
        return mapper.toDto(
                repository.save(
                        mapper.updateComment(
                                repository
                                        .findByIdAndDiscussionId(comId, discId)
                                        .orElseThrow(()->new NotFoundException("Comment for update not found")),
                                upd)));
    }

    public void deleteComment(long discId, long comId) {
        repository.deleteByIdAndDiscussionId(comId, discId);
    }
}
