package com.example.TwitterApp.model.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private String userName;
    public LikeDTO(String userName) {
        this.userName = userName;
    }

    public String getUsername() {
        return userName;
    }
}
