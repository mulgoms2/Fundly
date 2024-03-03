package com.fundly.project.controller;

import com.fundly.project.exception.ProjectAddFailureException;
import com.fundly.project.exception.ProjectNofFoundException;
import com.fundly.project.exception.ProjectUpdateFailureException;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/editor")
@SessionAttributes("projectDto")
public class ProjectEditorController {
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

    @GetMapping("/start")
    public String getStartPage(@ModelAttribute ProjectDto projectDto) {
        return "project/start";
    }

    @GetMapping("/info")
    public String getBasicInfo(@ModelAttribute ProjectDto projectDto, Model model) {
        model.addAttribute("basicInfo", ProjectDto.toBasicInfo(projectDto));
        return "project.basicInfo";
    }

    @PostMapping("/info")
    public String makeProject(@SessionAttribute String user_email, HttpSession session, Model model) {
//        public String makeProject(ProjectAddRequest addRequest, HttpSession session, Model model) {
//        이곳에서 새로운 프로젝트를 만든다.
        ProjectAddRequest addRequest = ProjectAddRequest.builder().user_id(user_email).build();

        ProjectDto addedProject = projectService.add(addRequest);

        ProjectBasicInfo basicInfo = ProjectDto.toBasicInfo(addedProject);

        model.addAttribute("basicInfo", basicInfo);
        model.addAttribute("projectDto", addedProject);

        session.setAttribute("pj_id", basicInfo.getPj_id());

        return "project.basicInfo";
    }

    @PatchMapping("/info")
    public ResponseEntity<Boolean> updateBasicInfo(@Valid @RequestBody ProjectInfoUpdateRequest updateRequest, ProjectDto project) {
//        프로젝트 객체를 업데이트한다.
        project.updateBasicInfo(updateRequest);
//        프로젝트 업데이트 정보를 db에 반영한다.
        projectService.update(project);
//        성공여부를 응답한다.
        return ResponseEntity.ok(true);
    }

    @ExceptionHandler(ProjectUpdateFailureException.class)
    private ResponseEntity<Boolean> failToUpdate() {
        return ResponseEntity.badRequest().body(false);
    }

    @ExceptionHandler(ProjectAddFailureException.class)
    private String projectAddFail(Model model) {
        model.addAttribute("errorMsg", "프로젝트 생성에 실패하였습니다. 다시 시도해주세요");
        return "project/clientError";
    }

    @ExceptionHandler(Exception.class)
    private String handleCommonError(Model model) {
        model.addAttribute("errorMsg", "잘못 된 접근입니다. 다시 시도해주세요.");
        return "project/clientError";
    }

}
