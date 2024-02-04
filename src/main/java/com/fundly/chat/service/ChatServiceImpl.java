package com.fundly.chat.service;

import com.fundly.chat.model.ChatRoomDao;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.SelBuyMsgDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRoomDao chatRoomDao;
    public String getChatRoom(String user_id, String pj_id) {
//        유저 id와 프로젝트 id를 받아 채팅방(topic)을 만든다.

        ChatRoomDto chatRoomDto = findRoom(user_id, pj_id);

//        채팅방이 없으면 새로운 채팅방을 생성
        if (chatRoomDto == null) {
            try {
                chatRoomDao.makeChatRoom(user_id, pj_id);
                chatRoomDto = findRoom(user_id, pj_id);
            } catch (Exception e) {
                log.error("error with make chat room");
                throw new RuntimeException(e);
            }
        }

//        채팅방 이름을 반환
        String roomName = chatRoomDto.getUser_id() + chatRoomDto.getPj_id();

        return roomName;
    }

    @Override
    public boolean saveMessage(SelBuyMsgDetails message) {

        try {
            chatRoomDao.insertMsg(message);
        } catch (Exception e) {
            log.error("error with save Message");
            throw new RuntimeException(e);
        }

        return true;
    }

    public void loadMessages() {

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
