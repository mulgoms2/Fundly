package com.fundly.user.service;

import com.fundly.user.model.LikeDao;
import com.persistence.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeDao likedao;

    @Override
    public int changeLike(LikeDto likedto) {

        try {

            int result = 0;
            // 찜한 목록 조회
            LikeDto likes = likedao.getLike(likedto);
            //
            if(likes == null) {
                result = likedao.insertLike(likedto);
            } else {
                if (likes.getLike_status() == 1) {
                    likedao.cancelLike(likedto);
                } else {
                    result = likedao.reLike(likedto);
                }
            }
            return result;

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

}
