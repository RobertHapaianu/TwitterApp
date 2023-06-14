package com.example.TwitterApp.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "like_id", nullable = false)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userWhoLiked;

    public Like() {}

    public Like(Post post, User userWhoLiked) {
        this.post = post;
        this.userWhoLiked = userWhoLiked;
    }

    public User getUserWhoLiked() {
        return userWhoLiked;
    }
}
