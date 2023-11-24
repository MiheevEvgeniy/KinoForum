package com.MyProject.model;

import com.MyProject.enums.FilmRating;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Films", schema = "public")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column(name = "release_year")
    private Integer releaseYear;
    @Column(nullable = false)
    private LocalTime duration;
    @ElementCollection
    private List<Long> directorIds;
    @Enumerated(EnumType.STRING)
    private FilmRating rating;
    @Column(nullable = false)
    private String country;
    @ElementCollection
    private List<Long> categoryIds;

}
