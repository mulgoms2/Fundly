/* change btn */
const changeImg = document.getElementById('changeImg');
const changeName = document.getElementById('changeName');
const changeIntro = document.getElementById('changeIntro');

changeImg?.addEventListener("click", () => {
    const pTagbefore = document.querySelector('.pTagbefore');
    const pTagDetail = document.querySelector('.pTagDetail');

    changeBtn(changeImg, pTagbefore, pTagDetail);
});

changeName?.addEventListener("click", () => {
    const pTagbeforeName = document.querySelector('.pTagbeforeName');
    const pTagDetailName = document.querySelector('.pTagDetailName');
    const userNameValue = document.getElementById('userNameValue');
    document.getElementById('userNameValue').value = pTagbeforeName.textContent.trim();

    changeBtn(changeName, pTagbeforeName, pTagDetailName);
    userNameValue.style.border = "1px solid rgb(230, 230, 230)";
});

changeIntro?.addEventListener("click", () => {
    const pTagbeforeIntro = document.querySelector('.pTagbeforeIntro');
    const pTagDetailIntro = document.querySelector('.pTagDetailIntro');
    const introValue = document.getElementById('introValue');

    if (document.querySelector('pre') !== null)
        document.getElementById('introValue').value = document.querySelector('pre').textContent.trim();

    changeBtn(changeIntro, pTagbeforeIntro, pTagDetailIntro);
    introValue.style.border = "border: 1px solid rgb(230, 230, 230)";
    introValue.style.resize = "none";
    introValue.style.width = "100%";
});

/* save btn */
const imgsave = document.getElementById('imgsave');
const namesave = document.getElementById('namesave');
const introsave = document.getElementById('introsave');
const url = "/user/update";

/* imgsave*/
imgsave?.addEventListener("click", () => {
    const pTagImgFile = document.querySelector('.pTagImgFile');
    const pTagChangeImgFile = document.querySelector('.pTagChangeImgFile');
    const profileImg = document.getElementById('profileImg');

    // 파일 객체 가져오기
    const imgFile = document.querySelector('#fileimg').files[0];

    // FormData 객체 생성
    const formData = new FormData();
    formData.append('file', imgFile);

    // POST 요청 보내기
    fetch("/user/imgupdate", {
        method: 'POST',
        // headers: {
        //     "content-type": "application/json"
        // },
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            pTagImgFile.style.background = `url('/user/img/${data[1]}') 50% 37% / cover no-repeat`;
            pTagChangeImgFile.style.background = `url('/user/img/${data[1]}') 50% 37% / cover no-repeat`;
            profileImg.style.background = `url('/user/img/${data[1]}') 50% 37% / cover no-repeat`;
            changeImg.click();
            alert('프로필 이미지가 성공적으로 수정되었습니다.');

        })
        .catch(error => {
            // 오류 발생 시 처리하는 코드
            console.error('Error:', error);
        });
});


/* name */
namesave?.addEventListener("click", () => {

    const pTagbeforeName = document.querySelector('.pTagbeforeName');
    const userName = document.getElementById('userNameValue').value;
    // const url = "/user/update";
    const data = {'user_name': userName, 'user_email': getCookie('user_email')};

    // fetch API를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then((response) => {
            // 응답 처리
            if (!response.ok) {
                throw response.text();
            }
            return response.json()
        })
        .then((userInfo) => {

            let info = userInfo;
            pTagbeforeName.innerText = info.user_name;
            document.getElementById('ifTxt').innerText = info.user_name;
            changeName.click();

            alert('이름이 성공적으로 수정되었습니다.');

        })
        .catch(error => error).then(error => {
        // 오류 처리
        console.error('error msg : ', error)
    })
});// },{capture:true});

/* intro */
introsave?.addEventListener("click", () => {

    const pTagbeforeIntro = document.querySelector('.pTagbeforeIntro');
    const userintro = document.getElementById('introValue').value;
    const url = "/user/update";
    const data = {'user_intro': userintro, 'user_email': getCookie('user_email')};

    // fetch API를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then((response) => {
            // 응답 처리
            if (!response.ok) {
                throw response.text();
            }
            return response.json()
        })
        .then((userInfo) => {

            let info = userInfo;
            pTagbeforeIntro.innerText = info.user_intro;
            changeIntro.click();
            alert('소개가 성공적으로 수정되었습니다.');

        })
        .catch(error => error).then(error => {
        // 오류 처리
        console.error('error msg : ', error)
    })
});

/* enter key focus */
const userNameValue = document.getElementById('userNameValue');
const introValue = document.getElementById('introValue');

userNameValue?.addEventListener("keydown", function (e) {
    if (e.key === "Enter") {
        namesave.click();
    }
})

introValue?.addEventListener("keydown", function (e) {
    if (e.key === "Enter") {
        if (!e.shiftKey) {
            // alert('엔터 실행');
            introsave.click();
        }
    }
})

function handleFile(files) {
    document.querySelector('.pTagChangeImgFile').style.background = `url('${URL.createObjectURL(files[0])}') 50% 37% / cover no-repeat`;
}

