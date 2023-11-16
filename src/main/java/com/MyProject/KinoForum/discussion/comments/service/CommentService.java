package com.MyProject.KinoForum.discussion.comments.service;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.category.service.CategoryService;
import com.MyProject.KinoForum.discussion.comments.dto.CommentDto;
import com.MyProject.KinoForum.discussion.comments.dto.NewComment;
import com.MyProject.KinoForum.discussion.comments.dto.UpdateCommentDto;
import com.MyProject.KinoForum.discussion.comments.mapper.CommentMapper;
import com.MyProject.KinoForum.discussion.comments.model.Comment;
import com.MyProject.KinoForum.discussion.comments.repository.CommentRepository;
import com.MyProject.KinoForum.discussion.model.Discussion;
import com.MyProject.KinoForum.discussion.service.DiscussionService;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import com.MyProject.KinoForum.user.model.User;
import com.MyProject.KinoForum.user.service.UserService;
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
    private final DiscussionService discussionService;
    @Transactional
    public CommentDto addComment(long discId, NewComment newComment) {
        User author = userService.getUserEntity(newComment.getAuthor());
        Discussion discussion = discussionService.getDiscussionEntity(discId);

        CommentDto commentDto = mapper.toDto(repository
                                .save(mapper.toEntity(
                                        newComment,
                                        author,
                                        discussion)));
        discussionService.incrementCommentsAmount(discId);
        return commentDto;
    }

    public CommentDto getComment(long discId, long comId) {
        return mapper.toDto(getCommentEntity(comId, discId));
    }
    public void likeOrDislikeComment(long discId, long comId, boolean isLiked) {
        Comment comment = getCommentEntity(comId,discId);
        if (isLiked){
            comment.setLikes(comment.getLikes()+1);
            repository.save(comment);
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
