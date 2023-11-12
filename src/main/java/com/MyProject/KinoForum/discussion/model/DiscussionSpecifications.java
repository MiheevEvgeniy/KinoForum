package com.MyProject.KinoForum.discussion.model;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.user.model.User;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;


public class DiscussionSpecifications {
    public static Specification<Discussion> withCategories(List<Category> categories) {
//        return new Specification<Discussion>() {
//            public Predicate toPredicate(Root<Discussion> root, CriteriaQuery<?> query, CriteriaBuilder builder){
//                final Path<Category> categoriesGroup = root.<Category> get("categories");
//                return categoriesGroup.in(categories);
//            }
//        };
//        CriteriaQuery<z> q = cb.createQuery(Category.class);
//
//
//        Root<Employee> root = q.from(Employee.class);
//        q.select(root);
//
//        List<String> parentList = Arrays.asList(new String[]{"John", "Raj"});
//
//        Expression<String> parentExpression = root.get(Employee_.Parent);
//        Predicate parentPredicate = parentExpression.in(parentList);
//        q.where(parentPredicate);
//        q.orderBy(cb.asc(root.get(Employee_.Parent));
//
//        q.getResultList();
        if (categories != null) {
            return (root, query, cb) -> root.join("categories").in(categories);
        }
        return null;
    }
    public static Specification<Discussion> withAuthor(User author) {
        if (author != null) {
            return (root, query, cb) -> cb.equal(root.get("author"), author);
        }
        return null;
    }
    public static Specification<Discussion> withOpenedAt(LocalDateTime openedAt) {
        if (openedAt != null) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(query.from(LocalDateTime.class).get("openedAt"), openedAt);
        }
        return null;
    }
    public static Specification<Discussion> withStatus(DiscussionStatus status) {
        if (status != null) {
            return (root, query, cb) -> cb.equal(root.get("status"), status);
        }
        return null;
    }
}
