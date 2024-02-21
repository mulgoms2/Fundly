
package com.fundly.user.dto;

import lombok.*;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    @Email (message = "유효하지 않은 이메일 형식입니다.")
    @NotBlank (message = "이메일을 입력해주세요.")
    private String user_email;

    private String user_name;

//    @Size(min=8, max=20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요.")
    @NotBlank (message = "비밀번호를 입력해주세요.")
    private String user_pwd;

    @Nullable
    private String user_status; // 회원상태
}

