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
        <link rel="stylesheet" href="<c:url value='/static/user/css/Profile.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/css/Coupon.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/css/fundingProject.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/css/Alarm.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/css/Message.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/css/MakeProject.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/css/Setting.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/main/common.css'/>">
        <%-- script --%>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
        <!-- header -->
        <script defer src="/static/user/js/header.js"></script>
        <!--Mypage js -->
        <script defer src="/static/user/js/likes.js"></script>
        <script defer src="/static/user/js/profile.js"></script>
    </head>

    <body>
        <tiles:insertAttribute name="header"/>

        <tiles:insertAttribute name="body"/>

        <tiles:insertAttribute name="footer"/>
    </body>
</html>
