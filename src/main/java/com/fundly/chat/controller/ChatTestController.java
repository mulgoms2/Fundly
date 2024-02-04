package com.fundly.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatTestController {

    @GetMapping("/chatIndex")
    public String chatIndex() {

        return "chat/chatIndex";
    }
}
