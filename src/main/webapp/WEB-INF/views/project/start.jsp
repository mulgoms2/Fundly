<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dobigulbi
  Date: 3/1/24
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${projectDto ne null}">
<%--    반드시 프로젝트 id를 컨트롤러로 함께 보내야한다.--%>
    <form action="<c:url value="/editor/info" />">
<%--        <input type="hidden" name="pj_id" value="${pj_id}">--%>
        <button>이어서 작성하기</button>
    </form>
</c:if>
<c:if test="${projectDto eq null}">
    <form action="/editor/info" method="post">
        <button>지금 시작하기</button>
    </form>
</c:if>
</body>
</html>
