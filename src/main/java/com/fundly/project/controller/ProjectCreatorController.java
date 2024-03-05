package com.fundly.project.controller;

import com.fundly.project.exception.ProjectNofFoundException;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/project/editor")
@SessionAttributes("projectDto")
public class ProjectCreatorController {
    @Autowired
    ProjectService projectService;

    @ModelAttribute("projectDto")
    ProjectDto initProjectEditor(@SessionAttribute String user_email, HttpSession session) {
        try {
            ProjectDto projectDto = projectService.getEditingProject(user_email);
            session.setAttribute("pj_id", projectDto.getPj_id());
            return projectDto;
        } catch (ProjectNofFoundException e) {
            return null;
        }
    }

    @GetMapping("/creator")
    public String creatorInfo(){
        return "project.creator";
    }


}

