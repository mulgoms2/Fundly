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
            <c:forEach items="${chatRequest.chatRoomDto.message_list}" var="msg">
                <c:if test="${msg.file_cnt ne 0}">
                    <tr class="chatRight">
                        <td>
                            <img class="chatAttImg" src="${msg.file_url}" style="width: 300px" alt=""/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${msg.file_cnt eq 0}">
                    <tr>
                        <td id="msgCont" class="chatRight">${msg.msg_cont}</td>
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
            stompClient.subscribe('/chatSub/${chatRequest.chatRoomDto.room_num}', (response) => {
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
            room_num:${chatRequest.chatRoomDto.room_num},
            msg_cont: message,
            buy_id: "${chatRequest.chatRoomDto.user_id}",
            pj_id: "${chatRequest.chatRoomDto.pj_id}",
            send_user_id: "${chatRequest.chatRoomDto.user_id}"
        };

        stompClient.publish({
            destination: "/chatPub/chat/${chatRequest.chatRoomDto.room_num}",
            body: JSON.stringify(messageDto)
        });

        document.querySelector("#chat").value = "";
    }

    const sendImg = () => {

        if (document.querySelector("#img").value === "") {
            return;
        }
        const formData = new FormData();

        const files = document.querySelector("#img").files;


        // 멀티파트 전송시에도 메시지를 함께 첨부할 수 있으며, 컨트롤러에서 하나 이상의 dto에 나누어 담을 수 있다.
        formData.append("file", files[0]);
        formData.append("buy_id", "${chatRequest.chatRoomDto.user_id}");
        formData.append("pj_id", "${chatRequest.chatRoomDto.pj_id}");
        formData.append("room_num", "${chatRequest.chatRoomDto.room_num}");
        formData.append("file_cnt", files.length);

        document.querySelector("#img").value = "";

        const result = fetch("/chat/file", {
            method: "POST",
            headers: {},
            body: formData
        });
    }

    // 메시지 생성시 하단 스크롤 유지
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
        console.log(message);

        if (message.file_cnt !== 0) {
            // 이미지 출력
            document.querySelector("#chatBox").innerHTML += `<tr><td><img class="chatAttImg" src="${"${message.file_url}"}" /></td></tr>`;
            return;
        }

        // 일반 메시지 출력

      <%--if (message.send_user_id === "${sessionScope.loginId}")--%>
      //   if (message.send_user_id === )

        document.querySelector("#chatBox").innerHTML += `<tr class="chatRight"><td> ${'${message.msg_cont}'} </td></tr>`;

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