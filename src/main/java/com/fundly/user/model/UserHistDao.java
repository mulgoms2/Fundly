package com.fundly.user.model;

import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.dto.UserProfileDto;
import com.persistence.dto.UserHistDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserHistDao {
    Integer insert(UserHistDto userHistDto) throws Exception;
}
