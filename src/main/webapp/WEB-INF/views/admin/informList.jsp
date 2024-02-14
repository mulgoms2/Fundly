
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <a href="#" class="">회원관리</a>
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
                    <a href="#" class="">프로젝트관리</a>
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
            <a href="#">
                <i class="fa-regular fa-comments"></i>
                <span>보도 자료</span>
            </a>
            <a href="#">
                <i class="fa-solid fa-location-dot fa-lg"></i>
                <span>공지사항</span>
            </a>
            <a href="#">
                <i class="fa-solid fa-gift fa-lg"></i>
                <span>이벤트</span>
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
                    <th><button id="write" onclick="location.href=' <c:url value='/admin/write'/>'">글쓰기</button> </th>
                </tr>
                <!-- 데이터가 없을 경우 -->
<%--                <td colspan="9">--%>
<%--                    <p class="noData">데이터가 없습니다</p>--%>
<%--                </td>--%>

                <c:forEach var="InformDto" items="${InformList}">
                    <tr>
                        <td><a href="<c:url value='/admin/select?inform_seq=${InformDto.inform_seq}'/>">${InformDto.inform_seq}</a></td>
                        <td><a href="<c:url value='/admin/select?inform_seq=${InformDto.inform_seq}'/>">${InformDto.inform_title}</a></td>
                        <td><a href="<c:url value='/admin/select?inform_seq=${InformDto.inform_seq}'/>">${InformDto.reg_id}</a></td>
                        <td><a href="<c:url value='/admin/select?inform_seq=${InformDto.inform_seq}'/>">${InformDto.inform_view_cnt}</a></td>
                        <td><a href="<c:url value='/admin/select?inform_seq=${InformDto.inform_seq}'/>">${InformDto.reg_dtm}</a></td>
                        <td><a href="<c:url value='/admin/select?inform_seq=${InformDto.inform_seq}'/>">${InformDto.mod_dtm!=null? InformDto.mod_dtm :InformDto.reg_dtm}</a></td>
                        <td><a href="<c:url value='/admin/select?inform_seq=${InformDto.inform_seq}'/>">${InformDto.hid_yn}</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<footer>
    <p>@Copyright 2024. 텀블벅 관리자 사이트 All Rights Reserved.</p>
</footer>

</body>
</html>
