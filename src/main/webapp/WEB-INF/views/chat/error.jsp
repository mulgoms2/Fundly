<%@ page import="com.persistence.dto.ChatRequest" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Objects" %>
<%@ page import="org.springframework.validation.BeanPropertyBindingResult" %>
<%@ page import="org.springframework.validation.FieldError" %>
<%@ page import="java.util.List" %>
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
<%
    Enumeration<String> attributeNames = request.getAttributeNames();

    Iterator<String> iterator = attributeNames.asIterator();

    while (iterator.hasNext()) {
        String next = iterator.next();
        System.out.println(next);
    }
    Object requ = request.getAttribute("org.springframework.validation.BindingResult.chatRequest");

    System.out.println("\n\n\n"+requ+"\n\n\n");

    BeanPropertyBindingResult result = (BeanPropertyBindingResult) requ;
    List<FieldError> list =  result.getFieldErrors();

    list.stream().forEach(System.out::println);
%>
<%=((ChatRequest)request.getAttribute("chatRequest")).getBuy_id()%>
<%--<form:errors path="chatRequest.buy_id"/>--%>
<%--<form:errors path="chatRequest.pj_id"/>--%>
</body>
</html>
