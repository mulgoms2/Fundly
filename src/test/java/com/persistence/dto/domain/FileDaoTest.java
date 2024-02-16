package com.persistence.dto.domain;

import com.persistence.dto.FileDto;
import config.RootContext;
import config.ServletContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig(classes = {ServletContext.class, RootContext.class})
class FileDaoTest {
    @Autowired
    FileDao fileDao;
    @Test
    void saveFile() {

    }

    @Test
    void getFileList() {
    }

    @Test
    void getFile() {
    }

    @Test
    void getSavedFileUri() {
    }
}