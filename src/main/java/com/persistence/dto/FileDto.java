package com.persistence.dto;

import com.fundly.chat.validate.ValidFile;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class FileDto {
    private Integer seq;
    @ValidFile(message = "유효하지 않은 파일입니다.")
    private MultipartFile file;
//    여기다가 본인 테이블 적는다
    private String table_name;
    private String file_origin_url;
    private String file_saved_url;
    private String table_key;
}