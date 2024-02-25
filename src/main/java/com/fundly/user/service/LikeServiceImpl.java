package com.fundly.user.service;

import com.fundly.project.model.ProjectMapper;
import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {

    LikeDao likedao;
    ProjectMapper pjdao;
    LikeService likeservice;

    public LikeServiceImpl() {}
    public LikeServiceImpl (LikeDao likedao,ProjectMapper pjdao,LikeService likeservice) {
        this.likedao = likedao;
        this.pjdao = pjdao;
        this.likeservice = likeservice;
    }

    @Autowired
    public LikeServiceImpl(LikeDao likedao, ProjectMapper pjdao) {

        this.likedao = likedao;
        this.pjdao = pjdao;

    }

    @Override
    public void changeLike(LikeDto likedto, ProjectDto pjdto) throws Exception {
        try {

            // 찜한 목록 조회
            LikeDto likes = likedao.getLike(likedto);

            if(likes == null) {
                likedao.insertLike(likedto);
                pjdao.upLikeCnt(pjdto);
            } else {
                if (likes.getLike_status() == 1) {
                    likedao.cancelLike(likedto);
                    pjdao.downLikeCnt(pjdto);
                } else {
                    likedao.reLike(likedto);
                    pjdao.upLikeCnt(pjdto);
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

//    @Override
//    public void changeLike(LikeDto likedto) {
//
//        try {
//
//            // 찜한 목록 조회
//            LikeDto likes = likedao.getLike(likedto);
//
//            if(likes == null) {
//                likedao.insertLike(likedto);
//            } else {
//                if (likes.getLike_status() == 1) {
//                    likedao.cancelLike(likedto);
//                } else {
//                    likedao.reLike(likedto);
//                }
//            }
//
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//
//        }
//
//    }

    @Override
    public LikeDto getLike(LikeDto likedto) {

        try {

            return likedao.getLike(likedto);

        } catch(Exception e) {

            throw new RuntimeException(e);

        }

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
    public List<ProjectDto> getListWithPjEntire(LikeDto likedto) throws Exception {
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

    public List<LikeDto> getPage(Map map) throws Exception {
        return likedao.selectPage(map);
    }
}
