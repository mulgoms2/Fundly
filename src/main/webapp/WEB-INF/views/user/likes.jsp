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
<%--관심 프로젝트--%>
<div class="main">
    <div class="userSetting">
        <div class="title">관심 프로젝트</div>
        <div class="tapContainer">
            <div class="info">
                <span class="font1">0</span>개의 프로젝트를 좋아합니다.
            </div>
<%--            <div class="tapContainerList" id="divtapContainer">--%>
<%--                <span class="tapItem fontcolor"><a href="" >좋아한 1<div></div></a></span>--%>
<%--                <span class="tapItem fontcolor"><a href="" >알림신청 0<div></div></a></span>--%>
<%--            </div>--%>
        </div>
    </div>
    <!-- 탭 값 -->
    <div class="tapContainerMain">
        <div class="containerlike">
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

            <div class="projectListWithCard">
                <div class="projectCardWrap">
                    <div class="cardWrap">
                        <div class="banBox"><!--2번 배너-->
                            <div class="mnBan">
                                <div class="banImg after">
                                    <a href="#">
                                        <img src="/static/testimg/item2.webp" alt="">
                                    </a>
                                </div>
                            </div>
                            <div class="banTxt">
                                <div class="txtGr">
                                    <div class="subTxt">
                                                 <span>
                                                    <a href="#">캐릭터·굿즈</a>
                                                </span>
                                        <span class="bar">
                                                    <a href="#">왈맹 스튜디오</a>
                                                </span>
                                    </div>
                                    <div>
                                        <div class="subTit">
                                            <a href="#">너 눈을 왜 그렇게 떠...? 왈맹이 굿즈... 컴백...</a>
                                        </div>
                                        <div class="subDt">
                                            <a href="#">카카오톡 이모티콘 '너 눈을 왜 그렇게 떠' 시리즈의 주인공 왈맹이를 만나보세요.</a>
                                        </div>
                                        <div class="psTag">
                                            <span class="per">1625%</span>
                                            <span class="won"><em>16,853,700</em>원</span>
                                            <span class="day">20일 남음</span>
                                        </div>
                                    </div>
                                    <div class="percentBar"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>