package com.fundly.chat.service;

import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetailsDto;

import java.util.ArrayList;

public interface ChatService {
    ChatRoomDto findRoom(String user_id, String pj_id) throws Exception;

    boolean saveMessage(SelBuyMsgDetailsDto message);

    ArrayList<SelBuyMsgDetailsDto> loadMessages(String user_id, String pj_id);

    String getChatRoomName(String user_id, String pj_id);
    void enterRoom();


}
