<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>
    <link rel="stylesheet" href="/static/chat/css/chatCss.css"/>
</head>
<body id="chatBody">
<div class="chatMainContainer">

    <div class="chatTable">
        <table id="conversation" class="table table-striped">
            <thead>
            <tr>
                <th>chat</th>
            </tr>
            </thead>
            <tbody id="chatBox">
            <c:forEach items="${messageList}" var="msg">
                <tr>
                    <td>${msg.send_user_id} ${msg.dba_reg_dtm.hours} : ${msg.dba_reg_dtm.minutes} ${msg.msg_cont}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="chatFormContainer">
        <form id="chatForm">
            <div class="chatIptBox">
                <input type="text" id="chat" class="form-control">
                <button id="send" class="btn btn-default" type="submit">Send</button>
            </div>
        </form>
    </div>
</div>
<script>
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/endPoint'
    });

    stompClient.onConnect = (frame) => {
        setConnected(true);

        console.log("connected");
        stompClient.subscribe('/chatSub/${roomName}', (response) => {
            displayMessage(JSON.parse(response.body));
        });

        scrollToButtom();
    };

    stompClient.onWebSocketError = (error) => {
        console.error('Error with websocket', error);
    };

    stompClient.onStompError = (frame) => {
        console.error('Broker reported error: ' + frame.headers['message']);
        console.error('Additional details: ' + frame.body);
    };

    function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        } else {
            $("#conversation").hide();
        }
        // $("#chatBox").html("");
    }

    function connect() {
        stompClient.activate();
    }

    function disconnect() {
        stompClient.deactivate();
        setConnected(false);
        console.log("Disconnected");
    }

    function sendMessage() {
        const message = {
            msg_cont: $("#chat").val(),
            buy_id: "${user_id}",
            pj_id: "${pj_id}",
            send_user_id: "${user_id}",
            // 이미지 파일이 널러블하다(?)
            // img_file :
        };

        stompClient.publish({
            destination: "/chatPub/chat/${roomName}",
            body: JSON.stringify(message)
        });

        document.querySelector("#chat").value = "";
    }

    function displayMessage(message) {
        const date = new Date();
        const hour = date.getHours();
        const minute = date.getMinutes();
        // if(message.file) 만약에

        document.querySelector("#chatBox").innerHTML += "<tr><td>" + message.send_user_id + "  " + hour + " : " + minute + " " + message.msg_cont + "</td></tr>";

        scrollToButtom();
    }

    window.onload = () => {
        document.querySelector("#chatForm").addEventListener("submit", e => e.preventDefault());
        document.querySelector("#send").addEventListener('click', sendMessage)
        connect();
    };

    function scrollToButtom(){
        const scrollHeight = document.querySelector("#chatBody").scrollHeight;
        window.scrollTo({top: scrollHeight});
    }
</script>
</body>
</html>