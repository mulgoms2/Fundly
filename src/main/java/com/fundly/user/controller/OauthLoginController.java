package com.fundly.user.controller;

import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.service.JoinService;
import com.fundly.user.service.KakaoService;
import com.fundly.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    private  final LoginService loginService;

    private final JoinService joinService;


    @Autowired
    public OauthLoginController (KakaoService kakaoService, JoinService joinService, LoginService loginService){
        this.kakaoService = kakaoService;
        this.joinService = joinService;
        this.loginService = loginService;
    }

    @PostMapping("/kakao")
    public String loginUrlKakao() {
//        log.info("code = {} ", code);

        /* 동의 항목 */
//        String reqUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
//                + kakaoRestAPI_Key
//                + "&redirect_uri="
//                + kakaoRedirect_uri
//                + "&response_type=code";
////                + "&scope=" +
////                scope;

        String reqUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
                + kakaoRestAPI_Key
                + "&redirect_uri="
                + kakaoRedirect_uri;
//                + "&prompt=select_account" ;

        log.error("reqUrl = "  +reqUrl);

        return "redirect:" + reqUrl;

        //http://127.0.0.1/kakao-login?
        // code=6ltH-c71r0x7vT6Ao4VqjzFv8LLN8TvmyL4u5TdtQL6NwNiMThVdCP62OdYKPXMYAAABjb6HRVnUNEQ5evY1pg
    }

    @GetMapping("/kakao-login")
    public String getCI(@RequestParam String code,Model model, HttpSession session, HttpServletResponse response) throws IOException {

//        System.out.println("code = " + code);
//        System.out.println(kakaoService.getAccessToken(code));
        String access_token = kakaoService.getAccessToken(code);
//        log.info("ddddddddddddd");
        Map<String, Object> userInfo = kakaoService.getUserInfo(access_token);
        Map<String, Object> service_terms = kakaoService.getservice_terms(access_token);

        String snsId = (String)userInfo.get("id");
        String user_email = (String)userInfo.get("email");
        String user_name = (String)userInfo.get("name");
        String user_pwd = snsId;
        String user_age_check = service_terms.get("user_age_check") =="true" ? "on" : "";
        String site_term_agree_yn = service_terms.get("site_term_agree_yn") =="true" ? "on" : "";
        String p_Info_agree_yn = service_terms.get("p_Info_agree_yn") =="true" ? "on" : "";
        String m_info_rcv_agree_yn = service_terms.get("m_info_rcv_agree_yn") =="true" ? "on" : "";

        UserJoinDto userJoinDto = UserJoinDto.builder()
                                             .user_email(user_email)
                                             .user_name(user_name)
                                             .user_pwd(user_pwd)
                                             .user_pwdConfirm(user_pwd)
                                             .age_agree_yn(user_age_check)
                                             .site_term_agree_yn(site_term_agree_yn)
                                             .p_Info_agree_yn(p_Info_agree_yn)
                                             .m_info_rcv_agree_yn(m_info_rcv_agree_yn)
                                             .build();

        log.error("유저 정보 ========\n" + userJoinDto);
        try {

//            if(loginService.Login(use))
            joinService.userJoin(userJoinDto);


            /*kakao accesstoken*/
            session.setAttribute("kat",access_token);
            // 세션의 유효시간 (30분)
            session.setMaxInactiveInterval(1800);

            /* cookie add */
            Cookie cookie = new Cookie("kat",access_token);
            response.addCookie(cookie);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        model.addAttribute("code", code);
//        model.addAttribute("access_token", access_token);
//        model.addAttribute("userInfo", userInfo);
//
//        //값 꺼내오기위한 처리
//        if(model.getAttribute("userInfo") != null){
//            session.setAttribute("nickname", userInfo.get("nickname"));
//            session.setAttribute("email", userInfo.get("email"));
//            session.setAttribute("age_range", userInfo.get("age_range"));
//        }

        return "redirect:/";
    }

//    @GetMapping("/checkSignUp")
//    public String checkGoogleSignUp(HttpSession session, Model model) throws Exception {
//
//        String userEmail = "";
//
//        String kakao
//
//
////        if (session.getAttribute("kakaoUserInfo") != null) {
////            OauthDto userInfo = (OauthDto) session.getAttribute("kakaoUserInfo");
////            userEmail = userInfo.getEmail();
////        }
////
////        UserDto userDto = userLoginService.signinOauth(userEmail, session);
////
////        if (userDto != null) {
////            return "redirect:/";
////        } else {
////            model.addAttribute("userEmail", userEmail);
////            String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
////            String userPwd = RandomStringUtils.random(20, allowedCharacters);
////            model.addAttribute("userPwd", userPwd);
////
////            return "user/joinOauth";
////        }
//    }

}
