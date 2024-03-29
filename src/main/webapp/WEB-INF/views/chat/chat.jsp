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
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Nanum+Gothic+Coding&family=Noto+Serif+KR:wght@300&display=swap"
          rel="stylesheet">
</head>
<body id="chatBody">
<div class="chatMainContainer">
    <div id="chatContainer" class="chatContainer">
        <c:forEach items="${chatRequest.selBuyMsgDto.message_list}" var="msg">
            <c:set var="time" value="${msg.svr_intime_string}"/>
            <c:if test="${msg.file_cnt ne 0}">
                <c:if test="${msg.send_user_id eq sessionScope.user_email}">
                    ${sessionScope.loginId}
                    <div class="chatBox right" data-time="${time}">
                        <img class="chatAttImg" src="${msg.file_url}" style="width: 300px" alt=""/>
                    </div>
                </c:if>
                <c:if test="${msg.send_user_id ne sessionScope.user_email}">
                    <div class="chatBox left" data-time="${time}">
                        <img class="chatAttImg" src="${msg.file_url}" style="width: 300px" alt=""/>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${msg.file_cnt eq 0}">
                <c:if test="${msg.send_user_id eq sessionScope.user_email}">
                    <div class="chatBox right" data-time="${time}">
                        <div class="chat">${msg.msg_cont}</div>
                    </div>
                </c:if>
                <c:if test="${msg.send_user_id ne sessionScope.user_email}">
                    <div class="chatBox left" data-time="${time}">
                        <div class="chat">${msg.msg_cont}</div>
                    </div>
                </c:if>
            </c:if>
        </c:forEach>
    </div>
    <div class="chatFormContainer">
        <form id="chatForm">
            <div class="chatIptBox">
                <textarea id="chat" class="chatIpt" autocomplete="off"></textarea>
                <button id="send" class="sendBtn" type="submit"><i class="fa-solid fa-arrow-up"></i></button>
            </div>
        </form>
        <label class="labelImgFileIpt" for="img"><i class="fa-solid fa-upload"></i></label>
        <input id="img" class="imgFileIpt" name="file_img" type="file" accept="image/*"
               formenctype="multipart/form-data"/>
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
            stompClient.subscribe('/chatSub/${chatRequest.selBuyMsgDto.room_num}', (response) => {
                parsingMessage(JSON.parse(response.body));
            });

            scrollToBottom();
        };

        stompClient.onWebSocketError = (error) => {
            console.error('Error with websocket', error);
        };

        stompClient.onStompError = (frame) => {
            console.error('Broker reported error: ' + frame.headers['message']);
            console.error('Additional details: ' + frame.body);
        };
    };

    // 나가기 버튼에 매핑해서 호출하자.
    const disconnect = () => {
        stompClient.deactivate();
        setConnected(false);
        console.log("Disconnected");
    };

    const sendMessage = () => {
        const message = document.querySelector("#chat").value;

        if (message.trim() === "") {
            document.querySelector("#chat").value = "";
            return;
        }

        const messageDto = {
            room_num:${chatRequest.selBuyMsgDto.room_num},
            msg_cont: message,
            buy_id: "${chatRequest.selBuyMsgDto.user_id}",
            pj_id: "${chatRequest.selBuyMsgDto.pj_id}",
            send_user_id: "${sessionScope.user_email}"
        };

        stompClient.publish({
            destination: "/chatPub/chat/${chatRequest.selBuyMsgDto.room_num}",

            body: JSON.stringify(messageDto)
        });

        setTimeout(() => {
            document.querySelector("#chat").value = "";
        }, 1);
    };

    const sendImg = async () => {

        if (document.querySelector("#img").value === "") {
            return;
        }
        const formData = new FormData();

        const files = document.querySelector("#img").files;

        // 멀티파트 전송시에도 메시지를 함께 첨부할 수 있으며, 컨트롤러에서 하나 이상의 dto에 나누어 담을 수 있다.
        formData.append("file", files[0]);
        formData.append("buy_id", "${chatRequest.selBuyMsgDto.user_id}");
        formData.append("pj_id", "${chatRequest.selBuyMsgDto.pj_id}");
        formData.append("send_user_id", "${sessionScope.user_email}");
        formData.append("room_num", "${chatRequest.selBuyMsgDto.room_num}");
        formData.append("file_cnt", files.length);

        document.querySelector("#img").value = "";

        // fetch의 결과는 Promise<Result> 이다.
        // await를 통해 Result 를 프로미스로부터 꺼내온다.
        // result 객체의 json() 메서드는 Promise<서버로부터 얻은값> 을 리턴한다.
        // await를 통해 프로미스가 품고있는 서버로부터 얻은 값을 꺼낸다.
        // const result = await (await fetch("/chat/file", {
        //     method: "POST",
        //     headers: {},
        //     body: formData
        // })).json();

        const result = await fetch("/chat/file", {
            method: "POST",
            headers: {},
            body: formData
        });

        user = await result.json();

        document.querySelector(".body").innerHtml = '<h1>${user.name } 반갑습니다. </h14>'


        if (!result.ok) {
            alert("전송할 수 없는 파일입니다");
        }
    };

    // 메시지 생성시 하단 스크롤 유지
    const scrollToBottom = () => {
        const scrollHeight = document.querySelector(".chatMainContainer").scrollHeight;
        document.querySelector(".chatMainContainer").scrollTo({top: scrollHeight});
    };

    // 구독중인 토픽에 변화가 생길때 실행되는 콜백
    // 채팅방에 입장해서 생기는 일은 모델에 데이터를 담아와서 jsp로 처리하고있다.
    const parsingMessage = (message) => {
        const position = message.send_user_id === "${sessionScope.user_email}" ? "right" : "left";

        const msg = message.file_cnt != 0 ? paintImg(position, message.file_url, message.svr_intime_string) : paintChat(position, message.msg_cont, message.svr_intime_string);

        setTimeout(scrollToBottom, 1);
    };

    const paintChat = (position, text, time) => {
        document.querySelector("#chatContainer").innerHTML += `<div class="chatBox ${'${position}'}" data-time="${'${time}'}"><div class="chat">${'${text}'}</div></div>`;
    };

    const paintImg = (position, imgUrl, time) => {
        document.querySelector("#chatContainer").innerHTML += `<div class="chatBox ${'${position}'}" data-time="${'${time}'}"><img id="imgTag" class="chatAttImg" src="${"${imgUrl}"}" onload="scrollToBottom()"/></div>`;
        // document.querySelector("#imgTag").addEventListener("load", scrollToBottom
        // );
    };

    window.onload = () => {
        document.querySelector("#chatForm").addEventListener("submit", e => e.preventDefault());
        document.querySelector("#chat").addEventListener("keypress", (e) => {
            if (e.key === "Enter") {
                sendMessage();
            }
        });
        document.querySelector("#send").addEventListener('click', sendMessage);
        document.querySelector("#img").addEventListener('input', sendImg);
        // document.querySelector("#imgSendBtn").addEventListener('click', sendImg);
        connect();

        // const lo = document.querySelectorAll(".chatBox");
        // lo.forEach(e => e.setAttribute("data-time", "ddd"));
    };
</script>
</body>
</html>