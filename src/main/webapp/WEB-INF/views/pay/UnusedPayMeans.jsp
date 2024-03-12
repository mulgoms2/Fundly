<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: greta--%>
<%--  Date: 2024/02/01--%>
<%--  Time: 7:28 PM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%--<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>결제 수단</title>--%>
<%--    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>--%>
<%--</head>--%>
<%--<style>--%>
<%--  .btn {--%>
<%--    background-color: rgb(236, 236, 236);--%>
<%--    border: none;--%>
<%--    color: black;--%>
<%--    padding: 6px 12px;--%>
<%--    font-size: 16px;--%>
<%--    cursor: pointer;--%>
<%--    border-radius: 5px;--%>
<%--    margin-left: 30px;--%>
<%--  }--%>

<%--  .btn:hover {--%>
<%--    text-decoration: underline;--%>
<%--  }--%>

<%--  .default_tag {--%>
<%--    background-color: orangered;--%>
<%--    border: none;--%>
<%--    color: white;--%>
<%--    padding: 6px 12px;--%>
<%--    font-size: 16px;--%>
<%--    cursor: pointer;--%>
<%--    border-radius: 5px;--%>
<%--    margin-left: 50px;--%>
<%--  }--%>

<%--  .imgBox {--%>
<%--    display: block;--%>
<%--    border-radius: 4px;--%>
<%--    overflow: hidden;--%>
<%--    /*width: 85px;*/--%>
<%--    width: 120px;--%>
<%--    height: 54px;--%>
<%--  }--%>

<%--</style>--%>
<%--&lt;%&ndash;<body>&ndash;%&gt;--%>
<%--<div id="pay_means_data">--%>
<%--  <div style="display: inline-flex">--%>
<%--    <div><h2>등록된 결제수단</h2></div>--%>
<%--    <div><button type="button" id="regBtn" class="btn" onclick="location.href='<c:url value="/pay/menas/register"/>'">+ 추가</button></div>--%>
<%--  </div>--%>
<%--  <div>--%>
<%--    <table>--%>
<%--      <tr>--%>
<%--        <th class="img_url">이미지</th>--%>
<%--        <th class="user_id">회원 ID</th>--%>
<%--        <th class="pay_id">결제수단 ID</th>--%>
<%--        <th class="own_type">유형</th>--%>
<%--        <th class="card_no">카드번호</th>--%>
<%--        <th class="card_co_type">카드사</th>--%>
<%--        <th class="default_pay_means_yn">기본결제수단여부</th>--%>
<%--        <th class=""></th>--%>
<%--        <th class=""></th>--%>
<%--        <th class=""></th>--%>
<%--      </tr>--%>
<%--      <c:forEach var="payMeansDto" items="${list}">--%>
<%--        <tr>--%>
<%--          <td class="img_url">--%>
<%--            <img class="imgBox" src="<c:url value='${payMeansDto.file_path}${payMeansDto.file_name}${payMeansDto.file_extension}'/>" alt="결제수단 이미지">--%>
<%--          </td>--%>
<%--          <td class="user_id">${payMeansDto.user_id}</td>--%>
<%--          <td class="pay_means_id">${payMeansDto.pay_means_id}</td>--%>
<%--          <td class="own_type">--%>
<%--            <c:if test="${payMeansDto.own_type eq 'personal'}">개인</c:if>--%>
<%--            <c:if test="${payMeansDto.own_type eq 'corporate'}">법인</c:if>--%>
<%--          </td>--%>
<%--          <td class="card_no">************${payMeansDto.card_no}</td>--%>
<%--          <td class="card_co_type">${payMeansDto.card_co_type}</td>--%>
<%--          <td class="default_pay_means_yn">${payMeansDto.default_pay_means_yn}</td>--%>
<%--          <td class=""><button type="button" class="btn removeBtn">삭제</button></td>--%>
<%--          <td class=""><button type="button" class="btn defaultSetBtn">기본 결제수단 지정</button></td>--%>
<%--          <c:if test="${payMeansDto.default_pay_means_yn == 'Y'}">--%>
<%--            <td><span class="default_tag">기본</span></td>--%>
<%--          </c:if>--%>
<%--        </tr>--%>
<%--      </c:forEach>--%>
<%--    </table>--%>
<%--  </div>--%>
<%--</div>--%>
<%--<script>--%>
<%--  let msg = "${msg}";--%>
<%--  if (msg === "REG_SUCCESS") alert("결제수단 등록에 성공했습니다.");--%>
<%--  // if (msg === "REG_ERROR") alert("결제수단 등록에 실패했습니다. 다시 시도해 주세요.");--%>
<%--  if (msg === "LIST_ERROR")  alert("결제수단 조회에 실패했습니다. 다시 시도해 주세요.");--%>
<%--  if (msg === "DEL_ERROR")  alert("결제수단 삭제에 실패했습니다. 다시 시도해 주세요.");--%>
<%--  if (msg === "UPDATE_ERROR")  alert("기본 결제수단 지정에 실패했습니다. 다시 시도해 주세요.");--%>

<%--  $(document).ready(function () {--%>
<%--    $(".removeBtn").click(function () {--%>
<%--      if(!confirm("정말로 삭제하시겠습니까?")) return; // '취소' 클릭 시--%>
<%--      let userId = $(this).closest("tr").find(".user_id").text();--%>
<%--      let payMeansId = $(this).closest("tr").find(".pay_means_id").text();--%>
<%--      let currentUnixTime = Math.floor((new Date()).getTime() / 1000); // 현재시간 unix time--%>
<%--      let threeMonthsAgoUnixTime = currentUnixTime - (3 * 30 * 24 * 60 * 60); // 현재 시간으로부터 3개월 전 unix time--%>

<%--      $.ajax({--%>
<%--        type: "POST",--%>
<%--        url: "/pay/means/remove",--%>
<%--        data: {user_id: userId, pay_means_id: payMeansId,--%>
<%--          from: threeMonthsAgoUnixTime, to: currentUnixTime},--%>
<%--        success: function () {--%>
<%--          location.reload();--%>
<%--        },--%>
<%--        error: function () {}--%>
<%--      })--%>
<%--    })--%>

<%--    $(".defaultSetBtn").click(function () {--%>
<%--      let userId = $(this).closest("tr").find(".user_id").text();--%>
<%--      let payMeansId = $(this).closest("tr").find(".pay_means_id").text();--%>

<%--      $.ajax({--%>
<%--        type: "POST",--%>
<%--        url: "/pay/means/update",--%>
<%--        data: {user_id: userId, pay_means_id: payMeansId},--%>
<%--        success: function () {--%>
<%--          $('#pay_means_data').load(window.location.href + ' #pay_means_data');--%>
<%--        },--%>
<%--        error: function () {}--%>
<%--      })--%>
<%--    })--%>
<%--  })--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>