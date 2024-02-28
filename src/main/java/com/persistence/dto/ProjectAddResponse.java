package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectAddResponse {
    private String pj_id;
    private String sel_id;
}
