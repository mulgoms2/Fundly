package com.fundly.user.service;

import com.fundly.user.model.UserJoinDao;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.UUID;


@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class JoinServiceImplTest {

    @Autowired
    UserJoinDao userJoinDao;

    @Autowired
    JoinService joinService;
//    JoinServiceImpl joinService;
    private String uuid_user_id;


    @Test
    @DisplayName("db select Test")
    @SneakyThrows
    void count() throws Exception {

        log.error("count() = " + joinService.count());

    }

    @Test
    @DisplayName("db insert Test")
    @SneakyThrows
    void insert()   {
        uuid_user_id = UUID.randomUUID().toString();
        String user_status = "A"; // 활동중 (임의의 회원상태 코드)

        UserDto userDto = new UserDto();
        userDto.setUser_id(uuid_user_id);
        userDto.setUser_pwd("1111"); // 임시 하드코딩
        userDto.getUser_name();
        userDto.getUser_email();
        userDto.setUser_status(user_status);
        userDto.setDba_reg_id(uuid_user_id);

        try{
            joinService.userJoin(userDto);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
//        for(int i = 30; i <33; i++) {
//            String uuid_user_id = UUID.randomUUID().toString();
//            String user_status = "A"; // 활동중
//
//            UserDto userDto = new UserDto();
//            userDto.setUser_id(uuid_user_id);
//            userDto.setUser_pwd("1111");
//            userDto.setUser_name("호랑이");
//            userDto.setUser_email("aaa" + i + "@abc.com");
//            userDto.setUser_status(user_status);
//            userDto.setDba_reg_id(uuid_user_id);
//
//            System.out.println("userDto = " + userDto);
//
//            int cnt = userJoinDao.insert(userDto);
//            System.out.println("cnt = " + cnt);
//        }
    }
}