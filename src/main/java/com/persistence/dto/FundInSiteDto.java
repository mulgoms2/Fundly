package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FundInSiteDto {
    private int fund_in_site_seq;
    private String fund_in_site_title;
    private String fund_in_site_cont;
    private int fund_in_site_view_cnt;
    private String hid_yn;
    private Date reg_dtm;
    private String reg_id;
    private int file_cnt;
}
