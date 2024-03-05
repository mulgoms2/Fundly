package com.fundly.user.service;

import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class MailSendServiceTest {

    @Autowired
    MailSendService mailSendService;

    @Test
    void makeRandomNumber() {
    }

    @Test
    void joinEmail() {
        try {
            mailSendService.joinEmail("initsave@gmail.com");
//            log.error(mailSendService.joinEmail("initsave@gmail.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void mailSend() {

//        mailSendService.mailSend();
    }
}