<%--
  Created by IntelliJ IDEA.
  User: lemon
  Date: 2024-02-01
  Time: 오후 12:02
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
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://kit.fontawesome.com/a26f9e7c74.js" crossorigin="anonymous"></script>
    <script defer src="/static/project/reward.js"></script>
    <style>
        .hidden {
            display : none;
        }
        .pjBox {
            display: flex;
            flex-direction: row;
            justify-content: space-around;
        }
        .pjBox .pjForm {
            display: flex;
            flex-direction: column;
        }
        input {
            outline : none;
        }
        div.multiOpt, div.singleOpt {
            display : none;
        }
        li:hover {
            cursor: pointer;
        }
        .input {
            width: 80%;
        }
        .dropdown {
            width: 29%;
            padding: 5px;
        }
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
    </style>
</head>
<body>
<div class="pjSubTb">
    <button type="button" id="gftBtn">
        선물
    </button>
    <button type="button" id="itmBtn">
        아이템
    </button>
</div>

<div class="pjWrap">
    <div class="pjCont">
        <!-- reward.jsp 요청시, 등록된 아이템이 하나도 없다면 보여주는 화면 -->
        <%--        <c:set var="itemList" value="${itemList}"/>--%>
        <%--        <c:if test="${empty itemList}"> <!--JS로 처리하고 싶었지만 안된다 -->--%>
        <div class="pjBox str" id="str">
            <div>
                <div>
                    <div>
                        선물 만들기
                    </div>
                </div>
                <div>
                    <button type="button" id="strBtn">아이템을 먼저 만들어주세요</button>
                    <div>
                        <h2>후원 가치를 높이는 선물</h2>
                        <br>
                        <p>아직 등록된 아이템이 없습니다.</p>
                        <br>
                        <p>선물은 후원자에게 프로젝트의 가치를 전달하는 수단입니다. </p>
                        <p>아이템을 등록 후 다양한 금액대로 여러 개의 선물을 만들어주세요. </p>
                        <p>펀딩 성공률이 높아지고, 더 많은 후원 금액을 모금할 수 있어요.</p>
                    </div>
                </div>
            </div>
        </div>
        <%--        </c:if>--%>

        <!-- 선물 만들기 페이지 -->
        <!--역시 JS로 하고 싶었지만 -->
        <div class="pjBox gift" id="gift">
            <div class="pjInfo">
                <div>
                    <div>
                        <p>내가 만든 선물 count</p>
                        <div></div>
                    </div>
                </div>
            </div>
            <div class="pjForm">
                <div>
                    <div>
                        <p>선물 만들기</p>
                        <p>선물은 후원자에게 프로젝트의 가치를 전달하는 수단입니다. </p>
                        <p>다양한 금액대로 여러 개의 선물을 만들어주세요. 펀딩 성공률이 높아지고, 더 많은 후원 금액을 모금할 수 있어요.</p>
                    </div>
                    <section>
                        <div>
                            <p>선물 아이템</p>
                            <p>선물을 구성하는 아이템을 추가해주세요.</p>
                        </div>
                        <div>
                            <div class="dropdown" style="border:0.1px solid black; cursor:pointer">
                                <%--                                    <input style="border:none;outline:none;margin:0" type="text" inputmode="text" size=22 placeholder="아이템을 선택해주세요(클릭)" disabled>--%>
                                아이템을 선택해주세요(클릭) <i class="fas fa-regular fa-chevron-down"></i>
                            </div>
                            <div id="itmDropdown">
                                <%--                                        <ul>--%>
                                <%--                                            <li>러닝화 (객관식 옵션) </li>--%>
                                <%--                                            <li>만년필 (주관식 옵션) </li>--%>
                                <%--                                            <li>감자칩 (옵션 없음) </li>--%>
                                <%--                                        </ul>--%>
                            </div>
                            <%--                                <div class="footer">--%>
                            <%--                                    <p>0개의 아이템 선택</p>--%>
                            <%--                                </div>--%>
                            <%--                                    <button type="button">선택완료</button>--%>
                        </div>
                        <div id="selectItm"></div>
                    </section>
                    <section>
                        <div>
                            <p>선물 이름</p>
                            <p>어떤 아이템으로 구성되어있는지 쉽게 알 수 있도록 선물 이름을 붙여주세요</p>
                        </div>
                        <div>
                            <input type="text" inputmode="text" placeholder="방향제+엽서 세트 A" value="">
                            <p>0/50</p>
                        </div>
                    </section>
                    <section>
                        <div>
                            <p>수량제한</p>
                            <p>선착순으로 선물을 제공하는 경우 총 선물 수량을 설정해주세요.</p>
                        </div>
                        <div>
                            <div>
                                <label for="lim">있음</label>
                                <input name="limit" id="lim" type="radio" value="수량제한 있음">
                                <input type="text">개
                            </div>
                            <label for="unlim">없음</label>
                            <input name="limit" id="unlim" type="radio" value="수량제한 없음">
                        </div>
                    </section>
                    <section>
                        <div>
                            <p>1인당 최대 선택 제한</p>
                            <p>후원자 1명이 이 선물을 몇 개까지 선택할 수 있는지 설정해주세요.</p>
                        </div>
                        <div>
                            <div>
                                <label for="maxLim">있음</label>
                                <input name="maxLimit" id="maxLim" type="radio" value="1인당 선택 제한 있음">
                                <input type="text">개
                            </div>
                            <label for="maxUnlim">없음</label>
                            <input name="maxLimit" id="maxUnlim" type="radio" value="1인당 선택 제한 없음">
                        </div>
                    </section>
                    <section>
                        <p>예상전달일</p>
                        <div>
                            <p>2024년 4월 2일</p>
                            <hr>
                            <div>
                                <p>결제 종료일(2024-03-29)로부터</p>
                                <div>
                                    <input type="text" inputmode="numeric">일 뒤
                                </div>
                            </div>
                        </div>
                    </section>
                    <!-- <section></section> 배송여부는 제외함 -->
                    <section>
                        <div>
                            <p>선물금액</p>
                            <p>선물 제작 및 전달에 필요한 모든 비용(포장비, 배송비 등)이 포함된 금액으로 입력해주세요.</p>
                        </div>
                        <div>
                            <input type="text" inputmode="numeric" placeholder="1000원 이상의 금액을 입력하세요."> 원
                        </div>
                    </section>
                    <div class="btnWrap">
                        <button type="button">초기화</button>
                        <button type="button">저장</button>
                    </div>
                </div>
            </div>
        </div> <!--gift-->





        <!--아이템 만들기 페이지-->
        <div class="pjBox item" id="item">
            <div class="pjInfo">
                <div>
                    <div>내가 만든 아이템 count</div>
                    <div id="itemList">
                        <c:forEach var="itemDto" items="${itemList}">
                            <%--                        <div style="cursor:pointer" onclick=removeItm(itemArr,this)>--%>
                            <div style="cursor:pointer" onclick=removeItm(itemArr,this) data-item_id='${itemDto.item_id}' data-pj_id='${itemDto.pj_id}'>
                                    <%--                        <input type="hidden" value="${itemDto.item_id}"/>--%>
                                    <%--                        <input type="hidden" value="${itemDto.pj_id}"/>--%>
                                <p style="font-weight: 600">${itemDto.item_name}</p>
                                <p>${itemDto.item_option_type}</p>
                                <c:if test="${not empty itemDto.item_option}">
                                    <ul>
                                        <c:forEach var="item_option" items="${itemDto.item_option}">
                                            <li>${item_option}</li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="pjForm">
                <div>
                    <div>
                        <p>아이템 만들기</p>
                        <p>아이템은 선물에 포함되는 구성 품목을 말합니다. 특별한 물건부터 의미있는 경험까지 선물을 구성할 아이템을 만들어 보세요.</p>
                    </div>
                    <section>
                        <p>아이템 이름</p>
                        <div>
                            <input id="itmName" class="input" type="text" placeholder="아이템 이름을 50자 이내로 입력해주세요.">
                        </div>
                        <div></div>

                    </section>
                    <section>
                        <p>옵션 조건</p>
                        <div>
                            <div>
                                <label for="noOpt">없음</label>
                                <input type="radio" name="optType" id="noOpt" value="옵션 없음">
                            </div>
                            <div>
                                <label for="singleOpt">주관식</label>
                                <input type="radio" name="optType" id="singleOpt" value="주관식 옵션">
                            </div>
                            <div>
                                <label for="multiOpt">객관식</label>
                                <input type="radio" name="optType" id="multiOpt" value="객관식 옵션">
                            </div>
                        </div>
                    </section>
                    <!--객관식-->
                    <section>
                        <div class="radio multiOpt">
                            <div>
                                <p>옵션 항목</p>
                                <p>입력완료 후 Enter키를 누르면 옵션 항목이 생성됩니다.</p>
                            </div>
                            <div>
                                <textarea class="input" placeholder="최소 2개 이상의 옵션항목을 입력해주세요.(100자 이내) *예시* 블랙-230mm, 블랙-240mm"></textarea> <!--엔터키를 치면 아래에 버튼이 생성됨-->
                            </div>
                            <div>
                            </div>
                            <section>
                                <div id="multiResult">
                                </div>
                            </section>
                        </div>
                        <div></div>
                        <!--주관식-->
                        <div class="radio singleOpt">
                            <div>
                                <textarea class="input" placeholder="예) 각인할 메세지를 입력하세요."></textarea>
                            </div>
                            <div></div>
                        </div>
                    </section>
                    <div class="btnWrap">
                        <button type="button" class="init">초기화</button>
                        <button type="button" class="save">저장</button> <!-- input과 textarea가 모두 입력되어야 활성화되게 -->
                    </div>
                </div>
            </div>
        </div> <!--Item-->
    </div>
</div>
<script>
</script>
</body>
</html>



