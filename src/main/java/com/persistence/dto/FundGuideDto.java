package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FundGuideDto {
    private Integer fund_guide_seq;
    private int fund_guide_sort;
    private String fund_guide_type;
    private String fund_guide_title;
    private String fund_guide_sub_title;
    private String fund_guide_cont;
    private int fund_guide_view_cnt;
    private String hid_yn;
    private Date reg_dtm;
    private String reg_id;
    private Date mod_dtm;
    private String mod_id;
    private int file_cnt;
}
