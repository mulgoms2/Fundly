package com.fundly.user.model;

import com.fundly.user.dto.OauthDto;
import config.RootContext;
import config.ServletContext;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@Builder
@SpringJUnitWebConfig({RootContext.class, ServletContext.class})
class OauthDaoTest {

    private OauthDao oauthDao;

    @Autowired
    public OauthDaoTest(OauthDao oauthDao){this.oauthDao = oauthDao;}

    @Test
//    @SneakyThrows
    @DisplayName("OAUTH 정보 불러오기")
    void oauthInfo() {

        OauthDto oauthDto = OauthDto.builder()
                .snsId("3355810633")
                .user_email("initsave@gmail.com")
                .refresh_token("okhvvd7dcKzx5YNOT_Ui9iZFuqK57H6997sKKiUOAAABjc339OzMISgqRbFCUQ")
                .access_token("IW7xV_FR-5-5CcgEvrcT2ExrBpWKrHCf4kIKKiUOAAABjc339PDMISgqRbFCUQ")
                .user_name("전임경")
                .build();

        try {
            OauthDto value = oauthDao.OauthInfo(oauthDto);

            assertEquals(oauthDto.getSnsId(),value.getSnsId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
//    @SneakyThrows
    @DisplayName("OAUTH 정보 저장하기")
    void insert() {
    }
}