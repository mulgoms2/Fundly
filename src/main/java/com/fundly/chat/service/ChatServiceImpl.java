package com.fundly.chat.service;

import com.fundly.chat.model.ChatRoomDao;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.domain.FileDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRoomDao chatRoomDao;
    @Autowired
    FileDao fileDao;

    @Override
    public String getChatRoomName(String user_id, String pj_id) {
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
    public boolean saveMessage(SelBuyMsgDetailsDto message) {

        try {
            chatRoomDao.insertMsg(message);
        } catch (Exception e) {
            log.error("error with save Message");
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public ArrayList<SelBuyMsgDetailsDto> loadMessages(String user_id, String pj_id) {
        try {
//            특정회원의 메시지 전체를 조회한다.
            ArrayList<SelBuyMsgDetailsDto> msgList = chatRoomDao.loadAllMessages(user_id, pj_id);

//            첨부파일이 존재하는 메시지를 파일테이블에서 첨부파일을 조회한 후 dto에 저장한다.
            msgList.stream()
                    .filter(this::isFileAttached)
                    .forEach(this::loadFile);

            return msgList;
        } catch (Exception e) {
//            메시지를 로드하는데 실패하면 어떤 예외처리를 해야할까
//            에러메시지를 전달한다.
            throw new RuntimeException("error when message loading", e);
        }
    }


    public ChatRoomDto findRoom(String user_id, String pj_id) {
        try {
            return chatRoomDao.selectChatRoom(user_id, pj_id);
        } catch (Exception e) {
            log.trace("can not find chat room");
            throw new RuntimeException(e);
        }
    }
    private void loadFile(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        //            파일 카운트가 존재하는 메시지들에 각각 자기의 파일을 찾아서 매칭해준다.
//        이미지 파일이 리스트인 이유는 일단 특정 메시지에 대한 식별자를 제공하지 않은게 가장 큰 문제이고
//        두번째로는 식별자를 정확히 제공하더라도, 한 게시물(한 채팅메시지)에 대하여 여러개의 첨부파일을 가질 수도 있기 때문이다.

//        채팅 메시지의 식별자인 auto increament 컬럼의 값을 파일 테이블에 저장했어야 한다.

        ArrayList<FileDto> imgFileList = fileDao.getFile(user_id + pj_id);
//        selBuyMsgDetailsDto.
    }

    public boolean isFileAttached(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        return selBuyMsgDetailsDto.getFile_cnt() != 0;
    }
}
