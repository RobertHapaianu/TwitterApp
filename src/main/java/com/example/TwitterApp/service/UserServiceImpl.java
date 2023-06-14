package com.example.TwitterApp.service;

import com.example.TwitterApp.controller.UserController;
import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.User;
import com.example.TwitterApp.repository.PostRepository;
import com.example.TwitterApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void registerUser(User user) {
        userRepository.createUser(user);
    }

    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
    public User getUserByFirstName(String firstName) {
        return userRepository.getUserByFirstName(firstName);
    }
    public User getUserByLastName(String lastName) {
        return userRepository.getUserByLastName(lastName);
    }

    public void follow(String userName, Map<String, String> userNameToFollow) {
        userRepository.follow(userName, userNameToFollow);
    }

    public List<Post> getFeed(String UserName) {
        return postRepository.getFeed(getUserByUserName(UserName));
    }
}
