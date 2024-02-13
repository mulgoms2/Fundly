package com.fundly.admin.controller;

import com.fundly.admin.service.CtgGuideService;
import com.fundly.admin.service.NewsService;
import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    NewsService newsService;
    @Autowired
    CtgGuideService ctgGuideService;


    @GetMapping("/list")
    public String getNewsList(Model model){
        try {
            List<NewsDto> NewsList = newsService.selectAllNews();
            model.addAttribute("NewsList",NewsList);
            return "admin/index";
        } catch (Exception e) {
            return "";
        }

    }

    @GetMapping("/ctglist")
    public String getCtgList(Model model){
        try {
            List<CtgGuideDto> CtgList = ctgGuideService.selectAllCtg();
            model.addAttribute("CtgList",CtgList);
            return "admin/ctgguide";
        } catch (Exception e) {
            return "";
        }

    }


    @RequestMapping("/select")
    public String selectNews(Integer news_seq, Model model){
        try {
            NewsDto News = newsService.selectNews(news_seq);
            model.addAttribute("News",News);
            return "admin/newsRead";
        }catch (Exception e){
            return "admin/index";
        }
    }

    @PostMapping("/write")
    public String writeNews(NewsDto newsDto){
        try {
            newsService.insertNews(newsDto);
        }catch (Exception e){
            return "redirect:/admin/list";
        }
        return "redirect:/admin/list";
    }
    @GetMapping("/write")
    public String writeNews(){
        return "admin/newsWrite";
    }

    @PostMapping("/modify")
    public String modifyNews(NewsDto newsDto){
        try {
            System.out.println(newsDto);
            newsService.updateNews(newsDto);
        }catch (Exception e){
            return "";
        }

        return "redirect:/admin/list";
    }
    @GetMapping("/modify")
    public String modifyNews1(Integer news_seq,Model model){
        try {
            NewsDto newsModifyInfo = newsService.selectNews(news_seq);
            model.addAttribute("newsModifyInfo",newsModifyInfo);
            return "admin/newsWrite";
        } catch (Exception e){
            return "redirect:/admin/list";
        }

    }

    @PostMapping("/delete")
    public String deleteNews(Integer news_seq,String reg_id){
        try {
            newsService.deleteNews(news_seq,reg_id);
        }catch (Exception e){
            return "redirect:/admin/list";
        }


        return "redirect:/admin/list";
    }

}
