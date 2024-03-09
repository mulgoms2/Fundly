package com.fundly.project.controller;

import com.fundly.project.exception.ProjectNotFoundException;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@SessionAttributes("projectDto")
@RequestMapping("/project/editor")
public class ProjectFundingController {
    @Autowired
    ProjectService projectService;
    @ModelAttribute("projectDto")
    ProjectDto initProjectEditor(@SessionAttribute String user_email, HttpSession session) {
        try {
            ProjectDto projectDto = projectService.getEditingProject(user_email);
            session.setAttribute("pj_id", projectDto.getPj_id());
            return projectDto;
        } catch (ProjectNotFoundException e) {
            return null;
        }
    }

    @GetMapping("/funding")
    public String fundingPlan(@SessionAttribute ProjectDto projectDto, Model m){
        log.error("\n\n projectDto={} \n\n", projectDto);
        FundingForm fundingForm = null; //funding페이지를 처음 작성하는 경우에는
        try{
            fundingForm = ProjectDto.toFundingForm(projectDto); // dto에 funding 데이터가 없어서 NPE터짐.
        } catch(Exception e){
            e.printStackTrace();
        }
        m.addAttribute("fundingForm", fundingForm);
        log.error("\n\n fundingForm={} \n\n", fundingForm);

        return "project.funding";
    }

    @PostMapping("/funding")
    @ResponseBody
    public ResponseEntity<Boolean> updateFundingPlan(@RequestBody FundingForm fundingForm, ProjectDto projectDto){
        log.error("\n\n fundingForm={} \n\n", fundingForm);
        log.error("\n\n projectDto={} \n\n", projectDto);
        projectDto.updateFunding(fundingForm);

        try {
            projectService.update(projectDto); //성공적으로 업데이트 되었는지 어떻게 판단하지?
            return ResponseEntity.ok().body(true);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }
    }

}
