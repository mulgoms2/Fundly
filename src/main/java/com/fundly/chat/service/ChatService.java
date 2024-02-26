package com.fundly.chat.service;

import com.fundly.chat.model.ChatRoomRequest;
import com.fundly.chat.model.ChatRoomResponse;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;

public interface ChatService {
    String SEL_BUY_MSG_DETAILS = "SEL_BUY_MSG_DETAILS";
    String IMG_SAVE_LOCATION = "/Users/dobigulbi/chat/file/";
    boolean saveMessage(SelBuyMsgDetailsDto message);
    SelBuyMsgDto joinChatRoom(ChatRequest chatRequest);
    void saveFileMessage(FileDto img_file, SelBuyMsgDetailsDto selBuyMsgDetailsDto);
}
