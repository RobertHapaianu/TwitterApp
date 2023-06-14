package com.example.TwitterApp.model.dto;

import java.util.UUID;

public class ReplyDTO {
    private String text;

    public ReplyDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
