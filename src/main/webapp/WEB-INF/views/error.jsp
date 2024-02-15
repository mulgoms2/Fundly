<%--
  Created by IntelliJ IDEA.
  User: dobigulbi
  Date: 2/15/24
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
error
error
error
<%=exception%>
<%=exception.getCause()%>
<%=exception.getMessage()%>
<br/>
<%=java.util.Arrays.stream(exception.getStackTrace()).map(e->e.toString()).reduce("",(a,b)->{
    return a + b + "\n";})%>
</body>
</html>
