<%--
  Created by IntelliJ IDEA.
  User: bada
  Date: 2/5/24
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

%>
<html>
<head>
    <title>관심프로젝트</title>
</head>
<body>
    <h1>관심 프로젝트 리스트</h1>
<%--    <h1>${user_id}</h1>--%>
    <c:if test="${result!=null}">
        <c:if test="${result == 0}">
        <h2>빈하트</h2>
        </c:if>
        <c:if test="${result == 1}">
        <h2>빨간하트</h2>
        </c:if>
    </c:if>
</body>
</html>
