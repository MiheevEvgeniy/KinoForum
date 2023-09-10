package com.MyProject.KinoForum.film.model;

import com.MyProject.KinoForum.enums.FilmRating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Films", schema = "public")
@Getter
@Setter
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
    //TODO add director entity
    @Column
    private String director;
    @Enumerated(EnumType.STRING)
    private FilmRating rating;
    @Column(nullable = false)
    private String country;
    //TODO add category entity
    @Column(nullable = false)
    private String category;

}
