package com.fundly.user.model;

import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserJoinDao {

    int count() throws Exception;

    int emailCheck(UserDto userdto) throws Exception;

    int idCheck() throws Exception;

    int insert(UserDto dto) throws Exception;
}
