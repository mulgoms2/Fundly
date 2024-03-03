package com.fundly.user.service;

import com.fundly.user.dto.UserProfileDto;
import com.fundly.user.model.UserHistDao;
import com.persistence.dto.UserDto;
import com.persistence.dto.UserHistDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class UserHistServiceImpl implements UserHistService {
    private final UserHistDao userHistDao;

    @Autowired
    public UserHistServiceImpl(UserHistDao userHistDao){
        this.userHistDao = userHistDao;
    }

    @Override
    public int userHistinsert(UserProfileDto userProfileDto){
        try {

            String uuid = UUID.randomUUID().toString();
            /* 주소지에 대해서 ? ? ? ? ?*/

            UserHistDto userHistDto = UserHistDto.builder()
                    .user_hist_id(uuid)
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
//                    .dba_reg_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
//                    .dba_reg_id(userProfileDto.getUser_email())
                    .build();

            int cnt = userHistDao.insert(userHistDto);

            return cnt;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
