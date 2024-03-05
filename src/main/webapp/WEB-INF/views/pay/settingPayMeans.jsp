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
<link rel="stylesheet" href="/static/main/common.css">
<link rel="stylesheet" href="/static/pay/css/pay-means-list.css">
<link rel="stylesheet" href="/static/pay/css/pay-means-popup.css">
<div class="flexOnly">
    <div class="container">
        <div class="profileimg">
            <div class="profileimgFormHeader flexOnly">
                <p class="pTag">등록된 결제수단</p>
                <button type="button" id="regBtn" class="ButtonTag">+ 추가</button>
            </div>
            <div class="listBox" id="pay_means_data">
                <c:forEach var="payMeansDto" items="${list}">
                    <div class="listBoxElement">
                        <div class="thumbnail">
                            <div class="cardImg">
                                <img class="imgBox" src="<c:url value='${payMeansDto.file_path}${payMeansDto.file_name}${payMeansDto.file_extension}'/>" alt="결제수단 이미지">
                            </div>
                        </div>
                        <div class="content">
                            <div class="companyName">${payMeansDto.card_co_type}
                                <c:if test="${payMeansDto.default_pay_means_yn == 'Y'}">
                                    <span class="default_tag">기본</span>
                                </c:if>
                            </div>
                            <div class="cardNumber">************${payMeansDto.card_no}</div>
                        </div>
                        <div class="func">
                            <button type="button" class="meatballBtn">
                                    <img src="/static/img/meatball.svg" alt="btn" />
                            </button>
                            <div class="clickBtnContent">
                                <button type="button" class="defaultSetBtn" data-user-id="${payMeansDto.user_id}" data-pay-means-id="${payMeansDto.pay_means_id}">기본 결제수단 지정</button>
                                <button type="button" class="removeBtn" data-user-id="${payMeansDto.user_id}" data-pay-means-id="${payMeansDto.pay_means_id}">삭제</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="userSettingContainer">
        <div class="userInfoMod"></div>
        <div class="userInfoComment">
            <p>결제수단을 등록/삭제하면 현재 후원에 등록한 결제수단이 변경/삭제되나요?</p>
            <div class="userInfoCommentContent">
                아닙니다. 이곳에서 결제수단을 등록/삭제하더라도 이미 후원에 등록한 결제수단이 변경/삭제되지 않습니다. 이를 변경하시려면 후원현황에서 변경해주세요.
                <span><a href="<c:url value='/mypage/fundingProject'/>">후원현황 바로가기</a></span>
            </div>
        </div>
    </div>
</div>
<%--register popup--%>
<jsp:include page="registerCardFormPop.jsp" />