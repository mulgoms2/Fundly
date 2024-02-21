package com.fundly.user.service;

import com.fundly.project.model.ProjectMapper;
import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import lombok.RequiredArgsConstructor;
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

    @Autowired
    public LikeServiceImpl(LikeDao likedao, ProjectMapper pjdao) {

        this.likedao = likedao;
        this.pjdao = pjdao;

    }

    @Override
    public void changeLike(LikeDto likedto) {

        try {

            // 찜한 목록 조회
            LikeDto likes = likedao.getLike(likedto);

            if(likes == null) {
                likedao.insertLike(likedto);
            } else {
                if (likes.getLike_status() == 1) {
                    likedao.cancelLike(likedto);
                } else {
                    likedao.reLike(likedto);
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

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
    public List<LikeDto> getLikeList(LikeDto likedto) {
        try {

            // 프로젝트나 아이디가 지워졌으면 좋아요 목록에도 지운다.
//            likedao.deleteLike(likedto);

            // 좋아요 목록에 있는 프로젝트의 정보를 프로젝트테이블에서 가져온다.
//            pjdao.getForLikes(likedto.getPj_id());

            return likedao.AllLikeList(likedto);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    @Transactional
    public List<ProjectDto> getLikeListWithPj(LikeDto likedto) throws Exception {

        // 좋아요 목록
        List<LikeDto> likes = likedao.AllLikeList(likedto);

        // 프로젝트 정보 담을 리스트
        List<ProjectDto> projectList = new ArrayList<>();

        // 좋아요 목록의 프로젝트 이름으로 프로젝트 정보 가져오기
        if (likes != null && !likes.isEmpty()) {
            for (LikeDto like : likes) {
                String pj_id = like.getPj_id();
                ProjectDto pjdto = pjdao.getForLikes(pj_id);
                projectList.add(pjdto);
            }
        } return projectList;

    }

    public List<LikeDto> getPage(Map map) throws Exception {
        return likedao.selectPage(map);
    }
}
