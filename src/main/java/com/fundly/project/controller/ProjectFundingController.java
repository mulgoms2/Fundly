package com.fundly.project.controller;

import com.fundly.project.exception.ProjectNofFoundException;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/project/editor")
public class ProjectFundingController {
    @Autowired
    ProjectService projectService;
    @ModelAttribute("projectDto")
    ProjectDto initProjectEditor(@SessionAttribute String user_email) {
        try {
            ProjectDto projectDto = projectService.getEditingProject(user_email);
            return projectDto;
        } catch (ProjectNofFoundException e) {
            return null;
        }
    }

    @GetMapping("/funding")
    public String fundingPlan(){
        return "project.funding";
    }

}
