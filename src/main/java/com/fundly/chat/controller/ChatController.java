package com.fundly.chat.controller;

import com.fundly.chat.service.ChatServiceImpl;
import com.persistence.dto.SelBuyMsgDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    ChatServiceImpl chatService;

    @GetMapping("/chat")
    public String chatRoom() {
        return "chat/chatIndex";
    }

    @GetMapping("/chatPop")
    public String joinChatRoom(String user_id, String pj_id, Model model) {

//        유저id, 프로젝트 id로 채팅방을 얻어온다.
        String chatRoomName = chatService.getChatRoom(user_id, pj_id);

        model.addAttribute("roomName", chatRoomName);

//        지난 채팅메시지를 가져온다.
        ArrayList<SelBuyMsgDetails> messageList = chatService.loadMessages(user_id, pj_id);

        model.addAttribute("messageList", messageList);

//        model에 아이디랑 pj_id를 임시로 담았다 왜담았지?
        model.addAttribute("user_id", user_id);
        model.addAttribute("pj_id", pj_id);

//        채팅방에 입장하면서 자동으로 채팅방에 대한 구독이 시작된다.
        return "chat/chat";
    }

    @MessageMapping("/chat/{roomName}")
    @SendTo("/chatSub/{roomName}")
    public SelBuyMsgDetails enterRoom(@DestinationVariable String roomName, SelBuyMsgDetails message) {
        log.error("chat roomname{} message {} message.cont{}  message.send_user {} " , roomName, message, message.getMsg_cont(), message.getSend_user_id());

//        파일이 첨부되어 오는지 확인해보자.
        chatService.saveMessage(message);

//        여기에서 메시지 발송자 아이디를 결정해야 할 것 같다. 유저의 아이디를 토대로?

        return message;
    }

    @PostMapping("/chat/file")
    public void file(@RequestParam("asdf") MultipartFile file) {

        log.error("file = {}", file);
    }

}
