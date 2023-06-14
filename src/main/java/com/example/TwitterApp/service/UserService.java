package com.example.TwitterApp.service;

import com.example.TwitterApp.model.dto.UserDTO;
import com.example.TwitterApp.model.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    void registerUser(User user);
    List<UserDTO> search(String name);
    User searchByUserName(String name);
//    User getUserByUserName(String userName);
//    User getUserByFirstName(String firstName);
//    User getUserByLastName(String lastName);
    ResponseEntity<?> follow(String userName, String userNameToFollow);
//    List<Post> getFeed(String UserName);
}
