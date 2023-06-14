package com.example.TwitterApp.service;

import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.Reply;
import com.example.TwitterApp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(String userName) {
        return postRepository.getAllPosts(userName);
    }

    public void addPost(String userName, Post post) {
        postRepository.addPost(userName, post);
    }

    public void likePost(String userName, Map<String, Integer> postId) {
        postRepository.likePost(userName, postId);
    }

    public void addReply(int postId, Reply reply) {
        postRepository.addReply(postId, reply);
    }
}
