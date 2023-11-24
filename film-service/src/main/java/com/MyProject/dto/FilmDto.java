package com.MyProject.dto;

import com.MyProject.enums.FilmRating;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Builder
@Data
public class FilmDto {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private LocalTime duration;
    private List<Long> directorIds;
    private FilmRating rating;
    private String country;
    private List<Long> categoryIds;
}
