<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
<div class="projectEditorWrap">
    <!--header-->
    <tiles:insertAttribute name="header"/>
    <!--body-->
    <tiles:insertAttribute name="body"/>
</div>
</body>
</html>
