package com.fundly.admin.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice("com.fundly.admin")
public class ControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException() {

        return "admin/error";
    }
}
