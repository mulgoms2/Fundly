package com.fundly.chat.pojo;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.fundly.chat")
public class ChatExecptionHandler {

    @ExceptionHandler(Exception.class)
    public String commonExceptionHandling() {
        log.error("ChatExeptionHandle.commonExecptionHandling() ");
        return "chat/error";
    }

    @ExceptionHandler(FileUploadIOException.class)
    public void fileUploadExceptionHandling() {
       log.error("error when fileUpload");
    }
}
