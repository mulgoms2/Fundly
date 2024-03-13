package com.fundly.admin.controller;

import com.fundly.admin.service.SubHelpService;
import com.fundly.admin.service.TermService;
import com.persistence.dto.NewsDto;
import com.persistence.dto.SubHelpDto;
import com.persistence.dto.TermDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/help")
public class HelpController {
    @Autowired
    SubHelpService subHelpService;
    @Autowired
    TermService termService;
    @GetMapping("/subList")
    public String subHelp(Model model) {
        try {
            int totalSortcnt = subHelpService.countSub();
            List<TermDto> termTitleList = termService.selectAllTitle();
            model.addAttribute("termTitleList",termTitleList);
            for (int i = 0; i < totalSortcnt; i++) {
                List<SubHelpDto> subList = subHelpService.selectAllSubHelp(i);
                int count = subHelpService.count(i);
                model.addAttribute("subList" + i, subList);
                model.addAttribute("count" + i, count);
            }

//            List<List<SubHelpDto>> subLists = new ArrayList<>();
//            List<Integer> counts = new ArrayList<>();
//
//            int totalSort = subHelpService.countSub();
//
//            for (int i = 0; i < totalSort; i++) {
//                List<SubHelpDto> subList = subHelpService.selectAllSubHelp(i);
//                int count = subHelpService.count(i);
//                subLists.add(subList);
//                counts.add(count);
//            }
//
//            model.addAttribute("subLists", subLists);
//            model.addAttribute("counts", counts);

            return "admin/subHelp";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/write")
    public String writeHelp(SubHelpDto subHelpDto, Model model){
        System.out.println(subHelpDto.getSub_help_sort());
            try {
               if(subHelpService.insertSubHelp(subHelpDto)!=1)
                   throw new Exception("입력실패");
                return "redirect:/help/subList";
            }catch (Exception e){
//                model.addAttribute("subHelpDto",subHelpDto);
                return "admin/helpWrite";
            }


        }
        @GetMapping("/write1")
    public String writeHelp1(){
        return "admin/helpWrite";
        }

    @GetMapping("/detail")
    public String detailHelp(int sub_help_sort, Model model){
        System.out.println(sub_help_sort);
        try {
              List<SubHelpDto> subList = subHelpService.selectAllSubHelp(sub_help_sort);
              model.addAttribute("subList",subList);
        }catch (Exception e){throw new RuntimeException(e);}
        return "admin/helpDetail";
    }

    @GetMapping("/detaildetail")
    public String detailDetailHelp(Integer sub_help_seq,int sub_help_sort,Model model){
        try {
            List<SubHelpDto> subList = subHelpService.selectAllSubHelp(sub_help_sort);
            SubHelpDto subHelpDto = subHelpService.selectSubHelp(sub_help_seq);
            System.out.println(subHelpDto.getSub_help_sort());
            model.addAttribute("subHelpDto",subHelpDto);
            model.addAttribute("subList",subList);
        }catch (Exception e){throw new RuntimeException(e);}
        return "admin/helpDetailDetail";
    }

    @PostMapping("/search")
    public String searchNews( String sub_help_title,Model model){
        try {
            List<SubHelpDto> subList = subHelpService.searchHelp(sub_help_title);
            model.addAttribute("subList",subList);
        }catch (Exception e){
            throw new RuntimeException(e);
        }return "admin/subHelpSearch";
    }
}
