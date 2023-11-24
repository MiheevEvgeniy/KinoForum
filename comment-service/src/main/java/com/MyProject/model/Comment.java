package com.MyProject.comments.model;

import com.MyProject.model.Discussion;
import com.MyProject.model.User;
import jakarta.persistence.*;
import lombok.*;

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
