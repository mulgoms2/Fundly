package com.fundly.chat.service;

import com.fundly.chat.repository.ChatRepository;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.SelBuyMsgDto;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.domain.FileDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    FileDao fileDao;
    ChatRepository chatRepository;
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatServiceImpl(SimpMessagingTemplate simpMessagingTemplate, ChatRepository chatRepository, FileDao fileDao) {
        this.fileDao = fileDao;
        this.chatRepository = chatRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    @Transactional
    public SelBuyMsgDto getChatRoom(ChatRequest request) {
        SelBuyMsgDto selBuyMsgDto;
        if ((selBuyMsgDto = chatRepository.findRoom(request)) == null) {
//            채팅방을 만들고 가져온뒤 반환 한다.
            chatRepository.makeChatRoom(request);
            selBuyMsgDto = chatRepository.findRoom(request);
        } else {
//            이미 존재하는 채팅방이 있으면 기존 채팅을 만들어 넣어준다.
            ArrayList<SelBuyMsgDetailsDto> mesageList = loadMessages(selBuyMsgDto);
            selBuyMsgDto.setMessage_list(mesageList);
            request.setSelBuyMsgDto(selBuyMsgDto);
        }
        return selBuyMsgDto;
    }

//    @Override
//    public void getChatRoom(ChatRequest chatRequest) {
////        채팅룸이 존재하면 채팅방 리턴
//        ChatRoomDto chatRoomDto;
//
//        selBuyMsgDao.selectChatRoom(chatRequest);
//
//        if ((chatRoomDto = selBuyMsgDao.selectChatRoom(chatRequest)) == null) {
////            채팅방 생성과 동시에 chatRequest 에 세팅된다
//            selBuyMsgDao.makeChatRoom(chatRequest);
//
//        } else {
////            채팅방이 존재하면 메시지 리스트를 가져온다.
//            chatRoomDto.setMessage_list(loadMessages(chatRoomDto));
////            채팅방 요청 객체에 채팅방을 담는다.
//            chatRequest.setChatRoomDto(chatRoomDto);
//        }
//    }

    @Override
    public boolean saveMessage(SelBuyMsgDetailsDto message) {
        try {
            chatRepository.insertMsg(message);

            String svr_intime = new SimpleDateFormat("HH:mm").format(new Date());
            message.setSvr_intime_string(svr_intime);
        } catch (Exception e) {
            log.error("error with save Message");
            throw new RuntimeException("error with saveMessage(SelBuyMsgDetailsDto message)", e);
        }
        return true;
    }

    @Override
    public ArrayList<SelBuyMsgDetailsDto> loadMessages(SelBuyMsgDto selBuyMsgDto) {
        try {
//            메시지 전체 조회 후 첨부파일 경로를 매핑해서 전달.
            return chatRepository.loadAllMessages(selBuyMsgDto).stream()
                    .map(this::timeFormatting)
//                    .map(this::mappingImgUrl)
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
            img_file.setFile_saved_url(savedImgUrl);
            img_file.setFile_origin_url(IMG_SAVE_LOCATION + originFileName);
            img_file.setTable_key(message.getMsg_id());

//            파일 Dto에 해당 메시지의 식별자를 적어서 저장해야한다.
            fileDao.saveFile(img_file);
        } catch (IOException e) {
            throw new RuntimeException("error with new File(savedImgUrl)", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource loadImgFile(String fileName) throws Exception {
        try {
            return new UrlResource(String.format("file:%s%s", IMG_SAVE_LOCATION, fileName));
        } catch (MalformedURLException e) {
            log.error("fail ChatServiceImpl.loadImgFile() : {}", e.getCause());
            throw new RuntimeException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "chat/error";
    }
}
