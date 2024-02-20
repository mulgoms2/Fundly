package com.persistence.dao;

import com.persistence.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface FileDao {
    int saveFile(FileDto fileDto) throws Exception;
    ArrayList<FileDto> getFileList(@Param("table_name") String table_name, @Param("table_key") String table_key) throws Exception;
    FileDto getFile(@Param("table_name") String table_name, @Param("table_key") String table_key) throws Exception;
    String getSavedFileUri(@Param("table_name") String table_name, @Param("table_key") String table_key) throws Exception;

    int count();
    void deleteAll();
}
