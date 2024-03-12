<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-19
  Time: 오후 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>user error page</title>
    </head>
    <body>
<%--    <h1>작업처리 도중 에러가 발생하였습니다.</h1>--%>
<%--    ===============================================================================<br>--%>
<%--    <%= exception.getMessage().replace("\n", "<br>") %>--%>
<%--    ===============================================================================<br>--%>

    </body>
    <script>
        alert("${errorMsg}");
        // alert('ddddd');
        window.location = "<c:url value="/" />";
    </script>
</html>
