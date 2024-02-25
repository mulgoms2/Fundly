package com.fundly.user.service;

import com.fundly.user.dto.UserJoinDto;

public interface JoinService {
    Integer userJoin(UserJoinDto userJoinDto) throws Exception;
    Integer emailCheck(UserJoinDto userJoinDto) throws Exception;
}
