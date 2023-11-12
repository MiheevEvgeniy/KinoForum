package com.MyProject.KinoForum.discussion.controller;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.discussion.dto.DiscussionDto;
import com.MyProject.KinoForum.discussion.dto.NewDiscussion;
import com.MyProject.KinoForum.discussion.dto.UpdateDiscussionDto;
import com.MyProject.KinoForum.discussion.service.DiscussionService;
import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.user.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discussions")
@Slf4j
@Validated
public class DiscussionController {
    private final DiscussionService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiscussionDto openDiscussion(@RequestBody @Valid NewDiscussion newDiscussion){
        log.info("openDiscussion started with body: {}", newDiscussion);
        DiscussionDto result = service.openDiscussion(newDiscussion);
        log.info("openDiscussion finished: {}", result);
        return result;
    }
    @GetMapping("/{id}")
    public DiscussionDto getDiscussion(@PathVariable("id") long id){
        log.info("getDiscussion started with id: {}", id);
        DiscussionDto result = service.getDiscussion(id);
        log.info("getDiscussion finished: {}", result);
        return result;
    }
    @GetMapping
    public List<DiscussionDto> search(@RequestParam(value = "size",required = false, defaultValue = "5") @Min(5) int size,
                                      @RequestParam(value = "from",required = false, defaultValue = "0") @Min(0) int from,
                                      @RequestParam(value = "categoriesIds",required = false) List<Long> categoriesIds,
                                      @RequestParam(value = "authorId",required = false) Long authorId,
                                      @RequestParam(value = "openedAt",required = false) LocalDateTime openedAt,
                                      @RequestParam(value = "status",required = false) DiscussionStatus status){
        log.info("search started with size: {}, " +
                "from: {}, " +
                "categoriesIds: {}, " +
                "authorId: {}, " +
                "openedAt: {}, " +
                "status: {}", size, from, categoriesIds, authorId, openedAt, status);
        List<DiscussionDto> result = service.search(size,from,categoriesIds,authorId,openedAt,status);
        log.info("search finished: {}", result);
        return result;
    }
    @PatchMapping("/{id}")
    public DiscussionDto patchDiscussion(@RequestBody UpdateDiscussionDto upd, @PathVariable("id") long discId){
        log.info("patchDiscussion started with body: {} and  id: {}",upd, discId);
        DiscussionDto result = service.patchDiscussion(upd,discId);
        log.info("patchDiscussion finished: {}", result);
        return result;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscussion(@PathVariable("id") Long discId){
        log.info("deleteDiscussion started with id: {}", discId);
        service.deleteDiscussion(discId);
        log.info("deleteDiscussion finished");
    }
}
