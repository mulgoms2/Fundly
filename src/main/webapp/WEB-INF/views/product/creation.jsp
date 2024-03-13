<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>펀들리</title>
    <link rel="stylesheet" href="/static/product/css/creation.css">
    <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/static/product/vendor/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="/static/product/vendor/jquery-ui.js"></script>
    <script type="text/javascript" src="/static/product/vendor/jquery.bpopup.min.js"></script>
    <script type="text/javascript" src="/static/product/vendor/moment.min.js"></script>
    <script type="text/javascript" src="/static/product/vendor/daterangepicker.js"></script>
    <script type="text/javascript" src="/static/product/creation.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
</head>
<body>
<div class="headWrap">
    <div class="creHead">
        <button class="toProject">
            <i class="fa-solid fa-arrow-left" aria-hidden="true"></i>
            내가 만든 프로젝트
        </button>
        <a class="subHead">
            <img class="logo" src="/static/img/fundly-logo.svg">
        </a>
        <div class="rgtBtnWrap">
            <a class="toLink">창작자 가이드</a>
            <a class="toLink">헬프센터</a>
            <button class="profileBtn">
                <span class="profImg" id="profileImg"></span>
            </button>
        </div>
    </div>
</div>
<div class="mainContainer">
    <div class="contBox">
        <div class="title">
            <p class="tit">후원자 관리</p>
        </div>
        <div class="sttBox">
            <div class="lftBox">
                <span class="sttBlue">선물 전달 중</span>
                <div class="infoTxt">
                    <p class="bxTit">선물 발송 후, 운송장을 입력하고 선물 전달 상태를 '전달 완료'로 변경해주세요.</p>
                    <p class="bxStit">운송장을 입력하여 후원자가 배송 상태를 확인할 수 있도록 해주세요.</p>
                </div>
            </div>
            <div class="rgtLink">
                <a href="#">자세히 보기</a>
            </div>
        </div>
        <div class="btnWrap">
            <div class="lftBtngrp">
                <button class="msgBtn" id="supboxBtn" href="#">
                    <i class="fa-regular fa-comments"></i>
                    <span>메시지 발송</span>
                </button>
                <button>
                    <i class="fa-solid fa-location-dot fa-lg"></i>
                    <span>배송지 확정</span>
                </button>
                <button>
                    <i class="fa-solid fa-gift fa-lg"></i>
                    <span>선물 전달 완료</span>
                </button>
            </div>
            <div class="rgtBtngrp">
                <button class="gray">
                    <i class="fa-solid fa-file-import"></i>
                    운송장 입력
                </button>
<%--                <button class="carrot">엑셀 메일 발송</button>--%>
            </div>
        </div>
        <div class="tbCont">
            <div class="tbWrap">
                <table class="chkWrap">
                    <colgroup>
                        <col width="40px">
                        <col width="80px">
                        <col width="60px">
                        <col>
                        <col width="100px">
                        <col width="120px">
                        <col width="80px">
                        <col width="80px">
                        <col width="80px">
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>
                                <div class="chkBox all">
                                    <input type="checkbox" id="chkAll" class="ipt" value="체크됨" checked="checked">
                                </div>
                            </th>
                            <th>후원번호</th>
                            <th>이름</th>
                            <th class="gftList">선물</th>
                            <th>총 후원 금액</th>
                            <th>후원 일시</th>
                            <th>알림 신청</th>
                            <th>결제 상태</th>
                            <th>선물 전달</th>
                        </tr>
                        <!-- 데이터가 없을 경우 -->
                        <%--                    <td colspan="9">--%>
                        <%--                        <p class="noData">데이터가 없습니다</p>--%>
                        <%--                    </td>--%>
                        <!-- 데이터 영역 -->
                        <tr>
                            <td>
                                <div class="chkBox on">
                                    <input type="checkbox" id="chk1" class="ipt" value="체크됨" checked="checked">
                                </div>
                            </td>
                            <td>1</td>
                            <td>김철수</td>
                            <td class="gftList taL">
                                <p>29800원</p>
                                <p class="ell">[텀블벅 혜택] 블로썸 퍼퓸 1개 / 26% 할인가 / 배송비포함</p>
                            </td>
                            <td class="taR">29800원</td>
                            <td>2024-03-05 15:30</td>
                            <td>Y</td>
                            <td>결제 예약</td>
                            <td>대기중</td>
                        </tr>
                        <tr>
                            <td>
                                <div class="chkBox">
                                    <input type="checkbox" id="chk2" class="ipt" value="체크안됨">
                                </div>
                            </td>
                            <td>2</td>
                            <td>박영희</td>
                            <td class="gftList taL">
                                <p>51100원</p>
                                <p class="ell">[얼리버드] 블로썸 퍼퓸 + 디퓨저 듀오 / 30% 할인가 / 배송비포함</p>
                            </td>
                            <td class="taR">51100원</td>
                            <td>2024-03-06 09:23</td>
                            <td>Y</td>
                            <td>결제 예약</td>
                            <td>대기중</td>
                        </tr>
                        <tr>
                            <td>
                                <div class="chkBox">
                                    <input type="checkbox" id="chk3" class="ipt" value="체크안됨">
                                </div>
                            </td>
                            <td>3</td>
                            <td>남현정</td>
                            <td class="gftList taL">
                                <p>65700원</p>
                                <p class="ell">[얼리버드] 블로썸 퍼퓸 더블구성 / 33% 할인가 / 배송비포함</p>
                            </td>
                            <td class="taR">65700원</td>
                            <td>2024-03-06 10:10</td>
                            <td>Y</td>
                            <td>결제 예약</td>
                            <td>대기중</td>
                        </tr>
                        <tr>
                            <td>
                                <div class="chkBox">
                                    <input type="checkbox" id="chk4" class="ipt" value="체크안됨">
                                </div>
                            </td>
                            <td>4</td>
                            <td>안현규</td>
                            <td class="taL">
                                <p>29800원</p>
                                <p class="ell">[텀블벅 혜택] 블로썸 퍼퓸 1개 / 26% 할인가 / 배송비포함</p>
                            </td>
                            <td class="taR">59600원</td>
                            <td>2024-03-06 11:43</td>
                            <td>Y</td>
                            <td>결제 예약</td>
                            <td>대기중</td>
                        </tr>
                        <tr>
                            <td>
                                <div class="chkBox">
                                    <input type="checkbox" id="chk5" class="ipt" value="체크안됨">
                                </div>
                            </td>
                            <td>5</td>
                            <td>이시아</td>
                            <td class="gftList taL">
                                <p>29800원</p>
                                <p class="ell">[텀블벅 혜택] 블로썸 퍼퓸 1개 / 26% 할인가 / 배송비포함</p>
                            </td>
                            <td class="taR">120200원</td>
                            <td>2024-03-07 12:33</td>
                            <td>Y</td>
                            <td>결제 예약</td>
                            <td>대기중</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="bPopWrap" id="popsupBox">
    <div class="popTit">
        <p class="pTit">프로젝트 신고</p>
        <div class="xBtnWrap">
            <button class="b-close">닫기</button>
        </div>
        <span class="popDesc">이 프로젝트에 문제가 있다면 텀블벅에게 알려주세요</span>
    </div>
    <div class="popScr">
        <button class="popBoxbtn">
            <div class="popBoxtit">이용약관 또는 프로젝트 심사 기준을 위반했습니다.</div>
            <span>시중에 판매·유통되었던 제품·콘텐츠</span>
            <span>프로젝트 진행중 타 채널 동시 판매</span>
            <span>프로젝트 관련 허위 사실 게시</span>
            <span>기부 목적의 후원금 모금</span>
        </button>
        <button class="popBoxbtn">
            <div class="popBoxtit">커뮤니티 운영원칙을 위반했습니다.</div>
            <span>욕설, 외설 및 혐오표현 게시</span>
            <span>광고성 콘텐츠의 도배</span>
        </button>
        <button class="popBoxbtn">
            <div class="popBoxtit">개인정보 보호 권리를 침해했습니다.</div>
            <span>선물 전달 이외 목적의 개인정보 사용</span>
        </button>
        <button class="popBoxbtn">
            <div class="popBoxtit">지식재산권을 침해했습니다.</div>
            <span>타인의 디자인권, 상표권, 특허권 등의 지식재산권을 침해함</span>
        </button>
        <button class="popBoxbtn">
            <div class="popBoxtit">프로젝트 이행에 문제가 있습니다.</div>
            <span>선물 전달 지연 및 선물 불량</span>
            <span>창작자의 의사소통 부재</span>
        </button>
    </div>
    <div class="popBtm">
        <button class="taskButton b-close">확인</button>
    </div>
</div>
</body>
</html>
