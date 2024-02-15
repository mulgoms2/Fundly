package com.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

// TODO : @Builder 후 LikeRequest 생성하기
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LikeDto {

    @NonNull
    private String user_id;
    @NonNull
    private String pj_id;
    private int like_status;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime like_dtm;
//    @NonNull
//    private String like_dtm;
//    private String pj_status;
//    private String fund_end_dtm;
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;

}
