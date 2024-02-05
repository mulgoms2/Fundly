package com.persistence.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
public class AdminDto {
    private String admin_id;
    private String admin_name;
    private String admin_pwd;
    private String admin_phone_no;
    private Date reg_dtm;
    private String reg_id;
}
