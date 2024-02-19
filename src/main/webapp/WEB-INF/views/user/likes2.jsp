<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-13
  Time: 오후 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>관심목록</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>프로젝트제목</th>
            <th>유저이름</th>
            <th>등록일</th>
            <th>좋아요상태</th>
        </tr>
        <c:forEach var="likes" items="${likes}">
        <tr>
            <td>${likes.pj_id}</td>
            <td>${likes.user_id}</td>
            <td>${likes.like_dtm}</td>
            <td>${likes.like_status}</td>
        </tr>
        </c:forEach>
    </table>
</body>
<%--관심 프로젝트--%>