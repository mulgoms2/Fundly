<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tumblbug Admin</title>
    <link rel="stylesheet" href="/static/css/webfont.css"> "<c:url value='/static/css/style.css'/>"
    <link rel="stylesheet" href="/static/css/common.css">
    <link href="<c:url value='/static/css/style.css'/>"  rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/vendor/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="/static/js/vendor/jquery-ui.js"></script>
    <script type="text/javascript" src="/static/js/vendor/jquery.bpopup.min.js"></script>
    <script type="text/javascript" src="/static/js/vendor/moment.min.js"></script>
    <script type="text/javascript" src="/static/js/vendor/daterangepicker.js"></script>
    <script type="text/javascript" src="/staticjs/common.js"></script>
    <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
</head>
<body>
<button class="mnBtnbl">
    <i class="arrow"></i>
</button>
<div class="lftMenuWrap">
    <div class="mnTit">
        <h1>텀블벅 어드민</h1>
        <button class="mnBtn">
            <i class="arrow"></i>
        </button>
    </div>
    <div class="lftMenu">
        <nav class="lftWrap">
            <ul class="lnb">
                <li>
                    <a href="#" class="">회원관리</a>
                    <ul class="sub">
                        <li>
                            <a href="#">회원정보</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">가맹점관리</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">상품관리</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">주문관리</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">통계분석</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="">고객지원</a>
                    <ul class="sub">
                        <li>
                            <a href="#">서브메뉴1</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴2</a>
                        </li>
                        <li>
                            <a href="#">서브메뉴3</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
<div class="conWrap">
    <div class="title">
        <p class="tit">후원자 관리</p>
    </div>
    <div class="sttBox">
        <div class="lftBox">
            <span class="sttBlue">선물 전달 중</span>
            <p class="bxTit">선물 발송 후, 운송장을 입력하고 선물 전달 상태를 '전달 완료'로 변경해주세요.</p>
            <p class="bxStit">운송장을 입력하여 후원자가 배송 상태를 확인할 수 있도록 해주세요.</p>
        </div>
        <div class="rgtLink">
            <a href="#">자세히 보기</a>
        </div>
    </div>
    <div class="btnWrap">
        <div class="lftBtngrp">
            <a href="#">
                <i class="fa-regular fa-comments"></i>
                <span>메시지 발송</span>
            </a>
            <a href="#">
                <i class="fa-solid fa-location-dot fa-lg"></i>
                <span>배송지 확정</span>
            </a>
            <a href="#">
                <i class="fa-solid fa-gift fa-lg"></i>
                <span>선물 전달 완료</span>
            </a>
        </div>
        <div class="rgtBtngrp">
            <button class="gray">
                <i class="fa-regular fa-file-import"></i>
                운송장 입력
            </button>
            <button class="carrot">엑셀 메일 발송</button>
        </div>
    </div>
    <div class="tbCont">
        <div class="tbWrap">
            <table>
                <tr>
                    <th>후원번호</th>
                    <th>이름</th>
                    <th>선물</th>
                    <th>추가 후원금</th>
                    <th>총 후원 금액</th>
                    <th>후원 일시</th>
                    <th>알림 신청</th>
                    <th>결제 상태</th>
                    <th>선물 전달</th>
                </tr>
                <!-- 데이터가 없을 경우 -->
                <td colspan="9">
                    <p class="noData">데이터가 없습니다</p>
                </td>
                <!-- 데이터 영역 -->
                <!-- <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr> -->
            </table>
        </div>
    </div>
</div>
<footer>
    <p>@Copyright 2024. 텀블벅 관리자 사이트 All Rights Reserved.</p>
</footer>
</body>
</html>
