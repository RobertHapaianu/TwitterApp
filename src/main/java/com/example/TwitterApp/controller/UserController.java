package com.example.TwitterApp.controller;

import com.example.TwitterApp.model.Post;
import com.example.TwitterApp.model.User;
import com.example.TwitterApp.service.UserService;
import com.example.TwitterApp.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "This endpoint returns all Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "This endpoint registers a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @Operation(summary = "This endpoint returns a user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping(value = "/username/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }


    @Operation(summary = "This endpoint returns a user by firstname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping(value = "/firstname/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByFirstName(@PathVariable String firstName) {
        return userService.getUserByFirstName(firstName);
    }

    @Operation(summary = "This endpoint returns a user by lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping(value = "/lastname/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByLastName(@PathVariable String lastName) {
        return userService.getUserByLastName(lastName);
    }

    @Operation(summary = "This endpoint adds a user to the following list of the current user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PatchMapping(value = "/username/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void follow(@PathVariable String userName, @RequestBody Map<String, String> userNameToFollow) {
        userService.follow(userName, userNameToFollow);
    }

    @Operation(summary = "This endpoint returns the feed of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "Feed not found",
                    content = @Content)})
    @GetMapping(value = "/feed/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getFeed(@PathVariable String userName) {
        return userService.getFeed(userName);
    }



}
