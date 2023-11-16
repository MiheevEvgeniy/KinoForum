package com.MyProject.KinoForum.discussion.comments.model;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.discussion.model.Discussion;
import com.MyProject.KinoForum.enums.DiscussionStatus;
import com.MyProject.KinoForum.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Comments", schema = "public")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "discussion_id", nullable = false)
    private Discussion discussion;
    private String message;
    private long likes;
    private long dislikes;
}
