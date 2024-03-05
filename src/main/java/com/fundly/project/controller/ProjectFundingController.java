package com.fundly.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/editor")
public class ProjectFundingController {
    @GetMapping("/funding")
    public String fundingPlan(){
        return "project.funding";
    }

}
