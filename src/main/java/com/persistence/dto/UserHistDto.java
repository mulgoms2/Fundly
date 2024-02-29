
package com.persistence.dto;

import lombok.*;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Setter
//@Getter
@Builder
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserHistDto {
    // 회원 이력 코드는 ? 20240225
    private String user_hist_id; // 회원이력코드
    private Integer seq; // ai
    private String user_id; // 회원아이디 (user_email)
    @Nullable
    private String user_status; // 회원상태
    private String change_date; // 변경일시
    private String adr; // 주소
    private String zip_code; // 우편번호
    private String adr_detail; // 상세주소
    private String phone_no; // 휴대폰번호
    private String name; // 이름
    private String email; //이메일
    private String pwd_mod_yn; // 비밀번호변경여부
    private String comments; // 비고
    private String dba_reg_dtm; // 최초등록일시
    private String dba_reg_id; // 최초등록자식별번호
    private String dba_mod_dtm; // 최종수정일시
    private String dba_mod_id; // 최종수정자식별번호
}

