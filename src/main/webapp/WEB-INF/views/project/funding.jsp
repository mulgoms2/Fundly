<%--
  Created by IntelliJ IDEA.
  User: lemon
  Date: 2024-02-01
  Time: 오후 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<div class="pjWrap">
    <div class="pjCont">
        <!-- 카테고리 -->
        <div class="pjBox goal">
            <dl class="pjInfo">
                <dt class="pjInfoTit">목표 금액
                    <span class="icon">
                        <i class="fas fa-solid fa-asterisk"></i>
                    </span>
                </dt>
                <dd class="pjInfoDscpt">프로젝트를 완수하기 위해 필요한 금액을 설정해주세요.
                </dd>
                <div class="pjNotice">
                    <i class="fas fa-solid fa-circle-info"></i>
                    <p class="pjNoticeTit">
                        목표 금액 설정 시 꼭 알아두세요!</p>
                    <p class="pjNoticeCont">종료일까지 목표금액을 달성하지 못하면 후원자 결제가 진행되지 않습니다.</p>
                    <p class="pjNoticeCont">후원 취소 및 결제 누락을 대비해 10% 이상 초과 달성을 목표로 해주세요.</p>
                    <p class="pjNoticeCont">제작비, 선물 배송비, 인건비, 예비 비용 등을 함께 고려해주세요.</p>
                </div>
            </dl>
            <div class="pjForm">
                <div class="goalTit">
                    <p>목표금액</p>
                </div>
                <div class="goalBx">
                    <div class="inputBx">
                        <input type="number" placeholder="50만원 이상 100억원 미만을 입력하세요.">
                        <span>원</span>
                    </div>
                    <p class="notice">50만원 이상의 금액을 입력해주세요.</p>
                </div>
                <div class="goalCalc">
                    <div class="result">
                        <span>목표 달성시 예상 수령 금액</span>
                        <em>100,000,000원</em>
                    </div>
                    <div class="fee">
                        <span>총 수수료(총 결제 성공 금액의 10% + VAT)</span>
                        <em>50,000 원</em>
                    </div>
                </div>
            </div>
        </div>
        <!-- 카테고리 -->
        <div class="pjBox plan">
            <dl class="pjInfo">
                <dt class="pjInfoTit">펀딩 일정
                    <span class="icon">
                        <i class="fas fa-solid fa-asterisk"></i>
                    </span>
                </dt>
                <dd class="pjInfoDscpt">설정한 일시가 되면 펀딩이 자동 시작됩니다. 펀딩 시작 전까지 날짜를 변경할 수 있고, 즉시 펀딩을 시작할 수도 있습니다.
                </dd>
            </dl>
            <div class="pjForm none"> <!-- 가상요소 선택자 before, after가 많이 적용되는 구간 -->
                <div class="listBx">
                    <ol>
                        <li>
                            <div class="start">
                                <div class="startD">
                                    <p class="tit">시작일</p>
                                    <button type="button"><i class="far fa-regular fa-calendar"></i><span>2024/2/22</span></button><!-- 추후 jquery datepicker로 변경 -->
                                </div>
                                <div class="startH">
                                    <p class="tit">시작시간</p>
                                    <button type="button"><span>시작 시간을 선택해주세요</span><i class="fas fa-solid fa-chevron-down"></i></button>
                                </div>
                            </div>
                            <div class="days">
                                <p class="tit">펀딩기간</p>
                                <p class="cont">최대 60일</p>
                            </div>
                        </li>
                        <li>
                            <div class="close">
                                <p class="tit">종료일</p>
                                <div class="notice">
                                    <p>선택하신 종료일 다음날 0시에 펀딩이 종료됩니다.</p>
                                </div>
                                <div>
                                    <button type="button"><i class="far fa-regular fa-calendar"></i><span>2024/2/22</span></button>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div>
                                <p class="tit">후원자 결제 종료</p>
                                <div class="notice">
                                    <p>프로젝트가 성공하면 펀딩 종료 다음 날 후원금이 결제됩니다.
                                        <br>결제가 이루어지지 않은 경우 24시간 간격으로 7일 동안 결제를 시도합니다.</p>
                                </div>
                                <div class="payEnd">
                                    <p>2024.02.29</p>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div>
                                <p class="tit">정산일</p>
                                <div class="notice">
                                    <p>모금액은 후원자 결제 종료 다음 날부터 영업일 기준(주말 및 공휴일 제외)
                                        <br>7일째 되는 날 입금됩니다.</p>
                                </div>
                                <div class="payIn">
                                    <p>2024.03.12</p> <!-- N영업일을 구하는 쿼리? JS에서 보여주기? 어쨌든 이 정보도 저장을 해야.. -->
                                </div>
                            </div>
                        </li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>
