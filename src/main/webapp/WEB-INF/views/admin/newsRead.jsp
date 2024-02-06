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
    <link rel="stylesheet" href="<q:url value='/static/css/news.css'/>">
</head>
<body>
<div class="class">공지사항</div>

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
<button type="submit"><a href="<c:url value="/admin/modify"/>">수정</a></button><button><a href="<c:url value="/admin/list"/>" >수정</a>목록</button>
</body>
</html>