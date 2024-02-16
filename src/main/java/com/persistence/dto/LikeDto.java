package com.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

// TODO : @Builder 후 LikeRequest 생성하기
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Data
@RequiredArgsConstructor
public class LikeDto {

    @NonNull private String user_id;
    @NonNull private String pj_id;
    private int like_status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @NonNull private LocalDateTime like_dtm;

    private Date dba_reg_dtm;
    private String dba_reg_id;
    private Date dba_mod_dtm;
    private String dba_mod_id;
}
