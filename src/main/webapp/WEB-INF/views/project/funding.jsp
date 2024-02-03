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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<div class="pjWrap">
    <div class="pjCont">
        <!-- 카테고리 -->
        <div class="pjBox goal">
            <dl class="pjInfo">
                <dt class="pjInfoTit">창작자 이름<span class="icon"><b>*</b></span>
                </dt>
                <dd class="pjInfoDscpt">창작자 개인이나 팀을 대표할 수 있는 이름을 써주세요.
                </dd>
                <div class="pjNotice">
                    <div class="pjNoticeTit">목표 금액 설정 시 꼭 알아두세요!</div>
                    <div>
                        <p>종료일까지 목표금액을 달성하지 못하면 후원자 결제가 진행되지 않습니다.</p>
                        <p>후원 취소 및 결제 누락을 대비해 10% 이상 초과 달성을 목표로 해주세요.</p>
                        <p>제작비, 선물 배송비, 인건비, 예비 비용 등을 함께 고려해주세요.</p>
                    </div>
                </div>
            </dl>
            <div class="pjForm">
                <div>
                    <p>목표금액</p>
                </div>
                <div>
                    <input type="text" placeholder="50만원 이상 100억원 미만을 입력하세요.">
                </div>
                <div>
                    <div>
                        <span>목표 달성시 예상 수령 금액</span>
                        <em>100,000,000 원</em>
                    </div>
                    <div>
                        총 수수료(결제대행+펀들리 서비스 이용 전체 합 10%)
                        <em>50,000 원</em>
                    </div>
                </div>
            </div>
        </div>
        <!-- 카테고리 -->
        <div class="pjBox plan">
            <dl class="pjInfo">
                <dt class="pjInfoTit">펀딩 일정 <span class="icon"><b>*</b></span>
                </dt>
                <dd class="pjInfoDscpt">설정한 일시가 되면 펀딩이 자동 시작됩니다. 펀딩 시작 전까지 날짜를 변경할 수 있고, 즉시 펀딩을 시작할 수도 있습니다.
                </dd>
            </dl>
            <div class="pjForm"> <!-- 가상요소 선택자 before, after가 많이 적용되는 구간 -->
                <div>
                    <ol>
                        <li>
                            <div>
                                <div>
                                    <p>시작일</p>
                                    <div>
                                        <input type="text" placeholder="시작 날짜를 입력해주세요"> <!-- 추후 jquery datepicker로 변경 -->
                                    </div>
                                </div>
                                <div>
                                    <p>시작시간</p>
                                    <div>
                                        <input type="text" placeholder="시작 시간을 선택해주세요">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <p>펀딩기간</p>최대 60일
                            </div>
                        </li>
                        <li>
                            <div>
                                <p>종료일</p>
                                <p>선택하신 종료일 다음날 0시에 펀딩이 종료됩니다.</p>
                                <div>
                                    <input type="text" placeholder="종료 날짜를 입력해주세요">
                                </div>
                            </div>
                        </li>
                        <li>
                            <div>
                                <p>후원자 결제 종료</p>
                                <p>프로젝트가 성공하면 펀딩 종료 다음날 후원금이 결제됩니다. 결제가 이루어지지 않은 경우 24시간 후에 단 한 번만 재결제를 시도합니다.</p>
                                <p>2024.02.29</p>
                            </div>
                        </li>
                        <li>
                            <div>
                                <p>정산일</p>
                                <p>모금액은 후원자 결제 종료 다음 날부터 영업일 기준(주말 및 공휴일 제외) 7일째 되는 날 입금됩니다.</p>
                                <p>2024.03.12</p> <!-- N영업일을 구하는 쿼리? JS에서 보여주기? 어쨌든 이 정보도 저장을 해야.. -->
                            </div>
                        </li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>