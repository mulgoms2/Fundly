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

<button onclick="test()"> test</button>
<script>
    function test() {
        const obj = {
            user_id : "sfesfs",
            pj_id : "heshfes"
        }
        fetch("/like2", {
            method : "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body : JSON.stringify(obj)
        }).then(resp => resp.json()).then(console.log);
    }
</script>
</body>
</html>
