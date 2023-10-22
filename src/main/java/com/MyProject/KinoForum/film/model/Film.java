package com.MyProject.KinoForum.film.model;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.enums.FilmRating;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;
    @Enumerated(EnumType.STRING)
    private FilmRating rating;
    @Column(nullable = false)
    private String country;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
