package com.fundly.user.service;

import com.fundly.user.model.UserJoinDao;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class JoinServiceImpl implements JoinService {

    @Autowired
    private UserJoinDao userJoinDao;

    private String uuid_user_id;

    @Override
//    @Transactional

    public int userJoin(UserDto userDto) {

//        uuid_user_id = UUID.randomUUID().toString();
        String user_status = "A"; // 활동중 (임의의 회원상태 코드)

//        userDto.setUser_id(uuid_user_id);
        userDto.setUser_id(userDto.getUser_email());
        userDto.getUser_pwd();
//        userDto.setUser_pwd("1111"); // 임시 하드코딩
        userDto.getUser_name();
        userDto.getUser_email();

        userDto.setUser_join_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//String.valueOf(LocalDate.now())
        if("on".equals((userDto.getSite_term_agree_yn()))) userDto.setSite_term_agree_yn("Y");
        if("on".equals((userDto.getP_Info_agree_yn()))) userDto.setP_Info_agree_yn("Y");
        if("on".equals((userDto.getAge_agree_yn()))) userDto.setAge_agree_yn("Y");
        if("on".equals((userDto.getP_info_oth_agree_yn()))) userDto.setP_info_oth_agree_yn("Y");
        if("on".equals((userDto.getM_info_rcv_agree_yn()))) userDto.setM_info_rcv_agree_yn("Y");
        userDto.setUser_status(user_status);
//        userDto.setDba_reg_id(uuid_user_id);
        userDto.setDba_reg_id(userDto.getUser_email());

        try{
            return userJoinDao.insert(userDto);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int emailCheck(UserDto userdto) throws Exception {
        return userJoinDao.emailCheck(userdto);
    }

    @Override
    public int count() throws Exception {
        return userJoinDao.count();
    }
}
