<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<q:url value='/static/admin/css/helpDetailDetail.css'/>">
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
                <a href="/">텀블벅으로 돌아가기</a>
                <a href="">문의하기</a>
            </nav>
        </div>
    </header>
</div>
<div class="container">
    <div class="article-container" id="article-container">
        <section class="article-sidebar">
            <section class="section-articles collapsible-sidebar" aria-expanded="true">
                <h3 class="collapsible-sidebar-title sidenav-title">이 섹션의 문서</h3>
                <ul>
                    <c:forEach var="subList1" items="${subList}">
                    <li>
                        <a href="<c:url value="/help/detaildetail?sub_help_seq=${subList1.sub_help_seq}&sub_help_sort=${subList1.sub_help_sort}"/> " class="sidenav-item current-article">${subList1.sub_help_title}</a>
                    </li>
                    </c:forEach>
                </ul>
                <a href="<c:url value='/help/detail?sub_help_sort=${subHelpDto.sub_help_sort}'/> " class="article-sidebar-item">더보기</a>
            </section>
        </section>
        <article class="article">
            <div class="article-main">
                <header class="article-header">
                    <h2 class="article-title">
                        ${subHelpDto.sub_help_title}

                    </h2>
                    <div class="article-author">
                        <div class="article-meta">
                            <ul class="meta-group">

                                <li class="meta-data">${subHelpDto.mod_dtm =null? subHelpDto.reg_dtm : subHelpDto.mod_dtm }</li>

                            </ul>
                        </div>
                    </div>
                </header>
                <section class="article-info">
                    <div class="article-content">
                        <div class="article-body">
                            ${subHelpDto.sub_help_cont}
                        </div>

                    </div>
                </section>
            </div>


        </article>
    </div>
</div>
</body>
</html>