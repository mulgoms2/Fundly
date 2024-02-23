package com.fundly.user.model;

import com.fundly.user.dto.OauthDto;
import com.persistence.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthDao {
    OauthDto OauthInfo(OauthDto oauthDto) throws Exception ;
    int insert(OauthDto oauthDto) throws Exception;
}
