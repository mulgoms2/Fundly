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
    public int checkLike(LikeDto likedto) throws Exception {

        int result = 0;
        int bno = 1;

        // 찜한 목록 조회
        List<LikeDto> likes = likedao.getLikeList(likedto);

        if(likes.isEmpty()) {
            result = likedao.insertLike(likedto);
        } else {
            for(int i = 0; i < likes.size(); i++) {
                likedto = likes.get(i);
                if(likedto.getLike_status()==1) {
                    likedao.cancelLike(likedto);
                } else {
                    result = likedao.reLike(likedto);
                }
            }
        }
        return result;
    }

    @Override
    public List<LikeDto> getList(LikeDto likedto) throws Exception{
        return likedao.getLikeList(likedto);
    }

    @Override
    public int remove(LikeDto likedto) {
        return 0;
    }
}
