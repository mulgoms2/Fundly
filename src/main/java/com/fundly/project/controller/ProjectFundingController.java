package com.fundly.project.controller;

import com.fundly.project.exception.ProjectNotFoundException;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;

@Slf4j
@Controller
@SessionAttributes("projectDto")
@RequestMapping("/project/editor")
public class ProjectFundingController {

    ProjectService projectService;
    FundingFormValidator formValidator;

    @ModelAttribute("projectDto")
    ProjectDto initProjectEditor(@SessionAttribute String user_email) {
        try {
            ProjectDto projectDto = projectService.getEditingProject(user_email);
            return projectDto;
        } catch (ProjectNotFoundException e) {
            return null;
        }
    }

    @Autowired
    public ProjectFundingController(ProjectService projectService, FundingFormValidator formValidator){
        this.projectService = projectService;
        this.formValidator = formValidator;
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
    public ResponseEntity<Boolean> updateFundingPlan(@RequestBody @Validated FundingForm fundingForm, BindingResult result, ProjectDto projectDto){
        log.error("\n\n fundingForm={} \n\n", fundingForm);
        log.error("\n\n projectDto={} \n\n", projectDto);

        if(result.hasErrors()){
            System.out.println("\n\n***** errorList");
            result.getAllErrors().stream().forEach(System.out::println);
            //테스트
        }

        projectDto.updateFunding(fundingForm);

        try {
            projectService.update(projectDto); //성공적으로 업데이트 되었는지 어떻게 판단하지?
            return ResponseEntity.ok().body(true);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(false);
        }
    }

    //JSON데이터는 deserialize에 실패하면 HttpMessageNotReadableException을 던진다.
    //(JSON parse error 발생). 객체를 만들지 못하면 Validation 단계로 넘어가지도 못함.
    @ExceptionHandler(HttpMessageNotReadableException.class) //공통예외로 뺄 수 있으면 좋겠다.
    public ResponseEntity<String> failedToJSONConversion(HttpMessageNotReadableException e){
        log.error("\n\n errorList \n\n");
        if(e.getCause() instanceof DateTimeParseException){
            DateTimeParseException e2 = (DateTimeParseException)e.getCause();
            System.out.println("e2 = " + e2);
            return ResponseEntity.badRequest().body(e2.getMessage());
        }
//        log.error("\n\ne.getCause()={}",e.getCause());
//        return ResponseEntity.badRequest().body(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());

    }
}
