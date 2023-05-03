package com.example.TwitterApp.repository;

import com.example.TwitterApp.advice.exception.UserNotFoundException;
import com.example.TwitterApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private Map<Integer, User> idUser = new HashMap<>();
    private Integer id = 0;

    public List<User> getAllUsers() {
        return idUser.values().stream().collect(Collectors.toList());
    }

    public void createUser(User user) {
        user.setId(id);
        idUser.put(id, user);
        id++;
    }

    public User getUserByUserName(String userName) {
        User user = idUser.values().stream()
                .filter(person -> userName.equals(person.getUserName()))
                .findAny()
                .orElse(null);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with username %s was not found", userName));
        }
        return user;
    }

    public User getUserByFirstName(String firstName) {
        User user = idUser.values().stream()
                .filter(person -> firstName.equals(person.getFirstName()))
                .findAny()
                .orElse(null);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with firstname %s was not found", firstName));
        }
        return user;
    }

    public User getUserByLastName(String lastName) {
        User user = idUser.values().stream()
                .filter(person -> lastName.equals(person.getLastName()))
                .findAny()
                .orElse(null);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(String.format("User with lastname %s was not found", lastName));
        }
        return user;
    }

    public void follow(String userName, Map<String, String> userNameToFollow) {
        getUserByUserName(userName).follow(getUserByUserName(userNameToFollow.get("userNameToFollow")));
    }
}
