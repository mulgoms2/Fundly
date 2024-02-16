package com.persistence.dto;

import com.fundly.chat.utils.ValidFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class FileDto {
    private Integer seq;
    @ValidFile(message = "유효하지 않은 파일입니다.")
    private MultipartFile file;
    private String table_name;
    private String file_origin_url;
    private String file_saved_url;
    private String table_key;
}