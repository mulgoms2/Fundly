<%--
  Created by IntelliJ IDEA.
  User: greta
  Date: 2024/02/19
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<div class="bPopWrap" id="popRegister">
    <div class="popTit">
        <p class="pTit">신용/체크카드 등록</p>
        <div>${regErrResData}</div>
        <div id="payMeansIdOutput"></div>
        <div class="xBtnWrap">
            <button class="b-close">닫기</button>
        </div>
    </div>
    <div class="popScr">
        <form action="" id="form" method="post">
            <div>
                <input type="radio" id="personal" name="own_type" value="personal" checked>개인
                <input type="radio" id="corporate" name="own_type" value="corporate">법인
            </div>
            <div>
                <label for="card_no">카드 번호</label>
                <input id="card_no" type="text" name="card_no" maxlength="19"
                       placeholder="0000 - 0000 - 0000 - 0000" autofocus
                <%--               onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"--%>
                />
            </div>

            <div>
                <div>카드 유효기간</div>
                <label for="months"></label>
                <select id="months" name="months" size="1"></select>

                <label for="years"></label>
                <select id="years" name="years" size="1"></select>
            </div>
            <input type="hidden" id="card_valid_date" name="card_valid_date">
            <div style="display: inline-flex;">
                <span>
                    <label for="card_pwd">카드 비밀번호 앞 두자리</label>
                    <input id="card_pwd" type="text" name="card_pwd" maxlength="2" placeholder="앞 2자리를 입력해주세요."
                           onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                </span>
                <span id="own_type_output">
                    <label for="own_birth">소유주 생년월일</label>
                    <input id="own_birth" type="text" name="own_birth" maxlength="6" placeholder="예) 920101"
                           onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                </span>
            </div>
            <div><input id="card_co_info_agree_yn" type="checkbox" name="card_co_info_agree_yn" value="Y">결제사 정보제공 동의</div>
            <div id="card_co_info_agree_yn_err_msg"></div>
            <div><input id="default_pay_means_yn" type="checkbox" name="default_pay_means_yn" value="Y" checked>기본 결제수단으로 등록</div>
        </form>
        <div class="popBottom">
            <button id="submitBtn" class="taskBtn">등록 완료</button>
        </div>
    </div>
</div>
<style>
    .bPopWrap .popBottom {
        padding: 25px 50px;
    }
    .taskBtn {
        width: 100%;
        height: 50px;
        padding: 0 5px;
        color: #ffffff;
        text-align: center;
        font-weight: 500;
        background-color: #fa6462;
        border-radius: 4px;
        border: 0;
    }
    .taskBtn:hover {
        background-color: #fc7573;
    }
</style>