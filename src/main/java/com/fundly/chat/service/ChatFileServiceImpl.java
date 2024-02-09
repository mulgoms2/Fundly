package com.fundly.chat.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
@Slf4j
public class ChatFileServiceImpl implements ChatFileService {

    @Override
    @SneakyThrows
    public String saveImageFile(MultipartFile img_file) {
        String originFileName = img_file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        String savedImgUrl = IMG_SAVE_LOCATION + uuid + originFileName;

        try {
//            파일을 서버에 저장했다.
            img_file.transferTo(new File(savedImgUrl));
//            파일 테이블에 저장된 파일 경로를 담아야한다.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedImgUrl;
    }

    @Override
    public Resource loadImgFile(String fileName) throws Exception {
        try {
            return new UrlResource(String.format("file:%s%s", IMG_SAVE_LOCATION, fileName));
        } catch (MalformedURLException e) {
            log.error("fail to save file : {}", fileName);
            throw new RuntimeException(e);
        }
    }
}

