package com.fundly.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice("com.fundly.project")
public class ProjectGlobalCatcher {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String catcher(Exception e){
        //구체적으로 어떤 에러를 공통으로 처리할 것인지 코드 작성하기
        log.error("exception message={}",e.getMessage());
        e.printStackTrace();
        return "project/error";
    }
}
