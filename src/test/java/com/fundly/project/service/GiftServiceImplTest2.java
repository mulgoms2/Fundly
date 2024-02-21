package com.fundly.project.service;

import com.fundly.project.controller.GiftForm;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDateTime;


@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class GiftServiceImplTest2 {
    @Autowired
    GiftService giftService;


    @Test
    @DisplayName("선물 등록하기")
    @SneakyThrows
    void registerGift() {
        GiftForm giftForm = GiftForm.builder()
                .gift_id("1234")
                .gift_name("선물세트1")
                .gift_money(25000)
                .gift_qty_lim_yn("y")
                .gift_total_qty(1000)
                .gift_max_qty_per_person(10)
                .gift_ship_due_date(LocalDateTime.now())
                .pj_id("123")
                .item_id(new Integer[]{22,24})
                .item_qty(new Integer[]{3,2})
                .dba_reg_id("asdf")
                .build();

        int rowCnt = giftService.registerGift(giftForm);
        log.error("\n\n rowCnt={} \n\n", rowCnt);

    }


}