package com.MyProject.KinoForum.discussion.dto;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.user.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
