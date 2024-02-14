<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-02-14
  Time: 오후 5:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="header-wrapper">
    <div class="notice"></div>
    <header class="header">
        <div class="logo">
            <a  href="<c:url value="/help/subList"/> ">
                <img src="/img/logo.png" alt="로고">
                헬프센터
            </a>
        </div>
        <div class="nav-wrapper">
            <span class="icon-menu"></span>
            <nav class="user-nav" id="user-nav">
                <a href="/Fundly/">텀블벅으로 돌아가기</a>
                <a href="">문의하기</a>
            </nav>
        </div>
    </header>
</div>

</body>
</html>
