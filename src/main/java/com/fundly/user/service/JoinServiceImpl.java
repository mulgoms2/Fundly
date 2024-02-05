package com.fundly.user.service;

import com.fundly.user.model.UserJoinDao;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class JoinServiceImpl implements JoinService {

    @Autowired
    UserJoinDao userJoinDao;

    private String uuid_user_id;

    @Override
//    @Transactional
    public int userJoin(UserDto userDto) {

        log.error(" 테스트 넘어옴 = " + "\\n\\n\\n\\n\\n\\n\\n테스트넘어옴\"");
//         return 0;
//        System.out.println("userJoinDao = " + userJoinDao);
//
        uuid_user_id = UUID.randomUUID().toString();
        String user_status = "A"; // 활동중 (임의의 회원상태 코드)

        userDto.setUser_id(uuid_user_id);
        userDto.setUser_pwd("1111"); // 임시 하드코딩
        userDto.getUser_name();
        userDto.getUser_email();
        userDto.setUser_status(user_status);
        userDto.setDba_reg_id(uuid_user_id);

        try{
            return userJoinDao.insert(userDto);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int emailCheck(String user_email) throws Exception {
        return userJoinDao.emailCheck(user_email);
    }

    @Override
    public int count() throws Exception {
        return userJoinDao.count();
    }
}
