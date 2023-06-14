package com.example.TwitterApp.repository;

import com.example.TwitterApp.advice.exception.UserNotFoundException;
import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.Reply;
import com.example.TwitterApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository
public class PostRepository {

    private Map<String, List<Post>> userNamePost = new HashMap<>();
    private Map<Integer, Post> idPostMap = new HashMap<>();

    private int id = 0;

    public List<Post> getAllPosts(String userName) {
        return userNamePost.get(userName);
    }

    public void addPost(String userName, Post post) {
        List<Post> postList = new ArrayList<>();
        if (userNamePost.containsKey(userName))
            userNamePost.get(userName).stream().forEach(elem -> postList.add(elem));

        post.setId(id);
        idPostMap.put(id, post);
        id++;
        postList.add(post);

        userNamePost.put(userName, postList);
    }

    public List<Post> getFeed(User user) {
        List<Post> postList = new ArrayList<>();
        user.getFollowing().stream().forEach(person -> postList.addAll(getAllPosts(person)));
        return postList;
    }


    public void likePost(String userName, Map<String, Integer> postId) {
        int idPost = postId.get("postId");
        idPostMap.get(idPost).getUserNameWhoLikedList().add(userName);
    }

    public void addReply(int postId, Reply reply) {
        idPostMap.get(postId).addToReplyList(reply);
    }
}
