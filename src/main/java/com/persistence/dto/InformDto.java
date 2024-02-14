package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InformDto {
    private Integer inform_seq;
    private int inform_sort;
    private String inform_title;
    private String inform_cont;
    private int inform_view_cnt;
    private String hid_yn;
    private Date reg_dtm;
    private String reg_id;
    private int file_cnt;
    private Date mod_dtm;
    private String mod_id;
}
