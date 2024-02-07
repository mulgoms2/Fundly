package com.fundly.user.service;

import com.fundly.user.model.UserLoginDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    @Autowired
    UserLoginDao userLoginDao;

    public int emailCheck(String user_email) throws Exception {
        return userLoginDao.emailCheck(user_email);
    }

    public int count() throws Exception {
        return userLoginDao.count();
    }
}
