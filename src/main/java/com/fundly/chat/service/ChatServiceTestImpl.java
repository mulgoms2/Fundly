package com.fundly.chat.service;

import com.fundly.chat.dao.ChatRoomDao;
import com.fundly.chat.model.ChatRoom;
import com.fundly.chat.model.ChatRoomRequest;
import com.fundly.chat.model.ChatRoomResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//        채팅방을 가져온다.
        ChatRoom chatRoom = getChatRoom(request);

        return ChatRoom.of(chatRoom);
    }

//    기존의 채팅방 혹은 새로만든 채팅방을 가져온다.
    public ChatRoom getChatRoom(ChatRoomRequest request) {
//       Dao에 chatRoomEntity를 넘겨서 채팅방을
        Long roomNum = chatRoomDao.findRoomNum(request.getUser_id(), request.getTo_user_id());
//        유저 정보로 조회 채팅방이 없을 때 makeChatRoom() 수행

        return roomNum == null ? makeChatRoom(request) : chatRoomDao.getRoom(roomNum);
    }

//    새로운 채팅방을 만든다.
    public ChatRoom makeChatRoom(ChatRoomRequest chatRoomRequest) {
        //todo chatRoomDao.save() 가 새로운 채팅방을 반환할때 채팅방 번호는 기존의 방번호의 +1 이어야 한다.
        ChatRoom savedChatRoom = chatRoomDao.save(chatRoomRequest.toEntity());

        return savedChatRoom;
    }
}
