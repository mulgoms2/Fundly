<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-13
  Time: 오후 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%--<jsp:useBean id="likes" scope="request" type="java.util.List"/>--%>
<c:set var="likeCnt" value="${fn:length(likes)}" />
<div class="main" id="likesMain">
    <c:if test="${empty likes}">
    <div class="likesTitle" id="likesTitleN">
        <div class="title">관심 프로젝트</div>
        <span class="noLikesCnt">${likeCnt}</span>개의 프로젝트를 좋아합니다.
    </c:if>
    <c:if test="${not empty likes}">
    <div class="likesTitle" id="likesTitleY">
        <div class="title">관심 프로젝트</div>
        <span class="likesCnt">${likeCnt}</span>개의 프로젝트를 좋아합니다.
    </c:if>
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
            <div class="projectCardWrap" id="projectCardWrap">
                <c:if test="${empty likes}">
                    <div class="noLikesConts">
                        <div class="icoImg">
                            <svg class= noLikeHeart xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                                <path fill="#595959" d="M225.8 468.2l-2.5-2.3L48.1 303.2C17.4 274.7 0 234.7 0 192.8v-3.3c0-70.4 50-130.8 119.2-144C158.6 37.9 198.9 47 231 69.6c9 6.4 17.4 13.8 25 22.3c4.2-4.8 8.7-9.2 13.5-13.3c3.7-3.2 7.5-6.2 11.5-9c0 0 0 0 0 0C313.1 47 353.4 37.9 392.8 45.4C462 58.6 512 119.1 512 189.5v3.3c0 41.9-17.4 81.9-48.1 110.4L288.7 465.9l-2.5 2.3c-8.2 7.6-19 11.9-30.2 11.9s-22-4.2-30.2-11.9zM239.1 145c-.4-.3-.7-.7-1-1.1l-17.8-20c0 0-.1-.1-.1-.1c0 0 0 0 0 0c-23.1-25.9-58-37.7-92-31.2C81.6 101.5 48 142.1 48 189.5v3.3c0 28.5 11.9 55.8 32.8 75.2L256 430.7 431.2 268c20.9-19.4 32.8-46.7 32.8-75.2v-3.3c0-47.3-33.6-88-80.1-96.9c-34-6.5-69 5.4-92 31.2c0 0 0 0-.1 .1s0 0-.1 .1l-17.8 20c-.3 .4-.7 .7-1 1.1c-4.5 4.5-10.6 7-16.9 7s-12.4-2.5-16.9-7z"/>
                            </svg>
                        </div>
                        <div class="noLikes">좋아한 프로젝트가 없습니다.</div>
                    </div>
                </c:if>
                <c:forEach items="${likes}" var="likes">
                <div class="cardWrap">
                    <div class="banBox">
                        <div class="mnBan">
                            <div class="banImg after">
                                <a href="<c:url value='/detail/${likes.pj_id}'/>">
                                    <img src="${likes.pj_thumbnail_url}" alt="">
                                </a>
                            </div>
                        </div>
                        <button class="likeBtn">
                            <div class="likeCnt" id="ikeCnt">${likes.curr_pj_like_cnt}</div>
                            <div class="pjId" id="pjId">${likes.pj_id}</div>
                            <div class="userId" id="userId">${likes.user_id}</div>
                            <div class="likeStatus" id="likeStatus">${likes.like_status}</div>
                            <div class="btnIco">
                                <div class="icoImg" onclick="clickLikeBtn(event)">
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                                        <path d="M225.8 468.2l-2.5-2.3L48.1 303.2C17.4 274.7 0 234.7 0 192.8v-3.3c0-70.4 50-130.8 119.2-144C158.6 37.9 198.9 47 231 69.6c9 6.4 17.4 13.8 25 22.3c4.2-4.8 8.7-9.2 13.5-13.3c3.7-3.2 7.5-6.2 11.5-9c0 0 0 0 0 0C313.1 47 353.4 37.9 392.8 45.4C462 58.6 512 119.1 512 189.5v3.3c0 41.9-17.4 81.9-48.1 110.4L288.7 465.9l-2.5 2.3c-8.2 7.6-19 11.9-30.2 11.9s-22-4.2-30.2-11.9zM239.1 145c-.4-.3-.7-.7-1-1.1l-17.8-20c0 0-.1-.1-.1-.1c0 0 0 0 0 0c-23.1-25.9-58-37.7-92-31.2C81.6 101.5 48 142.1 48 189.5v3.3c0 28.5 11.9 55.8 32.8 75.2L256 430.7 431.2 268c20.9-19.4 32.8-46.7 32.8-75.2v-3.3c0-47.3-33.6-88-80.1-96.9c-34-6.5-69 5.4-92 31.2c0 0 0 0-.1 .1s0 0-.1 .1l-17.8 20c-.3 .4-.7 .7-1 1.1c-4.5 4.5-10.6 7-16.9 7s-12.4-2.5-16.9-7z"/>
                                    </svg>
                                </div>
                                <div class="icoImg on" onclick="clickLikeBtn(event)">
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                                        <path fill="#fa6462" d="M47.6 300.4L228.3 469.1c7.5 7 17.4 10.9 27.7 10.9s20.2-3.9 27.7-10.9L464.4 300.4c30.4-28.3 47.6-68 47.6-109.5v-5.8c0-69.9-50.5-129.5-119.4-141C347 36.5 300.6 51.4 268 84L256 96 244 84c-32.6-32.6-79-47.5-124.6-39.9C50.5 55.6 0 115.2 0 185.1v5.8c0 41.5 17.2 81.2 47.6 109.5z"/>
                                    </svg>
                                </div>
                            </div>
                        </button>
                        <div class="banTxt">
                            <div class="txtGr">
                                <div class="subTxt">
                                     <span>
                                        <a href="#">${likes.sub_ctg}</a>
                                    </span>
                                    <span class="bar">
                                        <a href="#">${likes.pj_sel_name}</a>
                                    </span>
                                </div>
                                <div class="detailTxt">
                                    <div class="subTit">
                                        <a href="#">${likes.pj_long_title}</a>
                                    </div>
                                    <div class="subDt">
                                        <a href="#">${likes.pj_intro}</a>
                                    </div>
                                    <div class="psTag">

                            <%--        TODO: fund_per => curr_fund_money / fund_goal_money * 100 --%>

                                        <span class="per">100%</span>
                                        <span class="won"><em>${likes.curr_fund_money}</em> 원</span>

                            <%--        TODO: left_dtm => fund_end_dtm - fund_str_dtm --%>

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