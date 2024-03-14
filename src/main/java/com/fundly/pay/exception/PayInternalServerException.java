package com.fundly.pay.exception;

import lombok.Getter;

@Getter
public class PayInternalServerException extends RuntimeException {

    private String code;

    public PayInternalServerException() {};

    public PayInternalServerException(String code) {
        super(getErrMsg(code));
        this.code = code;
    }

    private static String getErrMsg(String code) {
        if (code.equals("PG_NOT_FOUND")) {
            return String.format("[%s] \"비인증결제모듈 사용정보를 찾을 수 없습니다.\"", code);
        } else {
            switch (code) {
                case "401":
                    return "[401 Unauthorized] 인증 토큰이 유효하지 않습니다.";
                case "404":
                    return "[404 Not Found] 포트원 요청 URL이 잘못되었습니다.";
                case "405":
                    return "[405 Method Not Allowed] 포트원 요청 메서드가 잘못되었습니다.";
                default:
                    return code + " Error";
            }
        }
    }
}
