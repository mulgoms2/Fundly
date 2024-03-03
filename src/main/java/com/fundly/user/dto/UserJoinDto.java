
package com.fundly.user.dto;

import lombok.*;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.*;

@Setter
@Getter
@Builder
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDto {
    @Nullable
    private String user_id;

    @Email (message = "유효하지 않은 이메일 형식입니다.")
    @NotBlank (message = "이메일을 입력해주세요.")
    private String user_email;

    @NotBlank ( message = "이름을 입력해주세요.")
    @Size(min=2, max =50, message= "이름은 2자~50자 이내로 입력해주세요.")
    private String user_name;

//    @Pattern(regexp ="\\s+", message="공백이네 ")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,20}",message= "비밀번호는 영문, 숫자, 특수문자를 혼합하여 입력해주세요.")
    @Size(min=8, max=20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요.")
    @NotBlank (message = "비밀번호를 입력해주세요.")
//    @NotEmpty (message = "비밀번호는 공백 없이 입력해주세요.")
    private String user_pwd;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,20}",message= "비밀번호가 일치하지 않습니다.")
    @Size(min=8, max=20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요.")
    @NotBlank (message = "비밀번호를 입력해주세요.")
//    @NotEmpty (message = "비밀번호는 공백 없이 입력해주세요.")
    private String user_pwdConfirm;

    @Nullable
    private String user_join_date;

    @Nullable
    private String user_status; // 회원상태

    @NotNull
    private String site_term_agree_yn;  // 사이트 이용약관 동의 여부

    @NotNull
    private String p_Info_agree_yn;     // 개인정보제공 수집 동의 여부

    @NotNull
    private String age_agree_yn;        // 만 14세 이상 동의 여부

    private String p_info_oth_agree_yn; // 개인정보 제3자 제공 동의 여부

    private String m_info_rcv_agree_yn; // 마케팅정보수신 여부(선택)

//    @Nullable
//    private String dba_reg_dtm;
//
//    @Nullable
//    private String dba_reg_id;
}

