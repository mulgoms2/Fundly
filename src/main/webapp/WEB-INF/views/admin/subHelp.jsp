
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<q:url value='/static/admin/css/subHelp.css'/>">
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
<main role="main">
    <section class="section hero" style="background-image: url(https://theme.zdassets.com/theme_assets/730708/7120778d7f16436a3c2046f700f601d1df6a6a81.jpg);">
        <div class="hero-gradient">
        </div>
        <div class="hero-container">
            <div class="hero-inner">
                <h1>무엇을 도와드릴까요?</h1>
                <form role="search" class="search search-full" data-search="" data-instant="true" autocomplete="off" action="https://help.tumblbug.com/hc/ko/search" accept-charset="UTF-8" method="get"><input name="utf8" type="hidden" value="✓" autocomplete="off"><input type="search" name="query" id="query" placeholder="결제, 정산, 심사기준 등으로 검색해보세요" autocomplete="off" aria-label="결제, 정산, 심사기준 등으로 검색해보세요"></form>
            </div>
        </div>
    </section>

    <div class="container">



        <section class="section knowledge-base">
            <h2 class="section-title">주제별 도움말</h2>
            <div class="three collapsible columns">
                <div class="column">
                    <h3>텀블벅 기본</h3>
                    <ul class="article-list">
                        <c:forEach var="SubHelpDto" items="${subList0}">
                            <li class="article-list-item article-promoted"><a class="article-list-link" href="<c:url value='/help/detaildetail?sub_help_seq=${SubHelpDto.sub_help_seq}&sub_help_sort=${SubHelpDto.sub_help_sort}' />">${SubHelpDto.sub_help_title}</a></li>
                        </c:forEach>
                    </ul>

                    <a href="<c:url value='/help/detail?sub_help_sort=0'/>">총${count0}개 글 보기</a>

                    <br><br><br><br>
                </div>
                <div class="column">
                    <h3>후원자 질문</h3>
                    <ul class="article-list">
                        <c:forEach var="SubHelpDto" items="${subList1}">
                            <li class="article-list-item article-promoted"><a class="article-list-link" href="<c:url value='/help/detaildetail?sub_help_seq=${SubHelpDto.sub_help_seq}&sub_help_sort=${SubHelpDto.sub_help_sort}' />">${SubHelpDto.sub_help_title}</a></li>
                        </c:forEach>
                    </ul>

                    <a href="<c:url value='/help/detail?sub_help_sort=1'/>">총${count1}개 글 보기</a>

                    <br><br><br><br>
                </div>
                <div class="column">
                    <h3>프로젝트 올리기</h3>
                    <ul class="article-list">
                        <c:forEach var="SubHelpDto" items="${subList2}">
                            <li class="article-list-item article-promoted"><a class="article-list-link" href="<c:url value='/help/detaildetail?sub_help_seq=${SubHelpDto.sub_help_seq}&sub_help_sort=${SubHelpDto.sub_help_sort}' />">${SubHelpDto.sub_help_title}</a></li>
                        </c:forEach>
                    </ul>

                    <a href="<c:url value='/help/detail?sub_help_sort=2'/>">총${count2}개 글 보기</a>

                    <br><br><br><br>
                </div>
                <div class="column">
                    <h3>시작하고 알리기</h3>
                    <ul class="article-list">
                        <c:forEach var="SubHelpDto" items="${subList3}">
                            <li class="article-list-item article-promoted"><a class="article-list-link" href="<c:url value='/help/detaildetail?sub_help_seq=${SubHelpDto.sub_help_seq}&sub_help_sort=${SubHelpDto.sub_help_sort}' />">${SubHelpDto.sub_help_title}</a></li>
                        </c:forEach>
                    </ul>

                    <a href="<c:url value='/help/detail?sub_help_sort=3'/>">총${count3}개 글 보기</a>

                    <br><br><br><br>
                </div>
                <div class="column">
                    <h3>소통하고 전달하기</h3>
                    <ul class="article-list">
                        <c:forEach var="SubHelpDto" items="${subList4}">
                            <li class="article-list-item article-promoted"><a class="article-list-link" href="<c:url value='/help/detaildetail?sub_help_seq=${SubHelpDto.sub_help_seq}&sub_help_sort=${SubHelpDto.sub_help_sort}' />">${SubHelpDto.sub_help_title}</a></li>
                        </c:forEach>
                    </ul>

                    <a href="<c:url value='/help/detail?sub_help_sort=4'/>">총${count4}개 글 보기</a>

                    <br><br><br><br>
                </div>

<%--    <c:forEach var="subList" items="${subLists}" varStatus="status">--%>
<%--        <div class="column">--%>
<%--            <h3>주제 ${}</h3>--%>
<%--            <ul class="article-list">--%>
<%--                <c:forEach var="SubHelpDto" items="${subList}">--%>
<%--                    <li class="article-list-item article-promoted">--%>
<%--                        <a class="article-list-link" href="<c:url value='/help/detaildetail?sub_help_seq=${SubHelpDto.sub_help_seq}&sub_help_sort=${SubHelpDto.sub_help_sort}' />">${SubHelpDto.sub_help_title}</a>--%>
<%--                    </li>--%>
<%--                </c:forEach>--%>
<%--            </ul>--%>
<%--            <a href="<c:url value='/help/detail?sub_help_sort=${status.index}'/>">총 ${counts[status.index]}개 글 보기</a>--%>
<%--            <br><br><br><br>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
            </div>
        </section>

        <section class="section policy">
            <h2 class="section-title">약관 및 정책</h2>
            <div class="three collapsible columns">
                <div class="column">
                    <ul class="article-list">
                        <c:forEach var="termTitle" items="${termTitleList}">
                            <li class="article-list-item article-promoted">
                                <a href="<c:url value="/termDetail?term_title=${termTitle.term_title}"/> " class="article-list-link">
                                    ${termTitle.term_title}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </section>
    </div>
</main>
</body>
</html>
