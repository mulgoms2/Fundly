<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: dobigulbi
  Date: 2/14/24
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
서비스 수행 도중 에러가 발생하였습니다.
<form:errors path=""/>
<%--<form:errors path="*"></form:errors>--%>
<form:errors path="chatRequest.helo"/>
<%--<form:errors path="chatRequest.pj_id"/>--%>
<form:errors path="chatRequest.helo"/>
<form:errors path="helo"/>
<form:errors path="chatRequest.pj_id"/>
</body>
</html>
