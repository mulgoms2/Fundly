package com.fundly.user.model;

import com.persistence.dto.SelBuyMsgDetailsDto;
import com.persistence.dto.UserDto;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class UserJoinDaoTest {

    @Autowired
    UserJoinDao userJoinDao;



    @Test
    void count() throws Exception {

        int cnt = userJoinDao.count();
        assertTrue(cnt==3);

    }

    @Test
    void emailCheck() throws Exception {
        UserDto user = new UserDto();
        user.setUser_email("asdf@asdf.com");

        int cnt = userJoinDao.emailCheck(user);
        assertTrue(cnt==1);
    }

    @Test
    void insert() throws Exception {
        for(int i = 0; i <10; i++) {
            String uuid_user_id = UUID.randomUUID().toString();
            String user_status = "A"; // 활동중

            UserDto userDto = new UserDto();
            userDto.setUser_id(uuid_user_id);
            userDto.setUser_name("호랑이");
            userDto.setUser_pwd("1111");
            userDto.setUser_email("aaa" + i + "@abc.com");
            // ow.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));


//            userDto.setUser_join_date("2024-02-14 14:30:24.333");
            userDto.setSite_term_agree_yn("Y");
            userDto.setP_Info_agree_yn("Y");
            userDto.setM_info_rcv_agree_yn("Y");
            userDto.setAge_agree_yn("Y");

            userDto.setUser_status(user_status);
            userDto.setDba_reg_id(uuid_user_id);

            System.out.println("userDto = " + userDto);

            int cnt = userJoinDao.insert(userDto);
            System.out.println("cnt = " + cnt);
        }
    }
}