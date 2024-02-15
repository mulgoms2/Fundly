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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//import javax.validation.Valid;

//import javax.validation.Valid;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    ChatService chatService;

    @GetMapping("/chat")
//    테스트용
    public String chatRoom(@Valid ChatRequest chatRequest, BindingResult result)  {

        if (result.hasErrors()) {
//            return chatRequest;
            return "chat/error";
        }

//        return chatRequest;
        return "chat/chat";
    }

    @GetMapping("/chatPop")
    public String joinChatRoom(@Valid @ModelAttribute ChatRequest chatRequest, Errors errors, BindingResult result) {
//        user_id, pj_id를 통해 식별되는 채팅방을 불러온다.
        chatService.getChatRoom(chatRequest);


        return "chat/chat";
    }

    //    MessageMapping을 통해 유저의 메시지 전송이 매핑되며. /chatPub/chat/{방번호} pathVariable 의 일종인 것 같다.
    @MessageMapping("/chat/{roomNum}")
    @SendTo("/chatSub/{roomNum}")
    public SelBuyMsgDetailsDto publishMessage(@DestinationVariable String roomNum, SelBuyMsgDetailsDto message, SimpMessageHeaderAccessor accessor) {
//        httpsession 객체에 담긴 데이터를 이용할 수 있다.
//        Object session = accessor.getSessionAttributes().get("session");
//        System.out.println(((HttpSession) session).getAttribute("user_email"));

//        채팅을 저장

        chatService.saveMessage(message);

//        메시지를 토픽에 발행한다. sendTo /chatSup/{}
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
//        채팅방에 이미지 경로가 담긴 메시지를 토픽에 발행한다ㅣ
        simpMessagingTemplate.convertAndSend("/chatSub/" + message.getRoom_num(), message);
    }

    @GetMapping(value = "**/file/{fileName}")
    @ResponseBody
//    이미지 태그가 파싱될때 src 주소에 의한 get 요청이 들어온다. Resource로 이미지를 응답한다.
    public Resource getImageResource(@PathVariable("fileName") String fileName) {
        try {
            return chatService.loadImgFile(fileName);
        } catch (Exception e) {
            log.error("error with getImageResouce = {}", fileName);
            throw new RuntimeException("유효하지 않은 파일명 입니다.", e);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException() {
        log.error("채팅 컨트롤러 수행 도중 예외가 발생하였습니다.");
        return "chat/error";
    }
}
