package com.MyProject.KinoForum.discussion.dto;

import com.MyProject.KinoForum.category.dto.NewCategory;
import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewDiscussion {
    @NotBlank
    @Size(min = 1, max = 1000)
    private String title;
    @Size(max = 3000)
    private String description;
    @NotNull
    private Long author;
    private List<Long> categories;
}
