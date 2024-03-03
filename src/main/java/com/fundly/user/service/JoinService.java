package com.fundly.user.service;

import com.fundly.user.dto.UserJoinDto;

public interface JoinService {
    int userJoin(UserJoinDto userJoinDto) throws Exception;
    int emailCheck(UserJoinDto userJoinDto) throws Exception;
}
