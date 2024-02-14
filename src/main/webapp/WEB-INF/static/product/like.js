window.onload = loadIsLike();

// 좋아요 버튼 클릭시 서버로 정보 전송
const clickLikeBtn = () => {

    // 1. 서버로 좋아요 정보 전송
    const obj = {
      pj_id : "P5040",
      user_id : "user"
    };

    // 2. 서버 응답 받기
    fetch("/like", {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(obj)
    }).then(res => res.json()).then(handleLike).catch(error=>console.log(error));
};

const handleLike = (likes) => {

        if(likes.like_status === 0) {

        // 3. 상태0일 때 좋아요 - 카운트
            countLike('minus');

        // 4. 좋아요 버튼 하양
            onloadLike('white');

            console.log(likes.like_status);

        } else if(likes.like_status === 1) {

        // 5. 상태1일 때 좋아요 + 카운트
            countLike('plus');

        // 6. 좋아요 버튼 빨강
            onloadLike('red');

            console.log(likes.like_status);
        }
}

// 1. 좋아요 상태에 따라 버튼이미지 전환
function onloadLike(type) {

    const heartRed = document.querySelector(".icoImg.on")
    const heartWhite = document.querySelector(".icoImg")

    // 2. 상태0이면 하양하트로 고정
    if(type === 'white') {
        heartRed.style.display = 'none';
        heartWhite.style.display = 'block';

    // 3. 상태1이면 빨강하트로 고정
    } else if(type === 'red') {
        heartRed.style.display = 'block';
        heartWhite.style.display = 'none';
    }
}

// 1. 좋아요 버튼 클릭 시 좋아요 수 변화
function countLike(type) {

    const like_cnt = document.getElementById("like_cnt")
    let curr_cnt = like_cnt.innerText;

    // 2. 좋아요 누르면 증가
    if (type === 'plus') {
        curr_cnt = parseInt(curr_cnt) + 1;

    // 3. 좋아요 취소하면 감소
    } else if (type === 'minus') {
        curr_cnt = parseInt(curr_cnt) - 1;
    }
    like_cnt.innerText = curr_cnt;
}

// 좋아요 상태 고정시키기
function loadIsLike() {
    alert('좋아요상태고정시키기!')
}

// const fetchLikeInfo = () => {
//     var params = $('#formtest').serialize();
//
//     return fetch("/chat", {
//         method: "POST",
//         headers: {
//             "content-type": "application/json"
//         },
//         body: params
//     });
// }
//
// // ajax로 좋아요 클릭시 데이터 전송 (페이지 이동x)
// function sendLikeInfo() {
//
//     var params = $('#formtest').serialize();
//
//     $.ajax({
//         url: "/like2",
//         dataType: "json",
//         data: params,
//         type: "POST",
//         success: function (data) {
//             alert("성공")
//         },
//         error: function () {
//             alert("에러")
//         }
//     });
// }