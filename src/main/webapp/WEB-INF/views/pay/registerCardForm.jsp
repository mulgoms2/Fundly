<%--
  Created by IntelliJ IDEA.
  User: greta
  Date: 2024/02/01
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>신용/체크 카드 등록</title>
</head>
<body>
<form action="" id="form" method="post">
    <h3>신용/체크 카드 등록</h3>
    <div>
        <input type="radio" id="personal" name="own_type" value="personal" checked>개인
        <input type="radio" id="corporate" name="own_type" value="corporate">법인
    </div>
    <div>
        <label for="card_no">카드 번호</label>
        <input id="card_no" type="text" name="card_no" maxlength="19"
               placeholder="0000 - 0000 - 0000 - 0000" autofocus
<%--               onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"--%>
        >
    </div>

    <div>
        <div>카드 유효기간</div>
        <label for="months"></label>
        <select id="months" name="months" size="1">
            <option value="01">1월</option>
            <option value="02">2월</option>
            <option value="03">3월</option>
            <option value="04">4월</option>
            <option value="05">5월</option>
            <option value="06">6월</option>
            <option value="07">7월</option>
            <option value="08">8월</option>
            <option value="09">9월</option>
            <option value="10">10월</option>
            <option value="11">11월</option>
            <option value="12">12월</option>
        </select>

        <label for="years"></label>
        <select id="years" name="years" size="1">
            <option value="2027">2027</option>
            <option value="2026">2026</option>
            <option value="2025">2025</option>
            <option value="2024">2024</option>
            <option value="2023">2023</option>
            <option value="2022">2022</option>
            <option value="2021">2021</option>
            <option value="2020">2020</option>
            <option value="2019">2019</option>
        </select>
    </div>
    <input type="hidden" id="card_valid_date" name="card_valid_date">
    <div style="display: inline-flex;">
            <span>
                <label for="card_pwd">카드 비밀번호 앞 두자리</label>
                <input id="card_pwd" type="text" name="card_pwd" maxlength="2" placeholder="앞 2자리를 입력해주세요."
                              onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
            </span>
            <span>
                <label for="own_birth">소유주 생년월일</label>
                <input id="own_birth" type="text" name="own_birth" maxlength="6" placeholder="예) 920101"
                              onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
            </span>
    </div>

    <div><label for="card_co_info_agree_yn"><input id="card_co_info_agree_yn" type="checkbox" name="card_co_info_agree_yn" value="Y">결제사 정보제공 동의</label></div>
    <div><label for="default_pay_means_yn"><input id="default_pay_means_yn" type="checkbox" name="default_pay_means_yn" value="Y">기본 결제수단으로 등록</label></div>
    <div><button id="submitBtn" type="submit">등록 완료</button></div>
</form>
<script>
    function updateCardValidDate() {
        let months = document.getElementById('months').value;
        let years = document.getElementById('years').value;

        // 'card_valid_date' input 요소에 값을 설정
        document.getElementById('card_valid_date').value = years + '-' + months;
    }

    // select 요소가 변경될 때마다 updateCardExpiration 함수 호출
    document.getElementById('months').addEventListener('change', updateCardValidDate);
    document.getElementById('years').addEventListener('change', updateCardValidDate);

    $(document).ready(function() {
        $("#submitBtn").on("click", function () {
            let form = $("#form");
            form.attr("action", "<c:url value='/pay/register'/>");
            form.attr("method", "post");
            form.submit();
        })
    })
</script>
</body>
</html>