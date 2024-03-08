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
    <div>
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
                        <input class="cardNoInput" id="card_no_2" type="password" inputmode="numeric" pattern="[0-9]*" maxlength="4" placeholder="0000" autocapitalize="off" autocomplete="off" value="" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                    </span>
                    <span class="cardNoBoxDivider">-</span>
                    <span class="cardNoBoxContent">
                        <input class="cardNoInput" id="card_no_3" type="password" inputmode="numeric" pattern="[0-9]*" maxlength="4" placeholder="0000" autocapitalize="off" autocomplete="off" value="" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
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
                    <span class="checkboxFirstSpan"><a target="_blank" href="https://tumblbug.com/payment_info_policy">내용보기</a></span>
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