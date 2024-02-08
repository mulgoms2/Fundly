package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TermDto {
   private int term_seq;
    private String term_title;
    private String term_cont;
    private String term_srt_date;
    private String term_end_date;
    private String term_status;
    private int  prev_term_no;
    private int term_view_cnt;
    private Date reg_dtm;
    private String reg_id;
    private Date mod_dtm;
    private String mod_id;
    private int file_cnt;
}
