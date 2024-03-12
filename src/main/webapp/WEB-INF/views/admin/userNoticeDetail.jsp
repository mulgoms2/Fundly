<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        .FKKEM {
            padding: 24px 24px 64px;
        }
        .FKKEM {
            padding: 24px 0px 65px;
        }
        .dwqwNI {
            max-width: 1160px;
        }
        .dwqwNI {
            max-width: 1080px;
            margin: 0px auto;
        }
        .dwqwNI {
            width: 100%;
        }
        .iikcKB {
            padding: 40px 40px 88px;
            border-radius: 4px;
            border: 1px solid rgb(230, 230, 230);
        }
        .iikcKB header {
            padding: 0px 0px 16px;
        }

        .iikcKB header .group {
            display: block;
            font-weight: bold;
            color: rgb(158, 158, 158);
            font-size: 13px;
            line-height: 20px;
            letter-spacing: -0.015em;
            margin-bottom: 4px;
        }

        .iikcKB header h3 {
            font-weight: bold;
            color: rgb(61, 61, 61);
            font-size: 24px;
            line-height: 36px;
            letter-spacing: -0.025em;
            margin-bottom: 12px;
        }

        .iikcKB .content {
            font-size: 16px;
            line-height: 27px;
            letter-spacing: -0.02em;
            padding: 48px 44px 0px;
        }

        .iquFLs {
            list-style: none;
            padding: 0px;
            margin: 0px 0px 40px;
        }

        .iquFLs li {
            display: flex;
            -webkit-box-align: center;
            align-items: center;
            width: 100%;
            height: 47px;
            font-size: 13px;
            line-height: 20px;
            letter-spacing: -0.015em;
            padding: 0px 16px;
            border-bottom: 1px solid rgb(230, 230, 230);
        }
        .iquFLs li button {
            flex: 1 1 0%;
            font-size: 16px;
            line-height: 27px;
            letter-spacing: -0.02em;
            text-overflow: ellipsis;
            overflow: hidden;
            word-break: normal;
            white-space: nowrap;
            color: rgb(109, 109, 109);
            background: transparent;
            text-align: left;
            border: 0px;
        }
        .FKKEM .btn-wrap {
            display: flex;
            flex-wrap: wrap;
            -webkit-box-pack: center;
            justify-content: center;
        }
        .FIsmA {
            width: 199px;
            height: 52px;
        }

        .FIsmA {
            /* width: 167px; */
            /* height: 44px; */
            font-size: 16px;
            line-height: 27px;
            letter-spacing: -0.02em;
            font-weight: normal;
            color: rgb(109, 109, 109);
            border: 1px solid rgb(230, 230, 230);
            border-radius: 4px;
            padding: 0px;
            box-sizing: border-box;
            background: rgb(255, 255, 255);
            margin: 0px;
        }

        .bTpnYq {
            cursor: pointer;
            display: inline-block;
            min-height: 1em;
            vertical-align: baseline;
            box-shadow: transparent 0px 0px 0px 1px inset, rgba(0, 0, 0, 0.1) 0px 0em 0px 0px inset;
            user-select: none;
            transition: opacity 0.1s ease 0s, background-color 0.1s ease 0s, color 0.1s ease 0s, box-shadow 0.1s ease 0s, background 0.1s ease 0s;
            -webkit-tap-highlight-color: transparent;
            text-transform: none;
            text-shadow: none;
            font-style: normal;
            text-align: center;
            text-decoration: none;
            opacity: 1;
        }
    </style>
</head>
<body>
<div class="Container__ContainerComponent-o2c7wa-0 dwqwNI Detail__StyledContainer-sc-1n09q0s-1 FKKEM">
    <div class="Detail__StyledDetail-sc-1n09q0s-2 iikcKB">
        <span class="group">공지사항</span>
        <header>
            <h3>${News.news_title}</h3>
            <span class="date">${News.mod_dtm==null ? News.reg_dtm: News.mod_dtm}</span>
        </header>
        <div class="content">${News.news_cont}</div>
    </div>
    <ul class="Detail__StyledSubNav-sc-1n09q0s-3 iquFLs">
        <li><b>이전글</b><button type="button" value="/notices/notice/460">
            <em>공지사항</em>[이용 안내] 2024년 설 연휴 서비스 이용 안내 (24.02.09.(금)~24.02.12.(월))</button>
        </li>
        <li><b>다음글</b><button type="button" value="/notices/notice/465">
            <em>공지사항</em>[이용 안내] 네이버페이 3월 은행/증권사 시스템 점검 일정 안내</button>
        </li>
    </ul>
    <div class="btn-wrap">
        <button class="Button-sc-1x93b2b-0 Detail__StyledButton-sc-1n09q0s-4 bTpnYq FIsmA"><a href="<c:url value='/noticeList?page=${page}'/>">목록으로 돌아가기</a></button>
    </div>
</div>

</body>
</html>