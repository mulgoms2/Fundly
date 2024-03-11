package com.fundly.admin.service;

import com.persistence.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusService {

    int updateStatus(ProjectDto dto) throws Exception;

    public List<ProjectDto> getSelectAllPj() throws Exception;
}
