package com.example.TwitterApp.repository;

import com.example.TwitterApp.model.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, String> {
}
