<%--
  Created by IntelliJ IDEA.
  User: lemon
  Date: 2024-02-18
  Time: 오후 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%--errorPage를 true로 설정하면 자동으로 상태코드가 500이된다.(변환된 서블릿 파일 참고)--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
An Error has occured during processing project.
<br>
<h2>
<b><%=exception.getMessage()%></b>
</h2>
</body>
</html>
