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
        <link rel="stylesheet" href="<c:url value='/static/user/css/Login.css?after'/>">
        <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
        <%-- login js --%>
        <script defer src="/static/user/js/login.js"></script>
    </head>

    <body>
        <header id="header"><a href="<c:url value='/'/>">Fundly</a></header>

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
                            <label for="user_email"></label><input type="text" id ="user_email" name="user_email" value="${userLoginDto.user_email}"  placeholder="이메일 입력" autocomplete="off" spellcheck="false"/>
                            <div id="msgEmail" class="msg"></div>
                        </div>

                        <div class="usPwd">
                            <input type="password" id="user_pwd" name="user_pwd" value="${userLoginDto.user_pwd}" placeholder="비밀번호 입력" autocomplete="new-password"/>

                            <div class="eyes">
                                <div class="password-toggle" id="togglePwd"></div>
                            </div>
                            <div id="msgPwd" class="msg"></div>
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
        <div class="copyr">
            <div>
                <p > @2024 Fundly Inc.</p>
            </div>
        </div>
    </body>
<script>
    /* input */
    const user_email = document.getElementById("user_email");
    const user_pwd = document.getElementById("user_pwd");

    /* pwd toggle */
    const togglePwd = document.getElementById('togglePwd');

    /* form */
    let usLoginForm = document.querySelector("#usLoginForm");

    /* 이메일, 비밀번호에 대한 정규식 */
    const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    // 이메일 정규식
    user_email.addEventListener("keydown", () => {
        if(!email.test(user_email.value) && user_email.value.length!==0) {
            setMessage('유효하지 않은 이메일 형식입니다.', "user_email", "msgEmail", "red");
            setMessage('', "user_pwd", "msgPwd", "black");
        } else{
            setMessage('', "user_email", "msgEmail", "black");
            user_email.focus();
        }
    })

    let msg = "${msg}";
    if(msg!==""){ setMessage(msg, "", "msgPwd", "red");}

    /* 유효성(입력된 값에 따른 변화 style,value) */
    function setMessage(msg, elementid, msgid, color){
        document.getElementById(msgid).innerHTML = `${'${msg}'}`;
        document.getElementById(elementid).style.border = "1px solid " + color;
    }

    /* usLoginForm submit */
    usLoginForm.addEventListener("submit",function(e) {
        e.preventDefault();

        // if(user_email.value.length === 0) {
        //     setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
        //     user_email.focus();
        //     return false;
        // }
        //
        // if(user_pwd.value.length === 0){
        //     setMessage('비밀번호를 입력하세요.', "user_pwd", "msgPwd", "red");
        //     user_pwd.focus();
        //     return false;
        // }
        usLoginForm.action = '<c:url value="/login/login"/>';
        usLoginForm.method = 'POST';
        usLoginForm.submit();
    })

    /* pwd */
    user_pwd.addEventListener("keyup", () => {
        if(user_pwd.value.length > 0) {
            setMessage('', "user_email", "msgEmail", "black");
        }
    })

    togglePwd.addEventListener('click', function () {
        if (user_pwd.type === 'password') {
            user_pwd.type = 'text';
            togglePwd.style.backgroundImage = 'url("/static/img/Icon-private.png")';
        } else {
            user_pwd.type = 'password';
            togglePwd.style.backgroundImage = 'url("/static/img/Icon-eye.png")';
        }
    });

    /* errmsg redirect */
    const errmsg = "${errmsg}";
    if (errmsg === "LOGIN_PWD_ERROR") alert("비밀번호를 확인해주세요.");
    if (errmsg === "LOGIN_STATUS_ERROR") alert("비활성 유저입니다.");
    if (errmsg === "LOGIN_EMAIL_ERROR") alert("가입 정보가 없습니다.");

    /* valid errorMsg */
    const validUserEmail = '${valid_user_email}';
    const validUserPwd = '${valid_user_pwd}';

    // alert("validUserEmail =" + validUserEmail+ " ,validUserName = "
    //     + validUserName+", validUserPwd =" + validUserPwd+ " ,validUserPwdConfirm = " + validUserPwdConfirm)

    if(validUserEmail!=='') {
        setMessage(validUserEmail, "user_email", "msgEmail", "red");
    }

    if(validUserPwd!=='') {
        setMessage(validUserPwd, "user_pwd", "msgPwd", "red");
    }

</script>
</html>
