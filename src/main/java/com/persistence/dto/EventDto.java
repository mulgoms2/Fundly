package com.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EventDto {
    private int event_seq;
    private int event_sort;
    private String event_title;
    private String event_cont;
    private String event_str_yn;
    private String event_str_date;
    private String event_end_date;
    private int event_view_cnt;
    private String hid_yn;
    private Date reg_dtm;
    private String reg_id;
    private Date mod_dtm;
    private String mod_id;

    private int file_cnt;
}
