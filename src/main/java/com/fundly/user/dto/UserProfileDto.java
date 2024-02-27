
package com.fundly.user.dto;

import lombok.*;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private String user_email;

    @NotBlank ( message = "이름을 입력해주세요.")
    @Size(min=2, max =50, message= "이름은 2자~50자 이내로 입력해주세요.")
    private String user_name;

//    @Size(min=8, max=20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요.")
    @NotBlank (message = "비밀번호를 입력해주세요.")
    private String user_pwd;

    //기존 비밀번호
    private String user_prev_pwd;

    //소개
    private String user_intro;

    //연락처
    private String user_phone_no ;

//    // 카카오 계정 연동
//    private

    // 회원 탈퇴 E?
    @Nullable
    private String user_status; // 회원상태

    // 메시지 수신 여부
    private String msg_rcv_yn;

    // 프로젝트 업데이트알림여부
    private String pj_upd_ntf_yn;

    // 관심프로젝트 알림여부 프로젝트
    private String like_pj_ntf_yn;

    // 마케팅 알림여부
    private String m_ntf_yn;

// 수정일시 수정자
    private String dba_mod_dtm;
    private String dba_mod_id;
}

