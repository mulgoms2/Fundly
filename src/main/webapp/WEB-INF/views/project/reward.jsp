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
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!--헤더의 일부-->
<div class="subHead">
    <div class="subTab">
        <div class="leftTab">
            <button id="gftBtn" class="active" type="button">
                <div><i class="fas fa-solid fa-gift"></i><span>선물</span></div>
            </button>
            <button id="itmBtn" type="button">
                <div><i class="fas fa-solid fa-list"></i><span>아이템</span></div>
            </button>
        </div>
        <div class="rightBtn">
            <div>
                <a href="javascript:void(0)">
                    <i class="fas fa-solid fa-circle-info"></i>
                    <span>선물&middot;아이템&middot;옵션&middot;가이드</span>
                </a>
            </div>
            <div>
                <a href="javascript:void(0)">
                    <i class="far fa-regular fa-lightbulb"></i>
                    <span>선물 아이디어</span>
                </a>
            </div>
        </div>
    </div>
</div>
<!--헤더의 일부-->

<div class="pjWrap reward">
    <div class="pjCont">
        <!-- reward.jsp 요청시, 등록된 아이템이 하나도 없다면 보여주는 화면 -->
        <%--        <c:set var="itemList" value="${itemList}"/>--%>
        <%--        <c:if test="${empty itemList}"> <!--JS로 처리하고 싶었지만 안된다 -->--%>
        <div class="pjBox str" id="str">
            <div class="pjInfo">
                <div>
                    <div class="myList">
                        내가 만든 선물 0
                    </div>
                    <div class="listCon">
                        <p>만든 선물이 없습니다.</p>
                    </div>
                </div>
            </div>
            <div class="pjForm">
                <div class="start">
                    <div>
                        <div class="tit">
                            <p>선물 만들기</p>
                            <i class="fas fa-solid fa-asterisk"></i>
                        </div>
                    </div>
                    <div>
                        <div class="cont">
                            <h2>후원 가치를 높이는 선물</h2>
                            <br>
                            <p>아직 등록된 아이템이 없습니다.</p>
                            <br>
                            <p>선물은 후원자에게 프로젝트의 가치를 전달하는 수단입니다. </p>
                            <p>아이템을 등록 후 다양한 금액대로 여러 개의 선물을 만들어주세요. </p>
                            <p>펀딩 성공률이 높아지고, 더 많은 후원 금액을 모금할 수 있어요.</p>
                            <button type="button" id="strBtn">아이템을 먼저 만들어주세요</button>
                        </div>
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
                    <div class="myList">내가 만든 선물 <span class="count"></span>
                    </div>
                    <div id="giftList">
                        <div>
                            <strong>1,000원+</strong>
                            <span>선물 없이 후원하기</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="pjForm">
                <div>
                    <div class="first">
                        <p class="tit">선물 만들기</p>
                        <p class="cont">선물은 후원자에게 프로젝트의 가치를 전달하는 수단입니다. <br>
                            다양한 금액대로 여러 개의 선물을 만들어주세요. <br>
                            펀딩 성공률이 높아지고, 더 많은 후원 금액을 모금할 수 있어요.</p>
                    </div>
                    <section>
                        <div>
                            <p>선물 아이템</p>
                            <p>선물을 구성하는 아이템을 추가해주세요.</p>
                        </div>
                        <div>
                            <div class="dropdown">
                                <%--                                    <input style="border:none;outline:none;margin:0" type="text" inputmode="text" size=22 placeholder="아이템을 선택해주세요(클릭)" disabled>--%>
                                <span>아이템을 선택해주세요(클릭)</span><span><i class="fas fa-regular fa-chevron-down"></i></span>
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
                        <div class="inputBx">
                            <input type="text" id="giftName" placeholder="방향제+엽서 세트 A" value="">
                        </div>
                        <div></div>
                    </section>
                    <section class="giftNum">
                        <div>
                            <p>수량제한</p>
                            <p>선착순으로 선물을 제공하는 경우 총 선물 수량을 설정해주세요.</p>
                        </div>
                        <div class="limRadio check">
                            <div>
                                <input name="limit" id="lim" type="radio" value="y">  <!--수량제한 있음-->
                                <label for="lim">있음</label>
                                <span style="visibility:hidden;">
                                        <input class='maxInput' id='maxLimVal' type="number" onkeyup="validRNum(this,1000)" value="1" max="1000">
                                        <div>개</div>
                                </span>
                            </div>
                            <div>
                                <input name="limit" id="unlim" type="radio" value="n"> <!--수량제한 없음-->
                                <label for="unlim">없음</label>
                            </div>
                        </div>
                        <p class="notice" style="display:none">1000이하의 숫자를 입력하세요</p>
                    </section>
                    <section class="giftNum">
                        <div>
                            <p>1인당 최대 선택 제한</p>
                            <p>후원자 1명이 이 선물을 몇 개까지 선택할 수 있는지 설정해주세요.</p>
                        </div>
                        <div class="limRadio check">
                            <div>
                                <input name="maxLimit" id="maxLim" type="radio" value="y"> <!--1인당 수량 제한 있음-->
                                <label for="maxLim">있음</label>
                                <span style="visibility: hidden;">
                                        <input class='maxInput' id='maxLimPer' type="number" onkeyup="validRNum(this,1000)" value="1" max="1000">
                                        <div>개</div>
                                </span>
                            </div>
                            <div>
                                <input name="maxLimit" id="maxUnlim" type="radio" value="n"> <!--1인당 수량 제한 없음-->
                                <label for="maxUnlim">없음</label>
                            </div>
                        </div>
                        <p class="notice" style="display:none">1000이하의 숫자를 입력하세요</p>
                    </section>
                    <section>
                        <p>예상전달일</p>
                        <div class="cal">
                            <div id="shipDate">
                                <i class="far fa-regular fa-calendar"></i>
                                <span></span>
                            </div>
                            <hr>
                            <div>
                                <p>결제 종료일(2024-03-01)로부터</p> <!--여기 나중에 고쳐야 함. 하드코딩 xx-->
                                <div>
                                    <div>
                                        <input id="payDay" type="hidden" value="2024-03-01T00:00:00"> <!--pj에서 넘어온 값을 넣어줘야함 <%--${pj.fund_end_dtm}--%> -->
                                        <input id="shipCalc" type="number" onkeyup="validDays(this,1825);calcDate(this)">
                                        <div>일 뒤</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <p class="notice" style="display:none">최대 1825일(5년) 이내로 입력하세요</p>
                    </section>
                    <!-- <section></section> 배송여부는 제외함 -->
                    <section>
                        <div>
                            <p>선물금액</p>
                            <p>선물 제작 및 전달에 필요한 모든 비용(포장비, 배송비 등)이 포함된 금액으로 입력해주세요.</p>
                        </div>
                        <div>
                            <div class="inputBx money check">
                                <input id="giftMoney" type="text" onkeyup="validRNum(this,10000000);inputNumberFormat(this);" placeholder="1000원 이상의 금액을 입력하세요.">
                                <div>원</div>
                            </div>
                            <p class="notice" style="display:none">10,000,000원 이하로 입력해주세요.</p>
                            <!--여긴 왜 적용이 안되지 css-->
                        </div>
                    </section>
                    <div class="btnWrap">
                        <button type="button" class="init">초기화</button>
                        <button type="button" class="save">저장</button>
                    </div>
                </div>
            </div>
        </div> <!--gift-->

        <!--아이템 만들기 페이지-->
        <div class="pjBox item" id="item">
            <div class="pjInfo">
                <div>
                    <div class="myList">내가 만든 아이템 count</div>
                    <div id="itemList">
                        <c:forEach var="itemDto" items="${itemList}">
                            <%--                        <div style="cursor:pointer" onclick=removeItm(itemArr,this)>--%>

                            <div style="cursor:pointer" onclick=removeItm(itemArr,this) data-item_id='${itemDto.item_id}' data-pj_id='${itemDto.pj_id}'>
                                    <%--                        <input type="hidden" value="${itemDto.item_id}"/>--%>
                                    <%--                        <input type="hidden" value="${itemDto.pj_id}"/>--%>
                                <div class="itmTit" style="border:none;">
                                    <p style="font-weight: 600">${itemDto.item_name}</p>
                                    <div>
                                        <i class="far fa-regular fa-trash-can"></i>
                                    </div>
                                </div>

                                <p class="itmT">${itemDto.item_option_type}</p>
                                <c:if test="${not empty itemDto.item_option}">
                                    <ul class="itmL">
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
                    <div class="first">
                        <p class="tit">아이템 만들기</p>
                        <p class="cont">아이템은 선물에 포함되는 구성 품목을 말합니다.<br>특별한 물건부터 의미있는 경험까지 선물을 구성할 아이템을 만들어 보세요.</p>
                    </div>
                    <section>
                        <p>아이템 이름</p>
                        <div class="inputBx">
                            <input autofocus id="itmName" class="input" type="text" placeholder="아이템 이름을 50자 이내로 입력해주세요.">
                        </div>
                        <div></div>
                    </section>
                    <section>
                        <p>옵션 조건</p>
                        <div class="optBox">
                            <div>
                                <input type="radio" name="optType" id="noOpt" value="옵션 없음">
                                <label for="noOpt">없음</label>
                            </div>
                            <div>
                                <input type="radio" name="optType" id="singleOpt" value="주관식 옵션">
                                <label for="singleOpt">주관식</label>
                            </div>
                            <div>
                                <input type="radio" name="optType" id="multiOpt" value="객관식 옵션">
                                <label for="multiOpt">객관식</label>
                            </div>
                        </div>
                    </section>
                    <!--객관식-->
                    <section>
                        <div class="radio multiOpt" style="display:none">
                            <div>
                                <p>옵션 항목</p>
                                <p>입력완료 후 Enter키를 누르면 옵션 항목이 생성됩니다.
                                    <br>최소 2개 이상의 옵션 항목을 만들어주세요.</p>
                            </div>
                            <div class="txtCont">
                                <textarea autofocus class="input" placeholder="최소 2개 이상의 옵션항목을 입력해주세요.(100자 이내)  &#13;&#10;*예시* 블랙-230mm, 블랙-240mm"></textarea> <!--엔터키를 치면 아래에 버튼이 생성됨-->
                            </div>
                            <div>
                            </div>
                            <div>
                                <div id="multiResult">
                                </div>
                            </div>
                        </div>
                        <div></div>
                        <!--주관식-->
                        <div class="radio singleOpt" style="display:none">
                            <div>
                                <p>옵션 항목</p>
                            </div>
                            <div class="txtCont">
                                <textarea autofocus class="input" placeholder="예) 각인할 메세지를 입력하세요."></textarea>
                            </div>
                            <div></div>
                        </div>
                    </section>
                    <div class="btnWrap">
                        <button type="button" id="optInit" class="init">초기화</button>
                        <button type="button" id="optSave" class="save" disabled>저장</button> <!-- input과 textarea가 모두 입력되어야 활성화되게 -->
                    </div>
                </div>
            </div>
        </div> <!--Item-->
    </div>
</div>
<%--<script src="/static/project/js/reward.js"></script>--%>



