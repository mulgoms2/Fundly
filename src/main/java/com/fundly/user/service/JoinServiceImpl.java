package com.fundly.user.service;

import com.fundly.project.exception.ProjectUpdateFailureException;
import com.fundly.user.dto.UserJoinDto;
import com.fundly.user.exception.UserJoinFailException;
import com.fundly.user.model.UserJoinDao;
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

    private final UserJoinDao userJoinDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinServiceImpl (UserJoinDao userJoinDao) {this.userJoinDao = userJoinDao;}
    @Autowired
    public JoinServiceImpl(UserJoinDao userJoinDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userJoinDao = userJoinDao;
        this.bCryptPasswordEncoder =bCryptPasswordEncoder;
    }

    @Override
    public int emailCheck(UserJoinDto userJoinDto)  {
        return userJoinDao.emailCheck(userJoinDto);
    }

    @Override
//    @Transactional(rollbackFor = SQLException.class)
//    @Transactional(rollbackFor = Exception.class)
    public int userJoin(UserJoinDto userJoinDto) {

        if(userJoinDao.emailCheck(userJoinDto)==1){
            // 익셉션에 (에러)에 따른 사용자에게 전달을 구별해서 컨트롤러에서 msg 전달 RuntimeException(x)
            UserJoinFailException e = new UserJoinFailException("이미 가입된 사용자입니다.");
            log.debug("emailCheck(userJoinDto) : {}\n{}", e.getMessage(), e.getStackTrace());
            throw e;
        }

        try{

            log.error("유저값 : " + userJoinDto);
            return userJoinDao.insert(setUserInfo(userJoinDto));

        }catch (Exception e){
            log.debug("userJoin(userJoinDto) : {}\n{}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
              /* 어떤 에러가 일어 날 수 있는가 .. ?
          *  Exception 외의 에러에서 joincontroller에서 가능한가 ?
            *  RuntimeException.class, SQLException.class,IllegalArgumentException.class ? */
        }
    }

    public UserJoinDto setUserInfo(UserJoinDto userJoinDto){
        String user_status = "A"; // 활동중 (임의의 회원상태 코드)
        String userInPwd = userJoinDto.getUser_pwd();
        String encoderPwd = bCryptPasswordEncoder.encode(userInPwd);


//        System.out.println("\n\n"+userJoinDto.getUser_phone_no()+"\n 유저 정보에 번호 있나?\n");

        userJoinDto.setUser_id(userJoinDto.getUser_email());
        userJoinDto.setUser_pwd(encoderPwd);
        userJoinDto.setUser_join_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//String.valueOf(LocalDate.now())
        if("on".equals((userJoinDto.getSite_term_agree_yn()))) userJoinDto.setSite_term_agree_yn("Y");
        if("on".equals((userJoinDto.getP_Info_agree_yn()))) userJoinDto.setP_Info_agree_yn("Y");
        if("on".equals((userJoinDto.getAge_agree_yn()))) userJoinDto.setAge_agree_yn("Y");
        if("on".equals((userJoinDto.getP_info_oth_agree_yn()))) userJoinDto.setP_info_oth_agree_yn("Y");
        if("on".equals((userJoinDto.getM_info_rcv_agree_yn()))) userJoinDto.setM_info_rcv_agree_yn("Y");
        userJoinDto.setUser_status(user_status);

        return userJoinDto;
    }
}
