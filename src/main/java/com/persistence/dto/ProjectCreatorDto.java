package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectCreatorDto {
    private String pj_sel_name;
    private String pj_sel_short_intro;
    private String pj_prof_image_url;
}

