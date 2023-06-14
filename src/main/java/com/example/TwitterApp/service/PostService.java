package com.example.TwitterApp.service;

import com.example.TwitterApp.model.dto.PostDTO;
import com.example.TwitterApp.model.entity.Post;
//import com.example.TwitterApp.model.entity.Reply;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    List<PostDTO> getPosts(String userName, LocalDateTime timeStamp);
    void addPost(String text, String userName);
    List<Post> getFeed(String userName);
    void likePost(String userName, String postId);
    void reply(String idPostReply, String userName, String text);
//    void addReply(int postId, Reply reply);
}
