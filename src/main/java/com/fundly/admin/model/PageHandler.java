package com.fundly.admin.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageHandler {
    final static int NAV_SIZE = 10;
    private int totalCnt; //총 게시물 갯수
    private int pageSize = 10; //한페이지 크기
    private int totalPage;//전체 페이지 갯수
    private int page; // 현재 페이지
    private int beginPage ; // 내비게이션의 첫번째 페이지
    private int endPage; // 내비게이션의 마지막 페이지
    private boolean showPrev; // 이전 페이지로의 이동
    private boolean showNext; // 다음 페이지로의 이동

    public PageHandler(int totalCnt, Integer  page){
        this(totalCnt, page,10);
    }
    public PageHandler(int totalCnt,  Integer  page, int  pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;
        totalPage = (int)Math.ceil(totalCnt/(double)pageSize);
        beginPage = (page-1)/ NAV_SIZE*NAV_SIZE+1;
        endPage =  Math.min(beginPage+NAV_SIZE-1,totalPage);
        showPrev = (beginPage != 1);
        showNext = (endPage != totalPage);
    }
    void print(){
        System.out.print("page = "+page);
        System.out.print(showPrev ? "[PREV]":"");
        for (int i= beginPage; i<= endPage; i++){
            System.out.print(i+" ");
        }
        System.out.print(showNext ? "[NEXT]":"");
    }
}
