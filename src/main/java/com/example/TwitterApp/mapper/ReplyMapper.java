package com.example.TwitterApp.mapper;

import com.example.TwitterApp.model.dto.ReplyDTO;
import com.example.TwitterApp.model.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    ReplyMapper INSTANCE =  Mappers.getMapper(ReplyMapper.class);

    @Mapping(target = "text", source = "text")
    public ReplyDTO replayTOReplyDTO(Reply reply);
}
