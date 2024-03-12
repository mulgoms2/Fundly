package com.fundly.user.service;

import com.fundly.user.dto.UserJoinDto;

public interface JoinService {
    int userJoin(UserJoinDto userJoinDto) ;
    int emailCheck(UserJoinDto userJoinDto) ;
}
