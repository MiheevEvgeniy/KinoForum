package com.MyProject.dto;

import com.MyProject.enums.FilmRating;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewFilm {
    @NotBlank
    @Size(min = 1, max = 1000)
    private String title;
    @Size(max = 3000)
    private String description;
    @Positive
    @Min(1895)
    private Integer releaseYear;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime duration;
    @NotNull
    private List<Long> directorIds;
    @NotNull
    private FilmRating rating;
    @NotBlank
    private String country;
    @NotNull
    private List<Long> categoryIds;
}
