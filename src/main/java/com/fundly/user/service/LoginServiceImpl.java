package com.fundly.user.service;

import com.fundly.user.model.UserLoginDao;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserLoginDao userLoginDao;

    @Override
    public int count() throws Exception {
        return userLoginDao.count();
    }

//    @Override
//    public int userCheck(String user_email, String user_pwd) throws Exception {
//        return userLoginDao.userCheck(user_email,user_pwd);
//    }

    @Override
    public UserDto Login(String user_email, String user_pwd, HttpSession session) throws Exception {

        /*
        * 1. 로그인성공,실패 값
        * */

        try {
            UserDto userInfo = userLoginDao.userInfo(user_email, user_pwd);

            String user_status = userInfo.getUser_status();

            if(userInfo!=null){

                String useremail = userInfo.getUser_email();

                if(!user_status.equals("A")){
                    return userInfo;
                }

                session.setAttribute("user_email",useremail);
                // 세션의 유효시간 (30분)
                session.setMaxInactiveInterval(1800);

                return userInfo;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
