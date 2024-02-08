package com.fundly.chat.controller;

import com.fundly.chat.service.ChatFileService;
import com.fundly.chat.service.ChatService;
import com.persistence.dto.SelBuyMsgDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

//        model에 아이디랑 pj_id를 임시로 담았다 왜담았지?
        model.addAttribute("user_id", user_id);
        model.addAttribute("pj_id", pj_id);

//        채팅방에 입장하면서 자동으로 채팅방에 대한 구독이 시작된다.
        return "chat/chat";
    }

    @MessageMapping("/chat/{roomName}")
    @SendTo("/chatSub/{roomName}")
    public SelBuyMsgDetailsDto publishMessage(@DestinationVariable String roomName, SelBuyMsgDetailsDto message) {
        log.error("chat roomname{} message {} message.cont{}  message.send_user {} ", roomName, message, message.getMsg_cont(), message.getSend_user_id());

//        파일이 첨부되어 오는지 확인해보자.
        chatService.saveMessage(message);

//        여기에서 메시지 발송자 아이디를 결정해야 할 것 같다. 유저의 아이디를 토대로?

        return message;
    }

    @PostMapping("/chat/file")
    @ResponseBody
    public ArrayList file(@RequestParam("img_file") MultipartFile file) {

        log.error("file = {}", file);
//        파일 저장 처리후에 파일 저장 경로를 리턴한다.

        List<String> list = new ArrayList<>();

//        파일을 저장하고 저장경로를 받는다.
        String savedUrl = chatFileService.saveImageFile(file);

//        저장경로를 json으로 리턴한다.
//        테스트 중.
        list.add(savedUrl);

//

        return (ArrayList) list;
    }

    @GetMapping(
            value = "**/WEB-INF/static/chat/file/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @ResponseBody
    public byte[] loadImageFile(@PathVariable("fileName") String fileName) throws FileNotFoundException {
//        InputStream is = getClass().getResourceAsStream("/"+fileName);

//        InputStream is = new FileInputStream("/Users/dobigulbi/IdeaProjects/Fundly/target/Fundly/WEB-INF/static/chat/file/" + fileName);
        InputStream is = new FileInputStream("**/Fundly/WEB-INF/static/chat/file/" + fileName);

        try {
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
