<%--
  Created by IntelliJ IDEA.
  User: init
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
    <link rel="stylesheet" href="<c:url value='/static/user/Login.css?after'/>">
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
                        <div class ="emailView">
                            <input type="text" id ="user_email"  name="user_email" placeholder="이메일 입력" autocomplete="off"/>

                            <div id="msgEmail" class="msg"/>
                        </div>

                        <div class="usPwd">
                            <input type="password" id="user_pwd" name="user_pwd" placeholder="비밀번호 입력" />

                            <div class="eyes">
                                <div class="password-toggle" id="togglePwd"></div>
                            </div>

                            <div id="msg" class="msg"></div>
                        </div>

<%--                        <div class="forgetPwd">--%>
<%--                            <a href="<c:url value='/login/forgetPwd'/>" class="forget">로그인 정보를 잊으셨나요?</a>--%>
<%--                        </div>--%>

                        <div class="btnLogin">
                            <button id="emailBtn" class="emailBtn">이메일로 로그인</button>
                        </div>

                    </form>
                </div>
                <div class="socLogin">
                    <div class="styLine">
                        <div class="otherLogin">
                            <span>다른 방법으로 로그인</span>
                        </div>
                        <button id="kakaBtn" class="kakaBtn" >
                            <div class="kakaImg"></div>
                            <div>카카오톡으로 로그인</div>
                        </button>
                        <button id="googleBtn" class="googleBtn">
                            <div class="googleImg"></div>
                            <div>구글로 로그인</div>
                        </button>
                    </div>
                </div>
                <div class ="joinLink">
                    <span>아직 펀들리 계정이 없으신가요?  </span><a href="<c:url value='/join/add'/>" class="join">회원가입</a>
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

    let msg = "${msg}";
    if(msg!==""){ setMessage(msg, "", "msg", "red");}


    // 이메일 정규식
    user_email.addEventListener("keyup", () => {

        if(!email.test(user_email.value) && user_email.value.length!=0) {
            setMessage('유효하지 않은 이메일 형식입니다.', "user_email", "msgEmail", "red");
        } else{
            setMessage('', "user_email", "msgEmail", "black");
        }
    })

    /* 유효성(입력된 값에 따른 변화 style,value) */
    function setMessage(msg, elementid, msgid, color){
        document.getElementById(msgid).innerHTML = `${'${msg}'}`;
        document.getElementById(elementid).style.border = "1px solid " + color;
        elementid.focus();
    }

    /* usLoginForm submit */
    usLoginForm.addEventListener("submit",function(e) {
        e.preventDefault();

        if(!(email.test(user_email.value)) || user_email.value.length <0) {
            setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
            return false;
        }

        if(user_pwd.value.length == 0){
            setMessage('비밀번호를 입력하세요.', "user_pwd", "msg", "red");
            return false;
        }

        usLoginForm.action = '<c:url value="/login/login"/>';
        usLoginForm.method = 'POST';
        usLoginForm.submit();
    })

    const pwdInput = document.getElementById('user_pwd');
    const togglePwd = document.getElementById('togglePwd');

    togglePwd.addEventListener('click', function () {

        if (pwdInput.type === 'password') {
            pwdInput.type = 'text';
            togglePwd.style.backgroundImage = 'url("/static/img/Icon-eye.png")';
        } else {
            pwdInput.type = 'password';
            togglePwd.style.backgroundImage = 'url("/static/img/Icon-private.png")';
        }
    });

</script>

</html>
