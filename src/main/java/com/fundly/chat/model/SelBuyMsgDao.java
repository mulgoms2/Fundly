package com.fundly.chat.model;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface SelBuyMsgDao {
    ChatRoomDto selectChatRoom(ChatRequest chatRequest) throws Exception;

    int makeChatRoom(ChatRequest chatRequest) throws Exception;

//    int insertMsg(SelBuyMsgDetailsDto selBuyMsgDetailsDto) throws Exception;

//    ArrayList<SelBuyMsgDetailsDto> loadAllMessages(ChatRoomDto chatRoomDto) throws Exception;

}
