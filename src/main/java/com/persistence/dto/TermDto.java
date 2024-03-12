package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class TermDto {
   private Integer term_seq;
    private String term_title;
    private String term_cont;
 @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime term_srt_date;
    private LocalDateTime term_end_date;
    private String term_status;
    private int  prev_term_no;
    private int term_view_cnt;
    private Date reg_dtm;
    private String reg_id;
    private Date mod_dtm;
    private String mod_id;
    private int file_cnt;
    private int next;
    private int prev;
}
