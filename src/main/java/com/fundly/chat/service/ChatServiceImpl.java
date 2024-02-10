package com.fundly.chat.service;

import com.fundly.chat.model.ChatRoomDao;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.domain.FileDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.UUID;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
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
//            특정 채팅방의 메시지를 전체 조회
            ArrayList<SelBuyMsgDetailsDto> msgList = chatRoomDao.loadAllMessages(user_id, pj_id);

//            파일테이블에서 첨부파일을 조회한 후 dto에 저장한다.
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
//        채팅 메시지의 식별자인 auto increament 컬럼의 값을 파일 테이블에 저장했어야 한다.

        String msgKey = selBuyMsgDetailsDto.getMsg_id();

        String savedFileUri = null;
        try {
            savedFileUri = fileDao.getSavedFileUri(SEL_BUY_MSG_DETAILS,msgKey);
        } catch (Exception e) {
            log.error("error with getSavedFileUri = {}", msgKey);
            throw new RuntimeException(e);
        }

        selBuyMsgDetailsDto.setFile_url(savedFileUri);
    }

    public boolean isFileAttached(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        return selBuyMsgDetailsDto.getFile_cnt() != 0;
    }
    @Override
    public SelBuyMsgDetailsDto saveImageFile(FileDto img_file) {
        String originFileName = img_file.getFile().getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String savedImgUrl = IMG_SAVE_LOCATION + uuid + originFileName;

        SelBuyMsgDetailsDto message = new SelBuyMsgDetailsDto();

//        이때 DTO에 메시지 키가 담긴다.
        message.setFile_cnt(1);
        message.setFile_url(savedImgUrl);

        saveMessage(message);

        String msgKey = message.getMsg_id();
        try {
//            파일을 서버에 저장했다.
            img_file.getFile().transferTo(new File(savedImgUrl));
//            파일 테이블에 저장된 파일 경로를 담아야한다.
            img_file.setTable_name("Sel_Buy_Msg_Details");
            img_file.setSaved_uri(savedImgUrl);
            img_file.setOrigin_uri(IMG_SAVE_LOCATION + originFileName);
            img_file.setTable_key(msgKey);

//            파일 Dto에 해당 메시지의 식별자를 적어서 저장해야한다.
            fileDao.saveFile(img_file);
        } catch (IOException e) {
            log.error("error with saveImageFile on service = {}", e.getCause());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("error with saveImageFile on service = {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return message;
    }
    @Override
    public Resource loadImgFile(String fileName) throws Exception {
        try {
            return new UrlResource(String.format("file:%s%s", IMG_SAVE_LOCATION, fileName));
        } catch (MalformedURLException e) {
            log.error("fail to save file : {}", fileName);
            throw new RuntimeException(e);
        }
    }
}
