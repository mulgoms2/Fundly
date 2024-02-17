package com.fundly.project.service;

import com.persistence.dto.GiftDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@Slf4j
@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
class GiftServiceImplTest {
    @Autowired
    GiftService giftService;

    GiftDto giftDto;

    @SneakyThrows
    @Test
    @DisplayName("선물 등록")
    void registerGift() {
        //테스트
        //프로젝트id = pj1인 프로젝트에서 선물 등록의 상황
        //해당 프로젝트의 아이템은 총 3가지 등록되어 있음(1pj1, 2pj1, 3pj1)

    }

    @SneakyThrows
    @Test
    @DisplayName("선물 조회")
    void getGift() {


    }
}