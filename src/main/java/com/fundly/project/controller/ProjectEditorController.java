package com.fundly.project.controller;

import com.fundly.project.exception.ProjectAddFailureException;
import com.fundly.project.exception.ProjectNofFoundException;
import com.fundly.project.service.ProjectService;
import com.persistence.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/editor")
@Slf4j
public class ProjectEditorController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/start")
    public String startPage(@SessionAttribute(required = false) String user_email, HttpSession session, Model model) {
        if (user_email == null || user_email.isEmpty()) {
            return "user/login";
        }
//        작성중인 프로젝트가 존재하는지 확인한다.
        try {
            String pj_id = projectService.getEditingProjectId(user_email);
            model.addAttribute("pj_id", pj_id);

            session.setAttribute("pj_id", pj_id);
        } catch (ProjectNofFoundException e) {
//            편집중인 프로젝트가 존재하지 않으면. 모델이 비어있어 뷰에서 새로 시작하기 버튼이 나온다.
            return "project/start";
        }
        return "project/start";
    }

    @GetMapping("/info")
//    public String getBasicInfo(@RequestParam(required = false) String pj_id, Model model) {
        public String getBasicInfo(@SessionAttribute(required = false) String pj_id, Model model) {
//        현재 진행중인 프로젝트가 존재하는 유저가 이어서 작성하기 버튼을 눌렀을 때 실행된다.
//        log.error("\n\n pj_id={} \n\n", pj_id);
        String errPage = "project/clientError";
        String errorMsg = "errorMsg";
        if (pj_id == null || pj_id.isEmpty()) {
            model.addAttribute(errorMsg, "서버에 일시적인 장애가 발생하였습니다. 다시 시도해주세요.");
            return errPage;
        }
//        작성중인 프로젝트의 기본정보 탭에 필요한 데이터를 불러온다.
        try {
            ProjectBasicInfo projectInfo = projectService.getProjectBasicInfo(pj_id);
            model.addAttribute("basicInfo", projectInfo);
        } catch (ProjectNofFoundException e) {
//            이어서 작성하려는 프로젝트가 조회되지 않는 예외적인 상황
            model.addAttribute(errorMsg, "작성하려는 프로젝트가 존재하지 않습니다. 다시 시도해주세요");
            return errPage;
        }
        return "project.basicInfo";
    }

    @PostMapping("/info")
    public String makeProject(@SessionAttribute(required = false) String user_email, HttpSession session, Model model) {
        if (user_email == null || user_email.isEmpty()) {
            model.addAttribute("errorMsg", "로그인 후 이용해주세요.");
            return "project/clientError";
        }
//        이곳에서 새로운 프로젝트를 만든다.
        ProjectAddRequest addRequest = ProjectAddRequest.builder().user_id(user_email).build();

        try {
//            프로젝트 추가 후 응답객체를 info 객체로 바꿔 뷰로 보낸다.
            ProjectAddResponse addResponse = projectService.add(addRequest);
            ProjectBasicInfo basicInfo = addResponse.toInfoDto();

            model.addAttribute("basicInfo", basicInfo);

            session.setAttribute("pj_id", basicInfo.getPj_id());
        } catch (ProjectAddFailureException e) {
            return "project/error";
        }

        return "project.basicInfo";
    }

    @PatchMapping("/info")
    @ResponseBody
    public ResponseEntity<Boolean> updateBasicInfo(@Valid @RequestBody ProjectInfoUpdateRequest request, Model model, BindingResult result) {
        if (result.hasErrors()) {
//            프로젝트 아이디가 없으면 에러가 발생한다. 나머지 정보들은 null 이어도 무관하다.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        try {
            projectService.updatePjInfo(request);
        } catch (ProjectNofFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }

        return ResponseEntity.ok(true);
    }
}
