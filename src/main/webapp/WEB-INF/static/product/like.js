// 좋아요 버튼 클릭시 좋아요 + -
const clickLikeBtn = () => {

    //     1. 서버로 좋아요 정보 전송
    const obj = {
      pj_id : "P5040",
      user_id : "user"
    };

    //     2. 서버 응답 받는다.
    fetch("/like", {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(obj)
    }).then(res => res.json()).then(handleLike);
};

// jason객체로 parsing된 데이터 가져오기
const handleLike = (likes) => {

    const like_cnt = document.querySelector("#like_cnt")
    const heartRed = document.querySelector(".icoImg.on")
    const heartWhite = document.querySelector(".icoImg")
    let curr_cnt = like_cnt.innerText;

    for(var i in likes) {

    //     3. 상태0일 때 좋아요 카운트 -
    //     4. 좋아요 버튼 하양
        if(likes[0].like_status === 0) {
            curr_cnt = parseInt(curr_cnt) - 1;
            heartRed.style.display = 'none';
            heartWhite.style.display = 'block';
            console.log(curr_cnt);
            console.log(likes[0].like_status);

    //     5. 상태1일 때 좋아요 카운트 +
    //     6. 좋아요 버튼 빨강
        } else if(likes[0].like_status === 1) {
            curr_cnt = parseInt(curr_cnt) + 1;
            heartRed.style.display = 'block';
            heartWhite.style.display = 'none';
            console.log(curr_cnt);
            console.log(likes[0].like_status);
        }
    }
}

const loadLike = () => {
    alert("좋아요상태체크하기")
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
// function clicklike(type) {
//     const like_cnt = document.getElementById("like_cnt")
//     let curr_cnt = like_cnt.innerText;
//
//     if (type === 'plus') {
//         curr_cnt = parseInt(curr_cnt) + 1;
//         alert("좋아요")
//     } else if (type === 'minus') {
//         curr_cnt = parseInt(curr_cnt) - 1;
//         alert("좋아요취소")
//     }
//
//     like_cnt.innerText = curr_cnt;
//
//     sendLikeInfo();
//
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