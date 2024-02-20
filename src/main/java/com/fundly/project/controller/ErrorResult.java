package com.fundly.project.controller;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
