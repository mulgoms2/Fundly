package com.fundly.project.util;

import com.fundly.project.exception.ImageSaveFailureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
public class FileUploader {
    static Path root = Paths.get(System.getProperty("user.name"));
    public static final String SAVE_LOCATION = "/Users/" + root + "/fundly/project/img/";
    public static final String REMOTE_URL = "/fundly/project/img/";

    public static String uploadFile(MultipartFile file) {
        String file_name = UUID.randomUUID() + file.getOriginalFilename();
        String tag_src_url = REMOTE_URL + file_name;

        try {
            file.transferTo(new File(SAVE_LOCATION + file_name));
        } catch (IOException e) {
            log.error("saveImgFIle(file) : {} \n{}", e.getCause(), e.getStackTrace());
            throw new ImageSaveFailureException("이미지를 저장하는데 실패하였습니다.");
        }
        return tag_src_url;
    }


}
