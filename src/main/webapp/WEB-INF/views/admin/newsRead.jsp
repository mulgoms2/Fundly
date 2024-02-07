<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-02-06
  Time: 오후 3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<q:url value='/static/admin/css/news.css'/>">
</head>
<body>
<div class="class">공지사항</div>

<form id="gogo">
<div class="content">
    <header class="contenthead">
        <span class="group">이벤트</span>
        <h3>
            ${News.news_title}
        </h3>
        <span class="date">${News.reg_dtm}</span>
    </header>

    <div class="contentcontent">${News.news_cont}</div>
</div>
</form>
<%--버튼이 폼태그안에있으면 /modify 엔드포인트에서
 /admin/newsWrite 페이지로의 포워딩이 발생.
  /admin/newsWrite 페이지로의 포워딩 직후에 다시
 /admin/newsRead 페이지로의 포워딩이 발생. --%>
<button onclick="location.href='<c:url value="/admin/modify?news_seq="/>${News.news_seq}'">수정하기</button>
<button onclick="location.href='<c:url value="/admin/list"/>'">목록</button>
</body>
</html>