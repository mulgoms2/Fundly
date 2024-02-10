<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>
    <script src="https://kit.fontawesome.com/99823c8069.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/static/chat/css/chatCss.css"/>
</head>
<body id="chatBody">
<div class="chatMainContainer">
    <div class="chatTableBox">
        <table id="conversation" class="chatTable">
            <tbody id="chatBox">
            <c:forEach items="${messageList}" var="msg">
                <c:if test="${msg.file_cnt ne 0}">
                    <tr>
                        <td>
                            <img class="chatAttImg" src="${msg.file_url}" style="width: 300px" alt=""/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${msg.file_cnt eq 0}">
                    <tr>
                        <td id="msgCont">${msg.msg_cont}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="chatFormContainer">
        <form id="chatForm">
            <div class="chatIptBox">
                <input type="text" id="chat" class="chatIpt" required>
                <button id="send" class="sendBtn" type="submit"><i class="fa-solid fa-arrow-up"></i></button>
            </div>
        </form>
        <input id="img" class="imgIpt" name="file_img" type="file" formenctype="multipart/form-data"/>
    </div>
</div>
<script>
    const stompClient = new StompJs.Client({
        brokerURL: 'ws://localhost:8080/endPoint'
    });


    const connect = () => {
        // 화면이 로딩되면 스톰프 연결 및 콜백 메서드 초기화를 진행한다.
        stompClient.activate();

        stompClient.onConnect = (frame) => {
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
    }

    // 나가기 버튼에 매핑해서 호출하자.
    const disconnect = () => {
        stompClient.deactivate();
        setConnected(false);
        console.log("Disconnected");
    }

    const sendMessage = () => {
        const message = document.querySelector("#chat").value;

        if (message === "") {
            return;
        }

        const messageDto = {
            msg_cont: message,
            buy_id: "${user_id}",
            pj_id: "${pj_id}",
            send_user_id: "${user_id}"
        };

        stompClient.publish({
            destination: "/chatPub/chat/${roomName}",
            body: JSON.stringify(messageDto)
        });

        document.querySelector("#chat").value = "";
    }

    const sendImg = async () => {

        if (document.querySelector("#img").value === "") {
            return;
        }
        const formData = new FormData();
        const file = document.querySelector("#img").files[0];

        const roomName = "${user_id}" + "${pj_id}";

        formData.append("file", file);
        formData.append("table_key", roomName)

        document.querySelector("#img").value = "";

        // await 은 fetch의 then 결과를 가져온다.
        const result = await fetch("/chat/file", {
            method: "POST",
            headers: {},
            body: formData
        });

        // 만약 서비스계층에서 파일테이블에 img url을 별도로 저장한다면 해당 메시지에는 file_url을 실을 필요가 없게된다.
        // 다만 문제는 파일 자체를 가져오는 것에 있는데, 저장된 메시지들을 불러오는 과정에서 이미지 전송 메시지였던 친구들을
        // 어떻게 토픽으로 발행할 것인가 하는 문제가 따른다.
        <%--const messageDto = {--%>
        <%--    buy_id: "${user_id}",--%>
        <%--    pj_id: "${pj_id}",--%>
        <%--    send_user_id: "${user_id}",--%>
        <%--    file_cnt: resultObject.length,--%>
        <%--    file_url: savedImgUrl--%>
        <%--};--%>

        <%--// 토픽 발행 완료. 발행과 동시에 displayMessage 콜백 호출일 일어난다.--%>
        <%--stompClient.publish({--%>
        <%--    destination: "/chatPub/chat/${roomName}",--%>
        <%--    body: JSON.stringify(messageDto)--%>
        <%--});--%>
    }

    const scrollToButtom = () => {
        const scrollHeight = document.querySelector("#chatBody").scrollHeight;
        window.scrollTo({top: scrollHeight});
    }

    // 구독중인 토픽에 변화가 생길때 실행되는 콜백
    // 채팅방에 입장해서 생기는 일은 모델에 데이터를 담아와서 jsp로 처리하고있다.
    const displayMessage = (message) => {
        const date = new Date();
        const hour = date.getHours();
        const minute = date.getMinutes();

        if (message.file_cnt !== 0) {
            // 메시지 객체가 파일 url을 담고있지 않다면 어떻게 해야될까?
            // 유저가 메시지를 전달받는 두가지 경우가 있다.
            // 하나는 채팅방에 입장해서 db에 저장된 메세지를 불러올때
            // 두번째는 유저가 파일을 전송하고 json으로 저장된 경로를 리턴받고 url이 실린 메시지를 발행했을때.
            document.querySelector("#chatBox").innerHTML += `<img class="chatAttImg" src="${"${message.file_url}"}" />`;
            return;
        }


        document.querySelector("#chatBox").innerHTML += `<tr><td> ${'${message.send_user_id}'} ${'${hour}'} : ${'${minute}'} ${'${message.msg_cont}'} </td></tr>`;

        scrollToButtom();
    }

    window.onload = () => {
        document.querySelector("#chatForm").addEventListener("submit", e => e.preventDefault());
        document.querySelector("#send").addEventListener('click', sendMessage);
        document.querySelector("#img").addEventListener('input', sendImg);
        // document.querySelector("#imgSendBtn").addEventListener('click', sendImg);
        connect();
    };
</script>
</body>
</html>