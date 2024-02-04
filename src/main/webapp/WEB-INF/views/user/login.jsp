<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-01-30
  Time: 오후 4:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="../css/style2.css">
    <link rel="stylesheet" href="<c:url value='/static/user/userLogin.css'/>">
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body>
<div class="mainWrap">
    <h4>간편하게 로그인하고</h4>
    <h3>세상에 하나뿐인</h3>
    <h3>특별한 프로젝트를 발견해보세요</h3>

    <div class="loginBox">
        <h2>로그인</h2>
        <div class="usLogin">
            <input type="text" name="user_email" placeholder="이메일 입력" /><br>
            <input type="password" name="user_email" placeholder="비밀번호 입력" />
        </div>
        <a href="" class="forget">로그인 정보를 잊으셨나요?</a>
    </div>

    <button c`lass="emailBtn" value="sumbit">이메일로 로그인</button>

    <div class="socLogin">
        <div class="styLine">
            <span>다른 방법으로 로그인</span>
            <button class="kakaBtn" value="sumbit">카카오로 로그인</button>
            <button class="googleBtn" value="sumbit">구글 로그인</button>
        </div>
    </div>

    <span>아직 펀들리 계정이 없으신가요?  </span><a href="" class="join">회원가입</a>
</div>
</body>

<script>
    // const $emailBtn = document.getElementById('emailBtn');
    // const $kakaBtn = document.getElementById('kakaBtn');
    // const $googleBtn = document.getElementById('googleBtn');
    //
    // $emailBtn.onclick = function (){
    //
    // }
</script>

</html>
