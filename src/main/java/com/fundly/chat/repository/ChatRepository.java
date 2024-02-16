package com.fundly.chat.repository;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;

import java.util.ArrayList;

public interface ChatRepository {
    SelBuyMsgDto findRoom(ChatRequest chatRequest);

    int makeChatRoom(ChatRequest chatRequest);

    ArrayList<SelBuyMsgDetailsDto> loadAllMessages(SelBuyMsgDto selBuyMsgDto);

    int insertMsg(SelBuyMsgDetailsDto selBuyMsgDetailsDto);

}
