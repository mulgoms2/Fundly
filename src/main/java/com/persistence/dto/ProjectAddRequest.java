package com.persistence.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ProjectAddRequest {
//    private String user_name;
    private String user_email;

    public ProjectDto toProject() {
        String pj_id = String.valueOf(UUID.randomUUID());

        return ProjectDto.builder().pj_id(pj_id).pj_status("작성중").pj_sel_id(user_email).build();
    }
}
