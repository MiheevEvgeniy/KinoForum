package com.MyProject.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentDto {
    private Long id;
    private Long author;
    private Long discussion;
    private String message;
    private Long likes;
    private Long dislikes;
}
