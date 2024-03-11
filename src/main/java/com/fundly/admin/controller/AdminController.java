package com.fundly.admin.controller;

import com.fundly.admin.model.PageHandler;
import com.fundly.admin.service.*;
import com.fundly.project.service.ProjectService;
import com.google.gson.JsonObject;
import com.persistence.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    NewsService newsService;
    @Autowired
    CtgGuideService ctgGuideService;
    @Autowired
    InformService informService;
    @Autowired
    AdminService adminService;
    @Autowired
    EventService eventService;
    @Autowired
    TermService termService;
    @Autowired
    StatusService statusService;
    @Autowired
    ProjectService projectService;
    @RequestMapping("/list")
    public String getNewsList(@RequestParam (required = false, defaultValue = "1") Integer page, Model model,
                              @RequestParam (required = false, defaultValue = "10") Integer pageSize,HttpServletRequest req){
                                //값이없어도에러나지않고 page=1 pageSize=10 default값으로 넣어준다
//        HttpSession session = req.getSession();
//
//        if (session.getAttribute("admin_id")==null){
//            model.addAttribute("msg","로그인후 이용가능합니다");
//            return "admin/login";
//        }

        try {
            List<NewsDto> NewsList = newsService.selectPage(page,pageSize);
            int totalCnt = newsService.count();
            PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);
            if(page > pageHandler.getTotalPage()){
                model.addAttribute("msg","게시물이없습니다");
                return "/admin/index";
            }
            model.addAttribute("NewsList",NewsList);
            model.addAttribute("ph",pageHandler);
            model.addAttribute("page",page);


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
            //런타임예외를던져서 ExceptionHandler가처리
        }
        return "admin/index";
    }




    @RequestMapping("/select")
    public String selectNews(Integer news_seq, Model model,Integer page){
        try {
            NewsDto News = newsService.selectNews(news_seq);
            model.addAttribute("page",page);
            model.addAttribute("News",News);
            return "admin/newsRead";
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/write")
    public String writeNews(@Valid  NewsDto newsDto, BindingResult bindingResult, Model model){
       if(bindingResult.hasErrors()){
           model.addAttribute("msg","writeError");
           model.addAttribute("newsDto",newsDto);
           //적었던정보를 모델에 담아서 admin/newsWrite로전달
           return "admin/newsWrite";}
        try {
            newsService.insertNews(newsDto);
        }catch (Exception e){
            model.addAttribute("newsDto",newsDto);
            return "admin/newsWrite";
        }
        return "redirect:/admin/list";
    }
    @GetMapping("/write")
    public String writeNews(){
        return "admin/newsWrite";
    }

    @PostMapping("/modify")
    public String modifyNews(NewsDto newsDto,Model model){
        try {
            if (newsService.updateNews(newsDto) != 1)
                throw new RuntimeException("수정실패");
            return "redirect:/admin/list";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("newsModifyInfo",newsDto);
            return "admin/newsWrite";
        }


    }
    @GetMapping("/modify")
    public String modifyNews1(Integer news_seq,Model model){
        try {
            NewsDto newsModifyInfo = newsService.selectNews(news_seq);
            model.addAttribute("newsModifyInfo",newsModifyInfo);
            return "admin/newsWrite";
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/delete")
    public String deleteNews(Integer news_seq,String reg_id){
        try {
            newsService.deleteNews(news_seq,reg_id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        return "redirect:/admin/list";
    }
    @PostMapping("/search")
    public String searchNews( String news_title,Model model){
        try {
            System.out.println(news_title);
            List<NewsDto> NewsList = newsService.searchNews(news_title);
            System.out.println(NewsList);
            model.addAttribute("NewsList",NewsList);
        }catch (Exception e){
            throw new RuntimeException(e);
        }return "admin/index";
    }

    @GetMapping("/eventList")
    public String getEventList(Model model){
        try {
            List<EventDto> eventList = eventService.selectAllEvent();
            model.addAttribute("eventList",eventList);
            model.addAttribute("now", LocalDateTime.now());
        }catch (Exception e){

        }
        return "admin/eventList";
    }
    @PostMapping("/eventWrite")
    public String insertEvent(EventDto eventDto,Model model){
        try {
            eventService.insertEvent(eventDto);
        }catch (Exception e){

        }return "redirect:/admin/eventList";
    }
    @GetMapping("/eventWrite")
    public String insertEvent(){
        return "admin/eventWrite";
    }

    @GetMapping("/termWrite")
    public String insertTerm(){return "admin/termWrite";}

    @PostMapping("/termWrite")
    public String insertTerm(TermDto termDto, Model model){
        try {
            termService.insertTerm(termDto);
        }catch (Exception e){}
        return "redirect:/";
    }
    @RequestMapping("/projectList")
    public String projectList(Model model){
        try {
            List<ProjectDto> projectList = statusService.getSelectAllPj();
            model.addAttribute("projectList",projectList);
        }catch (Exception e){}
        return "admin/projectScreen";
    }

    @PostMapping("/projectStatus")
    public String projectStatus(ProjectDto dto){
        try {
            System.out.println("pj_Id = " + dto.getPj_id());
            System.out.println("pj_status = " + dto.getPj_status());
            statusService.updateStatus(dto);
        }catch (Exception e){}
        return "redirect:/admin/projectList";
    }

    @GetMapping("/projectStatus")
    public String projectStatus(String pj_status,Model model){
        try {
            List<ProjectDto> projectList = projectService.getListByStatus(pj_status);
            model.addAttribute("projectList",projectList);
        }catch (Exception e){}
        return "admin/projectScreen";
    }


    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class})
    public String handleException(Model model) {
        model.addAttribute("msg","system Error");
        return "admin/error";
    }



}
