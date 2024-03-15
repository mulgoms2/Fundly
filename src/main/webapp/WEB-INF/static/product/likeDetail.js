
// 현재 좋아요 상태 정보 가져오기
window.onload = function() {IsLike();};

const IsLike = () => {

    let user_id = document.querySelector('.userId').innerText;
    let pj_id = document.querySelector('.pjId').innerText;
    let curr_pj_like_cnt = document.querySelector('.likeCnt').innerText;

    //요청보낼 데이터
    let obj = {
        pj_id : pj_id,
        user_id : user_id,
        curr_pj_like_cnt : curr_pj_like_cnt
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

    //로그인 유무를 확인하고 로그인 정보가 없으면 로그인페이지로 이동한다.
    if (user_id == null || user_id === ""){
        // 세션이 없는 경우, 다른 페이지로 이동
        // alert("로그인이 필요합니다.")
        window.location.href = '/login/login';
    } else {
        //요청보낼 데이터
        let obj = {
            pj_id : pj_id,
            user_id : user_id,
            curr_pj_like_cnt : curr_pj_like_cnt
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
    }
};

const handleLike = (res) => {
    console.log(res)

    if(res.like_status === 0) {

        colorLike('white');

    } else if(res.like_status === 1) {

        colorLike('red');

    }

    const likeCnt = document.querySelector('.likeCnt')
    console.log(res.curr_pj_like_cnt)
    likeCnt.innerHTML = `<span class="likeCnt">${res.curr_pj_like_cnt}</span>`;
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