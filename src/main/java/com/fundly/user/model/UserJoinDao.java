package com.fundly.user.model;

import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserJoinDao {
    int emailCheck(UserDto userdto) throws Exception;
    int insert(UserDto dto) throws Exception;
}
