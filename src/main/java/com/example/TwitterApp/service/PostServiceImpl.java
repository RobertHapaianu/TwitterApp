package com.example.TwitterApp.service;

import com.example.TwitterApp.advice.exception.PostNotFoundException;
import com.example.TwitterApp.advice.exception.UserNotFoundException;
import com.example.TwitterApp.model.dto.PostDTO;
import com.example.TwitterApp.mapper.PostMapper;
import com.example.TwitterApp.model.dto.UserDTO;
import com.example.TwitterApp.model.entity.Like;
import com.example.TwitterApp.model.entity.Post;
import com.example.TwitterApp.model.entity.User;
import com.example.TwitterApp.model.entity.Reply;
import com.example.TwitterApp.repository.LikeRepository;
import com.example.TwitterApp.repository.PostRepository;
import com.example.TwitterApp.repository.ReplyRepository;
import com.example.TwitterApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final ReplyRepository replyRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository, UserService userService, ReplyRepository replyRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.replyRepository = replyRepository;
    }

    public List<PostDTO> getPosts(String userName, LocalDateTime timeStamp) {
        if (userRepository.findUserByUserName(userName) == null)
            throw new UserNotFoundException("Invalid username!");

        List<Post> posts = postRepository.findPostsByUser(userRepository.findUserByUserName(userName));
        List<PostDTO> postDTOS = new ArrayList<>();
        if (timeStamp == null) {
            for (Post post : posts) {
                postDTOS.add(PostMapper.INSTANCE.postToPostDTO(post));
            }
        }
        else {
            List<Post> posts1 = posts.stream()
                    .filter(post -> post.getTimeStamp().isAfter(timeStamp)).toList();
            for (Post post : posts1) {
                postDTOS.add(PostMapper.INSTANCE.postToPostDTO(post));
            }
        }
        return postDTOS;
    }

    public List<Post> getAllUserPosts(String userName) {
        if (userRepository.findUserByUserName(userName) == null)
            throw new UserNotFoundException("Invalid username!");

        return postRepository.findPostsByUser(userRepository.findUserByUserName(userName));
    }

    public void addPost(String text, String userName) {
        if(userRepository.findUserByUserName(userName) == null)
            throw new UserNotFoundException("Invalid username!");

        Post post = new Post();
        post.setUser(userRepository.findUserByUserName(userName));
        post.setText(text);
        post.setTimeStamp(LocalDateTime.now());
        postRepository.save(post);
    }

    public List<Post> getFeed(String userName) {
        if (userRepository.findUserByUserName(userName) == null)
            throw new UserNotFoundException("Invalid username!");
        List<Post> feed = new ArrayList<>();
        for (User user : userRepository.findUserByUserName(userName).getFollowing())
            feed.addAll(getAllUserPosts(user.getUserName()));
        return feed;
    }

    public void likePost(String userName, String postId) {
        if(postRepository.findById(postId).isEmpty()) {
            throw new PostNotFoundException("This post does not exist!");
        }
        Optional<Post> post = postRepository.findById(postId);
        User user = userRepository.findUserByUserName(userName);
        Like like = new Like(post.get(), user);
        likeRepository.save(like);
        post.get().getLikes().add(like);
        postRepository.save(post.get());
    }

    public void reply(String idPostReply, String userName, String text) {
        User userReply = userRepository.findUserByUserName(userName);
        Optional<Post> postReply = postRepository.findById(idPostReply);
        Reply reply = new Reply();
        List<UserDTO> users = userService.getAllUsers();
        for (UserDTO user : users) {
            List<Post> posts  = getAllUserPosts(user.getUserName());
            for (Post post : posts) {
                if(idPostReply.equals(post.getId())) {
                    reply.setUser(userRepository.findUserByUserName(userName));
                    reply.setPost(post);
                    reply.setText(text);
                    reply.setPublic(true);
                    replyRepository.save(reply);
                    post.setReply(reply);
                    postRepository.save(post);
                }
            }
        }
        List<Reply> replies = replyRepository.findAll();
        for (Reply reply1 : replies) {
            if (idPostReply.equals(reply1.getId())) {
                reply.setUser(userRepository.findUserByUserName(userName));
                reply.setParentReply(reply1);
                reply.setText(text);
                reply.setPublic(true);
                replyRepository.save(reply);
                reply1.setChildrenReplies(reply);
                replyRepository.save(reply1);
            }
        }
    }
}
