package com.fundly.chat.service;

import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import org.springframework.core.io.Resource;

import java.util.ArrayList;

public interface ChatService {
    String SEL_BUY_MSG_DETAILS = "SEL_BUY_MSG_DETAILS";
    ChatRoomDto findRoom(String user_id, String pj_id) throws Exception;

    boolean saveMessage(SelBuyMsgDetailsDto message);

    ArrayList<SelBuyMsgDetailsDto> loadMessages(String user_id, String pj_id);

    String getChatRoomName(String user_id, String pj_id);
    String IMG_SAVE_LOCATION = "/Users/dobigulbi/chat/file/";
    SelBuyMsgDetailsDto saveImageFile(FileDto img_file) throws Exception;

    Resource loadImgFile(String fileName) throws Exception;
}
