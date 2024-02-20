package com.fundly.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public class GiftValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GiftForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.error("\n\n GiftValidator is called \n\n");
        GiftForm giftForm = (GiftForm) target;

        String gift_name = giftForm.getGift_name(); //0~50자 이하.
        String gift_qty_lim_yn = giftForm.getGift_qty_lim_yn(); //y또는 n이어야 함
        Integer gift_total_qty = giftForm.getGift_total_qty(); //y인 경우에만 입력값이 존재. 1000이하의 값
        Integer gift_max_qty_per_person = giftForm.getGift_max_qty_per_person();
        //y라면 total qty이하의 값을 가져야하고, n이라면 1000 이하의 값

        LocalDateTime gift_ship_due_date = giftForm.getGift_ship_due_date();
        LocalDateTime pj_pay_due_dtm = giftForm.getPj_pay_due_dtm();
        // 현재 날짜 기준최대 1825일

        String gift_ship_need_yn = giftForm.getGift_ship_need_yn();
        //y또는 n

        Integer gift_money = giftForm.getGift_money();
        //1000이상 10,000,000 이하


        //1.선물 이름 검증 (공백 혹은 빈값, null값 또는 글자수 초과 검증)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gift_name", "required");

        if(gift_name == null || gift_name.length() > 50){
            errors.rejectValue("gift_name","invalidLength",new String[]{"0","50"},null);
        }

        //2. 선착순 선물 유무
        if(!gift_qty_lim_yn.equals("y")&&!gift_qty_lim_yn.equals("n")){
            errors.rejectValue("gift_qty_lim_yn","invalidChoice", new String[]{"y","n"},null);
        }

        //3. 선물 최대 수량
        if(gift_qty_lim_yn.equals("y")){ //선착순 선물의 경우에만 체크(선착순==수량제한 있음)
            if(gift_total_qty > 1000){ //1000개 이하만 가능
                errors.rejectValue("gift_total_qty","invalidNumber", new String[]{"1000"},null);
            }
        }

        //4. 인당 선물 최대 선택 수량
        if(gift_qty_lim_yn.equals("y")){//선착순 선물의 경우
            if(gift_max_qty_per_person > gift_total_qty) { //인당 최대 선택 수량은 총 선물 수량을 넘을 수 없다.
                errors.rejectValue("gift_max_qty_per_person","invalidNumber", new String[]{""+gift_total_qty},null);
            }
        } else {
            if(gift_max_qty_per_person > 1000){//선착순이 아니어도, 인당 최대 선택수량은 1000개
                errors.rejectValue("gift_max_qty_per_person","invalidNumber", new String[]{"1000"}, null);
            }
        }

        //5. 선물 예상 전달일 ( **결제 최종 종료일 : 펀딩 마감일 + 7일)
        // 결제 최종 종료일(pj테이블에 저장된 값)로부터 1일~1825일 내로 입력해야함
        long days = ChronoUnit.DAYS.between(pj_pay_due_dtm, gift_ship_due_date);
        // 후원자 결제 종료일로부터 1일 이상 1825이하의 날짜로 정해야 유효한 값.
        if(days<1 || days>1825)
            errors.rejectValue("gift_ship_due_date", "invalidNumber", new String[]{"1","1825"},null);



        //6. 배송상품 유무 선택
        if(!gift_ship_need_yn.equals("y")&&!gift_ship_need_yn.equals("n")){
            errors.rejectValue("gift_ship_need_yn","invalidChoice",new String[]{"y","n"},null);
        }

        //7. 선물 금액(가격)
        if(gift_money <1000 || gift_money > 10000000) {
            errors.rejectValue("gift_money","invalidNumber", new String[]{"1000","10000000"},null);
        }














    }
}
