package com.MyProject.KinoForum.film.dto;

import com.MyProject.KinoForum.enums.FilmRating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;

@Data
@Builder
public class NewFilm {
    @NotBlank
    @Size(min = 1, max = 1000)
    private String title;
    @Size(max = 3000)
    private String description;
    @Positive
    private Integer releaseYear;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime duration;
    private Long directorId;
    @NotNull
    private FilmRating rating;
    @NotBlank
    private String country;
    @NotBlank
    private Long categoryId;
}
