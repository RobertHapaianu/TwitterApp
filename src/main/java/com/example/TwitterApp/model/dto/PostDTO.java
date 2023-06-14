package com.example.TwitterApp.model.dto;
import com.example.TwitterApp.model.entity.Like;
import com.example.TwitterApp.model.entity.Reply;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class PostDTO {
    private String text;
    private List<Reply> replies;
    private List<LikeDTO> likes;
}
