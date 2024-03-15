package com.fundly.admin.controller;

import com.fundly.admin.model.PageHandler;
import com.fundly.admin.service.EventService;
import com.fundly.admin.service.NewsService;
import com.fundly.admin.service.TermService;
import com.fundly.project.service.ProjectService;
import com.fundly.user.dto.LikeProjectDto;
import com.fundly.user.service.UserInfoService;
import com.persistence.dto.EventDto;
import com.persistence.dto.NewsDto;
import com.persistence.dto.TermDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
public class                                                                                             AdminUserController {
    @Autowired
    NewsService newsService;
    @Autowired
    EventService eventService;
    @Autowired
    TermService termService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserInfoService userInfoService;


    @RequestMapping("/noticeList")
    public String getNewsList(@RequestParam(required = false, defaultValue = "1") Integer page, Model model,
                              @RequestParam (required = false, defaultValue = "10") Integer pageSize, HttpSession session, HttpServletRequest request){
        //값이없어도에러나지않고 page=1 pageSize=10 default값으로 넣어준다

        if (page==0){
            page = 1;
        }

        try {
            String user_email = (String) (session.getAttribute("user_email"));// "helloworld@abc.com";

            if (user_email == null || user_email == "") {
//                throw new RuntimeException("USER_ERROR");
//                log.error("로그인되지 않았습니다");
//                model.addAttribute("errmsg", "USER_ERROR");
                /* session email 없다면 메인 화면으로 가면서 로그인 정보 확인 요청 ..
                 * url 부분은 어떻게 가리는가 ? */
                return "redirect:/login/login";
            }

            UserDto dto = UserDto.builder()
                    .user_email(user_email)
                    .user_id(user_email)
                    .build();
            UserDto userInfo = userInfoService.userInfo(dto);
            String userjoindateView = userInfoService.userJoindate(dto);
            String user_profileImg = getCookieValue(request, "user_profileImg");

            model.addAttribute("userInfo", userInfo);
            model.addAttribute("userjoindateView", userjoindateView);
            model.addAttribute("user_profileImg", user_profileImg);

            // 유저 아이디 통해 좋아요 목록 불러오기 (디폴트 : 진행중인 프로젝트)
//            List<LikeProjectDto> likes = likeservice.getLikeList(userInfo.getUser_id());
//            model.addAttribute("likes", likes);
//            log.error("\n\n\n" + likes);
            List<NewsDto> NewsList = newsService.selectPage(page,pageSize);
            int totalCnt = newsService.count();
            PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);
            if(page > pageHandler.getTotalPage()){
                model.addAttribute("msg","게시물이없습니다");
                return "user.userNotice";
            }
            model.addAttribute("NewsList",NewsList);
            model.addAttribute("ph",pageHandler);
            model.addAttribute("page",page);


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
            //런타임예외를던져서 ExceptionHandler가처리
        }
        return "user.userNotice";
    }
    @RequestMapping("/noticeDetail")
    public String noticeDetail(Integer news_seq, Model model,Integer page, HttpSession session, HttpServletRequest request){
        try {
            String user_email = (String) (session.getAttribute("user_email"));// "helloworld@abc.com";

            if (user_email == null || user_email == "") {
//                throw new RuntimeException("USER_ERROR");
//                log.error("로그인되지 않았습니다");
//                model.addAttribute("errmsg", "USER_ERROR");
                /* session email 없다면 메인 화면으로 가면서 로그인 정보 확인 요청 ..
                 * url 부분은 어떻게 가리는가 ? */
                return "redirect:/login/login";
            }

            UserDto dto = UserDto.builder()
                    .user_email(user_email)
                    .user_id(user_email)
                    .build();
            UserDto userInfo = userInfoService.userInfo(dto);
            String userjoindateView = userInfoService.userJoindate(dto);
            String user_profileImg = getCookieValue(request, "user_profileImg");

            model.addAttribute("userInfo", userInfo);
            model.addAttribute("userjoindateView", userjoindateView);
            model.addAttribute("user_profileImg", user_profileImg);

            // 유저 아이디 통해 좋아요 목록 불러오기 (디폴트 : 진행중인 프로젝트)
//            List<LikeProjectDto> likes = likeservice.getLikeList(userInfo.getUser_id());
//            model.addAttribute("likes", likes);
//            log.error("\n\n\n" + likes);
            NewsDto News = newsService.selectNews(news_seq);
            model.addAttribute("page",page);
            model.addAttribute("News",News);
            return "user.userNoticeDetail";
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/eventList")
    public String eventList(@RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model
            , HttpSession session, HttpServletRequest request){
        //값이없어도에러나지않고 page=1 pageSize=10 default값으로 넣어준다
        if (page==0){
            page = 1;
        }
        try{
            String user_email = (String) (session.getAttribute("user_email"));// "helloworld@abc.com";

            if (user_email == null || user_email == "") {
//                throw new RuntimeException("USER_ERROR");
//                log.error("로그인되지 않았습니다");
//                model.addAttribute("errmsg", "USER_ERROR");
                /* session email 없다면 메인 화면으로 가면서 로그인 정보 확인 요청 ..
                 * url 부분은 어떻게 가리는가 ? */
                return "redirect:/login/login";
            }

            UserDto dto = UserDto.builder()
                    .user_email(user_email)
                    .user_id(user_email)
                    .build();
            UserDto userInfo = userInfoService.userInfo(dto);
            String userjoindateView = userInfoService.userJoindate(dto);
            String user_profileImg = getCookieValue(request, "user_profileImg");

            model.addAttribute("userInfo", userInfo);
            model.addAttribute("userjoindateView", userjoindateView);
            model.addAttribute("user_profileImg", user_profileImg);

            // 유저 아이디 통해 좋아요 목록 불러오기 (디폴트 : 진행중인 프로젝트)
//            List<LikeProjectDto> likes = likeservice.getLikeList(userInfo.getUser_id());
//            model.addAttribute("likes", likes);
//            log.error("\n\n\n" + likes);
            List<EventDto> EventList = eventService.selectPage(page,pageSize);
            int totalCnt = eventService.count();
            PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);
            if(page > pageHandler.getTotalPage()){
                return "user.userEvent";
            }
            model.addAttribute("EventList",EventList);
            model.addAttribute("ph",pageHandler);
            model.addAttribute("page",page);
            model.addAttribute("now", LocalDateTime.now());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "user.userEvent";
    }
    @RequestMapping("/eventDetail")
    public String eventDetail(Model model,Integer page,Integer event_seq
            , HttpSession session, HttpServletRequest request){
        try {
            String user_email = (String) (session.getAttribute("user_email"));// "helloworld@abc.com";

            if (user_email == null || user_email == "") {
//                throw new RuntimeException("USER_ERROR");
//                log.error("로그인되지 않았습니다");
//                model.addAttribute("errmsg", "USER_ERROR");
                /* session email 없다면 메인 화면으로 가면서 로그인 정보 확인 요청 ..
                 * url 부분은 어떻게 가리는가 ? */
                return "redirect:/login/login";
            }

            UserDto dto = UserDto.builder()
                    .user_email(user_email)
                    .user_id(user_email)
                    .build();
            UserDto userInfo = userInfoService.userInfo(dto);
            String userjoindateView = userInfoService.userJoindate(dto);
            String user_profileImg = getCookieValue(request, "user_profileImg");

            model.addAttribute("userInfo", userInfo);
            model.addAttribute("userjoindateView", userjoindateView);
            model.addAttribute("user_profileImg", user_profileImg);

            // 유저 아이디 통해 좋아요 목록 불러오기 (디폴트 : 진행중인 프로젝트)
//            List<LikeProjectDto> likes = likeservice.getLikeList(userInfo.getUser_id());
//            model.addAttribute("likes", likes);
//            log.error("\n\n\n" + likes);
            EventDto event = eventService.selectEvent(event_seq);
            model.addAttribute("page",page);
            model.addAttribute("event",event);
            model.addAttribute("now",LocalDateTime.now());
        }catch (Exception e){}
        return "user.userEventDetail";
    }
    @RequestMapping("/termDetail")
    public String selectTerm(Model model,String term_title,
                            @RequestParam(required = false) Integer term_seq){
        try {
            List<TermDto> termTitleList = termService.selectAllTitle();
            TermDto term = termService.selectTitle(term_title);
            System.out.println("term:="+term.getTerm_seq());
            if (term_seq==null) {
                term_seq = term.getTerm_seq();
            }
            TermDto prne = termService.termPrevNext(term_seq, term_title);
            System.out.println("prne :"+prne.getPrev()+"next"+prne.getNext());

            model.addAttribute("term",term);
            model.addAttribute("termTitleList",termTitleList);
            model.addAttribute("prne",prne);
        }catch (Exception e){}
        return "admin/userTermDetail";
    }
    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        // 쿠키 배열이 null이 아니고, 각 쿠키를 확인하여 원하는 쿠키의 값을 찾음
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName()
                        .equals(cookieName)) {
                    if (cookie.getName()
                            .equals(cookieName) && cookieName.equals("user_profileImg")) {
                        try {
                            return URLDecoder.decode(cookie.getValue(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        return cookie.getValue();
                    }
                }
            }
        }

        return null;
    }

}
