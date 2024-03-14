package com.fundly.pay.exception;

import lombok.Getter;

@Getter
public class CardInputNotFoundException extends RuntimeException {
    private String code;

    public CardInputNotFoundException() {};

    public CardInputNotFoundException(String code) {
        super(code);
        this.code = code;
    }
}

//if (fieldName.equals("pwd") || fieldName.equals("cardNumber")) {
//        return fieldName + "를 입력하셔야 합니다.";
//        } else {
//        return fieldName + "을 입력하셔야 합니다.";
//        }