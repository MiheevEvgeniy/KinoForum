package com.MyProject.model;

import com.MyProject.model.Category;
import com.MyProject.enums.DiscussionStatus;
import com.MyProject.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;


public class DiscussionSpecifications {
    public static Specification<Discussion> withCategories(List<Category> categories) {
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
