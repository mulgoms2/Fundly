package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectAddResponse {
    private String pj_id;
    private String sel_id;
    private String sel_name;

    public ProjectBasicInfo toInfoDto() {
        return ProjectBasicInfo.builder().pj_id(pj_id).sel_name(sel_name).build();
    }
}
