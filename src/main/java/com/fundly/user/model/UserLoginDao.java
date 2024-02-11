package com.fundly.user.model;

import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginDao {

    int count() throws Exception;

//    int userCheck(@Param ("user_email") String user_email, @Param("user_pwd") String user_pwd) throws Exception;

    UserDto selectUser(@Param ("user_email") String user_email, @Param("user_pwd") String user_pwd) throws Exception;

}
