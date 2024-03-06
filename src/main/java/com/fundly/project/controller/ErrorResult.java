package com.fundly.project.controller;

import lombok.*;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ErrorResult {
    private List<ErrorDetail> errorDetails;

    @Builder
    public ErrorResult(Errors errors, MessageSource ms){
        this.errorDetails = errors.getFieldErrors().stream()
                .map(error -> ErrorDetail.builder()
                        .fieldError(error)
                        .messageSource(ms)
                        .build()
                ).collect(Collectors.toList());
    }
}
