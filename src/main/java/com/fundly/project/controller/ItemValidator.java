package com.fundly.project.controller;


import com.persistence.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Slf4j
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        log.error("isAssignable?={}",ItemDto.class.isAssignableFrom(clazz));
        log.error("clazz={}",clazz);
        return ItemDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.error("\n\n ItemValidator is called \n\n");
        ItemDto itemDto = (ItemDto)target;

        String item_name = itemDto.getItem_name();
        String item_option_type = itemDto.getItem_option_type();
        String item_option = itemDto.getItem_option();

        //아이템 이름 검증
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "item_name","required");

        if(item_name == null || item_name.length() > 50){
            errors.rejectValue("item_name","invalidLength",new String[]{"0","50"},null);
        }

        //객관식, 주관식 옵션 공통 검증
        if(item_option_type!="옵션 없음"){ //객관식 또는 주관식 옵션의 경우 옵션 내용을 필수 입력해야함.
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "item_option", "required");
        }

        //todo view단에서 객관식 옵션에 seperator(,)는 입력될 수 없게 막는 조치를 추가하기.
        //객관식 옵션 검증
        if(item_option_type.equals("객관식 옵션")){
            String[] optArr = item_option.split(",");
            log.error("optArr={}", Arrays.toString(optArr));
            if(optArr.length<3) // 최소 2개 이상을 입력해야하는 조건
                errors.rejectValue("item_option","insufficient",new String[]{"3"},null);
            for(int i=0; i<optArr.length; i++){ //각 옵션의 길이는 100자를 넘지 않는다.
                if(optArr[i]==null || optArr[i].length() > 100){
                    errors.rejectValue("item_option","invalidLength",new String[]{"1","100"},null);
                }
            }
        }

        //주관식 옵션 검증
        if(item_option_type.equals("주관식 옵션")){ //각 옵션의 길이는 100자를 넘지 않는다.
            if(item_option == null || item_option.length() > 3){
                errors.rejectValue("item_option","invalidLength",new String[]{"1","100"},null);
            }
        }

    }
}
