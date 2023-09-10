package com.MyProject.KinoForum.film.model;

import com.MyProject.KinoForum.enums.FilmRating;
import org.springframework.data.jpa.domain.Specification;

public class FIlmSpecifications {
    public static Specification<Film> withTitle(String title) {
        if (title != null) {
            return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%"+title.toLowerCase()+"%");
        }
        return null;
    }
    public static Specification<Film> withYear(Integer year) {
        if (year != null) {
            return (root, query, cb) -> cb.equal(root.get("releaseYear"), year);
        }
        return null;
    }
    public static Specification<Film> withDirector(String director) {
        if (director != null) {
            return (root, query, cb) -> cb.like(cb.lower(root.get("director")), "%"+director.toLowerCase()+"%");
        }
        return null;
    }
    public static Specification<Film> withRating(FilmRating rating) {
        if (rating != null) {
            return (root, query, cb) -> cb.equal(root.get("rating"), rating);
        }
        return null;
    }
    public static Specification<Film> withCountry(String country) {
        if (country != null) {
            return (root, query, cb) -> cb.like(cb.lower(root.get("country")), "%"+country.toLowerCase()+"%");
        }
        return null;
    }
    public static Specification<Film> withCategory(String category) {
        if (category != null) {
            return (root, query, cb) -> cb.like(cb.lower(root.get("category")), "%"+category.toLowerCase()+"%");
        }
        return null;
    }
}
