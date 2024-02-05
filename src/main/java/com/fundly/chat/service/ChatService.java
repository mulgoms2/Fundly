package com.fundly.chat.service;

import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetails;

import java.util.ArrayList;

public interface ChatService {
    ChatRoomDto findRoom(String user_id, String pj_id) throws Exception;

    boolean saveMessage(SelBuyMsgDetails message);

    ArrayList<SelBuyMsgDetails> loadMessages(String user_id, String pj_id);

    void enterRoom();


}
