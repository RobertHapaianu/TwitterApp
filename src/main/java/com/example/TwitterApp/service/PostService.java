package com.example.TwitterApp.service;

import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.Reply;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> getAllPosts(String userName);
    void addPost(String userName, Post post);
    void likePost(String userName, Map<String, Integer> postId);
    void addReply(int postId, Reply reply);
}
