<!--Header-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <script defer src="/static/project/js/header.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<div class="topContents">
    <div class="headerWrap">
        <div class="header">
            <div>
                <a id="homeBtn" href="<c:url value="/" />">
                    <i class="fas fa-solid fa-arrow-left"></i>
                </a>
            </div>
            <div class="hBtnWrap">
                <%--            <a href="pro" class="preview">제출하기</a>--%>
                <form id="pjSubmitForm" action="/project/editor/submit" method="post">
                    <button id="pjSubmit" class="pjSubmit">제출하기</button>
                </form>
                <button type="button" class="cancel">취소</button>
                <button id="saveBtn" type="button" class="save">저장</button>
                <button type="button" class="modify">수정</button>
            </div>
        </div>
    </div>
    <div class="menuWrap">
        <div class="topContentsWrapper">
            <div class="subTitle">프로젝트 기획</div>
        </div>
        <div class="fixedContentWrapper">
            <div class="planWrap">
                <ul>
                    <li><a id="default" class="active" href="<c:url value='/project/editor/info'/>">기본 정보</a></li>
                    <li><a id="funding" href="<c:url value='/project/editor/funding'/>">펀딩 계획</a></li>
                    <li><a id="reward" href="<c:url value='/project/editor/reward'/>">선물 구성</a></li>
                    <li><a id="story" href="<c:url value='/project/editor/story'/>">프로젝트 계획</a></li>
                    <li><a id="creator" href="<c:url value='/project/editor/creator'/>">창작자 정보</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
