package com.fundly.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDto {
    private String user_id;
    private String pj_id;
    private int curr_pj_like_cnt;
}
