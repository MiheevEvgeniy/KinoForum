package com.MyProject.service;

import com.MyProject.feign.CategoryService;
import com.MyProject.feign.UserService;
import com.MyProject.mapper.CategoryMapper;
import com.MyProject.mapper.UserMapper;
import com.MyProject.model.Category;
import com.MyProject.dto.DiscussionDto;
import com.MyProject.dto.NewDiscussion;
import com.MyProject.dto.UpdateDiscussionDto;
import com.MyProject.mapper.DiscussionMapper;
import com.MyProject.model.Discussion;
import com.MyProject.repository.DiscussionRepository;
import com.MyProject.enums.DiscussionStatus;
import com.MyProject.exceptions.NotFoundException;
import com.MyProject.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.MyProject.model.DiscussionSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscussionService {
    private final DiscussionRepository repository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final DiscussionMapper mapper;
    public DiscussionDto openDiscussion(NewDiscussion newDiscussion) {
        List<Category> categories = newDiscussion.getCategories()
                .stream()
                .map(categoryService::getCategory)
                .map(categoryMapper::toEntityFromCategoryDto)
                .toList();

        Discussion discussion = mapper.toEntity(newDiscussion,
                userMapper.toEntityFromUserDto(userService.getUser(newDiscussion.getAuthor())),
                categories);
        discussion.setStatus(DiscussionStatus.OPEN);

        return mapper.toDto(repository.save(discussion));
    }
    public Long incrementCommentsAmount(long discId) {
        Discussion discussion = getDiscussionEntity(discId);
        discussion.setCommentsAmount(discussion.getCommentsAmount() + 1);
        return repository.save(discussion).getCommentsAmount();
    }

    public DiscussionDto getDiscussion(long discId) {
        return mapper.toDto(getDiscussionEntity(discId));
    }

    public Discussion getDiscussionEntity(long discId){
        Optional<Discussion> discussion = repository.findById(discId);
        if(discussion.isEmpty()) throw new NotFoundException("Discussion not found");
        return discussion.get();
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
            author = userMapper.toEntityFromUserDto(userService.getUser(authorId));
        }
        if(categoriesIds != null && !categoriesIds.isEmpty()){
            categories = categoriesIds
                    .stream()
                    .map(categoryService::getCategory)
                    .map(categoryMapper::toEntityFromCategoryDto)
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
                    .map(categoryService::getCategory)
                    .map(categoryMapper::toEntityFromCategoryDto)
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
