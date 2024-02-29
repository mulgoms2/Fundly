package com.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelBuyMsgDetailsDto {
    @NotNull
    private Long room_num;
    private String msg_id;
    private String buy_id;
    private String pj_id;
    private Integer seq;
    private String send_user_id;
    private LocalDateTime svr_intime;
    private String svr_intime_string;
    private String msg_read_yn;
    private String msg_cont;
//    private LocalDateTime dba_reg_dtm;
//    private String dba_reg_id;
//    private LocalDateTime dba_mod_dtm;
//    private String dba_mod_id;
    private int file_cnt;
    private String file_url;
}
