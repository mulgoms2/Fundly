package com.fundly.user.model;

import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoDao {
    UserDto userInfo(UserDto userdto) throws Exception ;
    String userJoindate(UserDto userDto) throws Exception;
}
