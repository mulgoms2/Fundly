package com.fundly.project.service;

import com.fundly.project.controller.StoryForm;
import com.persistence.dto.*;

public interface ProjectService {
    ProjectTemplate getById(String pjId);

    ProjectAddResponse add(ProjectAddRequest pjAddReq);

    ProjectInfoUpdateResponse updatePjInfo(ProjectInfoUpdateRequest pjInfoUpdate);

    StoryForm updatePjStory(StoryForm storyForm);
    StoryForm getStoryFormByPjId(String pj_id);
}
