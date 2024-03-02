package com.fundly.project.service;

import com.fundly.project.exception.ProjectDoesntExistsException;
import com.fundly.project.exception.ProjectNofFoundException;
import com.fundly.project.model.ProjectMapper;
import com.persistence.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }


    // project.toTemplate() 프로젝트 템플릿을 뷰에 맞게 커스텀 할 필요가 있다.
    @Override
    @Transactional(readOnly = true)
    public ProjectTemplate getById(String pj_id) {
//        프로젝트를 아이디로 조회한다.
        ProjectDto pj = projectMapper.getByPjId(pj_id);

        if (pj == null) {
            ProjectNofFoundException ex = new ProjectNofFoundException("해당 아이디로 조회되는 프로젝트가 없습니다.");
            log.error("ProjectServiceImpl.getById(String pj_id) : {}\n {}\n", ex.getMessage(), ex.getStackTrace());
            throw ex;
        }
//        프로젝트를 뷰에 맞는 템플릿으로 반환해준다.
        return ProjectDto.toTemplate(pj);
    }

    @Override
    public ProjectInfoUpdateResponse updatePjInfo(ProjectInfoUpdateRequest request) {
//        업데이트 대상 프로젝트 가져오기
        String pjId = request.getPj_id();
        ProjectDto project = projectMapper.getByPjId(pjId);
//        상태변경하기
        project.updateInfo(request);
//        db에 상태 반영하기
        projectMapper.update(project);
        ProjectDto savedPj = projectMapper.getByPjId(pjId);

        return ProjectDto.toInfoUpdateResponse(savedPj);
    }

    @Override
    public ProjectBasicInfo getProjectBasicInfo(String pj_id) {
//        프로젝트 에디터. 기본정보 탭에 필요한 자료를 가져다준다.
        ProjectDto project = projectMapper.getByPjId(pj_id);
        if (project == null) {
            ProjectNofFoundException ex = new ProjectNofFoundException("해당 아이디로 조회되는 프로젝트가 존재하지 않습니다.");
            log.error("getProjectBasicInfo(String pj_id) : {}\n{}", ex.getMessage(), ex.getStackTrace());
            throw ex;
        }
        return ProjectDto.getInfo(project);
    }

    @Override
    public String getEditingProjectId(String user_email) {
        List<ProjectDto> pjList = projectMapper.getListByUserId(user_email);

        if (!pjList.isEmpty()) {
            for (ProjectDto pj : pjList) {
                if (pj.getPj_status().equals("작성중")) {
                    return pj.getPj_id();
                }
            }
        }
        throw new ProjectNofFoundException("편집중인 프로젝트가 존재하지 않습니다.");
    }

    //    //    아직 컨트롤러에서 어느정도까지 데이터가 필요한지 정확히 정해지지 않아 응답데이터가 미완성이다.
    @Override
    public ProjectAddResponse add(ProjectAddRequest pjAddReq) {
//        프로젝트 추가 요청을 통해 프로젝트를 생성한다.(프로젝트 키는 프로젝트 객체로 변환 될때 자동으로 생성)
        ProjectDto projectDto = pjAddReq.toProject();
        try {
            projectMapper.insert(projectDto);
        } catch (DuplicateKeyException e) {
//            키중복이 발생하면 해당 메서드를 재귀호출한다. 그러면 새로운 프로젝트가 생성되며 새로운 키로 다시 insert를 시도한다.
            add(pjAddReq);
        }
        ProjectDto savedProject = projectMapper.getByPjId(projectDto.getPj_id());
//        저장된 프로젝트를 가져와 응답객체로 반환한다.
        return ProjectDto.toResponseDto(savedProject);
    }
}
