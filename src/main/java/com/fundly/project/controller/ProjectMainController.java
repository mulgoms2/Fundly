package com.fundly.project.controller;

import com.fundly.project.service.ProjectService;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.ProjectTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/project")
public class ProjectMainController {
    ProjectService projectService;

    @Autowired
    public ProjectMainController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    //    프로젝트 뷰에서 카테고리별 요청을 받아 카테고리별 프로젝트를 리스트로 만들어 반환한다.
    @GetMapping("/{category}")
    public ResponseEntity<List<ProjectTemplate>> getListByCategory(@PathVariable String category) {
        List<ProjectDto> listByCategory = projectService.getListByCategory(category);

//        프로젝트Dto 리스트를 => 프로젝트 템플릿 리스트로 변환
        if (listByCategory == null) return ResponseEntity.ok(new ArrayList<>());

        List<ProjectTemplate> pjTemplateList = listByCategory.stream()
                .filter(this::isFundingStatus)
                .map(ProjectDto::toProjectTemplate)
                .collect(toList());

        return ResponseEntity.ok(pjTemplateList);
    }

    private boolean isFundingStatus(ProjectDto projectDto) {
        return projectDto.getPj_status()
                .equals("승인");
    }
}
