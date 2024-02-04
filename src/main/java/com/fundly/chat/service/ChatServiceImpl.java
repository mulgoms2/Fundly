package com.fundly.chat.service;

import com.fundly.chat.model.ChatRoomDao;
import com.persistence.dto.ChatRoomDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRoomDao chatRoomDao;
    public ChatRoomDto getChatRoom(String user_id, String pj_id) {
//        유저 id와 프로젝트 id를 받아 채팅방(topic)을 만든다.

        ChatRoomDto chatRoomDto = findRoom(user_id, pj_id);

        log.error("chat room dto = " + chatRoomDto);
        return chatRoomDto;
    }

    public ChatRoomDto findRoom(String user_id, String pj_id) {

        try {
            return chatRoomDao.selectChatRoom(user_id, pj_id);
        } catch (Exception e) {
            log.trace("can not find chat room");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void enterRoom() {

    }
}
