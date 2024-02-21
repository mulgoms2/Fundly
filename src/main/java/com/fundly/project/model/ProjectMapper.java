package com.fundly.project.model;

import com.persistence.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int deleteAll() throws NonTransientDataAccessException;
//    같은 행 중복시 DuplicateKeyExeption 발생,
    int insert(ProjectDto project) throws DataAccessException;
    int count() throws NonTransientDataAccessException;
    ProjectDto getByPjId(String pjId)  throws NonTransientDataAccessException;
    List<ProjectDto> getListByUserId(String userId)  throws NonTransientDataAccessException;
    int update(ProjectDto project) throws NonTransientDataAccessException;
    int delete(String pjId) throws NonTransientDataAccessException;
    List<ProjectDto> selectAllPj() throws NonTransientDataAccessException;

    ProjectDto selectBySatus(@Param("pj_id") String pjId,@Param("pj_status") String status);
    int changeLikeCnt(ProjectDto pjdto) throws Exception;
}
