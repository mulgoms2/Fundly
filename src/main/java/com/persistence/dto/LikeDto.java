package com.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

// TODO : @Builder 후 LikeRequest 생성하기
//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LikeDto {

    @NonNull private String user_id;
    @NonNull private String pj_id;
    private int like_status;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
//    private LocalDateTime like_dtm;
    private String like_dtm;
    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;

    // 회원의 좋아요 목록을 불러올 때의 생성자
    public LikeDto(@NonNull String user_id) {
        this.user_id = user_id;
    }
}
