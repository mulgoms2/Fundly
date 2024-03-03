/* change btn */
const changeImg = document.getElementById('changeImg');
const changeName = document.getElementById('changeName');
const changeIntro = document.getElementById('changeIntro');

changeImg.addEventListener("click",()=> {
    const pTagbefore = document.querySelector('.pTagbefore');
    const pTagDetail = document.querySelector('.pTagDetail');

    changeBtn(changeImg,pTagbefore,pTagDetail);
});

changeName.addEventListener("click",()=> {
    const pTagbeforeName = document.querySelector('.pTagbeforeName');
    const pTagDetailName = document.querySelector('.pTagDetailName');
    const userNameValue = document.getElementById('userNameValue');

    changeBtn(changeName,pTagbeforeName,pTagDetailName);
    userNameValue.style.border = "1px solid rgb(230, 230, 230)";
});

changeIntro.addEventListener("click",()=> {
    const pTagbeforeIntro = document.querySelector('.pTagbeforeIntro');
    const pTagDetailIntro = document.querySelector('.pTagDetailIntro');
    const introValue = document.getElementById('introValue');

    changeBtn(changeIntro,pTagbeforeIntro,pTagDetailIntro);

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
imgsave.addEventListener("click",()=> {
    const pTagImgFile = document.querySelector('.pTagImgFile');
    const profileImg = document.getElementById('profileImg');

    // 파일 객체 가져오기
    const imgFile = document.querySelector('#fileimg').files[0];

    // FormData 객체 생성
    const formData = new FormData();
    formData.append('file', imgFile);

    // POST 요청 보내기
    fetch("/user/imgupdate", {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // 이미지 업데이트 후에 새로고침 효과 클래스를 제거합니다.
            pTagImgFile.classList.remove('refreshing');
            profileImg.classList.remove('refreshing');

            // 새로운 이미지 경로로 배경을 설정합니다.
            pTagImgFile.style.background = `url('/user/img/${data[1]}') 50% 37% / cover no-repeat`;
            profileImg.style.background = `url('/user/img/${data[1]}') 50% 37% / cover no-repeat`;
            changeImg.click();

        })
        .catch(error => {
            // 오류 발생 시 처리하는 코드
            console.error('Error:', error);
        });
});


/* name */
namesave.addEventListener("click",()=> {

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
        .then( (response) =>{
            // 응답 처리
            if (!response.ok) {
                throw response.text();
            }
            return response.json()
        })
        .then((userInfo) => {

            let info = userInfo;

            // alert('이름이 성공적으로 수정되었습니다.');
            pTagbeforeName.innerText = info.user_name;
            document.getElementById('ifTxt').innerText =info.user_name;
            changeName.click();

        })
        .catch(error => error).then(error=>{
            // 오류 처리
            console.error('error msg : ', error)
        })
});// },{capture:true});

/* intro */
introsave.addEventListener("click",()=> {

    const pTagbeforeIntro = document.querySelector('.pTagbeforeIntro');
    const userintro = document.getElementById('introValue').value;
    // const url = "/user/update";
    const data = {'user_intro': userintro, 'user_email': getCookie('user_email')};

    // fetch API를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then( (response) =>{
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

        })
        .catch(error => error).then(error=>{

        // alert(error);
        // 오류 처리
        // console.error('error msg : ', error)
    })
});

/* enter key focus */

const userNameValue = document.getElementById('userNameValue');
const introValue = document.getElementById('introValue');

userNameValue.addEventListener("keydown",function (e){
    if(e.key === "Enter"){
        namesave.click();
    }
})

introValue.addEventListener("keydown",function (e){
    if(e.key === "Enter"){
        if(!e.shiftKey){
            // alert('엔터 실행');
            introsave.click();
        }
    }
})