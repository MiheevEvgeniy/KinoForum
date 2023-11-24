package com.MyProject.model;

import com.MyProject.enums.DiscussionStatus;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Discussions", schema = "public")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @CreationTimestamp
    @Column(name = "opened_at", nullable = false)
    private LocalDateTime openedAt;
    @Column(name = "closed_at")
    private LocalDateTime closedAt;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
    @ElementCollection
    private List<Category> categories;
    @Enumerated(EnumType.STRING)
    private DiscussionStatus status;
    @Column(name = "comments_amount")
    private long commentsAmount;
}
