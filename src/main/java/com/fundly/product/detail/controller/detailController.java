package com.fundly.product.detail.controller;

import com.fundly.project.service.ProjectService;
import com.fundly.project.service.ProjectServiceImpl;
import com.persistence.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class detailController {
    @Autowired
    private ProjectService projectService;

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
}
