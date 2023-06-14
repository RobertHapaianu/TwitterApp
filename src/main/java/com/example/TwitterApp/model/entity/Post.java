package com.example.TwitterApp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id", nullable = false)
    private String id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", nullable = false)
    private LocalDateTime timeStamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Post() {}

    public Post(String text, User user) {
        this.text = text;
        this.user = user;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> replies;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Like> getLikes() {
        return likes;
    }

//    public void addUserWhoLiked (User userWhoLiked) {
//        if(!usersWhoLikedList.contains(userWhoLiked))
//            this.usersWhoLikedList.add(userWhoLiked);
//    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReply(Reply reply) {
        this.replies.add(reply);
    }
//    public void addToReplyList(Reply reply) {
//        replies.add(reply);
//    }
}
