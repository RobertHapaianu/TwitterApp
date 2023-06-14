package com.example.TwitterApp.repository;

import com.example.TwitterApp.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, String> {
}
