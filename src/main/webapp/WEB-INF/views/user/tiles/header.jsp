<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%-- LoginInfo --%>
<c:set var="loginInfo" value="${user_email=='' || user_email == null ? '로그인/회원가입' : userInfo.user_name}"/>
<c:set var="userprofileImg" value="${user_profileImg=='' || user_profileImg == null ? '/static/img/avatar.webp' : '/user/img/'}"/>
<div class="header">
    <div class="hd">
        <a href="<c:url value='/'/>">
            <img class="logo" src="/static/img/fundly-logo.svg">
        </a>
        <div class="lftSmn">
            <div class="pjtUp">
                <a href="<c:url value="/project/editor/start" />">프로젝트 올리기</a>
            </div>
            <div class="like">
                <a href="/mypage/likes">
                <i class="fa-regular fa-heart"></i>
<%--                <div class="icoImg" id="heartWhite">--%>
<%--                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">--%>
<%--                        <path d="M225.8 468.2l-2.5-2.3L48.1 303.2C17.4 274.7 0 234.7 0 192.8v-3.3c0-70.4 50-130.8 119.2-144C158.6 37.9 198.9 47 231 69.6c9 6.4 17.4 13.8 25 22.3c4.2-4.8 8.7-9.2 13.5-13.3c3.7-3.2 7.5-6.2 11.5-9c0 0 0 0 0 0C313.1 47 353.4 37.9 392.8 45.4C462 58.6 512 119.1 512 189.5v3.3c0 41.9-17.4 81.9-48.1 110.4L288.7 465.9l-2.5 2.3c-8.2 7.6-19 11.9-30.2 11.9s-22-4.2-30.2-11.9zM239.1 145c-.4-.3-.7-.7-1-1.1l-17.8-20c0 0-.1-.1-.1-.1c0 0 0 0 0 0c-23.1-25.9-58-37.7-92-31.2C81.6 101.5 48 142.1 48 189.5v3.3c0 28.5 11.9 55.8 32.8 75.2L256 430.7 431.2 268c20.9-19.4 32.8-46.7 32.8-75.2v-3.3c0-47.3-33.6-88-80.1-96.9c-34-6.5-69 5.4-92 31.2c0 0 0 0-.1 .1s0 0-.1 .1l-17.8 20c-.3 .4-.7 .7-1 1.1c-4.5 4.5-10.6 7-16.9 7s-12.4-2.5-16.9-7z"/>--%>
<%--                    </svg>--%>
<%--                </div>--%>
<%--                <div class="icoImg on" id="heartRed">--%>
<%--                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">--%>
<%--                        <path fill="#fa6462"--%>
<%--                              d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z"/>--%>
<%--                    </svg>--%>
<%--                </div>--%>
                </a>
            </div>
            <div class="alm">
                <i class="fa-regular fa-bell"></i>
            </div>

            <div class="userIf" >
                <div class="infoGr" id = "loginInfo">
                    <div class="ifImg">
                        <span id="profileImg" style="background: url('${userprofileImg}${user_profileImg}') 50% 37% / cover no-repeat"></span>
                    </div>
                    <div class="ifTxt" id="ifTxt">${loginInfo}</div>
                    <div class="MyPageList" id = "MyPageList">
                        <a href="<c:url value='/mypage/profile'/>"><div class="pageItem" id = "Profile">프로필</div></a>
                        <a href="<c:url value='/mypage/coupon'/>"><div class="pageItem" id = "Coupon">응원권</div></a>
                        <div class="line"></div>
                        <a href="<c:url value='/mypage/fundingProject'/>"><div class="pageItem" id = "fundingProject">후원한 프로젝트</div></a>
                        <a href="<c:url value='/mypage/likes'/>"><div class="pageItem" id = "Likes">관심 프로젝트</div></a>
                        <div class="line"></div>
                        <a href="<c:url value='/mypage/alarm'/>"><div class="pageItem" id = "Alarm">알림</div></a>
                        <a href="<c:url value='/mypage/message'/>"><div class="pageItem" id = "Message">메시지</div></a>
                        <div class="line"></div>
                        <a href="<c:url value='/mypage/makeProject'/>"><div class="pageItem" id = "MakeProject">내가 만든 프로젝트</div></a>
                        <a href="<c:url value='/mypage/setting'/>"><div class="pageItem" id = "Setting">설정</div></a>
                        <div class="line"></div>
                        <a href="<c:url value='/login/logout'/>"><div class="pageItem" id = "LogOut">로그아웃</div></a>
                    </div>
                    <%--                        &lt;%&ndash;                    <div class="ifTxt">로그인/회원가입</div>&ndash;%&gt;--%>
                    <%--                        &lt;%&ndash;                    <div class="ifTxt">이한수</div>&ndash;%&gt;--%>
                </div>
            </div>
        </div>
    </div>
    <!--스크롤 시 고정되는 header-->
    <div class="sd">
        <div class="lftCtmn">
            <!--메뉴 슬라이드-->
            <div class="cateMn">
                <div class="mnSld">
                    <div class="hmMenu">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    카테고리
                </div>
                <div class="home">홈</div>
                <div class="popul">인기</div>
                <div class="new">신규</div>
                <div class="final">마감임박</div>
            </div>
            <!--검색창-->
            <div class="schMn">
                <input type="text" placeholder="검색어를 입력해주세요.">
                <div class="schIcon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
<%--    작성자 한윤재  --%>

</script>