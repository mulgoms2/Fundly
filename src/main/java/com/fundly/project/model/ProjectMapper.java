package com.fundly.project.model;

import com.persistence.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    ProjectDto getForLikes(String pj_id) throws Exception;
    int changeLikeCnt(ProjectDto pjdto) throws Exception;
    int deleteAll();
    int insert(ProjectDto project) throws DuplicateKeyException;
    int count();
    ProjectDto getByPjId(String pjId);
    List<ProjectDto> getListByUserId(String userId);
    int update(ProjectDto project);
    int delete(String pjId);
}
