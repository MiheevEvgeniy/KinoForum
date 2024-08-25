package com.MyProject.controller;

import com.MyProject.dto.CommentDto;
import com.MyProject.dto.NewComment;
import com.MyProject.dto.UpdateCommentDto;
import com.MyProject.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discussions/{discussionId}/comments")
@Slf4j
@Validated
public class CommentController {
    private final CommentService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable("discussionId") long discId, @RequestBody @Valid NewComment newComment){
        log.info("addComment started with body: {}", newComment);
        CommentDto result = service.addComment(discId, newComment);
        log.info("addComment finished: {}", result);
        return result;
    }
    @GetMapping("/{comId}")
    public CommentDto getComment(@PathVariable("discussionId") long discId, @PathVariable("comId") long comId){
        log.info("getComment started with comId: {} and discId: {}", comId, discId);
        CommentDto result = service.getComment(discId, comId);
        log.info("getComment finished: {}", result);
        return result;
    }
    @PostMapping("/{comId}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void likeComment(@PathVariable("discussionId") long discId, @PathVariable("comId") long comId){
        log.info("likeComment started with comId: {} and discId: {}", comId, discId);
        service.likeOrDislikeComment(discId, comId, true);
        log.info("likeComment finished");
    }
    @PostMapping("/{comId}/dislike")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislikeComment(@PathVariable("discussionId") long discId, @PathVariable("comId") long comId){
        log.info("dislikeComment started with comId: {} and discId: {}", comId, discId);
        service.likeOrDislikeComment(discId, comId, false);
        log.info("dislikeComment finished");
    }
    @GetMapping
    public List<CommentDto> getAllComments(@RequestParam(value = "size",required = false, defaultValue = "5") @Min(5) int size,
                                           @RequestParam(value = "from",required = false, defaultValue = "0") @Min(0) int from,
                                           @PathVariable("discussionId") long discId){
        log.info("getAllComments started with size: {}, " +
                "from: {}, " +
                "discId: {}", size, from, discId);
        List<CommentDto> result = service.getAllComments(size,from,discId);
        log.info("getAllComments finished: {}", result);
        return result;
    }
    @PatchMapping("/{comId}")
    public CommentDto patchComment(@PathVariable("discussionId") long discId,
                                   @PathVariable("comId") long comId,
                                   @RequestBody UpdateCommentDto upd){
        log.info("patchComment started with body: {},  comId: {} and discId: {}",upd, comId, discId);
        CommentDto result = service.patchComment(discId, comId, upd);
        log.info("patchComment finished: {}", result);
        return result;
    }
    @DeleteMapping("/{comId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("discussionId") long discId,
                              @PathVariable("comId") long comId){
        log.info("deleteComment started with comId: {} and discId: {}", comId, discId);
        service.deleteComment(comId, discId);
        log.info("deleteComment finished");
    }
}
