package com.fundly.chat.controller;

import com.fundly.chat.service.ChatFileService;
import com.fundly.chat.service.ChatService;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    ChatFileService chatFileService;

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

    @MessageMapping("/chat/{roomName}")
    @SendTo("/chatSub/{roomName}")
    public SelBuyMsgDetailsDto publishMessage(@DestinationVariable String roomName, SelBuyMsgDetailsDto message) {

        chatService.saveMessage(message);

        return message;
    }

    @PostMapping("/chat/file")
    @ResponseBody
    public ArrayList saveImgFile(FileDto file) {
//        파일 저장 처리후에 파일 저장 경로를 리턴한다.
        ArrayList<String> urlList = new ArrayList<>();
//        파일을 저장하고 저장경로를 받는다.
        String savedUrl = "";
        try {
            savedUrl = chatFileService.saveImageFile(file);
        } catch (Exception e) {
            log.error("error with saveImgFile = {}", file);
            throw new RuntimeException(e);
        }

//        저장경로를 json으로 리턴한다.
        urlList.add(savedUrl);

        return urlList;
    }

    @GetMapping(value = "**/file/{fileName}")
    @ResponseBody
    public Resource getImageResource(@PathVariable("fileName") String fileName) {
        try {
            return chatFileService.loadImgFile(fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
