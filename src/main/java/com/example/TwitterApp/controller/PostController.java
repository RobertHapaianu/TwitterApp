package com.example.TwitterApp.controller;

import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.Reply;
import com.example.TwitterApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getAllPosts(@PathVariable String userName) {
        return postService.getAllPosts(userName);
    }

    @PostMapping(value = "/add/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPost(@PathVariable String userName, @RequestBody Post post) {
        postService.addPost(userName, post);
    }

    @PatchMapping(value = "/like/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void likePost(@PathVariable String userName, @RequestBody Map<String, Integer> postId) {
        postService.likePost(userName, postId);
    }

    @PostMapping(value = "/addReply/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReply(@PathVariable int postId, @RequestBody Reply reply) {
        postService.addReply(postId, reply);
    }
}
