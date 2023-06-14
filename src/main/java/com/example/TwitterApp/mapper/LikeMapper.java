package com.example.TwitterApp.mapper;

import com.example.TwitterApp.model.dto.LikeDTO;
import com.example.TwitterApp.model.entity.Like;
import com.example.TwitterApp.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LikeMapper {
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);


    @Mapping(target = "userName", source = "userWhoLiked.userName")
    LikeDTO likeToLikeDTO(Like like);

}
