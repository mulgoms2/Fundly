package com.fundly.pay.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Slf4j
public class PayPageHandler {
    private Integer page = 1; // 현재 dummy 번호 (현재 페이지)
    private Integer offset;
    private Integer totalCnt; // 전체 데이터 개수
    private Integer dummySize; // 한 번 로딩할 dummy 데이터 크기 (페이지 크기)
    private Integer totalPage; // 전체 페이지 개수
    private Integer navSize; // 페이지 네비게이션의 크기
    private Integer beginPage; // 페이지 네비게이션의 첫번째 페이지
    private Integer endPage; // 페이지 네비게이션의 마지막 페이지 or 마지막 dummy 번호
    private boolean showPrev; // 이전 페이지로 이동하는 링크를 활성화할 것인지의 여부
    private boolean showNext; // 다음 페이지로 이동하는 링크를 활성화할 것인지의 여부 or 더보기 버튼을 보여줄 것인지의 여부

    public PayPageHandler(String pageName, Integer totalCnt, Integer page) {
        this.totalCnt = totalCnt;
        this.page = page;

        if (pageName.equals("setting")) doShowMorePaging(page); // 마이페이지 페이징
        if (pageName.equals("order")) doNavigationPaging(page); // 주문페이지 페이징
    }

    // 마이페이지 - 더보기 버튼을 이용한 페이징
    private void doShowMorePaging(Integer page) {
        dummySize = 5;
        totalPage = (int)Math.ceil(totalCnt / (double)dummySize); // 올림
        endPage = totalPage;
        showNext = (page < endPage); // 현재페이지가 endPage 보다 작을때 true, 같거나 크면 false
        offset = (page - 1) * dummySize;
    }

    // 주문페이지 - 페이지 네비게이션을 이용한 페이징
    private void doNavigationPaging(Integer page) {
        dummySize = 3;
        navSize = 10;
        totalPage = (int)Math.ceil(totalCnt / (double)dummySize); // 올림
        offset = (page - 1) * dummySize;
        beginPage = (page - 1) / navSize * navSize + 1;
        endPage = Math.min(beginPage + navSize - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }
}