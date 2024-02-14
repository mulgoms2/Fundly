package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubHelpDto {
   private Integer sub_help_seq;
    private int sub_help_sort;
    private String sub_help;
    private String sub_help_title;
    private String sub_help_cont;
    private int sub_help_view_cnt;
    private String hid_yn;
    private Date reg_dtm;
    private String reg_id;
    private Date mod_dtm;
    private String mod_id;
    private int file_cnt;
}
