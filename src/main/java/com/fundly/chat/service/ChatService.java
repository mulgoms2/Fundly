package com.fundly.chat.service;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import org.springframework.core.io.Resource;

import java.util.ArrayList;

public interface ChatService {
    String SEL_BUY_MSG_DETAILS = "SEL_BUY_MSG_DETAILS";
    boolean saveMessage(SelBuyMsgDetailsDto message);

//    메시지를 얻기위해서는 메시지 테이블을 room_num으로 조회하면 된다.
    ArrayList<SelBuyMsgDetailsDto> loadMessages(SelBuyMsgDto selBuyMsgDto);

    SelBuyMsgDto joinChatRoom(ChatRequest chatRoomDto);
    String IMG_SAVE_LOCATION = "/Users/dobigulbi/chat/file/";
    void saveImageFile(FileDto img_file, SelBuyMsgDetailsDto selBuyMsgDetailsDto) throws Exception;

    Resource loadImgFile(String fileName) throws Exception;

}
