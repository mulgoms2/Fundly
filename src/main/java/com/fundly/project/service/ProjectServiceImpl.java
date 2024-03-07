package com.fundly.project.service;

import com.fundly.project.controller.StoryForm;
import com.fundly.project.exception.ProjectNotFoundException;
import com.fundly.project.exception.ProjectUpdateFailureException;
import com.fundly.project.model.ProjectMapper;
import com.persistence.dao.FileDao;
import com.persistence.dto.ProjectAddRequest;
import com.persistence.dto.ProjectBasicInfo;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.ProjectInfoUpdateRequest;
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
    public ProjectServiceImpl(ProjectMapper projectMapper, FileDao fileDao) {
        this.projectMapper = projectMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public ProjectDto get(String pj_id) {
//        프로젝트를 아이디로 조회한다.
        ProjectDto pj = projectMapper.getByPjId(pj_id);

        if (pj == null) {
            ProjectNotFoundException ex = new ProjectNotFoundException("해당 아이디로 조회되는 프로젝트가 없습니다.");
            log.error("ProjectServiceImpl.getById(String pj_id) : {}\n {}\n", ex.getMessage(), ex.getStackTrace());
            throw ex;
        }
        return pj;
    }

    @Override
    public ProjectBasicInfo updatePjInfo(ProjectInfoUpdateRequest request) {
//        업데이트 대상 프로젝트 가져오기
        String pjId = request.getPj_id();
        ProjectDto project = projectMapper.getByPjId(pjId);
        if (project == null) {
            String errMsg = "업데이트 대상 프로젝트를 찾을 수 없습니다.";
            log.error(errMsg);
            throw new ProjectNotFoundException(errMsg);
        }

//        상태변경하기
        project.updateBasicInfo(request);
//        db에 상태 반영하기
        projectMapper.update(project);
        ProjectDto savedPj = projectMapper.getByPjId(pjId);

        return ProjectDto.toBasicInfo(savedPj);
    }


    @Override
    // Tx에 해당하지 않는듯. 쿼리 두번 호출하지만, 어차피 select에서 에러나면 dto를 꺼내올 수도 없으니 두번째 쿼리도 에러남.
    public StoryForm updatePjStory(StoryForm storyForm) { //프로젝트 계획 부분 업데이트
        ProjectDto project = projectMapper.getByPjId(storyForm.getPj_id());
        project.updateStory(storyForm); // projectDto의 해당 필드값을 초기화한다
        projectMapper.update(project); //DB값 update

        ProjectDto updatedProject = projectMapper.getByPjId(project.getPj_id());//DB에서 업데이트된 데이터를 꺼내옴

        return ProjectDto.toStoryForm(updatedProject); //반환값이 dto니까 테스트할때는 requestForm과 update된 dto의 필드값을 비교.
    }

    @Override
    public StoryForm getStoryFormByPjId(String pj_id) {
        ProjectDto projectDto = projectMapper.getByPjId(pj_id);
        return ProjectDto.toStoryForm(projectDto);
    }


    //    //    todo 아직 컨트롤러에서 어느정도까지 데이터가 필요한지 정확히 정해지지 않아 응답데이터가 미완성이다.
    public ProjectBasicInfo getProjectBasicInfo(String pj_id) {
//        프로젝트 에디터. 기본정보 탭에 필요한 자료를 가져다준다.
        ProjectDto project = projectMapper.getByPjId(pj_id);
        if (project == null) {
            ProjectNotFoundException ex = new ProjectNotFoundException("해당 아이디로 조회되는 프로젝트가 존재하지 않습니다.");
            log.error("getProjectBasicInfo(String pj_id) : {}\n{}", ex.getMessage(), ex.getStackTrace());
            throw ex;
        }
        return ProjectDto.toBasicInfo(project);
    }

    @Override
    public ProjectDto getEditingProject(String user_email) {
        List<ProjectDto> pjList = projectMapper.getListByUserId(user_email);

        if (!pjList.isEmpty()) {
            for (ProjectDto project : pjList) {
                if (project.getPj_status().equals("작성중")) {
                    return project;
                }
            }
        }
        throw new ProjectNotFoundException("편집중인 프로젝트가 존재하지 않습니다.");
    }

    //    //    아직 컨트롤러에서 어느정도까지 데이터가 필요한지 정확히 정해지지 않아 응답데이터가 미완성이다.
    @Override
    public ProjectDto add(ProjectAddRequest pjAddReq) {
//        프로젝트 추가 요청을 통해 프로젝트를 생성한다.(프로젝트 키는 프로젝트 객체로 변환 될때 자동으로 생성)
        ProjectDto projectDto = pjAddReq.toProject();
        try {
            projectMapper.insert(projectDto);
        } catch (DuplicateKeyException e) {
//            키중복이 발생하면 해당 메서드를 재귀호출한다. 그러면 새로운 프로젝트가 생성되며 새로운 키로 다시 insert를 시도한다.
            add(pjAddReq);
        }
        ProjectDto savedProject = projectMapper.getByPjId(projectDto.getPj_id());

        return savedProject;
    }

    @Override
//    가장 범용적인 프로젝트 업데이트 메서드. 전체 필드를 업데이트 한다. 주의사항 dto에 null이 담겨온 필드는 null로 업데이트된다.
    public ProjectDto update(ProjectDto projectDto) {
        int rowCount = projectMapper.update(projectDto);

        if (rowCount == 0) {
            ProjectUpdateFailureException ex = new ProjectUpdateFailureException("업데이트 대상 프로젝트를 찾을 수 없습니다.");
            log.debug("update(ProjectDto) : {}\n{}", ex.getMessage(), ex.getStackTrace());
            throw ex;
        }
        ProjectDto savedPj = projectMapper.getByPjId(projectDto.getPj_id());
        return savedPj;
    }

    public int selectLikeCnt(ProjectDto pjdto) throws Exception {
        return projectMapper.selectLikeCnt(pjdto);
    }
}
