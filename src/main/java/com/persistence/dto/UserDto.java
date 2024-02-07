
package com.persistence.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    @NonNull
    private String user_id;
    @NonNull
    private String user_name;
    @NonNull
    private String user_pwd;
    private String user_prev_pwd;
    private String user_home_no;
    private String user_email;
    private String pay_means;
    private String user_join_date;
    private String user_status; // 회원상태
    private String user_intro;
    private String sel_intro;
    private String msg_rcv_yn;
    private String pj_udt_ntf_yn;
    private String like_pj_ntf_yn;
    private String m_ntf_yn;
    private String iv_yn;
    private String business_license;
    private String site_term_agree_yn;  // 사이트 이용약관 동의 여부
    private String p_Info_agree_yn;     // 개인정보제공 수집 동의 여부
    private String age_agree_yn;        // 만 14세 이상 동의 여부
    private String p_info_oth_agree_yn; // 개인정보 제3자 제공 동의 여부
    private String m_info_rcv_agree_yn; // 마케팅정보수신 여부(선택)
    @EqualsAndHashCode.Exclude
    private String dba_reg_dtm;
    private String dba_reg_id;
    @EqualsAndHashCode.Exclude
    private Date dba_mod_dtm;
    private String dba_mod_id;
}

