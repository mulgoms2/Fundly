<%--
  Created by IntelliJ IDEA.
  User: greta
  Date: 2024/02/18
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="flexOnly">
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
