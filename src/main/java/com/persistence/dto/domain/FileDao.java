package com.persistence.dto.domain;

import com.persistence.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface FileDao {
    int saveFile(FileDto fileDto) throws Exception;

    ArrayList<FileDto> getFile(String table_key) throws Exception;
}
