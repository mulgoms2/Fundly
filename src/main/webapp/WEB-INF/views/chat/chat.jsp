<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <thead>
            <tr>
                <th>chat</th>
            </tr>
            </thead>
            <tbody id="chatBox">
            <c:forEach items="${messageList}" var="msg">
                <c:if test="${msg.file_cnt ne 0}">
                    <tr>
                        <td>
                            <img class="chatAttImg" src="${msg.file_url}" style="width: 300px" alt="" />
                        </td>
                    </tr>
                </c:if>
                <c:if test="${msg.file_cnt eq 0}">
                    <tr>
                        <td>${msg.send_user_id} ${msg.dba_reg_dtm.hours} : ${msg.dba_reg_dtm.minutes} ${msg.msg_cont}</td>
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
        <input id="img" name="file_img" type="file" formenctype="multipart/form-data"/>
        <button id="imgSendBtn">보내기</button>
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
        const img_file = document.querySelector("#img").files[0];

        const formData = new FormData();
        formData.append("img_file", img_file);

        document.querySelector("#img").value = "";

        // await 은 fetch의 then 결과를 가져온다.
        const result = await fetch("/chat/file", {
            method: "POST",
            headers: {},
            body: formData
        });

        const resultObject = await result.json();

        const savedImgUrl = resultObject[0];

        // 스톰프 서버에서는 메시지 발행이  이미지가 들어있는
        const messageDto = {
            buy_id: "${user_id}",
            pj_id: "${pj_id}",
            send_user_id: "${user_id}",
            file_cnt: resultObject.length,
            file_url: savedImgUrl
        };

        // 토픽 발행 완료. 발행과 동시에 displayMessage 콜백 호출일 일어난다.
        stompClient.publish({
            destination: "/chatPub/chat/${roomName}",
            body: JSON.stringify(messageDto)
        });
    }

    const scrollToButtom = () => {
        const scrollHeight = document.querySelector("#chatBody").scrollHeight;
        window.scrollTo({top: scrollHeight});
    }

    // 구독중인 토픽에 변화가 생길때 실행되는 콜백
    const displayMessage = (message) => {
        const date = new Date();
        const hour = date.getHours();
        const minute = date.getMinutes();

        // if(message.file) 만약에 토픽에서 발행된 메시지에 이미지(src경로)가 존재하면 파일 html 을 인서트
        console.log(message);

        if (message.file_cnt !== 0) {
            document.querySelector("#chatBox").innerHTML += '<img class=\"chatAttImg\" src=\"' + message.file_url + '\" style="width: 200px" />';

            return;
        }

        document.querySelector("#chatBox").innerHTML += "<tr><td>" + message.send_user_id + "  " + hour + " : " + minute + " " + message.msg_cont + "</td></tr>";

        scrollToButtom();
    }

    window.onload = () => {
        document.querySelector("#chatForm").addEventListener("submit", e => e.preventDefault());
        document.querySelector("#send").addEventListener('click', sendMessage)
        document.querySelector("#imgSendBtn").addEventListener('click', sendImg);
        connect();
    };
</script>
</body>
</html>