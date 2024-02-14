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
        <link rel="stylesheet" href="<c:url value='/static/user/usercommon.css'/>">
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    </head>

    <%--관심 프로젝트--%>
    <body>
        <div class="main">
            <div class="userSetting">
                <div class="title">관심 프로젝트</div>
                <div class="tapContainer">
                    <div class="tapContainerList" id="divtapContainer">
                        <span class="tapItem fontcolor"><a href="" >좋아한 1<div></div></a></span>
                        <span class="tapItem fontcolor"><a href="" >알림신청 0<div></div></a></span>
                    </div>
                </div>
            </div>

            <!-- 탭 값 -->
            <div class="tapContainerMain">
                <div class="container">
                    <div class="stateCombo">
                        <div class = "stateList" id="stateList" >진행중
                            <div class="upDown" id ="upDown"></div>
                            <div class="likestate" id = "likestate">
                                <div class="stateItem" id="all">전체</div>
                                <div class="stateItem" id="ing">진행중</div>
                                <div class="stateItem" id="close">종료된</div>
                            </div>
                        </div>

                        <div class="orderList" id="orderList">추가순
                            <div class="upDown2" id ="upDown2"></div>
                            <div class="stateOrderby" id="stateOrderby">
                                <div class="stateOrderbyItem" id="add">추가순</div>
                                <div class="stateOrderbyItem" id="deadline">마감 임박순</div>
                            </div>
                        </div>
                    </div>







                </div>
            </div>
        </div>

        <script>

            const tapContainer = document.getElementById('divtapContainer');

            tapContainer.addEventListener("click",(e)=>{

                // const tapItems = document.querySelectorAll('.tapItem');
                //
                // tapItems.forEach(item => {
                //     // item.classList.add('on');
                //     // item.classList.remove('on'); // 모든 탭의 활성화 클래스를 제거합니다.
                // });

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

            // const stateList = document.getElementById('stateList');
            // const orderList = document.getElementById('orderList');
            //
            // stateList.addEventListener('click', (e) => {
            //     toggleContent("likestate");
            //     if (stateOrderby.style.display == "block") {
            //         toggleContent("stateOrderby");
            //     }
            // });
            //
            // orderList.addEventListener('click', (e) => {
            //     toggleContent("stateOrderby");
            //     if (likestate.style.display == "block") {
            //         toggleContent("likestate");
            //     }
            // });

            window.addEventListener("click",(e)=>{

                const likestate = document.getElementById("likestate");
                const stateOrderby = document.getElementById("stateOrderby");

                if(e.target.id==='') {
                    if (likestate.style.display == "block") {
                        toggleContent("likestate");
                    } else if (stateOrderby.style.display == "block") {
                        toggleContent("stateOrderby");
                    }

                }else if(e.target.id==="stateList" || e.target.id==="upDown"){
                    toggleContent("likestate");
                    if(stateOrderby.style.display=="block"){ toggleContent("stateOrderby");}

                    if(e.target.id == "all"){
                    } else if(e.target.id == "ing") {
                    } else if(e.target.id == "close") {
                    }

                }else if(e.target.id==="orderList" || e.target.id==="upDown2"){
                    toggleContent("stateOrderby");
                    if(likestate.style.display=="block"){ toggleContent("likestate");}

                    if(e.target.id == "all"){
                    } else if(e.target.id == "ing") {
                    } else if(e.target.id == "close") {
                    }
                }
            })

            function toggleContent(list){
                const content = document.getElementById(list);

                if(content.style.display=="none"){

                    content.style.display="block";

                } else{
                    content.style.display="none";
                }
            }
        </script>
    </body>
</html>
