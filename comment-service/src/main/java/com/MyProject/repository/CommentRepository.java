package com.MyProject.comments.repository;

import com.MyProject.comments.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByDiscussionId(Long discussionId, Pageable pageable);
    Optional<Comment> findByIdAndDiscussionId(Long id, Long discussionId);
    void deleteByIdAndDiscussionId(Long id, Long discussionId);
}
