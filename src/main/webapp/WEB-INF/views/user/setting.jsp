<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>
<%--설정--%>
<div class="main">
    <div class="userSetting">
        <div class="title">설정</div>
        <div class="tapContainer ">
            <div class="tapContainerList" id="divtapContainer">
                <span class="tapItem fontcolor"><a href="" >프로필</a></span>
                <span class="tapItem fontcolor"><a href="" >계정</a></span>
                <span class="tapItem fontcolor"><a href="" >결제수단</a></span>
                <span class="tapItem fontcolor"><a href="" >배송지</a></span>
                <span class="tapItem fontcolor"><a href="" >알림</a></span>
            </div>
        </div>
    </div>
    <!-- 탭 값 -->
    <div class="tapContainerMain flexOnly">
        <div class="container">
            <div class="profileimg">
                <div class="profileimgFormHeader flexOnly">
                    <p class="pTag">프로필 사진</p>
                    <button class="ButtonTag">변경</button>
                </div>
                <div class="proImg">
                    <div class="proImgWrap">
                        <div class="proImgFile" src=""></div>
                    </div>
                </div>
            </div>
            <div class="userName">
                <div class="userNameFormHeader flexOnly">
                    <p class="pTag">이름</p>
                    <button class="ButtonTag">변경</button>
                </div>
                ${user_name}
            </div>
            <div class="userIntro">
                <div class="userIntroFormHeader flexOnly">
                    <p class="pTag">소개</p>
                    <button class="ButtonTag">변경</button>
                </div>
                <%--                        ${user_name}--%>
                안녕하세요 ${user_name} 이라고 합니다.
            </div>
        </div>
        <div class="userSettingContainer">
            <div class="userInfoMod"></div>
            <div class="userInfoComment">
                <p>어떤 정보가 프로필에 공개되나요?</p>
                <div class="userInfoCommentContent">
                    프로필 사진과, 이름, 사용자 이름, 소개글, 웹사이트 및 회원님과 관련된 프로젝트 등이 프로필 페이지에 공개 됩니다. 프라이버시 설정을 활성화하시면 후원한 프로젝트 목록을 숨길 수 있습니다.
                    <span><a href="<c:url value='/mypage/profile'/>">내 프로필 바로가기</a></span>
                </div>
            </div>
        </div>
    </div>
</div>