
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <a href="<c:url value='/admin/list'/>" class="">게시판관리</a>
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
        <div>
            <form action="<c:url value='/admin/search' />" method="post">
                <input type="text" name="news_title" value="">
                <button type="submit">검색</button>

            </form>
        </div>
        <div class="rgtLink">
            <a href="#">자세히 보기</a>
        </div>
    </div>
    <div class="btnWrap">
        <div class="lftBtngrp">
            <a href="<c:url value='/admin/projectList'/>">
                <i class="fa-regular fa-comments"></i>
                <span>전체</span>
            </a>
            <a href="<c:url value='/admin/projectStatus?pj_status=심사대기중'/>">
                <i class="fa-solid fa-location-dot fa-lg"></i>
                <span>심사대기중</span>
            </a>
            <a href="<c:url value="/admin/projectStatus?pj_status=반려"/> ">
                <i class="fa-solid fa-gift fa-lg"></i>
                <span>반려</span>
            </a>
            <a href="<c:url value="/admin/projectStatus?pj_status=승인"/> ">
                <i class="fa-solid fa-gift fa-lg"></i>
                <span>승인</span>
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
                    <th>프로젝트아이디</th>
                    <th>짧은제목</th>
                    <th>등록자</th>
                    <th>카테고리</th>
                    <th>심사요청일시</th>
                    <th>프로젝트상태</th>
                    <th></th>
                </tr>
                <!-- 데이터가 없을 경우 -->
                <%--                <td colspan="9">--%>
                <%--                    <p class="noData">데이터가 없습니다</p>--%>
                <%--                </td>--%>

                <c:forEach var="ProjectDto" items="${projectList}">
                    <form id="form_${ProjectDto.pj_id}" method="post" action="<c:url value="/admin/projectStatus"/>">
                    <input type="hidden" name="pj_id" value="${ProjectDto.pj_id}">
                    <tr>
                        <td>${ProjectDto.pj_id}</td>
                        <td><a href="">${ProjectDto.pj_short_title}</a></td>
                        <td><a href="">${ProjectDto.pj_sel_id}</a></td>
                        <td><a href="">${ProjectDto.ctg}</a></td>
                        <td><a href="">${ProjectDto.pj_reg_dtm}</a></td>
                        <td><a href="">${ProjectDto.pj_status}</a></td>
                        <td>
                            <select id="pj_status" name="pj_status" class="statusSelect">
                                <option value="승인" ${ProjectDto.pj_status eq '승인' ? 'selected' : ''}>승인</option>
                                <option value="반려" ${ProjectDto.pj_status eq '반려' ? 'selected' : ''}>반려</option>
                                <option value="심사대기중" ${ProjectDto.pj_status eq '심사대기중' ? 'selected' : ''}>심사대기중</option>
                            </select>
                        </td>
                        <td><button type="button" onclick="submitForm('${ProjectDto.pj_id}')">제출</button></td>
                    </tr>
                    </form>

                </c:forEach>
            </table>
        </div>
    </div>

</div>
<footer>
    <p>@Copyright 2024. 텀블벅 관리자 사이트 All Rights Reserved.</p>
</footer>

<script>
    function submitForm(pj_id) {
        var formId = "form_" + pj_id;
        var form = document.getElementById(formId);
        // var selectedOption = document.querySelector('#' + formId + ' select[name="pj_status"]').value;
        // var message = selectedOption + " 하시겠습니까?";
        if (confirm(" 선택된 프로젝트상태를 변경하시겠습니까?")) {
            form.submit();
        }
    }
</script>
</body>
</html>
