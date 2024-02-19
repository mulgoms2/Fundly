package com.fundly.user.service;

import com.fundly.user.model.UserInfoDao;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoDao userInfoDao;

    public UserInfoServiceImpl(){}
    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao){
        this.userInfoDao = userInfoDao;
    }

    @Override
    public UserDto userInfo(UserDto userdto){
        try {
            UserDto userInfo = userInfoDao.userInfo(userdto);//, user_pwd);

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
