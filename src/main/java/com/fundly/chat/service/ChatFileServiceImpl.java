package com.fundly.chat.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ChatFileServiceImpl implements ChatFileService{
    @Override
    @SneakyThrows
    public String saveImageFile(MultipartFile img_file) {

//        String savedUrl = "/Users/dobigulbi/IdeaProjects/Fundly/src/main/webapp/WEB-INF/static/chat/file/";
        String savedUrl = "/Users/dobigulbi/chat/file/";
        String sysMills = String.valueOf(System.currentTimeMillis());
        String originFileName = img_file.getOriginalFilename();
        String savedImgUrl = savedUrl + sysMills + originFileName;


        try {
//            파일을 서버에 저장했다.
            img_file.transferTo(new File(savedImgUrl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedImgUrl;
    }
}

