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
    public Integer getChatRoom(String user_id, String pj_id) {
//        유저 id와 프로젝트 id를 받아 채팅방(topic)을 만든다.

        Integer roomNum = findRoom(user_id, pj_id);
//        방이 존재하지 않으면 새로운 채팅방을 생성한다.
        if (roomNum == null) {
            Integer chatRoom = chatRoomDao.makeChatRoom(user_id, pj_id);
            log.error("chatroom ? "  + chatRoom);
        }

//        방이 존재하면 기존의 채팅방을 return 한다.
        return roomNum;
    }

    public void loadMessages() {

    }

    public Integer findRoom(String user_id, String pj_id) {
        try {
            ChatRoomDto chatRoomDto = chatRoomDao.selectChatRoom(user_id, pj_id);
            return chatRoomDto.getRoom_num();
        } catch (Exception e) {
            log.trace("can not find chat room");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void enterRoom() {

    }
}
