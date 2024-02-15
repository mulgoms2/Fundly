<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><tiles:insertAttribute name="title"/></title>
        <%-- main --%>
        <link rel="stylesheet" href="<c:url value='/static/main/common.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/main/style.css'/>">
        <%-- common --%>
        <link rel="stylesheet" href="<c:url value='/static/user/css/usercommon.css'/>">
        <%-- Mypage --%>
        <link rel="stylesheet" href="<c:url value='/static/user/css/Alarm.css'/>">

        <link rel="stylesheet" href="<c:url value='/static/user/css/Likes.css'/>">
        <%-- script --%>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
        <!--Mypage js -->
        <script defer src="/static/user/js/likes.js"></script>
    </head>

    <body>
    <%-- LoginInfo --%>

        <tiles:insertAttribute name="header"/>

        <tiles:insertAttribute name="body"/>

        <tiles:insertAttribute name="footer"/>
    </body>

    <script>
        /* 로그인/회원가입 or 로그인 회원 정보 */
        const loginInfo = document.getElementById('loginInfo');

        loginInfo.addEventListener("click",() => {

            if(loginInfo.outerText !== '로그인/회원가입'){
                toggleContent("MyPageList");
            }else{
                return window.location.href='/login/login';
            }
        })

        /* toggle */
        function toggleContent(toggle){
            const content = document.getElementById(toggle);
            if(content.style.display==="none" || content.style.display === ""){
                content.style.display="block";
            } else{
                content.style.display="none";
            }
        }
    </script>
</html>
