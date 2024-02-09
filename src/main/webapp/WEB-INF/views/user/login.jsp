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
    <link rel="stylesheet" href="<c:url value='/static/user/userLogin.css?after'/>">
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

    <body>
        <header id="header"><a href="/">Fundly<div class="line"></div></a></header>

        <main id="main">
            <div class="mainWrap">
    <%--            <div class="Explntxt">--%>
    <%--                <h4>간편하게 로그인하고</h4>--%>
    <%--                <h3>세상에 하나뿐인</h3>--%>
    <%--                <h3>특별한 프로젝트를 발견해보세요</h3>--%>
    <%--            </div>--%>
                <div class="loginBox">
                    <h2>로그인</h2>
                    <form id="usLoginForm">
                        <input type="text" name="user_email" placeholder="이메일 입력" />
                        <input type="password" name="user_email" placeholder="비밀번호 입력" />
                    </form>
                </div>

                <div class="forgetPwd">
                    <a href="" class="forget">로그인 정보를 잊으셨나요?</a>
                </div>

                <div class="btnLogin">
                    <button class="emailBtn" value="sumbit">이메일로 로그인</button>
                </div>
            <%--    <div class="socLogin">--%>
            <%--        <div class="styLine">--%>
            <%--            <span>다른 방법으로 로그인</span>--%>
            <%--            <button class="kakaBtn" value="sumbit">카카오로 로그인</button>--%>
            <%--            <button class="googleBtn" value="sumbit">구글 로그인</button>--%>
            <%--        </div>--%>
            <%--    </div>--%>
                <div>
                    <span>아직 펀들리 계정이 없으신가요?  </span><a href="/join/add" class="join">회원가입</a>
                </div>
            </div>
        </main>
    </body>

<script>
    /* input */
    const user_email = document.getElementById("user_email");
    const user_pwd = document.getElementById("user_pwd");

    /* form */
    let usLoginForm = document.querySelector("#usLoginForm");

    /* 이메일, 비밀번호에 대한 정규식 */
    const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    // 이메일 정규식
    user_email.addEventListener("keyup", () => {
        if(!email.test(user_email.value) && user_email.value.length!=0) {
            setMessage('유효하지 않은 이메일 형식입니다.', "user_email", "msgEmail", "red");
        } else{
            setMessage('', "user_email", "msgEmail","black");
        }
    })

    /* 유효성(입력된 값에 따른 변화 style,value) */
    function setMessage(msg, elementid, msgid, color){
        document.getElementById(msgid).innerHTML = `${'${msg}'}`;
        document.getElementById(elementid).style.border = "1px solid " + color;
        elementid.focus();
    }


    /* usLoginForm submit */
    usLoginForm.addEventListener("submit",function (e){
        e.preventDefault();

        if(!(email.test(user_email.value))) {
            setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
            return false;
        }

        if (user_pwdConfirm.value.length < 8 && user_pwdConfirm.value.length > 20 ) {
            setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwdConfirm", "msgPwd", "red");
            return false;
        }

        usLoginForm.action = '<c:url value="/user/login"/>';
        usLoginForm.method = 'POST';
        usLoginForm.submit();


    })




</script>

</html>
