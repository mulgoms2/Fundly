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
    <title>회원가입</title>
    <link rel="stylesheet" href="<c:url value='/static/user/userJoin.css'/>">
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body>
<div class="mainWrap">

    <%--        <div class="joinBox">--%>
    <%--            <h2>간편 가입</h2>--%>

    <%--            <div class = "snsAuth">--%>
    <%--                <button>구글</button>--%>
    <%--                <button>카카오</button>--%>
    <%--            </div>--%>
    <%--        </div>--%>

    <div class="joinCont">
        <h2>이메일 간편가입</h2>

        <form action="<c:url value='/join/add'/>" method="post" onsubmit="return formcheck(this);">

            <h3>이메일</h3>
            <div class="usEmail">
                <input type="email" id ="user_email" name="user_email" placeholder="이메일 계정" />
                <div id="msgEmail" class="msg"/>

                <%--                    <button>인증하기</button>--%>
            </div>

            <h3>이름</h3>
            <div class="usName">
                <input type="text" id ="user_name" name="user_name" placeholder="이름 입력" />
                <div id="msgName" class="msg"/>
            </div>

            <h3>비밀번호</h3>
            <div class="usPwd">
                <input type="password" id ="user_pwd" name="user_pwd" placeholder="비밀번호 입력" />
                <div id="msgPwd" class="msg"/>
            </div>

            <div class="usPwd">
                <input type="password" id = "user_pwdConfirm" name="user_pwdConfirm" placeholder="비밀번호 확인" />
                <div id="msgPwdConfirm" class="msg"/>
            </div>

            <button type="submit">약관 동의 후 가입 완료하기</button>

        </form>

        <script>
            const user_email = document.getElementById("user_email");
            const user_name = document.getElementById("user_name");
            const user_pwd = document.getElementById("user_pwd");
            const num = user_pwd.value.search(/[0-9]/g);
            const eng = user_pwd.value.search(/[a-z]/ig);
            const spe = user_pwd.value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

            window.addEventListener("keyup", () => {

            })


            // $(document).ready(function(){
            //
            //     // 이메일 정규식 조금 더 알아 보기
            //     $('#user_email').keyup(function() {
            //         const user_email = document.getElementById("user_email");
            //
            //         if (user_email.value.length < 3 && user_email.value.length!=0) {
            //             setMessage('이메일정보를 입력해주세요.', "user_email", "msgEmail","red");
            //         }else {
            //             setMessage('', "user_email", "msgEmail","black");
            //         }
            //     });
            //
            //     // 이름은 최소 2자 이상 입력 가능하게
            //     $('#user_name').keyup(function() {
            //         const user_name = document.getElementById("user_name");
            //
            //         if (user_name.value.length <= 2 && user_name.value.length!=0) {
            //             setMessage('최소 2자 이상 입력해주세요.', "user_name", "msgName", "red");
            //         }else {
            //             setMessage('', "user_name", "msgName", "black");
            //         }
            //     });
            //
            //     // 비밀번호는 8자 이상 20자(21개 변경) 이하 / 숫자, 영문 대소문자, 특수문자 중 2가지 이상 조합 / 3개 이상 연속으로 동일한 문자
            //     $('#user_pwd').keyup(function() {
            //
            //         const user_pwd = document.getElementById("user_pwd");
            //         const num = user_pwd.value.search(/[0-9]/g);
            //         const eng = user_pwd.value.search(/[a-z]/ig);
            //         const spe = user_pwd.value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
            //
            //         if (user_pwd.value.length < 8 && user_pwd.value.length > 20 ) {
            //             setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwd", "msgPwd", "red");
            //         }
            //         else if(user_pwd.value.search(/\s/) != -1){
            //             setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwd", "msgPwd", "red");
            //         }else if(num < 0 || eng < 0 || spe < 0 ){
            //             setMessage('영문,숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwd", "msgPwd", "red");
            //         }else {
            //             setMessage('', "user_pwd", "msgPwd", "black");
            //         }
            //     });
            //
            //     $('#user_pwdConfirm').keyup(function() {
            //         const user_pwdConfirm = document.getElementById("user_pwdConfirm");
            //         const user_pwd = document.getElementById("user_pwd");
            //         const num = user_pwd.value.search(/[0-9]/g);
            //         const eng = user_pwd.value.search(/[a-z]/ig);
            //         const spe = user_pwd.value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
            //
            //         alert(user_pwd.value +'와 '+ user_pwdConfirm.value);
            //
            //         if (user_pwdConfirm.value.length < 8 && user_pwdConfirm.value.length > 20 ) {
            //             setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            //             return false;
            //         }
            //         else if(user_pwdConfirm.value.search(/\s/) != -1){
            //             setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            //             return false;
            //         }else if(num < 0 || eng < 0 || spe < 0 ){
            //             setMessage('영문,숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            //             return false;
            //         }else if(!user_pwd.value.equals(user_pwdConfirm.value)){
            //             setMessage('비밀번호가 일치하지 않습니다.', "user_pwdConfirm", "msgPwdConfirm", "red");
            //             return false;
            //         }
            //         else {
            //             setMessage('', "user_pwdConfirm", "msgPwdConfirm", "black");
            //             return false;
            //         }
            //
            //     });
            // });
            //



            function  formcheck(frm) {

                if(frm.user_email.value.length == 0){

                    frm.user_email.focus();
                    setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
                    return false;
                }

                if(frm.user_name.value.length == 0){

                    frm.user_name.focus();
                    setMessage('이름을 입력해주세요.',"user_name", "msgName", "red");
                    return false;
                }

                if(frm.user_pwd.value.length == 0){

                    frm.user_pwd.focus();
                    setMessage('비밀번호를 입력해주세요.',"user_pwd", "msgPwd", "red");
                    return false;
                }

                if(frm.user_pwdConfirm.value.length == 0){

                    frm.user_pwdConfirm.focus();
                    setMessage('비밀번호를 한번 더 입력해주세요.,',"user_pwdConfirm", "msgPwdConfirm", "red");
                    return false;
                }
            }

            function setMessage(msg, elementid, msgid, color){
                document.getElementById(msgid).innerHTML = `${'${msg}'}`;
                document.getElementById(elementid).style.border = "1px solid " + color;
                elementid.focus();
            }

        </script>
    </div>
</div>

</body>


</html>
