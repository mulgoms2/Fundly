<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-13
  Time: 오후 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<div class="main" id="likesMain">
    <div class="likesTitle">
        <div class="title">관심 프로젝트</div>
        <%--        TODO: likeCnt => 유저의 like_status = 1인 likes의 count(*) --%>
        <span class="likesCnt">4</span>개의 프로젝트를 좋아합니다.
    </div>
    <div class="likesTap">
        <div class="stateCombo">
            <div class="stateList" id="stateList">진행중
                <div class="upDown" id="upDown"></div>
                <div class="likestate" id="likestate">
                    <div class="stateItem" id="all">전체</div>
                    <div class="stateItem" id="ing">진행중</div>
                    <div class="stateItem" id="close">종료된</div>
                </div>
            </div>
            <div class="orderList" id="orderList">추가순
                <div class="upDown2" id="upDown2"></div>
                <div class="stateOrderby" id="stateOrderby">
                    <div class="stateOrderbyItem" id="add">추가순</div>
                    <div class="stateOrderbyItem" id="deadline">마감 임박순</div>
                </div>
            </div>
        </div>
    </div>
    <!-- 탭 값 -->
    <div class="likesMainContainer">
        <div class="projectListWithCard">
            <div class="projectCardWrap">
                <c:forEach var="likes" items="${likes}">
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
<%--                                                <a href="#">${likes.pj_ctg}</a>--%>
                                            </span>
                                    <span class="bar">
                                                <a href="#">${likes.pj_sel_id}</a>
                                            </span>
                                </div>
                                <div class="detailTxt">
                                    <div class="subTit">
                                        <a href="#">${likes.pj_id}</a>
<%--                                            ${likes.like_status}--%>
                                    </div>
                                    <div class="subDt">
                                        <a href="#">${likes.pj_long_title}</a>
                                    </div>
                                    <div class="psTag">
                                        <span class="per">100%</span>
                                        <span class="won"><em>${likes.curr_fund_money}</em>원</span>
                                        <span class="day">20일 남음</span>
                                    </div>
                                    <div class="percentBar"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
