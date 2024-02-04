package com.fundly.chat.service;

import org.springframework.stereotype.Service;

@Service
public interface ChatService {
    Integer findRoom(String user_id, String pj_id) throws Exception;

    void enterRoom();


}
