package com.example.TwitterApp.service;

import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();
    void registerUser(User user);
    User getUserByUserName(String userName);
    User getUserByFirstName(String firstName);
    User getUserByLastName(String lastName);
    void follow(String userName, Map<String, String> userNameToFollow);
    List<Post> getFeed(String UserName);
}
