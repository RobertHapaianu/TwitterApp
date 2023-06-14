package com.example.TwitterApp.service;

import com.example.TwitterApp.advice.exception.UserNotFoundException;
import com.example.TwitterApp.mapper.UserMapper;
import com.example.TwitterApp.model.dto.UserDTO;
import com.example.TwitterApp.model.entity.User;
//import com.example.TwitterApp.repository.PostRepository;
import com.example.TwitterApp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
//    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
//        this.postRepository = postRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
//        return userRepository.findAll();
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public List<UserDTO> search(String name) {
        List<UserDTO> userDTOS = userRepository.findAllUserByUserNameOrFirstNameOrLastName(name, name, name).stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
        if (userDTOS.isEmpty()) throw new UserNotFoundException("couldn't find any user with this name");
        return userDTOS;
    }

    public User searchByUserName(String name) {
        return userRepository.findUserByUserName(name);
    }

    public ResponseEntity<?> follow(String userName, String userNameToFollow) {
        if (userRepository.findAll().stream().noneMatch(person -> userName.equals(person.getUserName())))
            return ResponseEntity.badRequest().body("User with username \"" + userName + "\" does not exist!");

        if (userRepository.findAll().stream().noneMatch(person -> userNameToFollow.equals(person.getUserName())))
            return ResponseEntity.badRequest().body("User with username: " + userNameToFollow + " does not exist!");

        if ((searchByUserName(userName).getFollowing()).stream().anyMatch(person -> userNameToFollow.equals(person.getUserName())))
            if ((searchByUserName(userNameToFollow).getFollowers()).stream().anyMatch(person -> userName.equals(person.getUserName())))
                return ResponseEntity.badRequest().body(userName + " already follows " + userNameToFollow + "!");

        if (userName.equals(userNameToFollow))
            return ResponseEntity.badRequest().body("A user cannot follow himself");

        ((searchByUserName(userName)).getFollowing()).add(userRepository.findUserByUserName(userNameToFollow));
        ((searchByUserName(userNameToFollow)).getFollowers()).add(userRepository.findUserByUserName(userName));
        userRepository.save(searchByUserName(userName));
        userRepository.save(searchByUserName(userNameToFollow));
        return ResponseEntity.ok("OK");
//        userRepository.saveAll((searchByUserName(userName)).getFollowing());
//        userRepository.saveAll((searchByUserName(userName)).getFollowers());
    }

//
//    public List<Post> getFeed(String UserName) {
//        return postRepository.getFeed(getUserByUserName(UserName));
//    }
}
