package com.fundly.chat.service;

import org.springframework.web.multipart.MultipartFile;

public interface ChatFileService {
//    save imgFile and return saved Url
    String saveImageFile(MultipartFile img_file);

}
