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
    <div>
        <p class="pTit">신용/체크카드 등록</p>
        <div>${regErrResData}</div>
        <div id="payMeansIdOutput"></div>
        <div class="xBtnWrap">
            <button class="b-close">닫기</button>
        </div>
    </div>
    <div class="popScr">
        <form action="" id="form" method="post">
            <div class="ownType">
                <label class="ownTypeRadio"><input type="radio" id="personal" name="own_type" value="personal" checked><span>개인</span></label>
                <label class="ownTypeRadio"><input type="radio" id="corporate" name="own_type" value="corporate"><span>법인</span></label>
            </div>
            <div class="formOptionWrapper">
                <div class="formOptionTitle">카드 번호</div>
                <div class="formOptionContent">
                    <span class="cardNoBoxContent">
                        <input class="cardNoInput" id="card_no_1" type="text" inputmode="numeric" pattern="[0-9]*" maxlength="4" placeholder="0000" autocapitalize="off" autocomplete="off" value="" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                    </span>
                    <span class="cardNoBoxDivider">-</span>
                    <span class="cardNoBoxContent">
                        <input class="cardNoInput" id="card_no_2" type="text" inputmode="numeric" pattern="[0-9]*" maxlength="4" placeholder="0000" autocapitalize="off" autocomplete="off" value="" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                    </span>
                    <span class="cardNoBoxDivider">-</span>
                    <span class="cardNoBoxContent">
                        <input class="cardNoInput" id="card_no_3" type="text" inputmode="numeric" pattern="[0-9]*" maxlength="4" placeholder="0000" autocapitalize="off" autocomplete="off" value="" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                    </span>
                    <span class="cardNoBoxDivider">-</span>
                    <span class="cardNoBoxContent">
                        <input class="cardNoInput" id="card_no_4" type="text" inputmode="numeric" pattern="[0-9]*" maxlength="4" placeholder="0000" autocapitalize="off" autocomplete="off" value="" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                    </span>
                    <input type="hidden" id="card_no" name="card_no">
                </div>
            </div>
            <div class="formOptionWrapper">
                <div class="formOptionTitle">카드 유효기간</div>
                <div class="cardValidDateInputWrapper">
                    <div class="cardValidDateMonths">
                        <select class="cardValidDateSelect" id="months" name="months" size="1"></select>
                    </div>
                    <div class="cardValidDateYears">
                        <select class="cardValidDateSelect" id="years" name="years" size="1"></select>
                    </div>
                    <input type="hidden" id="card_valid_date" name="card_valid_date">
                </div>
            </div>
            <div class="formOptionsWrapper">
                <div class="cardPwdWrapperFirst">
                    <div class="formOptionTitle">카드 비밀번호 앞 두자리</div>
                    <div class="formOptionContent">
                        <span>
                            <input class="formInput" id="card_pwd" type="password" name="card_pwd" inputmode="numeric" pattern="[0-9]*" maxlength="2" autocapitalize="off" autocomplete="new-password"
                                   placeholder="앞 2자리를 입력해주세요." onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                        </span>
                    </div>
                </div>
                <div class="cardPwdWrapperSecond" id="own_type_output">
                    <div class="formOptionTitle">소유주 생년월일</div>
                    <div class="formOptionContent">
                        <span>
                            <input class="formInput" id="own_birth" type="text" name="own_birth" inputmode="numeric" pattern="[0-9]*" maxlength="6" autocapitalize="off" autocomplete="new-password"
                                   placeholder="예) 920101" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                        </span>
                    </div>
                </div>
            </div>
            <div class="checkboxWrapper">
                <div class="checkboxFirst">
                    <span class="checkboxFirstSpan"><a target="_blank">내용보기</a></span>
                    <div class="checkboxInputWrapper">
                        <input class="checkboxInput" id="card_co_info_agree_yn" type="checkbox" name="card_co_info_agree_yn" value="Y">결제사 정보제공 동의
                    </div>
                    <div class="errorMsg" id="card_co_info_agree_yn_err_msg"></div>
                </div>
                <div class="checkboxInputWrapper">
                    <input class="checkboxInput" id="default_pay_means_yn" type="checkbox" name="default_pay_means_yn" value="Y" checked>기본 결제수단으로 등록
                </div>
            </div>
        </form>
        <div class="popBottom">
            <button id="submitBtn" class="taskBtn">등록 완료</button>
        </div>
    </div>
</div>
<style>
    .bPopWrap {
        padding: 50px 32px;
    }
    .pTit {
        padding-left: 40px;
        min-height: 36px;
        margin: 0px 0px 24px;
        align-items: center;
        font-size: 20px;
        font-weight: bold;
        line-height: 30px;
        letter-spacing: -0.025em;
        color: #3d3d3d;
    }
    .popScr {
        font-family: 'Noto Sans KR', system-ui, -apple-system, Segoe UI, Roboto, Ubuntu, Cantarell, Noto Sans, sans-serif, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        font-size: 14px;
        line-height: 24px;
        letter-spacing: -0.015em;
        font-weight: 400;
    }
    .ownType {
        display: flex;
        padding: 12px 0px 0px;
        margin: 0px 0px 32px;
    }
    .ownTypeRadio {
        cursor: pointer;
        display: flex;
        align-items: center;
        width: 117px;
        height: 24px;
    }
    .ownTypeRadio span {
        margin-left: 8px;
    }
    .formOptionWrapper {
        margin-bottom: 32px;
    }
    .formOptionTitle {
        color: rgb(61, 61, 61);
        margin: 0px 0px 10px;
    }
    .formOptionContent {
        height: 40px;
        border: 1px solid rgb(230, 230, 230);
        border-radius: 4px;
        display: flex;
        padding: 0px 12px;
        align-items: center;
        max-height: 44px;
        min-width: 100px;
        display: flex;
        font-size: 14px;
        line-height: 24px;
    }
    .formOptionContent:focus {
        border: 1px solid rgb(61, 61, 61);
    }
    .cardNoBoxContent {
        padding: 0px 12px;
        align-items: center;
    }
    .cardNoInput {
        max-width: 45px;
        border: 0px;
        outline: none;
    }
    .formInput {
        border: 0px;
        outline: none;
    }
    .cardNoInput:focus, .formInput:focus {
        caret-color: rgb(255, 87, 87);
    }
    .cardNoBoxDivider {
        margin: 0px 10px 0px 0px;
    }
    .cardValidDateInputWrapper {
        margin: 20px 0px 0px;
    }
    .cardValidDateMonths {
        margin-right: 12px;
        display: inline-flex;
        position: relative;
        border: 1px solid rgb(230, 230, 230);
        border-radius: 4px;
        background: rgb(255, 255, 255);
        color: rgb(13, 13, 13);
        height: 44px;
    }
    .cardValidDateYears {
        display: inline-flex;
        position: relative;
        border: 1px solid rgb(230, 230, 230);
        border-radius: 4px;
        background: rgb(255, 255, 255);
        color: rgb(13, 13, 13);
        height: 44px;
    }
    .cardValidDateSelect {
        display: flex;
        align-items: center;
        padding: 0px 36px 0px 12px;
        border: 0px;
        margin-right: 12px;
    }
    .formOptionsWrapper {
        display: flex;
        flex-flow: row;
        width: 100%;
    }
    .cardPwdWrapperFirst {
        margin-right: 6px;
        width: 50%;
    }
    .cardPwdWrapperSecond {
        width: 50%;
    }
    .checkboxWrapper {
        margin: 20px 0px 0px;
        display: flex;
        flex-flow: column;
    }
    .checkboxFirst {
        position: relative;
        margin-bottom: 12px;
    }
    .checkboxFirstSpan {
        position: absolute;
        right: 0px;
        top: 0px;
        cursor: pointer;
        font-weight: normal;
    }
    .checkboxFirstSpan > a {
        color: rgb(39, 163, 255);
        text-decoration: underline;
    }
    .checkboxInputWrapper {
        cursor: pointer;
        position: relative;
        overflow: hidden;
        color: rgb(61, 61, 61);
        display: inline-flex;
    }
    .checkboxInput {
        margin-right: 6px;
    }
    .errorMsg {
        margin: 8px 0px 0px;
        color: rgb(255, 87, 87);
        font-size: 13px;
        line-height: 20px;
        letter-spacing: -0.015em;
    }
    .popBottom {
        margin: 40px 0px 0px;
    }
    .taskBtn {
        width: 100%;
        height: 52px;
        padding: 0 24px;
        color: rgb(255, 255, 255);
        text-align: center;
        font-weight: bold;
        background-color: rgb(255, 87, 87);
        border-radius: 4px;
        border: 0;
        font-size: 16px;
        line-height: 27px;
        letter-spacing: -0.02em;
    }
    .taskBtn:hover {
        background-color: #fc7573;
    }
</style>