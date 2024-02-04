package com.fundly.chat.service;

import com.persistence.dto.ChatRoomDto;
import org.springframework.stereotype.Service;

@Service
public interface ChatService {
    ChatRoomDto findRoom(String user_id, String pj_id) throws Exception;

    void enterRoom();


}
