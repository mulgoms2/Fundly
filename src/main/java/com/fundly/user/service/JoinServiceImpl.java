package com.fundly.user.service;

import com.fundly.user.model.UserJoinDao;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class JoinServiceImpl implements JoinService {

    private UserJoinDao userJoinDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinServiceImpl () {}
    public JoinServiceImpl (UserJoinDao userJoinDao) {this.userJoinDao = userJoinDao;}
    @Autowired
    public JoinServiceImpl(UserJoinDao userJoinDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userJoinDao = userJoinDao;
        this.bCryptPasswordEncoder =bCryptPasswordEncoder;
    }

    @Override
    public int emailCheck(UserDto userdto) throws Exception {



        return userJoinDao.emailCheck(userdto);
    }

    @Override
//    @Transactional(rollbackFor = SQLException.class)
    @Transactional(rollbackFor = Exception.class)
    public int userJoin(UserDto userDto) throws Exception {

        String user_status = "A"; // 활동중 (임의의 회원상태 코드)

        try{

            if(userJoinDao.emailCheck(userDto)==1){
                throw new RuntimeException("이미 가입된 사용자입니다.");
            }

            String userInPwd = userDto.getUser_pwd();
            String encoderPwd = bCryptPasswordEncoder.encode(userInPwd);

            System.out.println("bCryptPasswordEncoder.encode(\"qwerrr123!\") = " + bCryptPasswordEncoder.encode("qwerrr123!"));
            System.out.println("userInPwd = " + userInPwd);
            System.out.println("encoderPwd = " + encoderPwd);

            userDto.setUser_id(userDto.getUser_email());
//            userDto.getUser_pwd();
            userDto.setUser_pwd(encoderPwd);
            userDto.getUser_name();
            userDto.getUser_email();
            userDto.setUser_join_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//String.valueOf(LocalDate.now())
            if("on".equals((userDto.getSite_term_agree_yn()))) userDto.setSite_term_agree_yn("Y");
            if("on".equals((userDto.getP_Info_agree_yn()))) userDto.setP_Info_agree_yn("Y");
            if("on".equals((userDto.getAge_agree_yn()))) userDto.setAge_agree_yn("Y");
            if("on".equals((userDto.getP_info_oth_agree_yn()))) userDto.setP_info_oth_agree_yn("Y");
            if("on".equals((userDto.getM_info_rcv_agree_yn()))) userDto.setM_info_rcv_agree_yn("Y");
            userDto.setUser_status(user_status);
            userDto.setDba_reg_id(userDto.getUser_email());


            System.out.println("userDto = " + userDto);
            return userJoinDao.insert(userDto);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
