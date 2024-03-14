package com.fundly.pay.exception;

import lombok.Getter;

@Getter
public class CardInputInvalidException extends RuntimeException {
    private String code;

    public CardInputInvalidException() {};

    public CardInputInvalidException(String code) {
        super(code + " is invalid.");
        this.code = code;
    }
}

//if (fieldName.equals("pg")) {
//        return "지원하지 않는 카드사입니다. 다른 카드를 등록해주세요.";
//        } else if (fieldName.equals("pwd") || fieldName.equals("cardNumber")) {
//        return fieldName + "가 유효하지 않습니다.";
//        } else {
//        return fieldName + "이 유효하지 않습니다.";
//        }