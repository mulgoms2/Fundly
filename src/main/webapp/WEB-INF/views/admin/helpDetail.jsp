
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" href="<q:url value='/static/admin/css/helpDetail.css'/>">
</head>
<body>

<div class="header-wrapper">
    <div class="notice"></div>
    <header class="header">
        <div class="logo">
            <a  href="<c:url value="/help/subList"/> ">
                <img src="/img/logo.png" alt="로고">
                헬프센터
            </a>
        </div>
        <div class="nav-wrapper">
            <span class="icon-menu"></span>
            <nav class="user-nav" id="user-nav">
                <a href="/Fundly/">텀블벅으로 돌아가기</a>
                <a href="">문의하기</a>
            </nav>
        </div>
    </header>
</div>

<div class="container">
    <nav class="sub-nav">
        <ol class="breadcrumbs">

            <li title="텀블벅 헬프센터">

                <a href="">텀블벅 헬프센터</a>

            </li>

            <li title="주제별 도움말">

                <a href="">주제별 도움말</a>

            </li>

            <li title="텀블벅 기본">

                <a href="">텀블벅 기본</a>

            </li>

        </ol>

        <form role="search" class="search" data-search="" action=""  method=""><input name="utf8" type="hidden" value="✓" autocomplete="off"><input type="search" name="query" id="query" placeholder="검색" aria-label="검색"></form>
    </nav>

    <div class="section-container">
        <section class="section-content">
            <header class="page-header">
                <h2>
                    텀블벅 기본
                </h2>
            </header>



            <ul class="article-list">

                <li class="article-list-item  article-promoted">
                    <c:forEach var="SubHelpDto" items="${subList}">
                         <li class="article-list-item article-promoted"><a class="article-list-link" href="<c:url value='/help/detaildetail?sub_help_sort=${SubHelpDto.sub_help_sort}&sub_help_seq=${SubHelpDto.sub_help_seq}' />">${SubHelpDto.sub_help_title}</a></li>
                    </c:forEach>

                </li>

            </ul>



        </section>
    </div>
</div>
</body>
</html>
