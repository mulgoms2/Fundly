<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-11
  Time: 오전 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<%--<c:set var="loginInfo" value="${user_email=='' || user_email == null ? '로그인/회원가입' : user_name}"/>--%>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/static/user/Coupon.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/common.css'/>">
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    </head>

    <%--응원권--%>
    <body>
        <div class="main">
            <div class="title">응원권</div>
            <p class="couponInfo font1">사용 가능한 응원권 <span>(1)</span></p>
            <ul>
                <li>
                    <div class="couponItem">
                        <div class="couponItemli">
                            <div class="couponItemLiUp">
                                <span class="couponItemLebel">사용가능</span>
                                <p class="font2">1,000원</p>
                                <p class="font3">오픈 10분 만에 초과달성 기념 할인</p>
                                <p class="font4">응원권 사용 기간 : 프로젝트 종료일인 2024-02-16까지<br>선물없이 후원하기에서 사용 불가</p>

                            </div>
                            <div class="couponItemLiDo">
                                <div class="CouponItemImg"></div>
                                <p class="font5">컬러, 종이, 인쇄 한권으로 종결 &lt 컬러 & 페이퍼 올인원&gt</p>
                            </div>
                        </div>

                        <div class="couponItemri">
                            <p class="font6">후원하러 가기</p>
                            <div class="fundingimg font6"></div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </body>
</html>
