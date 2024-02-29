package com.fundly.project.controller;

import com.fundly.project.service.ProjectService;
import com.fundly.project.service.ProjectServiceImpl;
import com.persistence.dto.ProjectInfoUpdateRequest;
import com.persistence.dto.ProjectInfoUpdateResponse;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/editor")
public class ProjectEditorController {
    @Autowired
    ProjectService projectService;
    @PatchMapping("/info")
    public ResponseEntity<ProjectInfoUpdateResponse> info(ProjectInfoUpdateRequest request) {
        ProjectInfoUpdateResponse projectInfoUpdateResponse = projectService.updatePjInfo(request);

        return ResponseEntity.ok().body(projectInfoUpdateResponse);
    }
}
