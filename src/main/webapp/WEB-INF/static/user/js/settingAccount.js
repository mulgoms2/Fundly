/* pwd */

/* 이메일, 비밀번호에 대한 정규식 */
const pwd =/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=\-.])(?=.*[0-9]).{8,20}$/;

/* pwd eye */
const pwdInput = document.getElementById('user_pwd');
const pwdInputConfirm = document.getElementById('user_pwdConfirm');

const togglePwd = document.getElementById('togglePwd');
const togglePwdConfirm = document.getElementById('togglePwdConfirm');

togglePwd.addEventListener('click', function () {

    if (pwdInput.type === 'password') {
        pwdInput.type = 'text';
        togglePwd.style.backgroundImage = 'url("/static/img/Icon-private.png")';
    } else {
        pwdInput.type = 'password';
        togglePwd.style.backgroundImage = 'url("/static/img/Icon-eye.png")';
    }
});

togglePwdConfirm.addEventListener('click', function () {

    if (pwdInputConfirm.type === 'password') {
        pwdInputConfirm.type = 'text';
        togglePwdConfirm.style.backgroundImage = 'url("/static/img/Icon-private.png")';
    } else {
        pwdInputConfirm.type = 'password';
        togglePwdConfirm.style.backgroundImage = 'url("/static/img/Icon-eye.png")';
    }
});


const userChaPwdValue = document.getElementById("userChaPwdValue");

// 비밀번호는 8자~20자 / 공백 / 숫자, 영문, 특수문자 조합
userChaPwdValue.addEventListener("keyup", () => {
    // if (user_pwd.value.length < 8 && user_pwd.value.length > 20 ) {
    if (!(user_pwd.value.length > 8 && user_pwd.value.length < 20) && user_pwd.value.length!== 0) {
        setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwd", "msgPwd", "red");
        return false;
    } else if(user_pwd.value.search(/\s/) !== -1){
        setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwd", "msgPwd", "red");
        return false;
    } else if(!pwd.test(user_pwd.value)&& user_pwd.value.length!== 0){
        setMessage('영문, 숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwd", "msgPwd", "red");
        return false;
    }
    else {
        setMessage('', "user_pwd", "msgPwd", "black");
    }
})

/* 유효성(입력된 값에 따른 변화 style,value) */
function setMessage(msg, elementid, msgid, color){
    document.getElementById(msgid).innerHTML = `${'${msg}'}`;
    document.getElementById(elementid).style.border = "1px solid " + color;
    // elementid.focus();
}

// // 비밀번호는 8자 이상 20자(21개 변경) 이하 / 숫자, 영문 대소문자, 특수문자 중 2가지 이상 조합 / 3개 이상 연속으로 동일한 문자
// // user_pwd와 비교
// user_pwdConfirm.addEventListener("keyup", () => {
//     if (!(user_pwdConfirm.value.length > 8 && user_pwdConfirm.value.length < 20) && user_pwdConfirm.length!== 0) {
//         setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
//         return false;
//     }
//     else if(user_pwdConfirm.value.search(/\s/) !== -1){
//         setMessage('비밀번호는 공백 없이 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
//         return false;
//     } else if(!pwd.test(user_pwdConfirm.value)&& user_pwdConfirm.value.length!== 0){
//         setMessage('영문, 숫자, 특수문자를 혼합하여 입력해주세요.', "user_pwdConfirm", "msgPwdConfirm", "red");
//         return false;
//     }else if(user_pwdConfirm.value !== user_pwd.value){
//         setMessage('비밀번호가 일치하지 않습니다.', "user_pwdConfirm", "msgPwdConfirm", "red");
//         return false;
//     }
//     else {
//         setMessage('', "user_pwdConfirm", "msgPwdConfirm", "black");
//     }
// })
//



/* change btn */
const changePwd = document.getElementById('changePwd');
const changePhoneNo = document.getElementById('changePhoneNo');

changePwd.addEventListener("click",()=> {
    const pTagbeforePwd = document.querySelector('.pTagbeforePwd');
    const pTagDetailPwd = document.querySelector('.pTagDetailPwd');

    changeBtn(changePwd,pTagbeforePwd,pTagDetailPwd);
});

changePhoneNo.addEventListener("click",()=> {
    const pTagbeforePhoneNo = document.querySelector('.pTagbeforePhoneNo');
    const pTagDetailPhoneNo = document.querySelector('.pTagDetailPhoneNo');
    const userNameValue = document.getElementById('userNameValue');

    changeBtn(changePhoneNo,pTagbeforePhoneNo,pTagDetailPhoneNo);
    // userNameValue.style.border = "1px solid rgb(230, 230, 230)";
});

/* save btn */
// const namesave = document.getElementById('namesave');
// const introsave = document.getElementById('introsave');
// namesave.addEventListener("click",()=> {
//
//     const userName = document.getElementById('userNameValue').value;
//     const url = "/user/update";
//     const data = {'user_name': userName, 'user_email': getCookie('user_email')};
//
//     // fetch API를 사용하여 POST 요청 보내기
//     fetch(url, {
//         method: 'POST',
//         headers: {
//             "content-type": "application/json"
//         },
//         body: JSON.stringify(data)
//     })
//         .then(function (response) {
//             // 응답 처리
//             if (!response.ok) {
//                 throw new Error('response error');
//             }
//
//         })
//         .catch(function (error) {
//             // 오류 처리
//             console.error('error msg : ', error);
//         });
// });// },{capture:true});
//
// introsave.addEventListener("click",()=> {
//
//     const userintro = document.getElementById('introValue').value;
//     const url = "/user/update";
//     const data = {'user_intro': userintro, 'user_email': getCookie('user_email')};
//
//     alert(userintro);
//
//     // fetch API를 사용하여 POST 요청 보내기
//     fetch(url, {
//         method: 'POST',
//         headers: {
//             "content-type": "application/json"
//         },
//         body: JSON.stringify(data)
//     })
//         .then(function (response) {
//             // 응답 처리
//             if (!response.ok) {
//                 throw new Error('response error');
//             }
//
//         })
//         .catch(function (error) {
//             // 오류 처리
//             console.error('error msg : ', error);
//         });
// });