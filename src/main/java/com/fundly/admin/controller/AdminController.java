package com.fundly.admin.controller;

import com.fundly.admin.model.PageHandler;
import com.fundly.admin.service.CtgGuideService;
import com.fundly.admin.service.InformService;
import com.fundly.admin.service.NewsService;
import com.persistence.dto.CtgGuideDto;
import com.persistence.dto.InformDto;
import com.persistence.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String getNewsList(@RequestParam (required = false, defaultValue = "1") Integer page, Model model,
                              @RequestParam (required = false, defaultValue = "10") Integer pageSize){
                                //값이없어도에러나지않고 page=1 pageSize=10 default값으로 넣어준다
        try {
            List<NewsDto> NewsList = newsService.selectPage(page,pageSize);
            int totalCnt = newsService.count();
            PageHandler pageHandler = new PageHandler(totalCnt,page,pageSize);
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
    @ExceptionHandler({RuntimeException.class, SQLException.class,IllegalArgumentException.class})
    public String handleException(Model model) {
        model.addAttribute("msg","system Error");
        return "admin/error";
    }


}
