package com.MyProject.model;

import com.MyProject.enums.FilmRating;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class FilmSpecifications {
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
    public static Specification<Film> withDirector(List<Long> directorIds) {
        if (directorIds != null) {
            return (root, query, cb) -> root.join("directors").in(directorIds);
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
    public static Specification<Film> withCategory(List<Long> categoryIds) {
        if (categoryIds != null) {
            return (root, query, cb) -> root.join("categories").in(categoryIds);
        }
        return null;
    }
}
