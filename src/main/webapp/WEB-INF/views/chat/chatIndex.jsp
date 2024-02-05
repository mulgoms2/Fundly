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
<form id="join" action="/chat" method="get">
<%--    <input type="hidden" name="user_id" value="${sessionScope.id}">--%>
    <input type="text" name="user_id" >
    <input type="text" name="pj_id" >
    <button type="submit">chat</button>
</form>
<script>
</script>
</body>
</html>
