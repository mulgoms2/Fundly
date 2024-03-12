package com.persistence.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class EventDto {
    private Integer event_seq;
    private int event_sort;
    private String event_title;
    private String event_cont;
    private String event_str_yn;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime event_str_date;
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime event_end_date;
    private int event_view_cnt;
    private String hid_yn;
    private LocalDateTime reg_dtm;
    private String reg_id;
    private LocalDateTime mod_dtm;
    private String mod_id;
    private int file_cnt;
}
