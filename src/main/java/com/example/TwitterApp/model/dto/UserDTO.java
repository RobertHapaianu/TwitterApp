package com.example.TwitterApp.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private List<FollowListUserDTO> following;
    private List<FollowListUserDTO> followers;
}
