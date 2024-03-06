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
    <div class="header">
        <div>
            <a href="javascript:void(0)"></a><i class="fas fa-solid fa-arrow-left"></i>
        </div>
        <div class="hBtnWrap">
            <button type="button" class="preview"><i class="fas fa-solid fa-eye"></i> &nbsp;&nbsp;미리 보기</button>
            <button type="button" class="cancel">취소</button>
            <button type="button" class="save">저장</button>
            <button type="button" class="modify">수정</button>
        </div>
    </div>
    <div class="topContentsWrapper">
        <span>프로젝트 기획</span>
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
