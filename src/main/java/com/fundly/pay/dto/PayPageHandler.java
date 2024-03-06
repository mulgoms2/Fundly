package com.fundly.pay.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class PayPageHandler {
    private Integer page = 1; // 현재 dummy 번호 (현재 페이지)
    private Integer offset;
    private Integer totalCnt; // 전체 데이터 개수
    private Integer dummySize = 5; // 한 번 로딩할 dummy 데이터 크기
    private Integer totalPage; // dummy 개수 (페이지 개수)
    private Integer endPage; // 마지막 페이지
    private boolean showNext; // 더보기 버튼을 보여줄 것인지의 여부

    public PayPageHandler(Integer totalCnt, Integer page) {
        this.totalCnt = totalCnt;
        this.page = page;

        doPaging(page);
    }

    private void doPaging(Integer page) {
        totalPage = (int)Math.ceil(totalCnt / (double)dummySize); // 올림
        endPage = totalPage;
        showNext = (page < endPage); // 현재페이지가 endPage 보다 작을때 true, 같거나 크면 false
        offset = (page - 1) * dummySize;
    }
}