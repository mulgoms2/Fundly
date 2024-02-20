package com.fundly.user.model;

import com.fundly.user.dto.UserLoginDto;
import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginDao {
    UserLoginDto selectUser(UserLoginDto userLoginDto) throws Exception;
}
