package com.fundly.user.service;

import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {

    @Autowired LikeDao likedao;

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
    public List<LikeDto> getLikeList(LikeDto likedto) {
        try {

            return likedao.AllLikeList(likedto);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }
    }

    public List<LikeDto> getPage(Map map) throws Exception {
        return likedao.selectPage(map);
    }
}
