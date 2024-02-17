package com.fundly.chat.repository;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;

import java.util.ArrayList;
import java.util.List;

public interface ChatRepository {
    SelBuyMsgDto getChatRoom(ChatRequest chatRequest) throws Exception;

    SelBuyMsgDto makeChatRoom(ChatRequest chatRequest) throws Exception;

    List<SelBuyMsgDetailsDto> loadAllMessages(SelBuyMsgDto selBuyMsgDto);

    int saveMessage(SelBuyMsgDetailsDto selBuyMsgDetailsDto);

//    void saveImgMessage();
}
