package com.fundly.project.service;

import com.fundly.project.exception.ProjectDoesntExistsException;
import com.fundly.project.model.ProjectMapper;
import com.persistence.dto.ProjectAddRequest;
import com.persistence.dto.ProjectAddResponse;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.ProjectTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }


    // todo project.toTemplate() 프로젝트 템플릿을 뷰에 맞게 커스텀 할 필요가 있다.
    @Override
    @Transactional(readOnly = true)
    public ProjectTemplate getById(String pj_id) throws ProjectDoesntExistsException {
//        프로젝트를 아이디로 조회한다.
        ProjectDto pj = projectMapper.getByPjId(pj_id);
        if (pj == null) {
            ProjectDoesntExistsException ex = new ProjectDoesntExistsException("해당 아이디로 조회되는 프로젝트가 없습니다.");
            log.error("ProjectServiceImpl.getById(String pj_id) : {}\n {}\n", ex.getMessage(), ex.getStackTrace());
            throw ex;
        }
//        프로젝트를 뷰에 맞는 템플릿으로 반환해준다.
        return ProjectDto.toTemplate(pj);
    }


    //    todo 아직 컨트롤러에서 어느정도까지 데이터가 필요한지 정확히 정해지지 않아 응답데이터가 미완성이다.
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
//        저장된 프로젝트를 가져와 응답객체로 반환한다.
        return ProjectDto.toResponseDto(projectDto);
    }
}
