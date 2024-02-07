package com.fundly.user.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLoginDao {

    int count() throws Exception;

    int emailCheck(String email) throws Exception;

    int idCheck(String email) throws Exception;
//    int insert(UserDto dto) throws Exception;
}
