package com.MyProject.dto;

import com.MyProject.model.Category;
import com.MyProject.enums.DiscussionStatus;
import com.MyProject.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class DiscussionDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private User author;
    private List<Category> categories;
    private DiscussionStatus status;
    private long commentsAmount;
}
