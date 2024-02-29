package com.persistence.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ProjectAddRequest {
    private String user_name;
    private String user_id;

    public ProjectDto toProject() {
        String pj_id = String.valueOf(UUID.randomUUID());

        return ProjectDto.builder().pj_id(pj_id).pj_sel_name(user_name).pj_sel_id(user_id).build();
    }
}
