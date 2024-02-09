package com.fundly.user.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginDao {

    int count() throws Exception;

    int emailCheck(String email) throws Exception;

    int LoginCheck(@Param ("user_email") String user_email, @Param("user_pwd") String user_pwd) throws Exception;

    int idCheck(String email) throws Exception;
//    int insert(UserDto dto) throws Exception;
}
