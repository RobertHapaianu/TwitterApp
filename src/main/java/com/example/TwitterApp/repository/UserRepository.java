package com.example.TwitterApp.repository;

import com.example.TwitterApp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {

    List<User> findAllUserByUserNameOrFirstNameOrLastName(String userName, String firstName, String lastName);
    User findUserByUserName(String userName);

}
