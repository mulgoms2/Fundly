<%--
  Created by IntelliJ IDEA.
  User: greta
  Date: 2024/02/01
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>결제 수단</title>
</head>
<script>
    let msg = "${msg}";
    if (msg == "REG_SUCCESS") alert("결제 수단 등록에 성공했습니다.");
    if (msg == "REG_ERROR") alert("결제 수단 등록에 실패했습니다. 다시 시도해 주세요.");
    if (msg == "LIST_ERR")  alert("결제 수단 조회에 실패했습니다. 다시 시도해 주세요.");
</script>
<style>
    .btn-register {
        background-color: rgb(236, 236, 236);
        border: none;
        color: black;
        padding: 6px 12px;
        font-size: 16px;
        cursor: pointer;
        border-radius: 5px;
        margin-left: 30px;
    }

    .btn-register:hover {
        text-decoration: underline;
    }
</style>
<body>
<div style="display: inline-flex">
    <div><h2>등록된 결제수단</h2></div>
    <div><button type="button" id="regBtn" class="btn-register" onclick="location.href='<c:url value="/pay/register"/>'">+ 추가</button></div>
</div>

<table>
    <tr>
        <th class="user_id">회원 ID</th>
        <th class="pay_id">결제수단 ID</th>
        <th class="own_type">유형</th>
        <th class="default_pay_means_yn">기본결제수단여부</th>
        <th class="card_co_type">카드사</th>
    </tr>
    <c:forEach var="payMeansDto" items="${list}">
        <tr>
            <td class="user_id">${payMeansDto.user_id}</td>
            <td class="pay_id">${payMeansDto.pay_means_id}</td>
            <td class="own_type">${payMeansDto.own_type}</td>
            <td class="default_pay_means_yn">${payMeansDto.default_pay_means_yn}</td>
            <td class="card_co_type">${payMeansDto.card_co_type}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>