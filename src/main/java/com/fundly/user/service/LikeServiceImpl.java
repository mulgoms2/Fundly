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

        // 찜한 목록 조회
        List<LikeDto> findList = likedao.getLikeList(likedto);

        if(findList.isEmpty()) {
            // 찜한 목록에 없으면 처음 좋아요
            result = likedao.insertLike(likedto);
        } else {
            // 목록에 있으면 좋아요 취소
            likedao.cancelLike(likedto);
        }
        return result;
    }

    @Override
    public List<LikeDto> getList(LikeDto likedto) throws Exception{

        // 찜한 목록 조회
        List<LikeDto> findList = likedao.getLikeList(likedto);
        // 3. 찜한 목록이 없으면 null 반환
        // 4. 찜한 목록이 있으면 DB 하나씩 조회
        // 5. 해당 사용자가 찜한 게시물의 이름으로 정보를 조회
        return null;
    }

    @Override
    public int remove(LikeDto likedto) {
        return 0;
    }
}
