<%--
  Created by IntelliJ IDEA.
  User: bada
  Date: 2/25/24
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>프로젝트 후원하기</title>
    <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

    <link rel="stylesheet" href="/static/order/css/order.css">
<%--    <script type="text/javascript" src="../js/common.js"></script>--%>
<%--    <script type="text/javascript" src="../js/vendor/jquery-2.2.4.min.js"></script>--%>
<%--    <script type="text/javascript" src="../js/vendor/jquery-ui.js"></script>--%>
<%--    <script type="text/javascript" src="../js/vendor/jquery.bpopup.min.js"></script>--%>
<%--    <script type="text/javascript" src="../js/vendor/moment.min.js"></script>--%>
<%--    <script type="text/javascript" src="../js/vendor/daterangepicker.js"></script>--%>

    <%-- pay module --%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <%-- jquery --%>
    <script type="text/javascript" src="/static/product/vendor/jquery.bpopup.min.js"></script>
    <script type="text/javascript" src="/static/pay/js/orderPayMeans.js"></script>
    <script type="text/javascript" src="/static/pay/js/registerPayMeans.js"></script>
    <link rel="stylesheet" href="/static/pay/css/pay-means-popup.css">
    <link rel="stylesheet" href="/static/main/common.css">
</head>
<body>
    <div class="headerContainer">
        <div class="header">
            <a href="<c:url value='/'/>">
                <img alt="" class="logo" src="/static/img/fundly-logo.svg">
            </a>
            <div class="orderTitle">프로젝트 후원하기</div>
        </div>
    </div>
    <div class="main orderPageFontStyle">
        <div class="pjContainer">
            <div class="thum">
                <a href="#">
                    <img src="/static/img/orderimg.png" alt="">
                </a>
            </div>
            <div class="pjTit">
                <div class="ctg">캐릭터 · 굿즈</div>
                <div class="pjTitle">오늘도 포실포실 귀여운 하루 되세요<포햄이들 인형키링></div>
                <div class="fundNum">
                    <div class="fundMoney">13,470,000원</div>
                    <div class="per">670%</div>
                    <div class="dDay">2일 남음</div>
                </div>
            </div>
        </div>
        <div class="orderWrap">
            <div class="orderLeft">
                <div class="orderContainer">
                    <div class="giftContainer">
                        <div class="Info">선물 정보</div>
                        <div class="Box">
                            <div class="boxConts">
                                <div class="giftPack">
                                    <div class="giftPackTit">선물구성</div>
                                    <div class="giftPackCont">
                                        <div class="giftLiTit">설기햄 1 SET (x1)</div>
<%--                                        <div class="giftLiTit">설기햄 1 SET (x${선물개수})</div>--%>
                                        <ul class="giftLi">
                                            <li>설기 키링(+하트 크로스백, 해바라기 꽃) (x1)</li>
                                            <li>스티커 (x1)</li>
                                            <li>주민증 (x1)</li>
                                        </ul>
                                        <div class="giftDeliver">
                                            <div class="giftDelTit">예상 전달일</div>
                                            <div class="giftDelDate">2024.05.17</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="giftMoney">
                                    <div class="giftMoneyTit">선물금액</div>
                                    <div class="giftMoneyCont">23,000원</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="buyerContainer">
                        <div class="Info">후원자 정보</div>
                        <div class="Box">
                            <div class="boxConts">
                                <div class="phoneNo">
                                    <div class="phoneNoTit">연락처</div>
                                    <div class="phoneNoCont">010-3615-5607</div>
                                </div>
                                <div class="email">
                                    <div class="emailTit">이메일</div>
                                    <div class="emailCont">silverzoo030@gmail.com</div>
                                </div>
                                <div class="buyerNotice">
                                    <div id="section1">* 위 연락처와 이메일로 후원 관련 소식이 전달됩니다.</div>
                                    <div id="section2">* 연락처 및 이메일 변경은 설정 > 계정 설정에서 가능합니다.</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="adrContainer">
                        <div class="Info">배송지</div>
                        <div class="Box">
                            <div class="boxConts">
                                <div class="adrBoxConts">
                                    <div class="adrDetailConts">
                                        <div class="buyerBasic">
                                            <div class="buyerName">이은주</div>
                                            <div class="basicMark">기본</div>
                                        </div>
                                        <div class="adr">[06241] 서울 강남구 강남대로 364 (미왕빌딩, 역삼동) 10층</div>
                                        <div class="adrPhoneNo">010-3615-5607</div>
                                    </div>
                                    <div class="adrBtn">
                                        <button type="button" class="popupBtn">변경</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="payContainerWrapper">
                        <div class="payContainer">
                            <div class="payFormHeader">
                                <p class="Info">결제수단</p>
                            </div>
                            <div class="Box">
                                <div>
                                    <div class="rdoSelectorWrapper">
                                        <div class="rdoListElemWrapper">
                                            <div class="rdoListElem">
                                                <label class="rdoListElemLabel">
                                                    <div>
                                                        <input name="parameterType" type="radio" value="" checked>
                                                    </div>
                                                    <div class="rdoSelectorOption">
                                                        카드 간편결제
                                                        <span class="rdoSelectorOptionTag">할부 가능</span>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="rdoListElemWrapper">
                                            <div class="rdoListElem">
                                                <label class="rdoListElemLabel">
                                                    <div>
                                                        <input name="parameterType" type="radio" value="" disabled>
                                                    </div>
                                                    <div class="rdoSelectorOption">
                                                        네이버페이
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="rdoListElemWrapper">
                                            <div class="rdoListElem">
                                                <label class="rdoListElemLabel">
                                                    <div>
                                                        <input name="parameterType" type="radio" value="" disabled>
                                                    </div>
                                                    <div class="rdoSelectorOption">
                                                        계좌이체
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="cardList" class="cardListElemContainer">
                                    <div class="cardListElemWrapper">
                                        <div class="cardListElem">
                                            <div class="payNoticeBannerWrapper">
                                                <div class="payNoticeBanner">
                                                    <div class="payNoticeBannerIcon">
                                                        <svg viewBox="0 0 48 48">
                                                            <path d="M21.5 23.1C21.5 23.0448 21.5448 23 21.6 23H26.4C26.4552 23 26.5 23.0448 26.5 23.1V33.9C26.5 33.9552 26.4552 34 26.4 34H21.6C21.5448 34 21.5 33.9552 21.5 33.9V23.1Z"></path>
                                                            <path d="M21 17C21 15.3431 22.3431 14 24 14C25.6569 14 27 15.3431 27 17C27 18.6569 25.6569 20 24 20C22.3431 20 21 18.6569 21 17Z"></path><path fill-rule="evenodd" clip-rule="evenodd" d="M24 40C32.8366 40 40 32.8366 40 24C40 15.1634 32.8366 8 24 8C15.1634 8 8 15.1634 8 24C8 32.8366 15.1634 40 24 40ZM24 44C35.0457 44 44 35.0457 44 24C44 12.9543 35.0457 4 24 4C12.9543 4 4 12.9543 4 24C4 35.0457 12.9543 44 24 44Z"></path>
                                                        </svg>
                                                    </div>
                                                    <span>할부로 후원하려면 카드를 재등록 해주세요. 신용카드만 할부 가능합니다.</span>
                                                </div>
                                                <button id="regBtn" type="button" class="popupBtn payRegBtn">등록</button>
                                            </div>
                                            <div class="cardInfoWrapper">
                                                <div class="cardImg"></div>
                                                <div class="cardContentWrapper">
                                                    <div class="cardContent">
                                                        <div class="cardContentTitle"></div>
                                                        <div class="cardContentNumber"></div>
                                                        <button type="button" class="popupBtn payChangeBtn">변경</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="cardInstallWrapper">
                                            <div class="cardInstall">
                                                <div>
                                                    <div class="cardInstallMonthTitle">할부 개월</div>
                                                    <div class="cardInstallMonthSelection">
                                                        <div class="monthContentSelectWrapper">
                                                            <div class="monthContentSelect">
                                                                <span class="monthSelectInputWrapper">
                                                                    <input disabled="" readonly="" type="text" inputmode="text" autocapitalize="off" autocomplete="off" class="inputInner" value="일시불">
                                                                    <div class="arrowDownIcon">
                                                                        <svg viewBox="0 0 48 48">
                                                                            <path fill-rule="evenodd" clip-rule="evenodd" d="M2 14.4065C2 13.1363 3.09843 12.0615 4.39657 12.0615C4.99571 12.0615 5.59485 12.257 6.09414 12.7455L23.9685 29.4526L41.843 12.6478C42.8415 11.7684 44.3394 11.7684 45.338 12.7455C46.2367 13.7226 46.2367 15.1882 45.2381 16.0676L23.9685 36L2.79886 16.0676C2.29957 15.6767 2 14.9928 2 14.4065Z"></path>
                                                                        </svg>
                                                                    </div>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <div class="cardInstallGuide">
                                                            <button class="guideBtn" type="button">
                                                                <span>무이자 할부 안내</span>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div>
                                                    <div class="cardInstallDescription">* 무이자 할부 기간은 카드사에 따라 결제 시점에 변동될 수 있습니다.</div>
                                                    <div>* 5만원 미만 후원은 할부가 불가합니다.</div>
                                                    <div>* 체크카드, 법인카드는 할부가 불가합니다.</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="defaultPayCheckboxWrapper">
                                            <div class="defaultPayCheckbox">
                                                <label class="updateDefaultBtnLabel" for="updateDefaultCheckbox"></label>
                                                <input id="updateDefaultCheckbox" type="checkbox" value="">기본 결제수단으로 등록
                                            </div>
                                        </div>
                                    </div>
                                </div>
<%--                                등록된 결제수단이 없는 경우 보여준다.--%>
                                <div id="emptyList" class="emptyList cardListElemContainer">
                                    <span>+ 카드 등록</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="orderRight">
                <div class="totalContainer">
                    <div class="totalConts">
                        <div class="Info" style="visibility: hidden">최종정보</div>
                        <div class="totalBox">
                            <div class="totalBoxConts">
                                <div class="totalName">최종 후원 금액</div>
                                <div class="totalMoney">22,000원</div>
                            </div>
                        </div>
                    </div>
                    <div class="totalNotice">프로젝트 성공 시, 결제는 2024.02.29 에 진행됩니다. 프로젝트가 무산되거나 중단된 경우, 예약된 결제는 자동으로 취소됩니다.</div>
                    <div class="totalcheck">
                        <div class="agree1">
                            <input type="checkbox">
                            <div>개인정보 제3자 제공 동의</div>
                            <a href="#" class="agreeLast">내용보기</a>
                        </div>
                        <div class="agree2">
                            <input type="checkbox">
                            <div>후원 유의사항 확인</div>
                            <a href="#" class="agreeLast">열기</a>
                        </div>
                    </div>
                    <div class="totalBtnBox">
                        <input type="button" id="orderBtn" class="orderBtn" value="후원하기">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--register popup--%>
    <jsp:include page="../pay/registerCardFormPop.jsp" />
</body>
</html>
