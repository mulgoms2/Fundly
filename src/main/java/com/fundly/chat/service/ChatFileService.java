package com.fundly.chat.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ChatFileService {
//    save imgFile and return saved Url
    String IMG_SAVE_LOCATION = "/Users/dobigulbi/chat/file/";
    String saveImageFile(MultipartFile img_file);

    Resource loadImgFile(String fileName) throws Exception;
}
