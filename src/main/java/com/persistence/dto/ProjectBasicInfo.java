package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectBasicInfo {
    private String pj_id;
    private String sel_name;
    private String ctg;
    private String sub_ctg;
    private String pj_short_title;
    private String pj_long_title;
    private List<String> tags;
}
