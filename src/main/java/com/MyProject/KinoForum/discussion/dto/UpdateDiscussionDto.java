package com.MyProject.KinoForum.discussion.dto;

import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.enums.FilmRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateDiscussionDto {
    private String title;
    private String description;
    private List<Long> categories;
}
