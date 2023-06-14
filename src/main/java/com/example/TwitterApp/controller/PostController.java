package com.example.TwitterApp.controller;

import com.example.TwitterApp.model.dto.PostDTO;
import com.example.TwitterApp.model.entity.Post;
//import com.example.TwitterApp.model.entity.Reply;
import com.example.TwitterApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
//import java.util.Map;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getPosts(@PathVariable String userName, @RequestBody(required = false) LocalDateTime time) {
        return postService.getPosts(userName, time);
    }

    @PostMapping(value = "/add/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPost(@RequestBody String text, @PathVariable String userName) {
        postService.addPost(text, userName);
    }

    @GetMapping(value = "/feed/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getFeed(@PathVariable String userName) {
        return postService.getFeed(userName);
    }

//    @PatchMapping(value = "/like/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void likePost(@PathVariable String userName, @RequestBody Map<String, Integer> postId) {
//        postService.likePost(userName, postId);
//    }

    @PostMapping(value = "/like/{userNameWhoLiked}/{postId}")
    public void likePost(@PathVariable String userNameWhoLiked, @PathVariable String postId) {
        postService.likePost(userNameWhoLiked, postId);
    }

//    @PostMapping(value = "/addReply/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void addReply(@PathVariable int postId, @RequestBody Reply reply) {
//        postService.addReply(postId, reply);
//    }

    @PostMapping(value = "/reply/{idPostReply}/{userName}")
    public void reply(@PathVariable String idPostReply, @PathVariable String userName, @RequestBody String text) {
        postService.reply(idPostReply, userName, text);
    }
}

