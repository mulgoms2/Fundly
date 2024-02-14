<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-02-04
  Time: 오후 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>비밀번호 찾기</title>

<%--    <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>--%>
<%--    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>--%>
</head>

<body>
<div class="mainWrap">
    <div class="emailBox">
        <h2>비밀번호 찾기</h2>
        <p>이메일 주소</p>
        <input type="text" name="user_email" placeholder="가입하신 이메일 주소를 입력해주세요." />

        <p>펀들리 가입시 이메일 계정을 입력해주세요. 비밀번호를 새로 만들 수 있는 링크를 이메일로 발송해드립니다.</p>
        <button class="rinkbtn" value="sumbit">링크 발송</button>
    </div>
</div>
</body>
</html>
