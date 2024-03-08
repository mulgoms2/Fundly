package com.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class LikeDto {

    private String user_id;
    private String pj_id;
    private int like_status;
    private String like_dtm;

    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_reg_dtm;
    private String dba_reg_id;
    @EqualsAndHashCode.Exclude
    private LocalDateTime dba_mod_dtm;
    private String dba_mod_id;

    // 회원의 좋아요 목록을 불러올 때의 생성자
    public LikeDto(String user_id) {
        this.user_id = user_id;
    }
}
