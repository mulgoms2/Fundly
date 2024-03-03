package com.fundly.user.service;

import com.fundly.user.dto.UserProfileDto;
import com.fundly.user.model.UserInfoDao;
import com.fundly.user.model.UserProfileDao;
import com.persistence.dao.FileDao;
import com.persistence.dto.FileDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileDao userProfileDao;
//    private final FileDao fileDao;

    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao){
        this.userProfileDao = userProfileDao;
    }

    @Override
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

    @Override
    public UserProfileDto userUpdate(UserProfileDto userProfileDto){
        try {
//            log.info("userProfileDto = \n" + userProfileDto + "\n");
            int cnt = userProfileDao.update(userProfileDto);

            if(cnt!=1){
                log.error("에러다!!!!!!!!");
            }

//            log.info("cnt = \n" + cnt + "\n");

            return userProfileDao.userProfileinfo(UserDto.builder().user_email(userProfileDto.getUser_email()).build());

        } catch (Exception e) {
            log.error("error == " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveImg(FileDto fileDto, String user_email, HttpServletResponse response){

        Objects.requireNonNull(fileDto,"id empty file");
        fileDto.setTable_name("user_info");

        try {
            fileDto.setTable_key(user_email);
            /* 프로필 정보가 있으면 update
            * 프로필 정보가 없으면 insert
            * */
            userProfileDao.saveFile(fileDto);

        } catch (Exception e) {
            log.error("error ChatServiceImpl.saveImageFile()");
            throw new RuntimeException(e);
        }
    }

    public String getUserProfileImg(FileDto fileDto) throws Exception {
        return userProfileDao.getUserProfileImg(fileDto);
    }
}
