package com.fundly.user.service;

import com.fundly.user.dto.UserProfileDto;
import com.fundly.user.model.UserHistDao;
import com.fundly.user.model.UserInfoDao;
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
public class UserProfileService {
    private final UserProfileDao userProfileDao;

    @Autowired
    public UserProfileService(UserProfileDao userProfileDao ){
        this.userProfileDao = userProfileDao;
    }

    public UserProfileDto userProfileInfo(UserDto userDto){
        try {
            UserProfileDto userProfileInfo = userProfileDao.userProfileinfo(userDto);

            log.info("userProfileInfo = \n" + userProfileInfo + "\n");

            if(userProfileInfo != null){
                return userProfileInfo;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserProfileDto userUpdate(UserProfileDto userProfileDto){
        try {

            userProfileDto.setDba_mod_dtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            log.info("userProfileDto = \n" + userProfileDto + "\n");

            UserProfileDto userProfileInfo = userProfileDao.update(userProfileDto);

            if(userProfileInfo != null){
                return userProfileInfo;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
