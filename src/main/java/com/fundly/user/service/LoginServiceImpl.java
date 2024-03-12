package com.fundly.user.service;

import com.fundly.user.dto.UserLoginDto;
import com.fundly.user.exception.UserJoinFailException;
import com.fundly.user.exception.UserLoinFailException;
import com.fundly.user.model.UserLoginDao;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final UserLoginDao userLoginDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginServiceImpl(UserLoginDao userLoginDao){
        this.userLoginDao = userLoginDao;
    }
    @Autowired
    public LoginServiceImpl(UserLoginDao userLoginDao,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userLoginDao = userLoginDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserLoginDto Login(UserLoginDto userLoginDto, HttpSession session, HttpServletResponse response) {

        /*
        * 1. 로그인성공,실패 값
        * */

        UserLoginDto userInfo = userLoginDao.selectUser(userLoginDto);
        
        if(userInfo==null){
            UserLoinFailException e = new UserLoinFailException("가입된 이메일 정보가 없습니다.");
            log.debug("userInfo==null : {}\n{}", e.getMessage(), e.getStackTrace());
            throw e;
        }

        String user_email = userInfo.getUser_email();
        String user_status = userInfo.getUser_status();

        /* 암호화 체크 */
        if(!bCryptPasswordEncoder.matches(userLoginDto.getUser_pwd(),userInfo.getUser_pwd())){
            UserLoinFailException e = new UserLoinFailException("비밀번호를 확인해 주세요.");
            log.debug("bCryptPasswordEncoder.matches(userLoginDto.getUser_pwd(),userInfo.getUser_pwd())) : {}\n{}", e.getMessage(), e.getStackTrace());
            throw e;
        }

        /** 예외처리에 따른 체계 다시 정립**/
        try {
            if(user_email!=null){
                /* 활동중이 아닌 경우 */
                if(!user_status.equals("A")){
                    UserLoinFailException e = new UserLoinFailException("회원상태가 사용할수 없는 상태입니다.");
                    log.debug("if(!user_status.equals(\"A\")) : {}\n{}", e.getMessage(), e.getStackTrace());
                    throw e;
                }

                session.setAttribute("user_email",user_email);
                // 세션의 유효시간 (30분)
                session.setMaxInactiveInterval(1800);

                /* cookie add */
                response.addCookie(setCookie("user_email",user_email,-1,"/"));

                return userInfo;
            }
            else {
                UserLoinFailException e = new UserLoinFailException("가입된 이메일 정보가 없습니다.");
                log.debug("if(user_email!=null) : {}\n{}", e.getMessage(), e.getStackTrace());
                throw e;

            }
        } catch (Exception e) {
            log.debug("Login : {}\n{}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
//            return null;
        }
    }

    // cookie key/value setting
    public Cookie setCookie(String cookieKey, String cookieValue, int maxAge, String path){
        log.info("coo = " + cookieKey + ", value = " + cookieValue + ", maxage = " + maxAge + ", path = " + path);
        Cookie cookie = new Cookie(cookieKey,cookieValue);
        cookie.setMaxAge(maxAge); // 쿠키를 삭제
        cookie.setPath(path);

        return cookie;
    }
}
