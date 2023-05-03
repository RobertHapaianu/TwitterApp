package com.example.TwitterApp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private int id;

    private String message;

    private List<String> userNameWhoLikedList = new ArrayList<>();

    private List<Reply> replies = new ArrayList<>();

//    private LocalDateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getUserNameWhoLikedList() {
        return userNameWhoLikedList;
    }

    public void addUserNameWhoLiked(String userNameWhoLiked) {
        if(!userNameWhoLikedList.contains(userNameWhoLiked))
            this.userNameWhoLikedList.add(userNameWhoLiked);
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void addToReplyList(Reply reply) {
        replies.add(reply);
    }

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }

}
