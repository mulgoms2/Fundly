<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-13
  Time: 오후 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/static/user/Likes.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/common.css'/>">
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    </head>

    <%--관심 프로젝트--%>
    <body>
        <div class="main">
            <div class="userSetting">
                <div class="title">관심 프로젝트</div>
                <div class="tapContainer">
                    <div class="tapContainerList" id="divtapContainer">
                        <span class="tapItem fontcolor"><a href="" >프로필</a></span>
                        <span class="tapItem fontcolor"><a href="" >계정</a></span>
                        <span class="tapItem fontcolor"><a href="" >결제수단</a></span>
                        <span class="tapItem fontcolor"><a href="" >배송지</a></span>
                        <span class="tapItem fontcolor"><a href="" >알림</a></span>
                    </div>
                </div>
            </div>
            <div class="line"></div>

            <!-- 탭 값 -->

            <div class="tapContainerMain">
                <div class="container">
                    <div class="profileimg">
                        <div class="profileimgFormHeader">
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
                        <div class="userNameFormHeader">
                            <p class="pTag">이름</p>
                            <button class="ButtonTag">변경</button>
                        </div>
                        ${user_name}
                    </div>

                    <div class="userIntro">
                        <div class="userIntroFormHeader">
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

        <script>

            const tapContainer = document.getElementById('divtapContainer');

            tapContainer.addEventListener("click",(e)=>{
                const tapItems = document.querySelectorAll('.tapItem');

                tapItems.forEach(item => {
                    // item.classList.add('on');
                    // item.classList.remove('on'); // 모든 탭의 활성화 클래스를 제거합니다.
                });

                if(e.target.id=="itemProfile"){
                    // e.target.parentElement.classList.add('on');
                    // tap_itemProfile.style.display = "block";

                    // tap_itemProfile.style.display = "block";
                    // // itemProjectReview.style.display = "none";
                    // // alert('프로필');

                }
                else if(e.target.id=="itemProjectReview"){
                    // tap_itemProfile.style.display = "none";
                    // // itemProjectReview.style.display = "block";
                    // alert('후기');

                } else if(e.target.id=="itemMakeProject"){
                    // tap_itemProfile.style.display = "none";
                    // // itemProjectReview.style.display = "none";
                    // alert('올린');

                } else if(e.target.id=="itemProjectOrder"){
                    // tap_itemProfile.style.display = "none";
                    // // itemProjectReview.style.display = "none";
                    // alert('후원');
                }
            })
        </script>
    </body>
</html>
