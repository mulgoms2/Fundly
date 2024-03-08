package com.fundly.product.detail.controller;

import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class detailController {
    @Autowired
    private ProjectService projectService;
//    private itemService itemService;
//    private giftService giftService;

    @GetMapping("/detail")
    public String detail(){
        return "product/detail";
    }

    @GetMapping("/detail/{pj_id}")
    public String showDetailView(@PathVariable String pj_id, Model model) {
        ProjectDto pj = projectService.get(pj_id);

        model.addAttribute("pj", pj);

        return "product/detail";
    }

    // 창작자 컨트롤러
    @GetMapping("/creation")
    public String creator(){
        return "product/creation";
    }

//    @PostMapping("/detail/{pj_id}")
//    public String detailPageinfo(@PathVariable String pj_id, Model model) {
//        itemService itemServe = itemService.get(pj_id);
//
//        model.addAttribute("itemServe", itemServe);
//
//        return "product/detail"
//    }
}
