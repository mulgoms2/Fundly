<%--
  Created by IntelliJ IDEA.
  User: lemon
  Date: 2024-02-02
  Time: 오후 5:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<form id="txtForm">
        <textarea name="first">
adfadfadfa
        </textarea>
    <textarea name="second">
adfadfqerfdzvczxvqweadf
        </textarea>
    <textarea name="third">
qdfzcvzcvqerffafadfadfadfad
        </textarea>
    <input type="submit">
</form>
<script>
    let txtForm = document.querySelector("#txtForm");
    txtForm.addEventListener("submit",function(e){
        e.preventDefault();
        if(txtForm.value = ""){
            return false;
        }
        txtForm.action = '<c:url value="/txt/post"/>';
        txtForm.method = 'POST';
        txtForm.submit();
    })
</script>
</body>
</html>