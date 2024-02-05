package com.fundly.admin.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewsDto {
    private int news_seq;
    private int news_sort;
    private String news_title;
    private String news_cont;
    @EqualsAndHashCode.Exclude
    private int news_view_cnt;
    @EqualsAndHashCode.Exclude
    private String hid_yn;
    @EqualsAndHashCode.Exclude
    private Date reg_dtm;
    private String reg_id;
    @EqualsAndHashCode.Exclude
    private int file_cnt;
}
