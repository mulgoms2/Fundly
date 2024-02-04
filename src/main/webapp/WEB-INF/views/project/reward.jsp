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
    <style>
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
    </style>
</head>
<body>
<div class="pjSubTb">
    <button class="isActive" type="button" id="gftBtn">
        선물
    </button>
    <button type="button" id="itmBtn">
        아이템
    </button>
</div>

<div class="pjWrap">
    <div class="pjCont">

        <!-- reward.jsp 요청시, 등록된 아이템이 하나도 없다면 보여주는 화면 -->
        <%--        <c:if test="#{empty itemList}">--%>
        <div class="pjBox str">
            <div id="pjItmGft">
                <div>
                    <div>
                        선물 만들기
                    </div>
                </div>
                <div>
                    <button type="button" id="strBtn">아이템을 만들어주세요</button>
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
        <%--        <c:if test="#{not empty itemList}">--%>
        <div class="pjBox gift" id="gift">
            <div class="pjInfo">
                <div>
                    <div>내가 만든 선물 count</div>
                    <ul>
                        <li></li>
                    </ul>
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
                            <input readonly="" type="text" inputmode="text" value="아이템을 선택해주세요">
                            <div>
                                <div>
                                    <ul>
                                        <li>러닝화 (객관식 옵션) </li>
                                        <li>만년필 (주관식 옵션) </li>
                                        <li>감자칩 (옵션 없음) </li>
                                    </ul>
                                    <div class="footer">
                                        <p>2개의 아이템 선택</p>
                                        <button type="button">선택완료</button>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                <input name="lim" id="lim" type="radio">
                                <input type="text"><p>개</p>
                            </div>
                            <label for="unlim">없음</label>
                            <input name="unlim" id="unlim" type="radio">
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
                                <input name="maxLim" id="maxLim" type="radio">
                                <input type="text"><p>개</p>
                            </div>
                            <label for="maxUnlim">없음</label>
                            <input name="maxUnlim" id="maxUnlim" type="radio">
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
                                    <input type="text" inputmode="numeric">
                                    <p>일 뒤</p>
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
                            <input type="text" inputmode="numeric" placeholder="1000원 이상의 금액을 입력하세요.">
                            <p>원</p>
                        </div>
                    </section>
                    <div class="btnWrap">
                        <button type="button">초기화</button>
                        <buton type="button">저장</buton>
                    </div>
                </div>
            </div>
        </div> <!--gift-->
        <%--</c:if>--%>

        <!--아이템 만들기 페이지-->
        <div class="pjBox item" id="item">
            <div class="pjInfo">
                <div>
                    <div>내가 만든 아이템 count</div>
                    <ul>
                        <li></li>
                    </ul>
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
                            <input id="itmName" type="text" inputmode="text" placeholder="아이템 이름을 입력해주세요.">
                        </div>
                        <div>
                            <span>`${'${itmName.value.length}'}/50`</span>
                        </div>
                    </section>
                    <section>
                        <p>옵션 조건</p>
                        <div>
                            <div>
                                <label for="optType">없음</label>
                                <input type="radio" name="optType">
                            </div>
                            <div>
                                <label for="optType">주관식</label>
                                <input type="radio" name="optType">
                            </div>
                            <div>
                                <label for="optType">객관식</label>
                                <input type="radio" name="optType">
                            </div>
                        </div>
                    </section>
                    <section id="">
                        <div>
                            <p>옵션 항목</p>
                            <p>입력완료 후 Enter키를 누르면 옵션 항목이 생성됩니다.</p>
                        </div>
                        <!--객관식-->
                        <div class="multi txtWrap">
                            <textarea placeholder="옵션항목을 입력해주세요."></textarea>
                        </div>
                        <section id="multi result">
                            <div>
                                <button type="button">250mm</button>
                                <button type="button">255mm</button>
                            </div>
                        </section>
                        <!--주관식-->
                        <div id="single">
                            <textarea placeholder="예) 각인할 메세지를 입력하세요."></textarea>
                        </div>
                    </section>
                    <div class="btnWrap">
                        <button type="button">초기화</button>
                        <buton type="button">저장</buton>
                    </div>
                </div>
            </div>
        </div> <!--Item-->
    </div>
</div>
<script>
    window.onload = function(){
        const itemPage = document.querySelector("#item");
        const giftPage = document.querySelector("#gift");
        const pjItmGft = document.querySelector("#pjItmGft");
        const strBtn = document.querySelector("#strBtn");
        const gftBtn = document.querySelector("#gftBtn");
        const itmBtn = document.querySelector("#itmBtn");
        const itmName = document.querySelector("#itmName");


        // console.dir(itemPage);
        // console.dir(giftPage);
        mkHidden([giftPage, itemPage]);

        strBtn.addEventListener("click",function(){
            // giftPage.style.display = "none";
            location.href = "#item";
            mkHidden([giftPage, pjItmGft]);
            mkVisible(itemPage);
        })
        gftBtn.addEventListener("click",function(){
            location.href = "#gift";
            // pjItmGft.style.display = "none";
            // giftPage.style.display = "block";
            mkHidden([itemPage, pjItmGft]);
            mkVisible(giftPage);
        })
        itmBtn.addEventListener("click",function(){
            location.href = "#item";
            mkHidden([giftPage, pjItmGft]);
            mkVisible(itemPage);
        })

        itmName.addEventListener("input",function(){
            console.dir(this);
            lengthCheck(this,50);
        })


    }// window.onload

    const tglHidden = function(elements){
        elements.forEach(element => {
            element.classList.toggle("hidden");
        })
    }
    const mkHidden = function(elements){
        elements.forEach(element => {
            element.style.display = "none";
        })
    }
    const mkVisible = function(element){
        element.style.display = "flex";
    }

    const lengthCheck = function(elem, maxLength){
        if(elem.value.length===0){
            alert("아이템 이름을 입력하세요.")
            elem.style.border = "1.5px solid red";
            elem.focus();

        }
        if(elem.value.length > maxLength){
            alert("아이템 이름의 길이는 50글자 이하여야 합니다.")
            elem.style.border = "1.5px solid red";
            elem.focus();

        }
    }
</script>
</body>
</html>



