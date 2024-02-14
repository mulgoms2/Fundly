<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-12
  Time: 오후 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/static/user/Message.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/usercommon.css'/>">
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    </head>

    <%-- 후원한 프로젝트 --%>
    <body>
        <div class="main">
            <div class="title">메시지</div>
            <div class="chkmag">
                <input type="checkbox" class="noreadchk" id = "noreadchk"><label for="noreadchk">안 읽은 메시지</label></input>
            </div>

            <div class="msgMain">
                <ul class="msgList">
                    <li class ="msgListItem">
                        <button class="msgItem">
                            <div class="itemInfo">
                                <div class="itemImg"></div>
                                <div class="itemInfoCont">
                                    <em>안젤라(Angela)</em><br>
                                    <strong>아하! 이런게 진짜 가볍고편하지~하루종일 쫀쫀한 손목보호대</strong><br>
                                    <p>왼쪽,오른쪽 차이가 많이 큰가요 ?</p>
                                </div>
                            </div>
                            <div class="msgTime">몇 초전</div>
                        </button>
                    </li>
                    <li class ="msgListItem">
                        <button class="msgItem">
                            <div class="itemInfo">
                                <div class="itemImg2"></div>
                                <div class="itemInfoCont">
                                    <em>리모롤</em><br>
                                    <strong>[모아주문] 동물보건사가 직접 만든 똑똑한 테이프클리너!</strong><br>
                                    <p>안녕하세요 리모롤 입니다:)
                                        물이 묻은 상태로 사용을 하시면 점착력이 낮아질 수 있습니다.
                                        감사합니다.</p>
                                </div>
                            </div>
                            <div class="msgTime">11일전</div>
                        </button>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>
