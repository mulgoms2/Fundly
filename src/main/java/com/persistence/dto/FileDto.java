package com.persistence.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileDto {
    private Integer seq;
    private MultipartFile file;
    private String table_name;
    private String origin_uri;
    private String saved_uri;
    private String table_key;
}
