package com.fundly.project.model;

import com.persistence.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.NonTransientDataAccessException;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int deleteAll() throws DataAccessException;
//    같은 행 중복시 DuplicateKeyExeption 발생,
    int insert(ProjectDto project) throws DataAccessException;
    int count() throws DataAccessException;
    ProjectDto getByPjId(String pjId)  throws DataAccessException;
    List<ProjectDto> getListByUserId(String userId)  throws DataAccessException;
    int update(ProjectDto project) throws DataAccessException;
    int delete(String pjId) throws DataAccessException;
    List<ProjectDto> selectAllPj() throws DataAccessException;
    ProjectDto selectByEntireStatus(@Param("pj_id") String pjId);
    ProjectDto selectByStatus(@Param("pj_id") String pjId, @Param("pj_status") String status);
    int upLikeCnt(ProjectDto pjdto) throws Exception;
    int downLikeCnt(ProjectDto pjdto) throws Exception;
}
