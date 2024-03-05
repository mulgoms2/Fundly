package com.fundly.user.service;

import com.fundly.user.dto.UserProfileDto;
import com.fundly.user.model.UserInfoDao;
import com.fundly.user.model.UserProfileDao;
import com.persistence.dao.FileDao;
import com.persistence.dto.FileDto;
import com.persistence.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileDao userProfileDao;
    private final UserHistService userHistService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //    private final FileDao fileDao;

    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao,UserHistService userHistService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userProfileDao = userProfileDao;
        this.userHistService = userHistService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserProfileDto userProfileInfo(UserDto userDto){
        try {
            UserProfileDto userProfileInfo = userProfileDao.userProfileinfo(userDto);

            log.info("userProfileInfo = \n" + userProfileInfo + "\n");

            if(userProfileInfo != null){
                return userProfileInfo;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserProfileDto userUpdate(UserDto userDto){
        try {

//            log.error("\n\n"  + userDto + "\n\n");

            log.error("====1====");
            UserProfileDto userProfileInfo = userProfileDao.userProfileinfo(userDto);

//            log.error("\n\n"  + userProfileInfo + "\n\n");
            if(userDto.getUser_name()!=null) userProfileInfo.setUser_name(userDto.getUser_name());
            if(userDto.getUser_intro()!=null) userProfileInfo.setUser_intro(userDto.getUser_intro());
            /* pwd 변경 시 기존 번호 가져오고 수정 할 값 입력 */
            if(userDto.getUser_prev_pwd()!=null){
                /* 암호화 체크 */
                if(!bCryptPasswordEncoder.matches(userDto.getUser_prev_pwd(),userProfileInfo.getUser_pwd())){
                    throw new RuntimeException("LOGIN_PWD_ERROR");
                    //   throw new RuntimeException("비밀번호를 확인해 주세요.");
                }
                userProfileInfo.setUser_prev_pwd(userProfileInfo.getUser_pwd());
            }

            if(userDto.getUser_pwd()!=null){
                String userInPwd = userDto.getUser_pwd();
                String encoderPwd = bCryptPasswordEncoder.encode(userInPwd);
                userProfileInfo.setUser_pwd(encoderPwd);
            }
//            log.info("userProfileInfo ===== " + userProfileInfo + "\n\n");

            if(userDto.getUser_phone_no()!=null) userProfileInfo.setUser_phone_no(userDto.getUser_phone_no());

            if(userProfileDao.update(userProfileInfo)!=1){
                log.error("유저정보 업데이트 에러 ");
            }

            if (userHistService.userHistinsert(userProfileInfo) != 1) {
                log.error("유저정보 저장 에러");
            }
            return userProfileDao.userProfileinfo(UserDto.builder().user_email(userProfileInfo.getUser_email()).build());

        } catch (Exception e) {
            log.error("error == " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveImg(FileDto fileDto, String user_email, HttpServletResponse response){

        Objects.requireNonNull(fileDto,"id empty file");
        fileDto.setTable_name("user_info");

        try {
            fileDto.setTable_key(user_email);
            /* 프로필 정보가 있으면 update
            * 프로필 정보가 없으면 insert
            * */

            String fileinfo = userProfileDao.getUserProfileImg(fileDto);

            if(fileinfo==null){
//                log.error("프로필 사진 등록중 ... ");
                userProfileDao.saveFile(fileDto);
            }else{
//                log.error("프로필 사진 업데이트중");
                userProfileDao.updateFile(fileDto);
            }
        } catch (Exception e) {
            log.error("error ChatServiceImpl.saveImageFile()");
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getUserProfileImg(FileDto fileDto) throws Exception {
        return userProfileDao.getUserProfileImg(fileDto);
    }

    public String getlastPwdDate(String user_email) throws Exception {
        return userProfileDao.lastPwdDate(user_email);
    }
}
