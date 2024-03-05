package com.fundly.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/editor")
public class ProjectCreatorController {
    @GetMapping("/creator")
    public String creatorInfo(){
        return "project.creator";
    }
}

