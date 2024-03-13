
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tumblbug Admin</title>
    <link rel="stylesheet" href="<q:url value='/static/admin/css/webfont.css'/>">
    <link rel="stylesheet" href="<q:url value='/static/admin/css/common.css'/>">
    <link href="<q:url value='/static/admin/css/style.css'/>"  rel="stylesheet"/>

</head>
<body>
<button class="mnBtnbl">
    <i class="arrow"></i>
</button>
<div class="lftMenuWrap">
    <div class="mnTit">
        <h1>텀블벅 어드민</h1>
        <button class="mnBtn">
            <i class="arrow"></i>
        </button>
    </div>
    <div class="lftMenu">
        <nav class="lftWrap">
            <ul class="lnb">
                <li>
                    <a href="<c:url value="/admin/list"/>" class="">게시판</a>
                    <ul class="sub">
                        <li>
                            <a href="#">회원정보</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="<c:url value="/admin/projectList"/> " class="">프로젝트관리</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">주문관리</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">통계분석</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">고객지원</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
<div class="conWrap">
    <div class="title">
        <p class="tit">게시판 관리</p>
    </div>
    <div class="sttBox">
        <div>
            <form action="<c:url value='/admin/search' />" method="post">
                <input type="text" name="news_title" value="">
                <button type="submit">검색</button>

            </form>
        </div>
        <%--        <div class="lftBox">--%>
        <%--            <span class="sttBlue">선물 전달 중</span>--%>
        <%--            <p class="bxTit">선물 발송 후, 운송장을 입력하고 선물 전달 상태를 '전달 완료'로 변경해주세요.</p>--%>
        <%--            <p class="bxStit">운송장을 입력하여 후원자가 배송 상태를 확인할 수 있도록 해주세요.</p>--%>
        <%--        </div>--%>
        <div class="rgtLink">
            <a href="#">자세히 보기</a>
        </div>
    </div>
    <div class="btnWrap">
        <div class="lftBtngrp">
            <a href="<c:url value='/admin/list'/>">
                <i class="fa-regular fa-comments"></i>
                <span>공지사항</span>
            </a>
            <a href="<c:url value="/admin/eventList"/> ">
                <i class="fa-solid fa-gift fa-lg"></i>
                <span>이벤트</span>
            </a>
            <a href="<c:url value="/admin/termList"/> ">
                <i class="fa-solid fa-gift fa-lg"></i>
                <span>이용약관</span>
            </a>
            <a href="<c:url value="/admin/subHelpList"/> ">
                <i class="fa-solid fa-gift fa-lg"></i>
                <span>헬프센터</span>
            </a>
        </div>
        <div class="rgtBtngrp">
            <button class="gray">
                <i class="fa-regular fa-file-import"></i>
                운송장 입력
            </button>
            <button class="carrot">엑셀 메일 발송</button>
        </div>
    </div>
    <div class="tbCont">
        <div class="tbWrap">
            <table>
                <tr>
                    <th>게시물번호</th>
                    <th>제목</th>
                    <th>등록자</th>
                    <th>조회수</th>
                    <th>등록일시</th>
                    <th>수정일시</th>
                    <th>숨김여부</th>
                    <th>이벤트시작</th>
                    <th>이벤트종료</th>
                    <th>진행상태</th>
                    <th><button id="write" onclick="location.href=' <c:url value='/admin/eventWrite'/>'">글쓰기</button> </th>
                </tr>
                <!-- 데이터가 없을 경우 -->
                <%--                <td colspan="9">--%>
                <%--                    <p class="noData">데이터가 없습니다</p>--%>
                <%--                </td>--%>

                <c:forEach var="EventDto" items="${eventList}">
                    <tr>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>">${EventDto.event_seq}</a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>">${EventDto.event_title}</a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>">${EventDto.reg_id}</a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>">${EventDto.event_view_cnt}</a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>">${EventDto.reg_dtm}</a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>"> ${EventDto.mod_dtm!=null? EventDto.mod_dtm :EventDto.reg_dtm}</a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>">${EventDto.hid_yn}</a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>"><tf:formatDateTime value="${EventDto.event_str_date}"/></a></td>
                        <td><a href="<c:url value='/admin/select?event_seq=${EventDto.event_seq}&page=${page}'/>"><tf:formatDateTime value="${EventDto.event_end_date}"/></a></td>
                        <c:choose>
                        <c:when test="${EventDto.event_str_date.isBefore(now) && EventDto.event_end_date.isAfter(now)}">
                        <td> 진행 중</td>
                        </c:when>
                        <c:otherwise>
                        <td>종료됨 </td>
                        </c:otherwise>
                        </c:choose>
                </c:forEach>
            </table>
            <td>

            </td>
        </div>
    </div>
    <div class="pg" style=" display: flex;  margin: 20px 400px 40px;justify-content: space-around;">
        <c:if test="${ph.showPrev}">
            <a href="<c:url value='/admin/list?page=${ph.beginPage-1}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            <a href="<c:url value='/admin/list?page=${i}'/> ">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
            <a href="<c:url value='/admin/list?page=${ph.endPage+1}'/>">&gt;</a>
        </c:if>
    </div>
    <input type="text" id="searchInput"  placeholder="검색어를 입력하세요">
</div>
<footer>
    <p>@Copyright 2024. 텀블벅 관리자 사이트 All Rights Reserved.</p>
</footer>

<script>
    if("${msg}"!=""){alert("${msg}")}
</script>
</body>
</html>
