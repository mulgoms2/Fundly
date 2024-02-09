<%--
  Created by IntelliJ IDEA.
  User: dobigulbi
  Date: 2/5/24
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <form action="/chat" method="post" >
<%--        <input type="hidden" name="_method" value="put">--%>
        <input type="text" name="hi">
        <button type="submit">post</button>
    </form>

    ${testDto.hi}
</head>
<body>
</body>
</html>
