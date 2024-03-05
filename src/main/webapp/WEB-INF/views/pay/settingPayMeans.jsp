<%--
  Created by IntelliJ IDEA.
  User: greta
  Date: 2024/02/01
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<script type="text/javascript" src="/static/product/vendor/jquery.bpopup.min.js"></script>
<script type="text/javascript" src="/static/pay/js/payMeans.js"></script>
<link rel="stylesheet" href="/static/pay/css/pay-means-list.css">
<link rel="stylesheet" href="/static/pay/css/pay-means-popup.css">
<link rel="stylesheet" href="/static/main/common.css">
<div class="containerWrapper fontStyle">
    <div class="leftContainer">
        <div class="settingFormWrapper">
            <div class="settingFormHeader">
                <p class="settingFormTitle">등록된 결제수단</p>
                <button class="settingFormButton" type="button" id="regBtn">+ 추가</button>
            </div>
            <div class="emptyList">
                <div class="emptyListErrorMsg">
                    <div class="ErrorMsgIcon">
                        <svg viewBox="0 0 48 48">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M24 43.8C13 43.8 4.2 35 4.2 24C4.2 13 13 4.2 24 4.2C35 4.2 43.8 13 43.8 24C43.8 35 35 43.8 24 43.8ZM24 2C11.9 2 2 11.9 2 24C2 36.1 11.9 46 24 46C36.1 46 46 36.1 46 24C46 11.9 36.1 2 24 2ZM24 32.3C22.7 32.3 21.8 33.401 21.8 34.6C21.8 35.8 22.9 36.8 24.1 36.8C25.4 36.8 26.3 35.7 26.3 34.5C26.3 33.3 25.3 32.3 24 32.3ZM24.1 29.0998H24C23.3 28.9998 22.7 28.4008 22.9 27.7008C22.8825 27.1051 22.8619 26.3713 22.8388 25.5474C22.7299 21.6673 22.565 15.7867 22.4 12.8998V12.7998C22.3 11.8998 23.1 11.2998 24.2 11.2998C25.3 11.2998 26 11.8998 26 12.7998V12.9998C25.8994 14.7101 25.8241 17.5591 25.7486 20.414C25.6741 23.2344 25.5994 26.0604 25.5 27.7998V27.9008C25.4 28.5998 24.8 29.0998 24.1 29.0998Z"></path>
                        </svg>
                    </div>
                    등록된 결제수단이 없습니다.
                    <br>
                    결제수단을 추가해주세요.
                </div>
            </div>
            <div class="listBox">
                <div id="pay_means_data"></div>
                <div class="boxViewMoreWrapper">
                    <button id="viewMoreBtn" type="button">
                        <span>더보기
                            <div class="arrowDown">
                                <svg viewBox="0 0 48 48">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M2 14.4065C2 13.1363 3.09843 12.0615 4.39657 12.0615C4.99571 12.0615 5.59485 12.257 6.09414 12.7455L23.9685 29.4526L41.843 12.6478C42.8415 11.7684 44.3394 11.7684 45.338 12.7455C46.2367 13.7226 46.2367 15.1882 45.2381 16.0676L23.9685 36L2.79886 16.0676C2.29957 15.6767 2 14.9928 2 14.4065Z"></path>
                                </svg>
                            </div>
                        </span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="rightContainer">
        <p class="faqTitle">결제수단을 등록/삭제하면 현재 후원에 등록한 결제수단이 변경/삭제되나요?</p>
        <div class="faqContent">
            아닙니다. 이곳에서 결제수단을 등록/삭제하더라도 이미 후원에 등록한 결제수단이 변경/삭제되지 않습니다. 이를 변경하시려면 후원현황에서 변경해주세요.
            <span class="faqContentLink"><a href="<c:url value='/mypage/fundingProject'/>">후원현황 바로가기</a></span>
        </div>
    </div>
</div>
<%--register popup--%>
<jsp:include page="registerCardFormPop.jsp" />