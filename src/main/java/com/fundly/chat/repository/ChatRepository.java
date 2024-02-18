package com.fundly.chat.repository;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.SelBuyMsgDto;

import java.util.ArrayList;
import java.util.List;

public interface ChatRepository {
    SelBuyMsgDto getChatRoom(ChatRequest chatRequest) throws Exception;

    SelBuyMsgDto makeChatRoom(ChatRequest chatRequest) throws Exception;

    List<SelBuyMsgDetailsDto> loadAllMessages(SelBuyMsgDto selBuyMsgDto) throws Exception;

    int saveMessage(SelBuyMsgDetailsDto selBuyMsgDetailsDto) throws Exception;

    void saveImageFile(FileDto fileDto) throws Exception;
}
