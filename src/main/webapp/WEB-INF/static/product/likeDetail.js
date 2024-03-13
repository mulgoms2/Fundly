
// 현재 좋아요 상태 정보 가져오기
window.onload = function() {IsLike();};

const IsLike = () => {

    let user_id = document.querySelector('.userId').innerText;
    let pj_id = document.querySelector('.pjId').innerText;
    let curr_pj_like_cnt = document.querySelector('.likeCnt').innerText;

    //요청보낼 데이터
    let obj = {
        pj_id : "P001",
        user_id : "bada@naver.com",
        curr_pj_like_cnt : 8
    };
    console.log(obj)

    // 1. 서버로 좋아요 정보 전송 2. 서버 응답 받기
    fetch("/like/check", {
        method: "POST",
        headers: {
            "content-type": "application/json",
            "accept": "application/json"
        },
        body: JSON.stringify(obj)
    }).then(res => res.json()).then(checkLike);
};

const checkLike = (res) => {
    console.log(res.like_status)
    if(res.like_status === 0) {

        colorLike('white');

    } else if(res.like_status === 1) {

        colorLike('red');

    }
}

// 좋아요 버튼 클릭시 정보 전송하고 상태값 변경 시킨 후 가져오기
const clickLikeBtn = () => {

    let user_id = document.querySelector('.userId').innerText;
    let pj_id = document.querySelector('.pjId').innerText;
    let curr_pj_like_cnt = document.querySelector('.likeCnt').innerText;

    //요청보낼 데이터
    let obj = {
        pj_id : "P001",
        user_id : "bada@naver.com",
        curr_pj_like_cnt : 9
    };
    console.log(obj)

    // 1. 서버로 좋아요 정보 전송 2. 서버 응답 받기
    fetch("/like/update", {
        method: "POST",
        headers: {
            "content-type": "application/json",
            "accept": "application/json"
        },
        body: JSON.stringify(obj)
    }).then(res => res.json()).then(handleLike);
};

const handleLike = (res) => {
    console.log(res)

    if(res.like_status === 0) {

        colorLike('white');

    } else if(res.like_status === 1) {

        colorLike('red');

    }
}

// 1. 좋아요 상태에 따라 버튼이미지 전환
function colorLike(type) {

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