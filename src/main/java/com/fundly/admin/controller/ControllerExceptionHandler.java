package com.fundly.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice("com.fundly.admin")
public class ControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException() {
        log.error("adminExceptionHandler");
        return "admin/error";
    }
}
