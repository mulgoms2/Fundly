package com.fundly.user.service;

import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.model.UserJoinDao;
import config.RootContext;
import config.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Slf4j
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class JoinServiceImplTest {

//    @Autowired
//    private  UserJoinDao userJoinDao;
//    @Autowired
//    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    UserJoinDao userJoinDao;

    //    @Autowired
    @InjectMocks
    private JoinService joinService;

    UserJoinDto userJoinDto;

    @BeforeEach
    @DisplayName("기본 데이터")
    void dataSetting() {
        userJoinDto = UserJoinDto.builder()
                                 .user_email("asdf@asdf.com")
                                 .user_pwd("1234")
                                 .user_name("호호호")
                                 .build();
    }


    @Test
//    @SneakyThrows
    @DisplayName("이메일 중복 확인")
    void emailcheck() {
        //이메일 체크
        try {
            log.info("userJoinDto  ==" + userJoinDto);

            UserJoinDto userInfo = UserJoinDto.builder()
                                              .user_email("asdf@asdf.com")
                                              .build();

            log.info("info == " + userInfo + "\n\n");

            log.error("11111111111111111111111111111111111111111111111111");
            given(userJoinDao.emailCheck(userInfo)).willReturn(1);
            log.error("2222222222222222222222222222222222222222222222222222222222222222222222222222222");
            when(userJoinDao.emailCheck(userInfo)).thenReturn(1);

            log.error("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            int cnt = joinService.emailCheck(userInfo);
            log.error("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

            assertEquals(1, cnt);

            log.error("cnt =============" + cnt);
            //then
            assertThat(cnt).isEqualTo(1);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        UserJoinDto userJoinDto = UserJoinDto.builder()
//                .user_pwd("1234")
////                .user_email("a12345@test.com")
//                .user_email("ab33c@test.com")
//                .build();
//        try {
//            int cnt = joinService.emailCheck(userJoinDto);
//            assertEquals(0,cnt);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @Test
    @DisplayName("회원가입 service 체크")
//    @SneakyThrows
    @Transactional
//    @Rollback
    void insert() {
        UserJoinDto userJoinDto = UserJoinDto.builder()
                                             .user_id("a12345@test.com")
                                             .user_pwd("qwerrr123!")
                                             .user_name("테스터")
                                             .user_email("a123")
                                             .user_join_date(LocalDateTime.now()
                                                                          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                             .site_term_agree_yn("Y")
                                             .p_Info_agree_yn("Y")
                                             .age_agree_yn("Y")
                                             .p_info_oth_agree_yn("Y")
                                             .m_info_rcv_agree_yn("Y")
                                             .user_status("A")
//                .dba_reg_id("a12345@test.com")
                                             .build();

        try {
            int cnt = joinService.userJoin(userJoinDto);
            assertEquals(1, cnt);
            System.out.println("cnt = " + cnt);

            userJoinDto.setUser_email("a@test.com");
            int cnt2 = joinService.emailCheck(userJoinDto);
            assertEquals(0, cnt2);
            System.out.println("cnt2 = " + cnt2);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}