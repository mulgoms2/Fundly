<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-12
  Time: 오전 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/static/user/Profile.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/usercommon.css'/>">
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div class="main">
            <!-- 프로필 & 탭 -->
            <div class="pageComponent">
                <div class="profilerHeader">

                    <div class="profileInfo">
                        <div class="ifImg"><span></span></div>
                        <div class="userInfo">
                            <div class="userInfoDetail">
                                <div class="username">${user_name}</div>
                                <span class ="setting">
                          <a href=""></a>
                        </span>
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

<%--            <div class="line"></div>--%>

            <!-- 탭 값 -->
            <div class="container">
                <div class="tap_itemProfile fontcolor" id="tap_itemProfile">등록된 소개가 없습니다.</div>
            </div>

            <!-- 프로젝트후기, 올린프로젝트, 후원한 프로젝트 -->


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
