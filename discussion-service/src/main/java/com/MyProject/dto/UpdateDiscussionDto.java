package com.MyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateDiscussionDto {
    private String title;
    private String description;
    private List<Long> categories;
    private long commentsAmount;
}
