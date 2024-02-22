package com.fundly.project.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import java.util.Locale;

@Getter
@ToString
public class ErrorDetail {
    private String objectName;
    private String field;
    private String errorCode;
    private String errorMessage;

    @Builder
    public ErrorDetail(FieldError fieldError, MessageSource messageSource, Locale locale){
        this.objectName = fieldError.getObjectName();
        this.field = fieldError.getField();
        this.errorCode = fieldError.getCode();
        this.errorMessage = messageSource.getMessage(fieldError, locale);
    }

}
