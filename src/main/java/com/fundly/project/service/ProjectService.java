package com.fundly.project.service;

import com.persistence.dto.ProjectAddRequest;
import com.persistence.dto.ProjectAddResponse;
import com.persistence.dto.ProjectDto;
import com.persistence.dto.ProjectTemplate;

public interface ProjectService {
    ProjectTemplate getById(String pjId);

    ProjectAddResponse add(ProjectAddRequest pjAddReq);
}
