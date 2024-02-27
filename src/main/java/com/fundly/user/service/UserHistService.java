package com.fundly.user.service;

import com.fundly.user.dto.UserProfileDto;
import com.fundly.user.model.UserHistDao;
import com.fundly.user.model.UserProfileDao;
import com.persistence.dto.UserDto;
import com.persistence.dto.UserHistDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class UserHistService {
    private final UserHistDao userHistDao;

    @Autowired
    public UserHistService(UserHistDao userHistDao){
        this.userHistDao = userHistDao;
    }

    public Integer userHistinsert(UserProfileDto userProfileDto){
        try {


            /* 주소지에 대해서 ? ? ? ? ?*/

            UserHistDto userHistDto = UserHistDto.builder()
                    .user_hist_id("20240226_001")
                    .user_id(userProfileDto.getUser_email())
                    .user_status(userProfileDto.getUser_status())
                    .change_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
//                    .adr()
//                    .zip_code()
//                    .adr_detail()
                    .phone_no(userProfileDto.getUser_phone_no())
                    .name(userProfileDto.getUser_name())
                    .email(userProfileDto.getUser_email())
                    .pwd_mod_yn(!userProfileDto.getUser_pwd().equals(userProfileDto.getUser_prev_pwd()) ? "N" : "Y")
                    .comments("")
                    .dba_reg_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .dba_reg_id(userProfileDto.getUser_email())
                    .build();

            int cnt = userHistDao.insert(userHistDto);

            if(cnt == 1){
               return cnt;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
