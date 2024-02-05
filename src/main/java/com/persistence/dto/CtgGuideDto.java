package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CtgGuideDto {
    private int ctg_guide_seq;
    private String ctg_guide_title;
    private String ctg_guide_cont;
    private int ctg_guide_view_cnt;
    private String hid_yn;
    private Date reg_dtm;
    private String reg_id;
    private int file_cnt;
}
