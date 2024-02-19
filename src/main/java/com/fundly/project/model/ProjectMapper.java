package com.fundly.project.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProjectMapper {
    int deleteAll();

    void insert();
}
