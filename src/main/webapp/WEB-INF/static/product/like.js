// 좋아요 버튼 클릭시 좋아요 + -

const clickLikeBtn = () => {
    const obj = {
      pj_id : "P1010",
      user_id : "user"
    };

//     if 버튼이 안눌린상태 일때
    fetch("/like2", {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(obj)
    }).then(res => res.json()).then(handleLike);


    // if ()
//     1. 서버로 좋아요 정보 전송
//     2. 서버 응답 받는다.
//     3. 응담 성공시 좋아요 카운트 증가
//     4. 좋아요 버튼 색상 변경

//     if 버튼이 눌려있는 상태일떄
//     위와 반대의 동작
};

const handleLike = (likeDto) => {
    console.log(likeDto[0].like_status);
}

const fetchLikeInfo = () => {
    var params = $('#formtest').serialize();

    return fetch("/chat", {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: params
    });
}

function clicklike(type) {
    const like_cnt = document.getElementById("like_cnt")
    let curr_cnt = like_cnt.innerText;

    if (type === 'plus') {
        curr_cnt = parseInt(curr_cnt) + 1;
        alert("좋아요")
    } else if (type === 'minus') {
        curr_cnt = parseInt(curr_cnt) - 1;
        alert("좋아요취소")
    }

    like_cnt.innerText = curr_cnt;

    sendLikeInfo();

}

// ajax로 좋아요 클릭시 데이터 전송 (페이지 이동x)

function sendLikeInfo() {

    var params = $('#formtest').serialize();

    $.ajax({
        url: "/like2",
        dataType: "json",
        data: params,
        type: "POST",
        success: function (data) {
            alert("성공")
        },
        error: function () {
            alert("에러")
        }
    });
}