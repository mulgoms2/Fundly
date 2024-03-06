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
import java.util.Objects;
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

            log.error("userProfileDto.getUser_pwd()"  + userProfileDto.getUser_pwd());
            log.error("userProfileDto.getUser_prev_pwd()"  + userProfileDto.getUser_prev_pwd());

            UserHistDto userHistDto = UserHistDto.builder()
                    .user_hist_id(uuid)
                    .user_id(userProfileDto.getUser_email())
                    .user_status(userProfileDto.getUser_status())
                    .change_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    /* 주소지에 대해서 ? ? ? ? ?*/
//                    .adr()
//                    .zip_code()
//                    .adr_detail()
                    .phone_no(userProfileDto.getUser_phone_no())
                    .name(userProfileDto.getUser_name())
                    .email(userProfileDto.getUser_email())
                    .pwd_mod_yn(Objects.equals(userProfileDto.getUser_pwd(), userProfileDto.getUser_prev_pwd()) ? "N" : "Y")
//                    .pwd_mod_yn("N")
                    .comments("")
//                    .dba_reg_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
//                    .dba_reg_id(userProfileDto.getUser_email())
                    .build();

                    log.error("histdTO ㄱ밧은 ? " +userHistDto);

            return userHistDao.insert(userHistDto);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
