package com.fundly.project.service;

import com.fundly.project.controller.StoryForm;
import com.persistence.dto.*;

public interface ProjectService {
    ProjectDto get(String pjId);

    ProjectDto add(ProjectAddRequest pjAddReq);

    StoryForm updatePjStory(StoryForm storyForm);
    StoryForm getStoryFormByPjId(String pj_id);

    ProjectBasicInfo getProjectBasicInfo(String pj_id);
    ProjectDto getEditingProject(String user_email);

    ProjectDto update(ProjectDto projectDto);
    ProjectBasicInfo updatePjInfo(ProjectInfoUpdateRequest pjInfoUpdate);

    int selectLikeCnt(ProjectDto pjdto) throws Exception;
}
