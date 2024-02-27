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
    <script type="text/javascript" src="../js/vendor/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="../js/vendor/jquery-ui.js"></script>
    <script type="text/javascript" src="../js/vendor/jquery.bpopup.min.js"></script>
    <script type="text/javascript" src="../js/vendor/moment.min.js"></script>
    <script type="text/javascript" src="../js/vendor/daterangepicker.js"></script>
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
    <div class="main">
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
                                    연락처
                                    010-3615-5607
                                </div>
                                <div class="email">
                                    이메일
                                    silverzoo030@gmail.com
                                </div>
                                <div class="buyerNotice" id="section1">* 위 연락처와 이메일로 후원 관련 소식이 전달됩니다.</div>
                                <div class="buyerNotice" id="section2">* 연락처 및 이메일 변경은 설정 > 계정 설정에서 가능합니다.</div>
                            </div>
                        </div>
                    </div>
                    <div class="adrContainer">
                        <div class="Info">배송지</div>
                        <div class="Box">
                            <div class="boxConts">
                                <div class="adrDetailConts">
                                    <div class="buyerName">이은주</div>
                                    <div class="basicTit">기본</div>
                                    <div class="adr">[06241] 서울 강남구 강남대로 364 (미왕빌딩, 역삼동) 10층</div>
                                    <div class="adrPhoneNo">010-3615-5607</div>
                                </div>
                                <input type="button" class="adrBtn" value="변경"/>
                            </div>
                        </div>
                    </div>
                    <div class="payContainer">
                        <div class="Info">결제수단</div>
                        <div class="Box">
                            <div class="boxConts">
                                <form class="rdochk">
                                    <div class="rdoWrap">
                                        <div class="rdoBox on">
                                            <input type="radio" id="rdo1" class="ipt" name="radioGroup" value="체크됨" checked>
                                            <label for="rdo1">카드 간편결제</label>
                                        </div>
                                        <div class="rdoBox">
                                            <input type="radio" id="rdo2" class="ipt" name="radioGroup" value="체크안됨">
                                            <label for="rdo2">네이버페이</label>
                                        </div>
                                        <div class="rdoBox">
                                            <input type="radio" id="rdo3" class="ipt" name="radioGroup" value="체크안됨">
                                            <label for="rdo3">계좌이체</label>
                                        </div>
                                    </div>
                                </form>
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
                        <input type="button" class="orderBtn" value="후원하기">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
