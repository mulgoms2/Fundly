package com.fundly.project.controller;

import com.persistence.dto.GiftDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
public class GiftValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GiftDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GiftDto giftDto = (GiftDto) target;


    }
}
