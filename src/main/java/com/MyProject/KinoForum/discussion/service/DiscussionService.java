package com.MyProject.KinoForum.discussion.service;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.category.service.CategoryService;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.director.service.DirectorService;
import com.MyProject.KinoForum.discussion.dto.DiscussionDto;
import com.MyProject.KinoForum.discussion.dto.NewDiscussion;
import com.MyProject.KinoForum.discussion.dto.UpdateDiscussionDto;
import com.MyProject.KinoForum.discussion.mapper.DiscussionMapper;
import com.MyProject.KinoForum.discussion.model.Discussion;
import com.MyProject.KinoForum.discussion.repository.DiscussionRepository;
import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.user.model.User;
import com.MyProject.KinoForum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.MyProject.KinoForum.discussion.model.DiscussionSpecifications.*;
import static com.MyProject.KinoForum.film.model.FilmSpecifications.*;
import static com.MyProject.KinoForum.film.model.FilmSpecifications.withCategory;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscussionService {
    private final DiscussionRepository repository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final DiscussionMapper mapper;
    public DiscussionDto openDiscussion(NewDiscussion newDiscussion) {
        List<Category> categories = newDiscussion.getCategories()
                .stream()
                .map(categoryService::getCategoryEntity)
                .toList();
        Discussion discussion = mapper.toEntity(newDiscussion,
                userService.getUserEntity(newDiscussion.getAuthor()),
                categories);
        discussion.setStatus(DiscussionStatus.OPEN);

        return mapper.toDto(repository.save(discussion));
    }

    public DiscussionDto getDiscussion(long discId) {
        Optional<Discussion> discussion = repository.findById(discId);
        if(discussion.isEmpty()) throw new NotFoundException("Discussion not found");
        return mapper.toDto(discussion.get());
    }

    public List<DiscussionDto> search(int size,
                                      int from,
                                      List<Long> categoriesIds,
                                      Long authorId,
                                      LocalDateTime openedAt,
                                      DiscussionStatus status) {
        User author = null;
        List<Category> categories = null;

        if (authorId != null){
            author = userService.getUserEntity(authorId);
        }
        if(categoriesIds != null && !categoriesIds.isEmpty()){
            categories = categoriesIds
                    .stream()
                    .map(categoryService::getCategoryEntity)
                    .toList();
        }
        return mapper
                .toDtoList(
                        repository
                                .findAll(where(withCategories(categories))
                                                .and(withAuthor(author))
                                                .and(withOpenedAt(openedAt))
                                                .and(withStatus(status)),
                                        PageRequest.of(from,size))
                                .toList());
    }
    public DiscussionDto patchDiscussion(UpdateDiscussionDto upd, long id) {
        List<Category> categories = null;
        
        if (upd.getCategories() != null && !upd.getCategories().isEmpty()){
            categories = upd.getCategories()
                    .stream()
                    .map(categoryService::getCategoryEntity)
                    .toList();
        }

        return mapper.toDto(
                repository.save(
                        mapper.updateDiscussion(
                                repository
                                        .findById(id)
                                        .orElseThrow(()->new NotFoundException("Discussion for update not found")),
                                upd,categories)));
    }

    public void deleteDiscussion(Long id) {
        repository.deleteById(id);
    }
}
