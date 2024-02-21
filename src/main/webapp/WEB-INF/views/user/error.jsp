<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-19
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>

<html>
    <head>
        <title>user error page</title>
    </head>

    <body>
    <h1>작업처리 도중 에러가 발생하였습니다.</h1>
    ===============================================================================<br>
    <%= exception.getMessage().replace("\n", "<br>") %>
    ===============================================================================<br>
    </body>
</html>
