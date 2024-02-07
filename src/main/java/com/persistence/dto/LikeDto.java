package com.persistence.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class LikeDto {
    @NonNull
    private String user_id;
    @NonNull
    private String pj_id;
    @NonNull
    private int like_status;
    private Timestamp dba_reg_dtm;
    private String dba_reg_id;
    private Timestamp dba_mod_dtm;
    private String dba_mod_id;
}
