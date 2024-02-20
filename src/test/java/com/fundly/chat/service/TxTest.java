package com.fundly.chat.service;

import com.persistence.dto.ChatRequest;
import com.persistence.dto.FileDto;
import com.persistence.dto.SelBuyMsgDetailsDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j

@SpringJUnitWebConfig(classes = {RootContext.class, ServletContext.class})
public class TxTest {
//    @Autowired
//    SelBuyMsgDao selBuyMsgDao;
//    @Autowired
//    SelBuyMsgDetailsDao selBuyMsgDetailsDao;
//    @Autowired
//    ChatService chatService;

//    @SpringJunitWebConfig에 주어진 클래스들을 조합해서 ac를 생성했다.
    @Autowired
    ApplicationContext ac;

    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(value = false)
    public void tx() {
        ChatRequest request = ChatRequest.builder().build();
        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().room_num(100L).msg_cont("hello").build();
//        selBuyMsgDao.makeChatRoom(request);
//        selBuyMsgDetailsDao.insertMsg(message);
    }

    //    케이스1 @EnableTransactionManager 없이 테스트 코드에서 자동롤백이 일어나는지 확인
    @Test
    @DisplayName("트랜잭션 매니저 없이 롤백확인")
    @Transactional
    @Rollback(value = false)
    void test() throws Exception {
        ChatRequest request = ChatRequest.builder().buy_id("aa").pj_id("aa").build();

//        selBuyMsgDao.makeChatRoom(request);
    }

//    케이스2, 트랜잭션 설정되어있는 메서드 호출하기
    @Test
    void
    test2() {
        FileDto file = FileDto.builder().file_origin_url("myfile.png").file_saved_url("/hello").build();
        SelBuyMsgDetailsDto message = SelBuyMsgDetailsDto.builder().msg_cont("my message").room_num(1L).buy_id("aa").pj_id("aa").build();

//        chatService.saveFileMessage(file, message);
    }

    @Test
    void test3() {
//        ApplicationContext rootAc = new AnnotationConfigApplicationContext(RootContext.class);
//        ApplicationContext servletAc = new AnnotationConfigApplicationContext(ServletContext.class);

//        System.out.println(Arrays.toString(rootAc.getBeanDefinitionNames()));

        System.out.println("\n\n\n");

//        System.out.println(Arrays.toString(servletAc.getBeanDefinitionNames()));
        System.out.println("\n\n\n");

        System.out.println(Arrays.toString(ac.getBeanDefinitionNames()));
        System.out.println("\n\n\n");
    }
}
