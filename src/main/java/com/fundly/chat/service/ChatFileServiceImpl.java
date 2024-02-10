package com.fundly.chat.service;

import com.persistence.dto.FileDto;
import com.persistence.dto.domain.FileDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
@Slf4j
public class ChatFileServiceImpl implements ChatFileService {

    @Autowired
    FileDao fileDao;
    @Override
    public String saveImageFile(FileDto img_file) {
        String originFileName = img_file.getFile().getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        String savedImgUrl = IMG_SAVE_LOCATION + uuid + originFileName;

        try {
//            파일을 서버에 저장했다.
            img_file.getFile().transferTo(new File(savedImgUrl));
//            파일 테이블에 저장된 파일 경로를 담아야한다.
            img_file.setTable_name("Sel_Buy_Msg_Details");
            img_file.setSaved_uri(savedImgUrl);
            img_file.setOrigin_uri(IMG_SAVE_LOCATION + originFileName);

//            여기에 메시지의 키를 담아야 한다. 가능할까? 처음부터 전달되어왔어야 한다?
//            클라이언트에서 파일이 전달되어오는 순간에는 식별자를 알 수가 없다.
//            식별자는 어디에서 알 수 있지?
//            파일이 첨부된 이후에 메시지가 클라이언트로부터 발송되어올 때 생성된다.

            img_file.setTable_key();

//            파일 Dto에 해당 메시지의 식별자를 적어서 저장해야한다.
            fileDao.saveFile(img_file);
        } catch (IOException e) {
            log.error("error with saveImageFile on service = {}", e.getCause());
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("error with saveImageFile on service = {}", e.getMessage());
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

