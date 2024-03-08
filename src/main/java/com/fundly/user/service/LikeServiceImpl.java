package com.fundly.user.service;

import com.fundly.project.exception.ProjectNotFoundException;
import com.fundly.project.model.ProjectMapper;
import com.fundly.user.dto.LikeProjectDto;
import com.fundly.user.model.LikeDao;
import com.fundly.user.validate.LikeValidator;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {

    LikeDao likedao;
    ProjectMapper pjdao;
    LikeService likeservice;
    LikeValidator likevalidator;

    public LikeServiceImpl() {}
    public LikeServiceImpl (LikeDao likedao,ProjectMapper pjdao,LikeService likeservice, LikeValidator likevalidator) {
        this.likedao = likedao;
        this.pjdao = pjdao;
        this.likeservice = likeservice;
        this.likevalidator = likevalidator;
    }

    @Autowired
    public LikeServiceImpl(LikeDao likedao, ProjectMapper pjdao) {

        this.likedao = likedao;
        this.pjdao = pjdao;

    }

    @Override
    public void changeLike(LikeDto likedto, ProjectDto pjdto) {

        // 프로젝트가 존재하는지에 대한 유효성 검사
//        Errors errors = new BeanPropertyBindingResult(likedto, "likedto");
//        likevalidator.validate(likedto,errors);

        // 찜한 목록 조회
        LikeDto likes = likedao.checkLike(likedto);

        // 목록에 있는지 확인 후 없으면 처음 좋아요
        if (likes == null) {
            likedao.insertLike(likedto);
            pjdao.upLikeCnt(pjdto);
        } else {
            // 이미 있다면 좋아요상태를 체크 후
            // 좋아요 취소
            if (likes.getLike_status() == 1) {
                likedao.cancelLike(likedto);
                pjdao.downLikeCnt(pjdto);
            // 다시 좋아요
            } else {
                likedao.reLike(likedto);
                pjdao.upLikeCnt(pjdto);
            }
        }
    }

    @Override
    public List<LikeProjectDto> getLikeList(String user_id) {

        return likedao.AllLikeListWithPj(user_id);

    }

    @Override
    public int getLikeCnt(LikeDto likedto) {
        return likedao.countLike(likedto);
    }

    @Override
    @Transactional
    public List<ProjectDto> getListWithPjStatus(LikeDto likedto, String pj_status) {
        try {

            // 좋아요 목록
            List<LikeDto> likes = likedao.AllLikeList(likedto);

            // 프로젝트 정보 담을 리스트
            List<ProjectDto> projectList = new ArrayList<>();

            // 좋아요 목록의 프로젝트 이름으로 프로젝트 정보 가져오기
            if (likes != null && !likes.isEmpty()) {

                // 원하는 상태값을 지정한다
                for (LikeDto like : likes) {
                    String pj_id = like.getPj_id();
                    ProjectDto pjdto = pjdao.selectByStatus(pj_id,pj_status);

                    // 프로젝트 이름과 상태가 일치하지 않으면 null을 반환하는데 이것을 리스트에 담지 않게 한다.
                    if(pjdto != null) {
                        projectList.add(pjdto);
                    }
                }
            } return projectList;

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    // 좋아요 목록 전체를 보여준다.
    @Override
    public List<ProjectDto> getListWithPjEntire(LikeDto likedto) {
        // 좋아요 목록
        List<LikeDto> likes = likedao.AllLikeList(likedto);

        // 프로젝트 정보 담을 리스트
        List<ProjectDto> projectList = new ArrayList<>();

        // 좋아요 목록의 프로젝트 이름으로 프로젝트 정보 가져오기
        if (likes != null && !likes.isEmpty()) {

            // 원하는 상태값을 지정한다
            for (LikeDto like : likes) {
                String pj_id = like.getPj_id();
                ProjectDto pjdto = pjdao.selectByEntireStatus(pj_id);
//
//                 프로젝트 이름과 상태가 일치하지 않으면 null을 반환하는데 이것을 리스트에 담지 않게 한다.
                if(pjdto != null) {
                    projectList.add(pjdto);
                }
            }
        } return projectList;
    }

    public List<LikeDto> getPage(Map map) {
        return likedao.selectPage(map);
    }
}

