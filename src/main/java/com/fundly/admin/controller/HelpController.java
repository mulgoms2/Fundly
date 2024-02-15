package com.fundly.admin.controller;

import com.fundly.admin.service.SubHelpService;
import com.persistence.dto.SubHelpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/help")
public class HelpController {
    @Autowired
    SubHelpService subHelpService;
    @GetMapping("/subList")
    public String subHelp(Model model) {
        try {
            List<SubHelpDto> subList0 = subHelpService.selectAllSubHelp(0);
            List<SubHelpDto> subList1 = subHelpService.selectAllSubHelp(1);
            List<SubHelpDto> subList2 = subHelpService.selectAllSubHelp(2);
            List<SubHelpDto> subList3 = subHelpService.selectAllSubHelp(3);
            List<SubHelpDto> subList4 = subHelpService.selectAllSubHelp(4);
            int count0 = subHelpService.count(0);
            int count1 = subHelpService.count(1);
            int count2 = subHelpService.count(2);
            int count3 = subHelpService.count(3);
            int count4 = subHelpService.count(4);
            model.addAttribute("count0", count0);
            model.addAttribute("count1", count1);
            model.addAttribute("count2", count2);
            model.addAttribute("count3", count3);
            model.addAttribute("count4", count4);
            model.addAttribute("subList0", subList0);
            model.addAttribute("subList1", subList1);
            model.addAttribute("subList2", subList2);
            model.addAttribute("subList3", subList3);
            model.addAttribute("subList4", subList4);
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
            model.addAttribute("subHelpDto",subHelpDto);
            model.addAttribute("subList",subList);
        }catch (Exception e){throw new RuntimeException(e);}
        return "admin/helpDetailDetail";
    }


}
