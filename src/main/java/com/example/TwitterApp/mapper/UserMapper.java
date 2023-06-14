package com.example.TwitterApp.mapper;

import com.example.TwitterApp.model.dto.UserDTO;
//import com.example.TwitterApp.model.dto.FollowListUserDTO;
import com.example.TwitterApp.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "following", source = "following")
    @Mapping(target = "followers", source = "followers")
    UserDTO userToUserDTO(User user);

//    List<UserDTO> usersToUserDTOs(List<User> users);
}
