package com.example.TwitterApp.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reply_id", nullable = false)
    private String id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToMany(mappedBy = "parentReply", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reply> childrenReplies;

    @ManyToOne
    Reply parentReply;

    @Column(name = "public", nullable = false)
    private boolean isPublic;

    public Reply() {}

    public Reply(User user, Post post, String text, boolean isPublic) {
        this.text = text;
        this.user = user;
        this.post = post;
        this.isPublic = isPublic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Reply> getChildrenReplies() {
        return childrenReplies;
    }

    public void setChildrenReplies(Reply childrenReply) {
        this.childrenReplies.add(childrenReply);
    }

    public Reply getParentReply() {
        return parentReply;
    }

    public void setParentReply(Reply parentReply) {
        this.parentReply = parentReply;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getId() {
        return id;
    }
}
