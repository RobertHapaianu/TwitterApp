package com.example.TwitterApp.controller;

import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.User;
import com.example.TwitterApp.service.UserService;
import com.example.TwitterApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @GetMapping(value = "/username/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

    @GetMapping(value = "/firstname/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByFirstName(@PathVariable String firstName) {
        return userService.getUserByFirstName(firstName);
    }

    @GetMapping(value = "/lastname/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByLastName(@PathVariable String lastName) {
        return userService.getUserByLastName(lastName);
    }

    @PatchMapping(value = "/username/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void follow(@PathVariable String userName, @RequestBody Map<String, String> userNameToFollow) {
        userService.follow(userName, userNameToFollow);
    }

    @GetMapping(value = "/feed/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getFeed(@PathVariable String userName) {
        return userService.getFeed(userName);
    }



}
