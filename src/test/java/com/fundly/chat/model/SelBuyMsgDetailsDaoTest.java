package com.fundly.chat.model;

import com.persistence.dto.SelBuyMsgDetailsDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
@Transactional
class SelBuyMsgDetailsDaoTest {
    @Autowired
    SelBuyMsgDetailsDao selBuyMsgDetailsDao;

    @BeforeEach
    void clean() {
        selBuyMsgDetailsDao.deleteAll();
        assertEquals(0, selBuyMsgDetailsDao.count());
    }


    @SneakyThrows
    @Test
    @DisplayName("메시지 삽입")
    void insertMsg() {
        SelBuyMsgDetailsDto selBuyMsgDetailsDto = SelBuyMsgDetailsDto.builder().room_num(1L).msg_cont("hello how are you").buy_id("asdf").pj_id("123").send_user_id("asdf").build();

        for (int i = 0; i < 100; i++) {
            selBuyMsgDetailsDao.insertMsg(selBuyMsgDetailsDto);
        }
        assertEquals(100, selBuyMsgDetailsDao.count());
    }

    @SneakyThrows
    @Test
    @DisplayName("메시지 가져오기")
    void loadAllMessages() {
        SelBuyMsgDetailsDto selBuyMsgDetailsDto = SelBuyMsgDetailsDto.builder().room_num(1L).msg_cont("hello how are you").buy_id("asdf").pj_id("123").send_user_id("asdf").build();

        for (int i = 0; i < 100; i++) {
            selBuyMsgDetailsDao.insertMsg(selBuyMsgDetailsDto);
        }
        assertEquals(100, selBuyMsgDetailsDao.count());

        List<SelBuyMsgDetailsDto> selBuyMsgDetailsDtos = selBuyMsgDetailsDao.loadAllMessages(1L);

        assertEquals(100, selBuyMsgDetailsDtos.size());

        selBuyMsgDetailsDtos.forEach(System.out::println);
    }
}