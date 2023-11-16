package com.MyProject.KinoForum.discussion.comments.dto;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.user.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class CommentDto {
    private Long author;
    private Long discussion;
    private String message;
    private Long likes;
    private Long dislikes;
}
