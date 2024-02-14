package com.fundly.user.service;

import com.persistence.dto.UserDto;
import org.springframework.stereotype.Service;

@Service

public interface JoinService {

//    @Transactional(rollbackFor = Exception.class)
    int userJoin(UserDto userDto);

    int emailCheck(UserDto userdto) throws Exception;

    int count() throws Exception;
}
