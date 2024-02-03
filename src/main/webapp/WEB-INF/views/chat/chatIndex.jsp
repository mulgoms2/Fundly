<%--
  Created by IntelliJ IDEA.
  User: dobigulbi
  Date: 2/3/24
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/chat" method="post">
<%--    <input type="hidden" name="user_id" value="${sessionScope.id}">--%>
    <input type="hidden" name="user_id" value="test">
    <input type="hidden" name="pj_id" value="testPj">
    <button type="submit">chat</button>
</form>
</body>
</html>
