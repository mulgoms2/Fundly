<%--
  Created by IntelliJ IDEA.
  User: dobigulbi
  Date: 2/3/24
  Time: 5:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<form id="join">
    <%--    <input type="hidden" name="user_id" value="${sessionScope.id}">--%>
    <input id="user" type="text" name="user_id">
    <input id="pj" type="text" name="pj_id">
    <button type="submit">chat</button>
</form>
<form method="post" action="/chat/file" enctype="multipart/form-data" >
    <input type="file" name="asdf"/>
    <button type="submit">post</button>
</form>

<script>
    $("#join").on("submit", (e) => {
        e.preventDefault();

        console.log("Helo");

        let url = "chatPop?user_id=" + $("#user").val() + "&pj_id=" + $("#pj").val();

        window.open(url, "_blank", 'width=600px,height=800px,scrollbars=yes');
    });
</script>
</body>
</html>
