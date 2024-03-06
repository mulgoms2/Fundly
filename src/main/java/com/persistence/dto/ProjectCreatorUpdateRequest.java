package com.persistence.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class ProjectCreatorUpdateRequest {
    @NotEmpty
    private String pj_sel_name;
    private String pj_sel_short_intro;
//    private MultipartFile pj_prof_image;
//    private String pj_prof_image_url;
}
