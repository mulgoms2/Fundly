package com.fundly.commonExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
//@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public String commonErrorHandling() {
        log.error("com.fundly.commonExceptionHandler.CommonExecptionHandler.commonErrorHandling()");
        return "error";
    }
}
