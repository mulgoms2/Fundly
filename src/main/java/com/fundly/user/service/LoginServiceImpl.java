package com.fundly.user.service;

import com.fundly.user.model.UserLoginDao;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private UserLoginDao userLoginDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginServiceImpl(){}
    public LoginServiceImpl(UserLoginDao userLoginDao){
        this.userLoginDao = userLoginDao;
    }
    @Autowired
    public LoginServiceImpl(UserLoginDao userLoginDao,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userLoginDao = userLoginDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto Login(UserDto userDto, HttpSession session) throws Exception {

        /*
        * 1. 로그인성공,실패 값
        * */

        try {
            UserDto userInfo = userLoginDao.selectUser(userDto);

            String user_email = userInfo.getUser_email();
            String user_status = userInfo.getUser_status();

            /* 암호화 체크 */
            if(!bCryptPasswordEncoder.matches(userDto.getUser_pwd(),userInfo.getUser_pwd())){
                throw new RuntimeException("비밀번호를 확인해 주세요.");
            }
            if(user_email!=null){
                /* 활동중이 아닌 경우 */
                if(!user_status.equals("A")){
                    return userInfo;
                }

                session.setAttribute("user_email",user_email);
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
