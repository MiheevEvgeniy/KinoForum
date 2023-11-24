package com.MyProject.dto;

import com.MyProject.enums.FilmRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateFilmDto {
    private String title;
    private String description;
    private Integer releaseYear;
    private LocalTime duration;
    private Long directorId;
    private FilmRating rating;
    private String country;
    private Long categoryId;
}
