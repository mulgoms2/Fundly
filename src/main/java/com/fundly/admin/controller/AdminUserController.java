package com.fundly.admin.controller;

import com.fundly.admin.model.PageHandler;
import com.fundly.admin.service.EventService;
import com.fundly.admin.service.NewsService;
import com.fundly.admin.service.TermService;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.EventDto;
import com.persistence.dto.NewsDto;
import com.persistence.dto.TermDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdminUserController {
    @Autowired
    NewsService newsService;
    @Autowired
    EventService eventService;
    @Autowired
    TermService termService;
    @Autowired
    ProjectService projectService;

    @RequestMapping("/noticeList")
    public String getNewsList(@RequestParam(required = false, defaultValue = "1") Integer page, Model model,
                              @RequestParam (required = false, defaultValue = "10") Integer pageSize, HttpServletRequest req){
        //값이없어도에러나지않고 page=1 pageSize=10 default값으로 넣어준다

        try {
            List<NewsDto> NewsList = newsService.selectPage(page,pageSize);
            int totalCnt = newsService.count();
            PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);
            if(page > pageHandler.getTotalPage()){
                model.addAttribute("msg","게시물이없습니다");
                return "redirect:/noticeList";
            }
            model.addAttribute("NewsList",NewsList);
            model.addAttribute("ph",pageHandler);
            model.addAttribute("page",page);


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
            //런타임예외를던져서 ExceptionHandler가처리
        }
        return "admin/userNotice";
    }
    @RequestMapping("/noticeDetail")
    public String noticeDetail(Integer news_seq, Model model,Integer page){
        try {
            NewsDto News = newsService.selectNews(news_seq);
            model.addAttribute("page",page);
            model.addAttribute("News",News);
            return "admin/userNoticeDetail";
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/eventList")
    public String eventList(@RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model){
        //값이없어도에러나지않고 page=1 pageSize=10 default값으로 넣어준다
        try{
            List<EventDto> EventList = eventService.selectPage(page,pageSize);
            int totalCnt = eventService.count();
            PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);
            if(page > pageHandler.getTotalPage()){
                return "redirect:/eventList";
            }
            model.addAttribute("EventList",EventList);
            model.addAttribute("ph",pageHandler);
            model.addAttribute("page",page);
            model.addAttribute("now", LocalDateTime.now());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "admin/userEvent";
    }
    @RequestMapping("/eventDetail")
    public String eventDetail(Model model,Integer page,Integer event_seq){
        try {
            EventDto event = eventService.selectEvent(event_seq);
            model.addAttribute("page",page);
            model.addAttribute("event",event);
            model.addAttribute("now",LocalDateTime.now());
        }catch (Exception e){}
        return "admin/userEventDetail";
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


}
