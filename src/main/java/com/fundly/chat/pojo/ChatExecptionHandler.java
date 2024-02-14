package com.fundly.chat.pojo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.fundly.chat")
public class ChatExecptionHandler {

    @ExceptionHandler(Exception.class)
    public String commonExceptionHandling() {
        log.error("잡았따");
        return "chat/error";
    }
}
