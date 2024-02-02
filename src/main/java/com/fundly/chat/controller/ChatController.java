package com.fundly.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ChatController {

    @GetMapping("/chat")
    public String chat() {
        return "chat/chat";
    }

//    //    채팅 메시지를 불러온다
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) {
//        return new Greeting("hello, " + HtmlUtils.htmlEscape(message.getName()));
//    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) {
        return message;
    }
}
