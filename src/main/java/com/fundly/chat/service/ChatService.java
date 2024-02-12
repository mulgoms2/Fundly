package com.fundly.chat.service;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import org.springframework.core.io.Resource;

import java.util.ArrayList;

public interface ChatService {
    String SEL_BUY_MSG_DETAILS = "SEL_BUY_MSG_DETAILS";
    boolean saveMessage(SelBuyMsgDetailsDto message);

    ArrayList<SelBuyMsgDetailsDto> loadMessages(ChatRoomDto chatRoomDto);

    void getChatRoom(ChatRequest chatRoomDto);
    String IMG_SAVE_LOCATION = "/Users/dobigulbi/chat/file/";
    void saveImageFile(FileDto img_file, SelBuyMsgDetailsDto selBuyMsgDetailsDto) throws Exception;

    Resource loadImgFile(String fileName) throws Exception;

}
