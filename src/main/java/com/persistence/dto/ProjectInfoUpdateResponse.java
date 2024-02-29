package com.persistence.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProjectInfoUpdateResponse {
    private String pj_id;
    private String ctg; //카테고리
    private String sub_ctg; //세부 카테고리
    private String pj_long_title; //프로젝트 긴 제목
    private String pj_short_title; //프로젝트 짧은 제목
    private String pj_short_intro; //프로제트 요약(기본정보 탭에서 작성)
    private String pj_thumbnail_url; // 프로젝트 대표이미지
    private String pj_tag; // 검색 태그
}
