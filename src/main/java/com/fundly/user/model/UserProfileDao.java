package com.fundly.user.model;

import com.fundly.user.dto.OauthDto;
import com.fundly.user.dto.UserProfileDto;
import com.persistence.dto.UserDto;
import com.persistence.dto.UserHistDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfileDao {

    UserProfileDto userProfileinfo(UserDto userDto) throws Exception ;
    UserProfileDto update(UserProfileDto userProfileDto) throws Exception ;
}
