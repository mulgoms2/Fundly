package com.fundly.user.controller;

import com.fundly.user.dto.OauthDto;
import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.service.JoinService;
import com.fundly.user.service.KakaoService;
import com.fundly.user.service.LoginService;
import com.fundly.user.service.OauthJoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/oauth")
@PropertySource(value ="/WEB-INF/config/Oauth.properties")
public class OauthLoginController {

    @Value("${kakaoRestAPI_Key}")
    private String kakaoRestAPI_Key;
    @Value("${kakaoRedirect_uri}")
    private String kakaoRedirect_uri;

    private final KakaoService kakaoService;
    private final OauthJoinService oauthJoinService;
    private final JoinService joinService;

    @Autowired
    public OauthLoginController (KakaoService kakaoService, JoinService joinService,OauthJoinService oauthJoinService){
        this.kakaoService = kakaoService;
        this.joinService = joinService;
        this.oauthJoinService = oauthJoinService;
    }

    @GetMapping("/logout")
    public String logout (HttpSession session,HttpServletRequest request ) throws Exception {

        String access_token = getCookieValue(request,"kat");
        String user_email = getCookieValue(request,"user_email");

        log.info("access_token = " +access_token + " \n\n");
        log.info("user_email = " +user_email + " \n\n");

        try {
//            Map<String, Object>  expires_in = kakaoService.getTokenTime(access_token);//("xr6YurVklja1riJkUyg_CB_spKvJSHp2irYKPXVbAAABjdoxWzCi-KZYUq23DA");
            Map<String, Object>  expires_in = null;
            try {
                expires_in = kakaoService.getTokenTime(access_token);
            } catch (IOException e) {
                if(e.getMessage()=="401"){
                    log.error("401 에러, 해당 토큰의 값이 없습니다.");
                }
            }

            log.info(expires_in.get("expires_in").toString());
            int tokenTime = Integer.parseInt(expires_in.get("expires_in").toString());

            // 연결 끊기 (원래는 로그아웃만 하면 된다)
            if(tokenTime >=0){
                log.info("연결 끊기 시작한다.");
//               String test = kakaoService.unlink(access_token);
                /* 로그 아웃
                *  oauth 에서 로그 아웃시 테이블의 acc,ref 값은 어떻게 처리할 것인가 ?
                * */
               String test = kakaoService.logout(access_token);

               log.info("test =  " + test);
                log.info("연결 끊기 종료한다.");
            }else{

                log.info("시간이 적거나 안맞다");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @PostMapping("/kakao")
    public String loginUrlKakao() {

        /* 카카오 계정 로그인 하기 위해서 카카오 url로 간다 .
        *  하지만 그전에 해당 자료가 있는지 확인 하고
        *  해당 자료에 따른 acc,ref 토큰 값을 확인하고
        *  해당 토큰 값이 있다면 update 로 처리 해준다.*/

        String reqUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
                + kakaoRestAPI_Key
                + "&redirect_uri="
                + kakaoRedirect_uri
//                + "&prompt=login";
                + "&prompt=select_account" ;

        log.error("reqUrl = "  +reqUrl);

        return "redirect:" + reqUrl;
    }

    @GetMapping("/kakao-login")
    public String getCI(@RequestParam String code, HttpSession session,
                        HttpServletResponse response, RedirectAttributes rattr) throws IOException {

//        System.out.println("code = " + code);
//        System.out.println(kakaoService.getAccessToken(code));

        String[] getToken = kakaoService.getToken(code);
        log.info("ttt \n\n = " +getToken.toString());

        String access_token = getToken[0];
        String refresh_token = getToken[1];

        Map<String, Object> userInfo = kakaoService.getUserInfo(access_token);
        Map<String, Object> service_terms = kakaoService.getservice_terms(access_token);

        String snsId = (String)userInfo.get("id");
        String user_email = (String)userInfo.get("email");
        String user_name = (String)userInfo.get("name");
        String user_pwd = snsId;

        System.out.println("ddd = " + service_terms.get("user_age_check"));

        String user_age_check = (boolean)service_terms.get("user_age_check") ? "on" : "";
        String site_term_agree_yn = (boolean)service_terms.get("site_term_agree_yn") ? "on" : "";
        String p_Info_agree_yn = (boolean)service_terms.get("p_Info_agree_yn") ? "on" : "";
        String p_info_oth_agree_yn = (boolean)service_terms.get("p_info_oth_agree_yn") ? "on" : "";
        String m_info_rcv_agree_yn = (boolean)service_terms.get("m_info_rcv_agree_yn") ? "on" : "";
//        String user_age_check = service_terms.get("user_age_check") =="true" ? "on" : "";
//        String site_term_agree_yn = service_terms.get("site_term_agree_yn") =="true" ? "on" : "";
//        String p_Info_agree_yn = service_terms.get("p_Info_agree_yn") =="true" ? "on" : "";
//        String m_info_rcv_agree_yn = service_terms.get("m_info_rcv_agree_yn") =="true" ? "on" : "";

        UserJoinDto userJoinDto = UserJoinDto.builder()
                                             .user_email(user_email)
                                             .user_name(user_name)
                                             .user_pwd(user_pwd)
                                             .user_pwdConfirm(user_pwd)
                                             .age_agree_yn(user_age_check)
                                             .site_term_agree_yn(site_term_agree_yn)
                                             .p_Info_agree_yn(p_Info_agree_yn)
                                             .p_info_oth_agree_yn(p_info_oth_agree_yn)
                                             .m_info_rcv_agree_yn(m_info_rcv_agree_yn)
                                             .build();

        log.error("유저 정보 ========\n" + userJoinDto);

        OauthDto oauthDto = OauthDto.builder()
                .snsId(snsId)
                .user_email(user_email)
                .access_token(access_token)
                .refresh_token(refresh_token)
                .user_name(user_name)
                .build();

        log.error("OauthDto 정보 ========\n" + oauthDto);

        try {

            /* oauth 가입/ 로그인
            1. oauth 테이블에 저장된 값이 없으면 신규 추가
            2. oauth 테이블에 저장된 값이 있으면 acess,refesh 비교 후 업데이트
            *  회원가입 정보 저장,oauth kakao 정보 저장*/
//            if(loginService.Login(use))
            int joincnt = joinService.emailCheck(userJoinDto);
            int oauthcnt = oauthJoinService.check(oauthDto);

            // user정보, oauth 정보가 없을 경우
            if(joincnt == 0 && oauthcnt == 0){
                joinService.userJoin(userJoinDto);
                oauthJoinService.userOauthJoin(oauthDto, session, response);

            }
            // user 정보가 있고, oauth 정보가 없는 경우
            else if(joincnt == 1 && oauthcnt == 0) {
                // 해당 값을 저장
            }

            // user,oauth 정보가 있고 토큰에 따른 값 업데이트
            else if(joincnt == 1 && oauthcnt == 1){
                // 해당 값을 갱신
                oauthJoinService.userOauthUpdate(oauthDto,session,response);
//                log.error("업데이트 했다 아이가 !!!!!!!!!!!\n\n\n");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            rattr.addFlashAttribute("errmsg", exception.getMessage().split(" ",2)[1]);
            return "redirect:/join/add";
//            throw new RuntimeException(e);
        }

        return "redirect:/";
    }

    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        // 쿠키 배열이 null이 아니고, 각 쿠키를 확인하여 원하는 쿠키의 값을 찾음
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class,Exception.class})
    public String handleException()
    {
        return "user/error";
    }

}
