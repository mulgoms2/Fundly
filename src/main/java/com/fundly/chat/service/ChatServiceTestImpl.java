package com.fundly.chat.service;

import com.fundly.chat.dao.ChatRoomDao;
import com.fundly.chat.model.ChatRoom;
import com.fundly.chat.model.ChatRoomRequest;
import com.fundly.chat.model.ChatRoomResponse;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ChatServiceTestImpl implements ChatServiceTest {
    ChatRoomDao chatRoomDao;

    @Autowired
    public ChatServiceTestImpl(ChatRoomDao chatRoomDao) {
        this.chatRoomDao = chatRoomDao;
    }

    @Override
    public ChatRoomResponse enter(ChatRoomRequest request) throws BadRequestException {
//        요청 null 검증
        if (request == null) {
            String errMsg = "잘못 된 요청입니다. 다시 시도해주세요";
            log.error("chatServiceTestImpl.enter(request) error. message = {}", errMsg);
            throw new BadRequestException(errMsg);
        }
//        기존의 채팅방이 존재하는지 확인한다.
        ChatRoom chatRoom = getChatRoom(request);

        return ChatRoom.of(chatRoom);
    }

//    기존의 채팅방 혹은 새로만든 채팅방을 가져온다.
    @Override
    public ChatRoom getChatRoom(ChatRoomRequest request) {
        Long roomNum = chatRoomDao.findRoomNum(request.getUser_id(), request.getTo_user_id());
//        리퀘스트 정보로 호출되는 채팅방이 없을 때 makeChatRoom() 수행

        return roomNum == null ? makeChatRoom(request) : chatRoomDao.getRoom(roomNum);
    }

//    새로운 채팅방을 만든다.
    private ChatRoom makeChatRoom(ChatRoomRequest chatRoomRequest) {
//        새로운 채팅방을 만든다는 것은 새로운 채팅방 객체를 생성해서 반환하는 것을 의미한다.
//        새로운 채팅방이라 함은 새로운 방 번호를 키로 갖는 객체이다.
        Long roomNum = chatRoomDao.maxRoomNum() + 1L;

        return ChatRoom.builder().room_num(roomNum).from_user_id(chatRoomRequest.getUser_id()).to_user_id(chatRoomRequest.getTo_user_id()).build();
    }
}
