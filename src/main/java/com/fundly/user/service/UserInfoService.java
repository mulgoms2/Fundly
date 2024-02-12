package com.fundly.user.service;

import com.fundly.user.model.UserInfoDao;
import com.persistence.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserDto userInfo(String user_email){//, String user_pwd){

        try {
            UserDto userInfo = userInfoDao.userInfo(user_email);//, user_pwd);

            if(userInfo != null){
                return userInfo;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
