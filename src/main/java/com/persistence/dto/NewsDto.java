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
    @NotBlank(message = "제목을 입력해주세요")
    private String news_title;
    @NotBlank(message = "내용을 입력해주세요")
    private String news_cont;
    @ToString.Exclude
    private int news_view_cnt;
    @ToString.Exclude
    private String hid_yn;
    @ToString.Exclude
    private Date reg_dtm;
    private String reg_id;
    @ToString.Exclude
    private int file_cnt;
    private Date mod_dtm;
    private String mod_id;
}
