package com.fundly.user.service;

import com.fundly.user.dto.OauthDto;
import com.fundly.user.model.OauthDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class OauthJoinServiceImpl implements OauthJoinService {

    private final OauthDao oauthDao;

    @Autowired
    public OauthJoinServiceImpl(OauthDao oauthDao) {this.oauthDao = oauthDao;}

    @Override
    public int check(OauthDto oauthDto) throws Exception {
        return oauthDao.check(oauthDto);
    }

    @Override
    public OauthDto OauthInfo(OauthDto oauthDto) throws Exception {
        return oauthDao.OauthInfo(oauthDto);
    }

//    @Transactional(rollbackFor = SQLException.class)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userOauthJoin(OauthDto oauthDto, HttpSession session, HttpServletResponse response) throws Exception {

        int cnt = 0;
        try{

            if(oauthDao.check(oauthDto)==1){
//                throw new RuntimeException("이미 가입된 사용자입니다.");
                throw new RuntimeException("JOIN_DUP_ERROR");
            }
//            userJoinDto.setDba_reg_id(userJoinDto.getUser_email());
//            log.error("값은 뭘까 ? " + oauthDto);

            cnt =oauthDao.insert(oauthDto);

            if(cnt == 1){
                /*kakao accesstoken*/
                session.setAttribute("kat",oauthDto.getAccess_token());
                // 세션의 유효시간 (30분)
                session.setMaxInactiveInterval(1800);

                /* cookie add */
                response.addCookie(setCookie("kat",oauthDto.getAccess_token(),-1,"/"));
                response.addCookie(setCookie("user_name",oauthDto.getUser_name(),-1,"/"));
            }

            return cnt;

        }catch (Exception e){
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userOauthUpdate(OauthDto oauthDto, HttpSession session, HttpServletResponse response) throws Exception{

        int cnt = 0;

        try{

            cnt = oauthDao.update(oauthDto);

            if(cnt == 1){
                /*kakao accesstoken*/
                session.setAttribute("kat",oauthDto.getAccess_token());
                // 세션의 유효시간 (30분)
                session.setMaxInactiveInterval(1800);

                /* cookie add */
                response.addCookie(setCookie("kat",oauthDto.getAccess_token(),-1,"/"));
                response.addCookie(setCookie("user_name",oauthDto.getUser_name(),-1,"/"));
                response.addCookie(setCookie("user_email",oauthDto.getUser_email(),-1,"/"));
            }

            return cnt;

        }catch (Exception e){
            log.info(e.getMessage());
            throw new RuntimeException(e);
            /* 어떤 에러가 일어 날 수 있는가 .. ?*/
        }
    }

    // cookie key/value setting
    @Override
    public Cookie setCookie(String cookieKey, String cookieValue, int maxAge, String path){
        Cookie cookie = new Cookie(cookieKey,cookieValue);
        cookie.setMaxAge(maxAge); // 쿠키를 삭제
        cookie.setPath(path);

        return cookie;
    }
}
