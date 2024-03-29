package com.persistence.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewsDto {

    private Integer news_seq;
    private int news_sort;
    @NotBlank(message = "titleblank")
    private String news_title;
    @NotBlank(message = "contentblank")
    private String news_cont;
    @ToString.Exclude
    private int news_view_cnt;
    @ToString.Exclude
    private String hid_yn;
    @ToString.Exclude
    private String reg_dtm;
    private String reg_id;
    @ToString.Exclude
    private int file_cnt;

    private String mod_dtm;
    private String mod_id;
}
