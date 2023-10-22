package com.MyProject.KinoForum.film.dto;

import com.MyProject.KinoForum.enums.FilmRating;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Builder
@Data
public class FilmDto {
    private String title;
    private String description;
    private Integer releaseYear;
    private LocalTime duration;
    private Long directorId;
    private FilmRating rating;
    private String country;
    private Long categoryId;
}
