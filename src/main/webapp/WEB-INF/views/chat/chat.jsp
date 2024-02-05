<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>
    <%--    <script src="/static/chat/app.js"></script>--%>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <input type="text" id="chat" class="form-control">
                </div>
                <button id="send" class="btn btn-default" type="submit">Send</button>
            </form>
            <button id="exit">EXIT</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>chat</th>
                </tr>
                </thead>
                <tbody id="chatBox">
                <c:forEach items="${messageList}" var="msg">
                    <tr>
                        <td>${msg.dba_reg_dtm.hours} : ${msg.dba_reg_dtm.minutes} ${msg.msg_cont}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
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
            displayMessage(JSON.parse(response.body).msg_cont);
        });
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
            send_user_id: "${user_id}"
        };
        stompClient.publish({
            destination: "/chatPub/chat/${roomName}",
            body: JSON.stringify(message)
        });

        $("#chat").val("");
    }

    function displayMessage(message) {
        const date = new Date();
        const hour = date.getHours();
        const minute = date.getMinutes();

        $("#chatBox").append("<tr><td>" + hour + " : " + minute + " " + message + "</td></tr>");

        // document.querySelector("#chatBox").innerHTML += "<tr><td>" + hour + " : " + minute + " " + message + "</td></tr>";
    }

    $(() => {
        $("form").on('submit', (e) => e.preventDefault());
        $("#exit").click(() => {
            disconnect();
            //     창닫기 해결해야한다.
        })
        $("#send").click(() => sendMessage());
    });

    window.onload = () => {
        connect();
    };

</script>
</body>
</html>