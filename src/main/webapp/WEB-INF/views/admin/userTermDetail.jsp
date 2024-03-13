<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            background-color: #FFFFFF;
            color: #3d3d3d;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.5;
            -webkit-font-smoothing: antialiased;
            word-break: keep-all;
        }
        /* --------------------header---------------------------- */
        a{
            color: rgb(39, 163, 255);
            text-decoration: none;
        }
        .header-wrapper {
            display: block;
            background-color: #ffffff;
        }
        .header {
            max-width: 1160px;
            margin: 0 auto;
            padding: 0 5%;
            position: relative;
            align-items: center;
            display: flex;
            height: 71px;
            justify-content: space-between;
        }
        /* .header {
            padding: 0;
            width: 90%;
        } */
        .logo a {
            line-height: 28px;
            text-decoration: none;
            font-weight: bold;
            color: inherit;
            -webkit-font-smoothing: antialiased;
        }
        .logo img {
            max-height: 28px;
            vertical-align: middle;
        }
        .user-nav {
            position: relative;
        }

        .user-nav {
            display: inline-block;
            /* position: absolute; */
            white-space: nowrap;
        }
        .nav-wrapper a {
            display: inline-block;
            padding: 0;
            margin: 0 10px;
        }

        .nav-wrapper a {
            border: 0;
            color: #3C3737;
            /* display: none; */
            font-size: 14px;
            font-weight: bold;
            /* padding: 0 0 0 25px; */
            line-height: 71px;
            width: auto;
            border-bottom: 2px solid transparent;
        }
        /* -----------------containerheader------------------------ */
        .container-divider {
            border-top: 1px solid rgba(0, 0, 0, 0.05);
            margin-bottom: 10px;
        }
        .container {
            padding: 0;
            width: 90%;
        }

        .container {
            max-width: 1160px;
            margin: 0 auto;
            /* padding: 0 5%; */
        }
        .sub-nav {
            align-items: baseline;
            flex-direction: row;
        }
        .sub-nav {
            display: flex;
            /* flex-direction: column; */
            justify-content: space-between;
            margin-bottom: 20px;
            min-height: 50px;
            padding-bottom: 10px;
        }
        .tab-nav {
            flex-direction: row;
        }

        .tab-nav {
            display: flex;
            /* flex-direction: column; */
            width: 100%;
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
            margin-bottom: 20px;
            padding-bottom: 10px;
        }
        ul {
            list-style: none;
            margin: 0;
            padding: 0;
        }
        .tab-nav .tab-title {
            flex-grow: 1;
        }
        .tab-nav .tab-item {
            flex-grow: 0;
        }
        /* -------------------------containerBody--------------------------- */
        .article-container {
            flex-direction: row;
        }

        .article-container {
            display: flex;
            /* flex-direction: column; */
        }
        .static.article {
            padding-left: 0;
            max-width: 720px;
        }

        .article {
            flex: 1 0 63%;
            /* padding-left: 30px; */
        }
        .static.article h1 {
            font-size: 32px;
        }
        .article-content {
            font-size: 15px;
            line-height: 1.8;
            margin: 40px 0;
            word-wrap: break-word;
        }
        .static.article .article-body {
            line-height: 1.8;
            margin-top: 0;
        }



    </style>
</head>
<body>
<div class="header-wrapper">
    <div class="notice"></div>
    <header class="header">
        <div class="logo">
            <a title="홈" href="<c:url value="/help/subList"/> ">
                <img src="//theme.zdassets.com/theme_assets/730708/96e585971ebd23d9b51450a15f28eafab17b0c15.png" alt="로고">
                헬프센터
            </a>
        </div>
        <div class="nav-wrapper">
            <span class="icon-menu"></span>
            <nav class="user-nav" id="user-nav">
                <a href="/">텀블벅으로 돌아가기</a>
            </nav>
        </div>
    </header>
</div>
<main role="main">
    <div class="container-divider"></div>
    <div class="container">
        <nav class="sub-nav">
            <ul class="tab-nav">
                <li class="tab-title"><h2>약관 및 정책</h2></li>
                <c:forEach  var="term" items="${termTitleList}">
                <li class="tab-item"><a href="<c:url value="/termDetail?term_title=${term.term_title}"/> ">${term.term_title}</a></li>
                </c:forEach>
            </ul>
        </nav>
        <div class="article-container">
            <article class="static article">
                <h1 class="article-title">
                    ${prne.term_title}(${prne.term_srt_date} ~ ${prne.term_end_date==null ? "": prne.term_end_date})
                </h1>
                <div>시행일: ${prne.term_srt_date}  <c:choose>
                    <c:when test="${prne.prev==0}">
                    </c:when>
                    <c:otherwise>
                    <a href="<c:url value="termDetail?term_seq=${prne.prev}&term_title=${prne.term_title}"/>">(개정전 약관 바로가기)</a>
                    </c:otherwise>
                </c:choose>
                </div>
                <div class="article-content">
                    <div class="article-body">
                        ${prne.term_cont}
                    </div>
                </div>
            </article>
        </div>
    </div>
</main>
</body>
</html>