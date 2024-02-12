package com.fundly.chat.service;

import com.fundly.chat.model.ChatRoomDao;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.ChatRoomDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.domain.FileDao;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static java.util.stream.Collectors.toCollection;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    ChatRoomDao chatRoomDao;
    @Autowired
    FileDao fileDao;

    @SneakyThrows
    @Override
    public void getChatRoom(ChatRequest chatRequest) {
//        채팅룸이 존재하면 채팅방 리턴
        ChatRoomDto chatRoomDto;

        if ((chatRoomDto = chatRoomDao.selectChatRoom(chatRequest)) == null) {
//            채팅방 생성과 동시에 chatRequest 에 세팅된다
            chatRoomDao.makeChatRoom(chatRequest);
        } else {
//            채팅방이 존재하면 메시지 리스트를 가져온다.
            chatRoomDto.setMessage_list(loadMessages(chatRoomDto));
//            채팅방 요청 객체에 채팅방을 담는다.
            chatRequest.setChatRoomDto(chatRoomDto);
        }
    }

    @Override
    public boolean saveMessage(SelBuyMsgDetailsDto message) {
        try {
            chatRoomDao.insertMsg(message);

            String svr_intime = new SimpleDateFormat("HH:mm").format(new Date());
            message.setSvr_intime_string(svr_intime);
        } catch (Exception e) {
            log.error("error with save Message");
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public ArrayList<SelBuyMsgDetailsDto> loadMessages(ChatRoomDto chatRoomDto) {
        try {
//            메시지 전체 조회 후 첨부파일 경로를 매핑해서 전달.
            return chatRoomDao.loadAllMessages(chatRoomDto).stream()
                    .map(this::timeFormatting)
                    .map(this::mappingImgUrl)
                    .collect(toCollection(ArrayList<SelBuyMsgDetailsDto>::new));
        } catch (Exception e) {
//            에러메시지를 전달한다.
            throw new RuntimeException("error with message loading", e);
        }
    }

    private SelBuyMsgDetailsDto timeFormatting(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        Date date = selBuyMsgDetailsDto.getSvr_intime();
        String hourAndMinute = new SimpleDateFormat("HH:mm").format(date);
        selBuyMsgDetailsDto.setSvr_intime_string(hourAndMinute);

        return selBuyMsgDetailsDto;
    }
    private SelBuyMsgDetailsDto mappingImgUrl(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
//        파일이 첨부되어있는 메시지에 첨부파일 url 을 매핑
        if (!isFileAttached(selBuyMsgDetailsDto)) {
            return selBuyMsgDetailsDto;
        }

        try {
//            파일 테이블에서 첨부파일 url을 가져와 dto에 세팅한다.
            String msgKey = selBuyMsgDetailsDto.getMsg_id();
            String savedFileUri = fileDao.getSavedFileUri(SEL_BUY_MSG_DETAILS, msgKey);
            selBuyMsgDetailsDto.setFile_url(savedFileUri);
            return selBuyMsgDetailsDto;
        } catch (Exception e) {
            log.error("error with getSavedFileUri");
            throw new RuntimeException(e);
        }
    }

    public boolean isFileAttached(SelBuyMsgDetailsDto selBuyMsgDetailsDto) {
        return selBuyMsgDetailsDto.getFile_cnt() != 0;
    }

    @Override
    public void saveImageFile(FileDto img_file, SelBuyMsgDetailsDto message) {
        String originFileName = img_file.getFile().getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String savedImgUrl = IMG_SAVE_LOCATION + uuid + originFileName;

        try {
//            메시지를 db에 저장
            saveMessage(message);
//            파일 저장과 동시에 채팅창에 보여지기 위해 이미지 url을 넣어준다.
            message.setFile_url(savedImgUrl);
//            파일을 서버에 저장했다.
            img_file.getFile().transferTo(new File(savedImgUrl));
//            파일 테이블에 저장된 파일 경로를 담아야한다.
            img_file.setTable_name(SEL_BUY_MSG_DETAILS);
            img_file.setSaved_uri(savedImgUrl);
            img_file.setOrigin_uri(IMG_SAVE_LOCATION + originFileName);
            img_file.setTable_key(message.getMsg_id());

//            파일 Dto에 해당 메시지의 식별자를 적어서 저장해야한다.
            fileDao.saveFile(img_file);
        } catch (IOException e) {
            throw new RuntimeException("error with new File(savedImgUrl)",e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
