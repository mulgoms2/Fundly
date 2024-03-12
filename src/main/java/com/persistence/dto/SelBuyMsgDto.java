package com.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelBuyMsgDto {
    @NotNull
    private Long room_num;
    private List<SelBuyMsgDetailsDto> message_list;
    private String user_id;
    private String pj_id;
//    private LocalDateTime dba_reg_dtm;
//    private String dba_reg_id;
//    private LocalDateTime dba_mod_dtm;
//    private String dba_mod_id;
}
