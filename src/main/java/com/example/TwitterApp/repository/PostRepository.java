package com.example.TwitterApp.repository;

import com.example.TwitterApp.model.entity.Post;
import com.example.TwitterApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findPostsByUser(User user);
//    Post findPostById(String postId);
}
