package com.fundly.chat.repository;

import com.persistence.dto.FileDto;

import java.io.File;

public interface FileRepository {
    //    파일 Url 을 저장한다.
    void saveFile(FileDto fileDto);
}
