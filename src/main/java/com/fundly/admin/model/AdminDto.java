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
public class AdminDto {
    private String admin_id;
    private String admin_name;
    private String admin_pwd;
    private String admin_phone_no;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Date reg_dtm;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String reg_id;
}
