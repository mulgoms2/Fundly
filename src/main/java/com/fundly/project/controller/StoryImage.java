package com.fundly.project.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@Data
public class StoryImage { //커맨드 객체
    MultipartFile file;
    int width;
    int height;
    long fileSize;
    String contentType;

    public void setFileSize(){
        this.fileSize = this.file.getSize();
    }

    public void setDimension() throws IOException {

    }

    public void setContentType(){
        this.contentType = this.file.getContentType();
    }

}


