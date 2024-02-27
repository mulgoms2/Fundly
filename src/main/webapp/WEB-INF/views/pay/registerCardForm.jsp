<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: greta--%>
<%--  Date: 2024/02/01--%>
<%--  Time: 5:03 PM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>신용/체크 카드 등록</title>--%>
<%--    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="" id="form" method="post">--%>
<%--    <h3>신용/체크 카드 등록</h3>--%>
<%--    <div>--%>
<%--        <input type="radio" id="personal" name="own_type" value="personal"--%>
<%--        ${result == 'error' ? (payMeansDto.own_type == 'personal' ? 'checked' : '') : 'checked'}>개인--%>
<%--        <input type="radio" id="corporate" name="own_type" value="corporate"--%>
<%--        ${result == 'error' ? (payMeansDto.own_type == 'corporate' ? 'checked' : '') : ''}>법인--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <label for="card_no">카드 번호</label>--%>
<%--        <input id="card_no" type="text" name="card_no" maxlength="19"--%>
<%--               placeholder="0000 - 0000 - 0000 - 0000" autofocus--%>
<%--               value="${result == 'error' ? payMeansDto.card_no : ''}"--%>
<%--&lt;%&ndash;               onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"&ndash;%&gt;--%>
<%--        />--%>
<%--    </div>--%>

<%--    <div>--%>
<%--        <div>카드 유효기간</div>--%>
<%--        <label for="months"></label>--%>
<%--        <select id="months" name="months" size="1"></select>--%>

<%--        <label for="years"></label>--%>
<%--        <select id="years" name="years" size="1"></select>--%>
<%--    </div>--%>
<%--    <input type="hidden" id="card_valid_date" name="card_valid_date">--%>
<%--    <div style="display: inline-flex;">--%>
<%--            <span>--%>
<%--                <label for="card_pwd">카드 비밀번호 앞 두자리</label>--%>
<%--                <input id="card_pwd" type="text" name="card_pwd" maxlength="2" placeholder="앞 2자리를 입력해주세요."--%>
<%--                       value="${result == 'error' ? payMeansDto.card_pwd : ''}"--%>
<%--                       onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>
<%--            </span>--%>
<%--            <span id="own_type_output">--%>
<%--                <label for="own_birth">소유주 생년월일</label>--%>
<%--                <input id="own_birth" type="text" name="own_birth" maxlength="6" placeholder="예) 920101"--%>
<%--                       value="${result == 'error' ? payMeansDto.own_birth : ''}"--%>
<%--                       onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>
<%--            </span>--%>
<%--    </div>--%>
<%--    <div><input id="card_co_info_agree_yn" type="checkbox" name="card_co_info_agree_yn" value="Y"--%>
<%--    ${result == 'error' ? (payMeansDto.card_co_info_agree_yn == 'Y' ? 'checked' : '') : ''}>결제사 정보제공 동의</div>--%>
<%--    <div><input id="default_pay_means_yn" type="checkbox" name="default_pay_means_yn" value="Y"--%>
<%--    ${result == 'error' ? (payMeansDto.default_pay_means_yn == 'Y' ? 'checked' : '') : ''}>기본 결제수단으로 등록</div>--%>
<%--    <div><button id="submitBtn" type="submit">등록 완료</button></div>--%>
<%--</form>--%>
<%--<script>--%>
<%--    $(document).ready(function() {--%>
<%--        let currentYear = new Date().getFullYear();--%>
<%--        let selectedYear = currentYear;--%>
<%--        let selectedMonth = '01';--%>

<%--        if ("${!result.equals(null) && result == 'error'}") {--%>
<%--            selectedYear = "${payMeansDto.card_valid_date}".split('-')[0];--%>
<%--            selectedMonth = "${payMeansDto.card_valid_date}".split('-')[1];--%>
<%--        }--%>

<%--        // years select--%>
<%--        for (let i = currentYear; i <= currentYear + 10; i++) {--%>
<%--            let yearsSelectedOption = '';--%>
<%--            if (i == selectedYear) {--%>
<%--                yearsSelectedOption = 'selected';--%>
<%--            }--%>
<%--            let yearsOption = '<option value=' + i + ' ' + yearsSelectedOption + '>' + i + '</option>';--%>
<%--            $('#years').append(yearsOption);--%>
<%--        }--%>

<%--        // months select--%>
<%--        for (let i = 1; i <= 12; i++) {--%>
<%--            let monthsSelectedOption = '';--%>
<%--            let monthValue = i < 10 ? '0' + i : '' + i; // 일의 자리 숫자면 앞에 0붙이기--%>
<%--            let monthName = i + '월';--%>
<%--            if (monthValue === selectedMonth) {--%>
<%--                monthsSelectedOption = 'selected';--%>
<%--            }--%>
<%--            let monthsOption = '<option value=' + monthValue + ' ' + monthsSelectedOption + '>' + monthName + '</option>';--%>
<%--            $('#months').append(monthsOption);--%>
<%--        }--%>

<%--        $('#card_valid_date').val($('#years').val() + '-' + $('#months').val());--%>

<%--        $('#months, #years').change(function () {--%>
<%--            $('#card_valid_date').val($('#years').val() + '-' + $('#months').val());--%>
<%--        })--%>

<%--        $("#submitBtn").click(function (e) {--%>
<%--            let formData = {--%>
<%--                own_type: $('input[name="own_type"]:checked').val(),--%>
<%--                card_no: $('#card_no').val(),--%>
<%--                card_valid_date: $('#card_valid_date').val(),--%>
<%--                own_birth: $('#own_birth').val(),--%>
<%--                card_pwd: $('#card_pwd').val(),--%>
<%--                card_co_info_agree_yn: $('#card_co_info_agree_yn').val(),--%>
<%--                default_pay_means_yn: $('#default_pay_means_yn').val()--%>
<%--            };--%>

<%--            if (!$("#card_co_info_agree_yn").prop("checked")) {--%>
<%--                alert("결제사 정보제공에 동의하셔야 합니다.");--%>
<%--                e.preventDefault(); // prevent form submit--%>
<%--            } else {--%>
<%--                // form.submit();--%>
<%--                $.ajax({--%>
<%--                    type: "POST",--%>
<%--                    url: "/pay/means/register",--%>
<%--                    data: JSON.stringify(formData),--%>
<%--                    success: function () {},--%>
<%--                    error: function () {--%>
<%--                        e.preventDefault();--%>
<%--                        alert("결제수단 등록에 실패했습니다. 다시 시도해 주세요.")--%>
<%--                    }--%>
<%--                })--%>
<%--            }--%>
<%--        })--%>

<%--        // 라디오 버튼의 변경 이벤트 감지--%>
<%--        $('input[name="own_type"]').change(function() {--%>
<%--            // 선택된 라디오 버튼의 값 가져오기--%>
<%--            let selectedValue = $('input[name="own_type"]:checked').val();--%>

<%--            // 결과를 출력할 엘리먼트 선택--%>
<%--            let outputElem = $('#own_type_output');--%>

<%--            // 선택된 값에 따라 다르게 출력--%>
<%--            if (selectedValue === 'personal') {--%>
<%--                outputElem.html('<label for="own_birth">소유주 생년월일</label>' +--%>
<%--                    '<input id="own_birth" type="text" name="own_birth" maxlength="6" placeholder="예) 920101" ' +--%>
<%--                    'onKeyup="this.value=this.value.replace(/[^0-9]/g,\'\');">');--%>
<%--            } else if (selectedValue === 'corporate') {--%>
<%--                outputElem.html('<label for="own_birth">사업자등록번호</label>' +--%>
<%--                    '<input id="own_birth" type="text" name="own_birth" maxlength="6" placeholder="예) 1078783297" ' +--%>
<%--                    'onKeyup="this.value=this.value.replace(/[^0-9]/g,\'\');">');--%>
<%--            }--%>
<%--        })--%>
<%--    })--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>