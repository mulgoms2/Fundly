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
        <style>
            .infoGr{
                position: relative;
            }

            .MyPageList{
                position: absolute;
                display: none;
                flex-direction: column;
                top: 50px;
                right: 0px;
                width: 240px;
                transition: all 0.3s ease-in-out 0s;
                border: 1px solid rgb(228, 228, 228);
                box-sizing: border-box;
                border-radius: 4px;
                z-index: 1200;
                padding: 10px 0;
                overflow: hidden;
                background: rgb(255, 255, 255);
            }

            .pageItem {
                padding: 4px 16px;
                display: flex;
                width: 100%;
                height: 46px;
                min-height: 46px;
                -webkit-box-align: center;
                align-items: center;
                -webkit-box-pack: start;
                justify-content: flex-start;
                font-size: 14px;
                line-height: 22px;
                color: rgb(13, 13, 13);
                cursor: pointer;
                transition: all 0.3s ease-in-out 0s;
            }

            .pageItem:hover {
                background: rgba(110, 110, 110, 0.1);
            }

            .line{
                display: flex;
                width: 100%;
                height: 1px;
                background: rgb(240, 240, 240);
                content: " ";
            }

            .tap_itemProfile{
                display: none;
            }

        </style>
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
                    <div class="userIf">
                        <div class="infoGr" id = "loginInfo" >
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
                                <div class="pageItem" id = "Like"> 관심 프로젝트 </div>
                                <div class="line"></div>
                                <div class="pageItem" id = "Alarm"> 알림 </div>
                                <div class="pageItem" id = "Msg"> 메시지 </div>
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
</html>

