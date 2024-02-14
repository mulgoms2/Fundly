<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-12
  Time: 오후 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/static/user/Alarm.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/usercommon.css'/>">
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    </head>

    <%-- 후원한 프로젝트 --%>
    <body>
        <div class="main">
            <div class="title">알림</div>
            <div class="tapContainer">
                <div class="tapContainerList" id="divtapContainer">
                    <span class="tapItem font1"><a href="" id="5">전체</a></span>
                    <span class="tapItem font1"><a href="" id="3">활동</a></span>
                    <span class="tapItem font1"><a href="" id="4">프로젝트</a></span>
                </div>
            </div>

            <div class="NotificationListMain">
                <div class="NotificationList">
                    <div class="NotificationListItem">
                        <div class="itemImg"></div>
                        <div class="itemInfo">
                            <div class="notifiContent">좋아하신 <b>주술회전 0</b> 프로젝트에 창작자의 새 게시글이 올라왔습니다.</div>
                            <div class="itemTitle"><font color="#6d6d6d">제목 : 스타벅스 커피 기프티콘 당첨자 발표&nbsp; 안녕하세요!! 아이브 엔터테인먼트 입니다. &nbsp; 오늘 오후에 스타벅스 커피 기프티콘 당첨자 추첨하도록 하겠습니다.
                                당첨되신 분들은 문자로 연락될 예정입니다.&nbsp; 감사합니다.</font></div>
                            <div class="itemDate">13일 전</div>
                        </div>
                        <div class="itemDel"><a href="">삭제</a></div>
                    </div>

                    <div class="NotificationListItem">
                        <div class="itemImg"></div>
                        <div class="itemInfo">
                            <div class="notifiContent">좋아하신 <b>주술회전 0</b> 프로젝트에 창작자의 새 게시글이 올라왔습니다.</div>
                            <div class="itemTitle"><font color="#6d6d6d">이번 스페셜 박스세트는 한정판 1,000개만 제작을 할 예정이였는데요,많은 성원 덕분에 추가 후원을 희망하시는 분들이 많이 계셔서 추가제작을 결정하였습니다.다만 먼저 후원하신 분들과 달리 1000개</font></div>
                            <div class="itemDate">21일 전</div>
                        </div>
                        <div class="itemDel"><a href="">삭제</a></div>
                    </div>

                    <div class="NotificationListItem">
                        <div class="itemImg"></div>
                        <div class="itemInfo">
                            <div class="notifiContent">펀들리 생활백서가 배달되었습니다📔</div>
                            <div class="itemTitle"><font color="#6d6d6d">내 취향에 맞는 프로젝트 찾는 법, 꿀팁 얻어가세요!</font></div>
                            <div class="itemDate">26일 전</div>
                        </div>
                        <div class="itemDel"><a href="">삭제</a></div>
                    </div>

                </div>
            </div>

        </div>
    </body>
</html>
