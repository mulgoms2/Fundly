package com.fundly.admin.controller;

import com.fundly.admin.service.CtgGuideService;
import com.fundly.admin.service.InformService;
import com.fundly.admin.service.NewsService;
import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.InformDto;
import com.persistence.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    NewsService newsService;
    @Autowired
    CtgGuideService ctgGuideService;
    @Autowired
    InformService informService;


    @GetMapping("/list")
    public String getNewsList(Model model, RedirectAttributes rattr){
        try {
            List<NewsDto> NewsList = newsService.selectAllNews();
            model.addAttribute("NewsList",NewsList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "admin/index";
    }

    @GetMapping("/ctglist")
    public String getCtgList(Model model){
        try {
            List<CtgGuideDto> CtgList = ctgGuideService.selectAllCtg();
            model.addAttribute("CtgList",CtgList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }return "admin/ctgList";

    }
    @GetMapping("/informlist")
    public String getInformList(Model model){
        try {
            List<InformDto> InformList = informService.selectAllInform();
            model.addAttribute("InformList",InformList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }return "admin/informList";

    }


    @RequestMapping("/select")
    public String selectNews(Integer news_seq, Model model){
        try {
            NewsDto News = newsService.selectNews(news_seq);
            model.addAttribute("News",News);
            return "admin/newsRead";
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/write")
    public String writeNews(NewsDto newsDto, Model model){
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

    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class})
    public String handleException() {

        return "admin/error";
    }


}
