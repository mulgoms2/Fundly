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
    <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
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

<%--            <form action="<c:url value='/join/add'/>" method="post" onsubmit="return test();">--%>
            <form id = "joinForm">
             <%--formcheck(this)">--%>


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

                 <div class="agreeAll">
                     <input type="checkbox" id = "all">
                        <label for="all"> 전체동의</label>

                     <div class="agrLine">

                         <div>
                             <input type="checkbox" id = "14age">
                                <label for="14age">만 14세 이상입니다.(필수)</label>
                             </input>
                         </div>

                         <div>
                             <input type="checkbox" id = "agreeA">
                                 <label for="agreeA">텀블벅 이용 약관동의(필수)</label>
                                 <a href="" >내용보기</a>
                         </input>
                         </div>

                         <div>
                             <input type="checkbox" id = "agreeB">
                                <label for="agreeB">개인정보 수집 및 이용 동의(필수)</label>
                                <a href="" >내용보기</a>
                             </input>
                         </div>

                         <div>
                             <input type="checkbox" id="agreeC">
                                 <label for="agreeC">개인정보 제 3자 제공 동의(선택)</label>
                                 <a href="" >내용보기</a>
                             </input>
                         </div>

                         <div>
                             <input type="checkbox" id="agreeD">
                             <label for = "agreeD">마케팅 정보 수신 동의(선택)</label>
                             </input>
                         </div>
                     </div>
                 </div>
            </div>

            <button id="joinBtn" class="joinBtn">가입하기</button>

                 <div class="centerDiv">
                     <p>이미 펀들리 계정이 있으신가요?</p>
                     <a href="">기존 계정으로 로그인하기</a>
                     <p>또는</p>
                 </div>
            </form>
        </div>
    </div>


    <button class="kakaBtn"> 카카오로 가입하기 </button>
    <p> @2024 Fundly Inc.</p>

</body>


<script>
    const user_email = document.getElementById("user_email");
    const user_name = document.getElementById("user_name");
    const user_pwd = document.getElementById("user_pwd");
    const user_pwdConfirm = document.getElementById("user_pwdConfirm");



    const num = user_pwd.value.search(/[0-9]/g);
    const eng = user_pwd.value.search(/[a-z]/ig);
    const spe = user_pwd.value.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    const email = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;



    /* 이메일, 이름, 비밀번호에 대한 정규식 */

    // 이메일 정규식 조금 더 알아 보기
    user_email.addEventListener("keyup", () => {
        // alert(email.test(user_email.value));
        // if (user_email.value.length < 3 && user_email.value.length!=0) {
        if(!email.test(user_email.value)){
            setMessage('이메일정보를 입력해주세요.', "user_email", "msgEmail", "red");
        }else {
            setMessage('', "user_email", "msgEmail","black");
        }
    })

    // 이름은 최소 2자 이상 입력 가능하게
    user_name.addEventListener("keyup", () => {
        if (user_name.value.length <= 2 && user_name.value.length!=0) {
            setMessage('최소 2자 이상 입력해주세요.', "user_name", "msgName", "red");
        }else {
            setMessage('', "user_name", "msgName", "black");
        }
    })

    // 비밀번호는 8자 이상 20자(21개 변경) 이하 / 숫자, 영문 대소문자, 특수문자 중 2가지 이상 조합 / 3개 이상 연속으로 동일한 문자
    user_pwd.addEventListener("keyup", () => {
        if (user_pwd.value.length < 8 && user_pwd.value.length > 20 ) {
            setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwd", "msgPwd", "red");
        }
        else if(user_pwd.value.search(/\s/) != -1){
            setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwd", "msgPwd", "red");
        }else if(num < 0 || eng < 0 || spe < 0 ){
            setMessage('영문,숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwd", "msgPwd", "red");
        }else {
            setMessage('', "user_pwd", "msgPwd", "black");
        }
    })

    // 비밀번호는 8자 이상 20자(21개 변경) 이하 / 숫자, 영문 대소문자, 특수문자 중 2가지 이상 조합 / 3개 이상 연속으로 동일한 문자
    // user_pwd와 비교
    user_pwdConfirm.addEventListener("keyup", () => {
        if (user_pwdConfirm.value.length < 8 && user_pwdConfirm.value.length > 20 ) {
            setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        }
        else if(user_pwdConfirm.value.search(/\s/) != -1){
            setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        }else if(num < 0 || eng < 0 || spe < 0 ){
            setMessage('영문,숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        }else if(!user_pwd.value.equals(user_pwdConfirm.value)){
            setMessage('비밀번호가 일치하지 않습니다.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        }
        else {
            setMessage('', "user_pwdConfirm", "msgPwdConfirm", "black");
            return false;
        }
    })


    // btnSubmit.addEventListener('click', function (){
    //
    //
    //     alert('수정3434중');
    //
    //     // if(!email.test(user_email.value)){
    //     //
    //     //     user_email.focus();
    //     //     setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
    //     //     return;
    //     // }
    //
    //     log.error('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
    //     log.error('\n\n\n\n\n\n\n\n\ email 값은 ? : "+email.test(user_email.value))');
    //     log.error('bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb');
    //     alert('수정중');
    // });
    //
    //
    // function  test () {
    //
    //     // alert('수정3434중');
    //
    //     // if(!email.test(user_email.value)){
    //     //
    //     //     user_email.focus();
    //     //     setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
    //     //     return false;
    //     // }
    //     //
    //     // log.error('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
    //     // log.error('\n\n\n\n\n\n\n\n\ email 값은 ? : "+email.test(user_email.value))');
    //     // log.error('bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb');
    //     // alert('수정중');
    //
    //     if(!(email.test(user_email.value))){
    //         alert(' 이메일 형식이 아니야 ~~~~~~~~~')
    //         setMessage('이메일정보를 입력해주세요.', "user_email", "msgEmail", "red");
    //
    //         event.preventDefault();
    //         return false;
    //     }else
    //     {
    //         alert(' 이메일 형시기야ㅑㅑㅑㅑㅑㅑ~~~'); event.preventDefault();
    //         return false;
    //     }
    //
    //
    //     alert('이건 들어오면 안된다아ㅏㅏㅏㅏㅏㅏ'); event.preventDefault();
    //     return false;
    //
    //
    // }
    //
    //
    // function  formcheck(frm) {
    //     // if(frm.user_email.value.length == 0 || frm.user_email.style.color=="red"){
    //
    //     //     console.log('ddddddddddddddddddddddddddddddddddd');
    //     //     if (!email.test(user_email.value)) {
    //     //
    //     //         frm.user_email.focus();
    //     //         setMessage('이메일을 입력해주세요.', "user_email", "msgEmail", "red");
    //     //         return false;
    //     //     }
    //     // }
    //
    //     if(frm.user_name.value.length == 0|| frm.user_name.style.color=="red"){
    //
    //         frm.user_name.focus();
    //         setMessage('이름을 입력해주세요.',"user_name", "msgName", "red");
    //         return false;
    //     }
    //
    //     if(frm.user_pwd.value.length == 0){
    //
    //         frm.user_pwd.focus();
    //         setMessage('비밀번호를 입력해주세요.',"user_pwd", "msgPwd", "red");
    //         return false;
    //     }
    //
    //     if(frm.user_pwdConfirm.value.length == 0){
    //
    //         frm.user_pwdConfirm.focus();
    //         setMessage('비밀번호를 한번 더 입력해주세요.,',"user_pwdConfirm", "msgPwdConfirm", "red");
    //         return false;
    //     }
    //
    //     return false;
    // }

    let joinForm = document.querySelector("#joinForm");
    let joinBtn = document.querySelector('#joinBtn');
        // joinForm.addEventListener("submit",function(e) {
            joinBtn.addEventListener("submit",function(e) {
                log.err('eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee');
            e.preventDefault();


            // if (joinForm.value = "") {
            //     return false;
            // }
            if(!(email.test(user_email.value))) {

                log.err('되돌아가ㅏㅏㅏㅏㅏㅏ');
                setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
                return false;
            }

            txtForm.action = '<c:url value="//join/add"/>';
            joinForm.method = 'POST';
            joinForm.submit();
        })

        function setMessage(msg, elementid, msgid, color){
            document.getElementById(msgid).innerHTML = `${'${msg}'}`;
            document.getElementById(elementid).style.border = "1px solid " + color;
            elementid.focus();
        }

    </script>

</html>
