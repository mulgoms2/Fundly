package com.fundly.admin.service;

import com.fundly.project.model.ProjectMapper;
import com.persistence.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusServiceImpl implements StatusService {
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public int updateStatus(ProjectDto dto) throws Exception {
        return projectMapper.updateStatus(dto);
    }
    @Override
    public List<ProjectDto> getSelectAllPj() throws Exception {
        return projectMapper.selectAllPj();
    }
}
