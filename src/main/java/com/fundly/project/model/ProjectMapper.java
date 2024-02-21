package com.fundly.project.model;

import com.persistence.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProjectMapper {

    ProjectDto getForLikes(String pj_id) throws Exception;

    int changeLikeCnt(ProjectDto pjdto) throws Exception;
}
