package com.fundly.user.service;

import com.persistence.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;


public interface JoinService {

    @Transactional(rollbackFor = Exception.class)
    int userJoin(UserDto userDto);

    int emailCheck(String user_email) throws Exception;

    int count() throws Exception;
}
