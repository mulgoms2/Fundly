package com.fundly.user.model;

import com.fundly.user.dto.OauthDto;
import com.fundly.user.dto.UserJoinDto;
import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthDao {
    Integer check(OauthDto oauthDto) throws Exception;
    OauthDto OauthInfo(OauthDto oauthDto) throws Exception ;
    int insert(OauthDto oauthDto) throws Exception;
    int update(OauthDto oauthDto) throws Exception;
}
