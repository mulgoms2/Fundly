<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-01-30
  Time: 오후 4:03
  To change this template use File | Settings | File Templates.
--%>
<%--<link rel="stylesheet" th:href="@{/css/summernote/summernote-lite.css}">--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>

<head>
    <title>회원가입</title>
</head>

<body>
    <div class="mainWrap">
        <div class="joinBox">
            <h2>간편 가입</h2>

            <div class = "snsAuth">
                <button>구글</button>
                <button>카카오</button>
            </div>
        </div>

        <div class="joinCont">
            <h2>이메일 간편가입</h2>

<%--            <form action="<c:url value="/login/login"/>" method="post" onsubmit="return formCheck(this);">--%>
            <form action="<c:url value='/join/add'/>" method="post" onsubmit="return formcheck(this);">
                <h3>이메일</h3>
                <div class="usEmail">
                    <input type="email" name="user_email" placeholder="이메일 계정" />
                    <button>인증하기</button>
                </div>

                <h3>이름</h3>
                <div class="usName">
                    <input type="text" name="user_name" placeholder="이름 입력" />
                </div>

                <h3>비밀번호</h3>
                <div class="usPwd">
                    <input type="password" name="user_pwd" placeholder="비밀번호 입력" /><br>
                    <input type="password" name="user_pwdConfirm" placeholder="비밀번호 확인" />
                </div>

                <button type="submit">약관 동의 후 가입 완료하기</button>


            </form>

            <script>
                function  formcheck(frm) {
                    let msg ='';
                    if(frm.user_email.value.length==0){
                        // setMessage('이메일 정보를 입력해 주세요,',frm.user_email);
                        alert("error");
                        return false;
                    }
                    return true;
                }
                <%--function setMessage(msg, element){--%>
                <%--    document.getElementById("msg").innerHTML = ` ${'${msg}'}`;--%>
                <%--    if(element) {--%>
                <%--        element.select();--%>
                <%--    }--%>
                <%--}--%>

            </script>

        </div>
    </div>

</body>


</html>
