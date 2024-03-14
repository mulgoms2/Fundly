package com.fundly.pay.exception;

import lombok.Getter;

@Getter
public class PayPortOneServerException extends RuntimeException {
    private String code;

    public PayPortOneServerException() {};

    public PayPortOneServerException(String code) {
        super(code + " Error");
        this.code = code;
    }
}
