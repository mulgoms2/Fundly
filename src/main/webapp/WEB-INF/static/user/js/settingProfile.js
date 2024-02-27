/* change btn */
const changeImg = document.getElementById('changeImg');
const changeName = document.getElementById('changeName');
const changeIntro = document.getElementById('changeIntro');

changeImg.addEventListener("click",()=> {
    const pTagbefore = document.querySelector('.pTagbefore');
    const pTagDetail = document.querySelector('.pTagDetail');

    changeBtn(changeImg,pTagbefore,pTagDetail);
});-
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
const namesave = document.getElementById('namesave');
const introsave = document.getElementById('introsave');

namesave.addEventListener("click",()=> {

    const userName = document.getElementById('userNameValue').value;
    const url = "/user/update";
    const data = {'user_name': userName, 'user_email': getCookie('user_email')};

    // fetch API를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(function (response) {
            // 응답 처리
            if (!response.ok) {
                throw new Error('response error');
            }

        })
        .catch(function (error) {
            // 오류 처리
            console.error('error msg : ', error);
        });
});// },{capture:true});


introsave.addEventListener("click",()=> {

    const userintro = document.getElementById('introValue').value;
    const url = "/user/update";
    const data = {'user_intro': userintro, 'user_email': getCookie('user_email')};


    alert(userintro);

    // fetch API를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(function (response) {
            // 응답 처리
            if (!response.ok) {
                throw new Error('response error');
            }

        })
        .catch(function (error) {
            // 오류 처리
            console.error('error msg : ', error);
        });
});