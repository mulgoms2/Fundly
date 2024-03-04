<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>
<c:set var="userprofileImg" value="${user_profileImg=='' || user_profileImg == null ? '/static/img/avatar.webp' : '/user/img/'}"/>
<div class="main">
    <!-- 프로필 & 탭 -->
    <div class="pageComponent">
        <div class="profilerHeader">
            <div class="profileInfo">
                <div class="ifImg"><span id="profileImg" style="background: url('${userprofileImg}${user_profileImg}') 50% 37% / cover no-repeat"></span></div>
                <div class="userInfo">
                    <div class="userInfoDetail">
                        <div class="username">${userInfo.user_name}</div>
                        <a href="<c:url value='/mypage/setting'/>"><span class ="setting"></span></a>
                    </div>
                    <div class="userJoindate">${userjoindateView}</div>
                </div>
            </div>
            <div class="tapContainer">
                <div class="tapContainerList" id="divtapContainer">
                    <span class="tapItem fontcolor" data-tab="itemProfile">프로필</span>
                    <span class="tapItem fontcolor" data-tab="itemProjectReview">프로젝트 후기</span>
                    <span class="tapItem fontcolor" data-tab="itemMakeProject">올린 프로젝트</span>
                    <span class="tapItem fontcolor" data-tab="itemProjectOrder">후원한 프로젝트</span>
                </div>
            </div>
        </div>
    </div>
    <!-- 탭 값 -->
    <div class="container">
        <div id="itemProfile" class="tabContent"><jsp:include page="profileDetail.jsp"/></div>
        <div id="itemProjectReview" class="tabContent">프로젝트후기</div>
        <div id="itemMakeProject" class="tabContent">올린 프로젝트</div>
        <div id="itemProjectOrder" class="tabContent">후원한 프로젝트</div>

    </div>
    <!-- 프로젝트후기, 올린프로젝트, 후원한 프로젝트 -->
</div>