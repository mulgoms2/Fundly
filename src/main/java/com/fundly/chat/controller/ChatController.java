package com.fundly.chat.controller;

import com.fundly.chat.service.ChatService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

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
    public String joinChatRoom(String user_id, String pj_id, Model model) {

//        유저id, 프로젝트 id로 채팅방을 얻어온다.
        String chatRoomName = chatService.getChatRoomName(user_id, pj_id);

        model.addAttribute("roomName", chatRoomName);

//        지난 채팅메시지를 가져온다.
        ArrayList<SelBuyMsgDetailsDto> messageList = chatService.loadMessages(user_id, pj_id);
        model.addAttribute("messageList", messageList);

//        model에 아이디랑 pj_id를 임시로 담았다
        model.addAttribute("user_id", user_id);
        model.addAttribute("pj_id", pj_id);

//        채팅방에 입장하면서 자동으로 채팅방에 대한 구독이 시작된다.
        return "chat/chat";
    }

//    MessageMapping을 통해 유저의 메시지 전송이 매핑되며. /chatPub/chat/{방번호} pathVariable 의 일종인 것 같다.
    @MessageMapping("/chat/{roomName}")
    @SendTo("/chatSub/{roomName}")
    public SelBuyMsgDetailsDto publishMessage(@DestinationVariable String roomName, SelBuyMsgDetailsDto message, SimpMessageHeaderAccessor accessor) {

        chatService.saveMessage(message);

        return message;
    }

    @PostMapping("/chat/file")
    @ResponseBody
    public void uploadFile(FileDto file, SelBuyMsgDetailsDto message) {
//        파일을 서버에 저장. 채팅방에 이미지가 담긴 메시지를 발행한다.
        String roomName = chatService.getChatRoomName(message.getBuy_id(), message.getPj_id());

        try {
//            이미지 파일을 서버에 저장한다.
            chatService.saveImageFile(file, message);
        } catch (Exception e) {
            log.error("error with uploadFile = {}", file);
            throw new RuntimeException(e);
        }
//        채팅방에 이미지 경로가 담긴 메시지를 전달한다.
        simpMessagingTemplate.convertAndSend("/chatSub/" + roomName, message);
    }

    @GetMapping(value = "**/file/{fileName}")
    @ResponseBody
    public Resource getImageResource(@PathVariable("fileName") String fileName) {
        try {
            return chatService.loadImgFile(fileName);
        } catch (Exception e) {
            log.error("error with getImageResouce = {}" ,fileName);
            throw new RuntimeException("유효하지 않은 파일명 입니다.",e);
        }
    }
}
