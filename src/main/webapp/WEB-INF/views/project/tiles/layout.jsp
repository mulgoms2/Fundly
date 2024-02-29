<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html> <!--TinyMCE 텍스트 에디터는 standard mode에서만 작동하므로 DOCTYPE 써주어야함-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><tiles:getAsString name="title"/></title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://kit.fontawesome.com/a26f9e7c74.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter&family=Noto+Sans+KR:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/static/project/css/header.css">
    <link rel="stylesheet" href="/static/project/css/reward.css">
    <link rel="stylesheet" href="/static/project/css/funding.css">
    <link rel="stylesheet" href="/static/project/css/basicInfo.css">
    <link rel="stylesheet" href="/static/project/css/creator.css">
    <link rel="stylesheet" href="/static/project/css/story.css">

    <%--    <link rel="stylesheet" href="/static/project/css/basicInfo.css">--%>

    <!--별개의 JS파일은 각 main에 따로 두는 것 고려 -->
    <script src="https://cdn.tiny.cloud/1/uh0icij1g3eyzvh7ppnwlga6kxke0lnffev72sw6bz89u7rb/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
<%--    <script defer src="/static/project/js/reward.js"></script>--%>

</head>
<body>
<div class="projectEditorWrap">
    <!--header-->
    <tiles:insertAttribute name="header"/>
    <%--        </div>--%>
    <%--    </div>--%>
    <!--body-->
    <tiles:insertAttribute name="body"/>
</div>
</body>
</html>
