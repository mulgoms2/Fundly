package com.fundly.admin.model;

import com.persistence.dto.AdminDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminDao {
    int insert(AdminDto dto) throws Exception;
    int update(AdminDto dto) throws Exception;

}
