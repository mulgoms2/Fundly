<%--
  Created by IntelliJ IDEA.
  User: greta
  Date: 2024/02/01
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결제 수단</title>
</head>
<script>
    let msg = "${msg}";
    if (msg == "REG_SUCCESS") alert("결제 수단 등록에 성공했습니다.");
    if (msg == "REG_ERROR") alert("결제 수단 등록에 실패했습니다. 다시 시도해주세요.");
</script>
<body>
<h2>결제 수단</h2>
<h3>${msg}</h3>
<h3>payMeansDto: ${payMeansDto}</h3>
</body>
</html>