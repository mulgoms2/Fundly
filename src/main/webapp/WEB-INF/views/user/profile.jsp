<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>
<div class="main">
    <!-- 프로필 & 탭 -->
    <div class="pageComponent">
        <div class="profilerHeader">
            <div class="profileInfo">
                <div class="ifImg"><span></span></div>
                <div class="userInfo">
                    <div class="userInfoDetail">
                        <div class="username">${user_name}</div>
                        <a href="<c:url value='/mypage/setting'/>"><span class ="setting"></span></a>
                    </div>
                    <div class="userJoindate">0주 전 가입</div>
                </div>
            </div>
            <div class="tapContainer">
                <div class="tapContainerList" id="divtapContainer">
                    <span class="tapItem fontcolor on"><a href="" id="itemProfile">프로필</a></span>
                    <span class="tapItem fontcolor"><a href="" id="itemProjectReview">프로젝트 후기</a></span>
                    <span class="tapItem fontcolor"><a href="" id="itemMakeProject">올린 프로젝트</a></span>
                    <span class="tapItem fontcolor"><a href="" id="itemProjectOrder">후원한 프로젝트</a></span>
                </div>
            </div>
        </div>
    </div>
    <!-- 탭 값 -->
    <div class="container">

        <div class="tap_itemProfile fontcolor" id="tap_itemProfile">
            등록된 소개가 없습니다.
        </div>
    </div>
    <!-- 프로젝트후기, 올린프로젝트, 후원한 프로젝트 -->
</div>