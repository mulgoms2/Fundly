package com.fundly.chat.dao;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SelBuyMsgDao {
    SelBuyMsgDto selectChatRoom(ChatRequest chatRequest) throws Exception;
    int makeChatRoom(ChatRequest chatRequest) throws Exception;
    int deleteAllChatRoom();
    int count();
}
