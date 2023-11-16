package com.MyProject.KinoForum.discussion.comments.repository;

import com.MyProject.KinoForum.discussion.comments.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByDiscussionId(Long discussionId, Pageable pageable);
    Optional<Comment> findByIdAndDiscussionId(Long id, Long discussionId);
    void deleteByIdAndDiscussionId(Long id, Long discussionId);
}
