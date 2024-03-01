package com.fundly.project.controller;

import com.fundly.project.service.ProjectService;
import com.fundly.project.service.ProjectServiceImpl;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.ProjectInfo;
import com.persistence.dto.ProjectInfoUpdateRequest;
import com.persistence.dto.ProjectInfoUpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/editor")
@Slf4j
public class ProjectEditorController {
    @Autowired
    ProjectService projectService;

//    @GetMapping("/info")
//    public ResponseEntity<ProjectInfo> getInfo(@RequestParam String user_id) {
//        if (user_id == null || user_id.isEmpty()) {
//            log.debug("ProjectEditorController.getInfo(String user_id) 유저아이디가 널이거나 빈 값이 전달되어왔습니다.");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
////        유저아이디로 식별된 프로젝트의 정보를 리턴한다.
//        ProjectInfo projectInfo = projectService.getProjectInfo(user_id);
//
//        return ResponseEntity.ok().body(projectInfo);
//    }
@GetMapping("/info")
public String getInfo(@RequestParam(required = false) String user_id, Model model) {
//    user_id 가 필수값이기 때문에 빈칸이나 널값이 들어오면 에러페이지로 이동한다.
    if (user_id == null || user_id.isEmpty()) {
        log.debug("ProjectEditorController.getInfo(String user_id) 유저아이디가 널이거나 빈 값이 전달되어왔습니다.");
        model.addAttribute("error", "로그인 후 이용해주세요");
        return "project/clientError";
    }
//        유저아이디로 식별된 프로젝트의 정보를 리턴한다.
    ProjectInfo projectInfo = projectService.getProjectInfo(user_id);

    return "project.basicInfo";
}


    @PatchMapping("/info")
    @ResponseBody
    public ResponseEntity<ProjectInfoUpdateResponse> updateInfo(@Valid @RequestBody ProjectInfoUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        프로젝트 업데이트 요청이 들어오면 서비스에 업데이트를 요청한 후 받은 응답을 리턴한다.
        ProjectInfoUpdateResponse projectInfoUpdateResponse = projectService.updatePjInfo(request);

        return ResponseEntity.ok().body(projectInfoUpdateResponse);
    }
}
