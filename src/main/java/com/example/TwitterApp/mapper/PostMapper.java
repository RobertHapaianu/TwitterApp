package com.example.TwitterApp.mapper;

import com.example.TwitterApp.model.dto.PostDTO;
import com.example.TwitterApp.model.dto.UserDTO;
import com.example.TwitterApp.model.entity.Post;
import com.example.TwitterApp.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDTO postToPostDTO(Post post);
}
