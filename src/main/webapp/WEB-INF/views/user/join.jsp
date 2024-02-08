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
    <title>회원가입</title>
    <link rel="stylesheet" href="<c:url value='/static/user/userJoin.css'/>">
    <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body>
    <header id="header"><a href="/">Fundly<div class="line"></div></a></header>
    <main id="main">
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
                        <div class ="emailView">
                            <input type="text" id ="user_email" name="user_email" placeholder="이메일 계정" autocomplete="off"/>
    <%--                        <button>인증하기</button>--%>
                        </div>
                        <div id="msgEmail" class="msg"/>

                    </div>

                    <h3>이름</h3>
                    <div class="usName">
                        <input type="text" id ="user_name" name="user_name" placeholder="이름 입력" autocomplete="off"/>
                        <div id="msgName" class="msg"/>
                    </div>

                    <h3>비밀번호</h3>
                    <div class="usPwd">
                        <input type="password" id ="user_pwd" name="user_pwd" placeholder="비밀번호 입력" autocomplete="off"/>
                        <div id="msgPwd" class="msg"/>
                    </div>

                    <div class="usPwd">
                        <input type="password" id = "user_pwdConfirm" name="user_pwdConfirm" placeholder="비밀번호 확인" autocomplete="off"/>
                        <div id="msgPwdConfirm" class="msg"/>
                    </div>

                     <div class="agreeAll">
                         <input type="checkbox" id = "allchk" onclick="AllCheck()">
                            <label for="allchk" id="all"> 전체동의</label>

                         <div class="agrLine">

                             <div class="singleChk">
                                 <input type="checkbox" class="singleChk" id = "14age">
                                    <label for="14age" class="single">만 14세 이상입니다.(필수)</label>
                                 </input>
                             </div>

                             <div class="singleChk">
                                 <input type="checkbox" class="singleChk" id = "agreeA">
                                     <label for="agreeA" class="single">텀블벅 이용 약관동의(필수)</label>
                                     <a href="" >내용보기</a>
                             </input>
                             </div>

                             <div class="singleChk">
                                 <input type="checkbox" class="singleChk" id = "agreeB">
                                    <label for="agreeB" class="single">개인정보 수집 및 이용 동의(필수)</label>
                                    <a href="" >내용보기</a>
                                 </input>
                             </div>

                             <div class="singleChk">
                                 <input type="checkbox" class="singleChk" id= "agreeC">
                                     <label for="agreeC" class="single">개인정보 제 3자 제공 동의(선택)</label>
                                     <a href="" >내용보기</a>
                                 </input>
                             </div>

                             <div class="singleChk">
                                 <input type="checkbox" class="singleChk" id= "agreeD">
                                 <label for = "agreeD" class="single">마케팅 정보 수신 동의(선택)</label>
                                 </input>
                             </div>
                         </div>
                     </div>
                </div>

                <button id="joinBtn" class="joinBtn">가입하기</button>

                     <div class="centerDiv">
                         <p>이미 펀들리 계정이 있으신가요?</p>
                         <a href="/user/login">기존 계정으로 로그인하기</a>
                         <div class="line">
                            <span>또는</span>
                         </div>
                     </div>
                </form>
            </div>
        </div>

<%--        <button class="kakaBtn"> 카카오로 가입하기 </button>--%>

        <p> @2024 Fundly Inc.</p>
    </main>
</body>


<script>
    /* input */
    const user_email = document.getElementById("user_email");
    const user_name = document.getElementById("user_name");
    const user_pwd = document.getElementById("user_pwd");
    const user_pwdConfirm = document.getElementById("user_pwdConfirm");

    /* 이메일, 비밀번호에 대한 정규식 */
    const pwd =  /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
    const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    /* checkbox */
    const checkAll = document.getElementById('allchk');
    const singleCheckboxes = document.querySelectorAll('.singleChk')
    let chkCount = document.querySelectorAll('.singleChk:checked');


    /* 정규식 */
    // 이메일 정규식
    user_email.addEventListener("keyup", () => {
        // if (user_email.value.length < 3 && user_email.value.length!=0) {
        if(user_email.value.length!=0) {
            setMessage('이메일정보를 입력해주세요.', "user_email", "msgEmail", "red");
        }else if(!email.test(user_email.value)){
            setMessage('유효하지 않은 이메일 형식입니다.', "user_email", "msgEmail", "red");
        } else{
            setMessage('', "user_email", "msgEmail","black");
        }
    })

    // 이름은 최소 2자 이상 입력
    user_name.addEventListener("keyup", () => {
        if (user_name.value.length <= 2 && user_name.value.length!=0) {
            setMessage('최소 2자 이상 입력해주세요.', "user_name", "msgName", "red");
        }else {
            setMessage('', "user_name", "msgName", "black");
        }
    })

    // 비밀번호는 8자~20자 / 공백 / 숫자, 영문, 특수문자 조합
    user_pwd.addEventListener("keyup", () => {
        // if (user_pwd.value.length < 8 && user_pwd.value.length > 20 ) {
        if (!(user_pwd.value.length > 8 && user_pwd.value.length < 20) && user_pwd.value.length!= 0) {
            setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwd", "msgPwd", "red");
            return false;
        } else if(user_pwd.value.search(/\s/) != -1){
            setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwd", "msgPwd", "red");
            return false;
        } else if(!pwd.test(user_pwd.value)&& user_pwd.value.length!= 0){
            setMessage('영문, 숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwd", "msgPwd", "red");
            return false;
        }
        else {
            setMessage('', "user_pwd", "msgPwd", "black");
        }
    })

    // 비밀번호는 8자 이상 20자(21개 변경) 이하 / 숫자, 영문 대소문자, 특수문자 중 2가지 이상 조합 / 3개 이상 연속으로 동일한 문자
    // user_pwd와 비교
    user_pwdConfirm.addEventListener("keyup", () => {
        if (!(user_pwdConfirm.value.length > 8 && user_pwdConfirm.value.length < 20) && user_pwdConfirm.length!= 0) {
            setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        }
        else if(user_pwdConfirm.value.search(/\s/) != -1){
            setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        } else if(!pwd.test(user_pwdConfirm.value)&& user_pwdConfirm.value.length!= 0){
            setMessage('영문, 숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        }else if(user_pwdConfirm.value !== user_pwd.value){
            setMessage('비밀번호가 일치하지 않습니다.', "user_pwdConfirm", "msgPwdConfirm", "red");
            return false;
        }
        else {
            setMessage('', "user_pwdConfirm", "msgPwdConfirm", "black");
        }
    })

    /* joinBtn submit */
    let joinForm = document.querySelector("#joinForm");

        // joinForm.addEventListener("submit",function(e) {
        joinForm.addEventListener("submit",function(e) {
            e.preventDefault();

            if(!(email.test(user_email.value))) {
                setMessage('이메일을 입력해주세요.',"user_email", "msgEmail", "red");
                return false;
            }

            if (user_name.value.length < 2 ) {
                // && user_name.value.length==0) {
                setMessage('최소 2자 이상 입력해주세요.', "user_name", "msgName", "red");
                return false;
            }

            if (user_pwd.value.length < 8 && user_pwd.value.length > 20 ) {
                setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwd", "msgPwd", "red");
                return false;
            }

            if (user_pwdConfirm.value.length < 8 && user_pwdConfirm.value.length > 20 ) {
                setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwdConfirm", "msgPwd", "red");
                return false;
            }

            if(!user_pwd.value.equals(user_pwdConfirm.value)){
                setMessage('비밀번호가 일치하지 않습니다.', "user_pwdConfirm", "msgPwdConfirm", "red");
                alert('5');
                return false;
            }

            singleCheckboxes.forEach(checkbox => {
                if(checkbox.checked)
                {

                }
            });

            txtForm.action = '<c:url value="//join/add"/>';
            joinForm.method = 'POST';
            joinForm.submit();
        })

        /* 유효성(입력된 값에 따른 변화 style,value) */
        function setMessage(msg, elementid, msgid, color){
            document.getElementById(msgid).innerHTML = `${'${msg}'}`;
            document.getElementById(elementid).style.border = "1px solid " + color;
            elementid.focus();
        }

        /* checkbox 정의 */

        // 전체 동의 체크박스 상태 변경 시 개별 동의 체크박스 상태 변경
        checkAll.addEventListener('change', function() {
            singleCheckboxes.forEach(checkbox => {
                checkbox.checked = checkAll.checked;
            });
        });

        // 개별 동의 체크박스 중 하나라도 체크 해제 시 전체 동의 체크박스도 체크 해제
        singleCheckboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function() {
                chkCount = document.querySelectorAll('.singleChk:checked');
                if (!this.checked) {
                    checkAll.checked = false;
                }

                if(chkCount.length == singleCheckboxes.length) checkAll.checked = true;
            });
        });


    </script>

</html>
