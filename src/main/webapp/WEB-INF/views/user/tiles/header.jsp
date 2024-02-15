<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-12
  Time: 오전 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="loginInfo" value="${user_email=='' || user_email == null ? '로그인/회원가입' : user_name}"/>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/static/main/common.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/main/style.css'/>">
    </head>

    <body>
        <div class="header">
            <div class="hd">
                <h1 class="logo">
                    <a href="#"></a>
                </h1>
                <div class="lftSmn">
                    <div class="pjtUp">
                        <a href="#">프로젝트 올리기</a>
                    </div>
                    <div class="like">
                        <i class="fa-regular fa-heart"></i>
                    </div>
                    <div class="alm">
                        <i class="fa-regular fa-bell"></i>
                    </div>
                    <div class="userIf" >
                        <div class="infoGr"  id = "loginInfo">
                            <div class="ifImg">
                                <span></span>
                            </div>
                            <div class="ifTxt" >${loginInfo}</div>
                            <%--                         onclick="toggleContent('MyPageList')">${loginInfo}</div>--%>

                            <div class="MyPageList" id = "MyPageList">
                                <div class="pageItem" id = "Profile"> 프로필 </div>
                                <div class="pageItem" id = "Coupon"> 응원권 </div>
                                <div class="line"></div>
                                <div class="pageItem" id = "fundingProject"> 후원한 프로젝트 </div>
                                <div class="pageItem" id = "Likes"> 관심 프로젝트 </div>
                                <div class="line"></div>
                                <div class="pageItem" id = "Alarm"> 알림 </div>
                                <div class="pageItem" id = "Message"> 메시지 </div>
                                <div class="line"></div>
                                <div class="pageItem" id = "MakeProject"> 내가 만든 프로젝트 </div>
                                <div class="pageItem" id = "Setting"> 설정 </div>
                                <div class="line"></div>
                                <div class="pageItem" id = "LogOut"> 로그아웃 </div>
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

        <div class="line"></div>
    </body>

    <script>
        const chat = document.getElementById('chat');

        function chatBtn(){
            <%--let url = "chatPop?user_id=" + "${sessionScope.user_email}" + "&pj_id=" + $("#pj").val();--%>
            <%----%>
//             window.open(url, "_blank", 'width=600px,height=800px,scrollbars=yes');
        }

        /* 로그인/회원가입 or 로그인 회원 정보 */
        const userIf = document.querySelector('.userIf');

        userIf.addEventListener("click",()=> {
            if(loginInfo.outerText !== '로그인/회원가입' ){
                toggleContent("MyPageList");
            }else{
                return window.location.href='/login/login';
            }
        },{capture:true})

        // const ifImg = document.querySelector('.ifImg');
        // const infoGr = document.querySelector('.infoGr');
        // const ifTxt = document.querySelector('.ifTxt');
        //
        // ifImg.addEventListener('click', (event) => {
        //     event.stopPropagation();
        //     userIf.click();
        // });
        //
        // infoGr.addEventListener('click', (event) => {
        //     event.stopPropagation();
        //     userIf.click();
        // });
        //
        // ifTxt.addEventListener('click', (event) => {
        //     event.stopPropagation();
        //     userIf.click();
        // });


        const MyPageList = document.getElementById('MyPageList');

        MyPageList.addEventListener("click",(e)=>{

            if(e.target.id === "Profile"){
                // 프로필 화면으로
                return window.location.href = '/mypage/profile';

            } else if(e.target.id === "Coupon") {
                // 응원권 화면으로
                return window.location.href = '/mypage/coupon';

            } else if(e.target.id === "fundingProject") {
                // 후원한 프로젝트 화면으로
                return window.location.href = '/mypage/fundingProject';

            } else if(e.target.id === "Likes") {
                // 관심 프로젝트
                return window.location.href = '/mypage/likes';

            } else if(e.target.id === "Alarm") {
                // 알림 화면으로
                return window.location.href = '/mypage/alarm';

            } else if(e.target.id === "Message") {
                // 메시지 화면으로
                return window.location.href = '/mypage/message';

            } else if(e.target.id === "MakeProject") {
                // 내가 만든 프로젝트 화면으로
                return window.location.href = '/mypage/makeProject';

            } else if(e.target.id === "Setting") {
                // 설정 화면으로
                return window.location.href = '/mypage/setting';

            } else if(e.target.id === "LogOut") {

                toggleContent("MyPageList");
                return window.location.href = '/login/logout';
            }
        })

        function toggleContent(MyPageList){
            const content = document.getElementById(MyPageList);

            if(content.style.display==="none" || content.style.display === ""){
                content.style.display="block";
            } else{
                content.style.display="none";
            }
        }
    </script>
</html>

