package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectTemplate {
    private String pj_id;
    private String thumbnail_img_url;
    private String sel_name;
    private String category;
    private String long_title;
    private String funding_percentage;
}
