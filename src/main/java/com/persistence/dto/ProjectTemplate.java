package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectTemplate {
    private String thumb_nail_img;
    private String category;
    private String sel_name;
    private String short_title;
}
