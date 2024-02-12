<jsp:useBean id="likedto" class="com.persistence.dto.LikeDto" scope="request"/>
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
<%--    <h2>${likedto.Pj_id}</h2>--%>
<%--    <h2>${likedto.User_id}</h2>--%>
    <h5>1이면 좋아요</h5>
    <h5>0이면 좋아요취소</h5>
    <table border="1">
        <tr>
            <td>번호</td>
            <td>프로젝트 제목</td>
            <td>프로젝트 아이디</td>
            <td>회원 아이디</td>
            <td>좋아요 상태</td>
        </tr>
        <c:forEach items="${list}" var="likedto">
            <tr>
                <td><c:out value="${likedto.bno}"/></td>
                <td><c:out value="프로젝트 제목"/></td>
                <td><c:out value="${likedto.pj_id}"/></td>
                <td><c:out value="${likedto.user_id}"/></td>
                <td><c:out value="${likedto.like_status}"/></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
