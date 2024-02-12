package com.fundly.chat.controller;

import com.fundly.chat.service.ChatService;
import com.persistence.dto.ChatRequest;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    ChatService chatService;

    @GetMapping("/chat")
    public String chatRoom() {
        return "chat/chatIndex";
    }

    @GetMapping("/chatPop")
    public String joinChatRoom(@ModelAttribute ChatRequest chatRequest, Model model) {
        chatService.getChatRoom(chatRequest);

        return "chat/chat";
    }

    //    MessageMapping을 통해 유저의 메시지 전송이 매핑되며. /chatPub/chat/{방번호} pathVariable 의 일종인 것 같다.
    @MessageMapping("/chat/{roomNum}")
    @SendTo("/chatSub/{roomNum}")
    public SelBuyMsgDetailsDto publishMessage(@DestinationVariable String roomNum, SelBuyMsgDetailsDto message, SimpMessageHeaderAccessor accessor) {
        chatService.saveMessage(message);

        return message;
    }

    @PostMapping("/chat/file")
    @ResponseBody
    public void uploadFile(FileDto file, SelBuyMsgDetailsDto message) {
        try {
//            이미지 파일을 서버에 저장한다.
            chatService.saveImageFile(file, message);
        } catch (Exception e) {
            log.error("error with uploadFile = {}", file);
            throw new RuntimeException("error with uploadFile(FileDto file, SelBuyMsgDatailsDto message)",e);
        }
//        채팅방에 이미지 경로가 담긴 메시지를 전달한다.
        simpMessagingTemplate.convertAndSend("/chatSub/" + message.getRoom_num(), message);
    }

    @GetMapping(value = "**/file/{fileName}")
    @ResponseBody
    public Resource getImageResource(@PathVariable("fileName") String fileName) {
        try {
            return chatService.loadImgFile(fileName);
        } catch (Exception e) {
            log.error("error with getImageResouce = {}", fileName);
            throw new RuntimeException("유효하지 않은 파일명 입니다.", e);
        }
    }
}
