package com.fundly.user.model;

import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoDao {

    int count() throws Exception;

    UserDto userInfo(@Param ("user_email") String user_email) throws Exception ; //, @Param("user_pwd") String user_pwd) throws Exception;

}
