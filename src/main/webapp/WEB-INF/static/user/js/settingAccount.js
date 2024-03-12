/* pwd */
/* 이메일, 비밀번호에 대한 정규식 */
const pwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=\-.])(?=.*[0-9]).{8,20}$/;

/* pwd eye */
const pwdNowInput = document.getElementById('userNowPwdValue');
const pwdInput = document.getElementById('userChaPwdValue');
const pwdInputConfirm = document.getElementById('userChaPwdConfirmValue');

const toggleNowPwd = document.getElementById('toggleNowPwd');
const togglePwd = document.getElementById('togglePwd');
const togglePwdConfirm = document.getElementById('togglePwdConfirm');

toggleNowPwd?.addEventListener('click', function () {

    if (pwdNowInput.type === 'password') {
        pwdNowInput.type = 'text';
        toggleNowPwd.style.backgroundImage = 'url("/static/img/Icon-private.png")';
    } else {
        pwdNowInput.type = 'password';
        toggleNowPwd.style.backgroundImage = 'url("/static/img/Icon-eye.png")';
    }
});

togglePwd?.addEventListener('click', function () {

    if (pwdInput.type === 'password') {
        pwdInput.type = 'text';
        togglePwd.style.backgroundImage = 'url("/static/img/Icon-private.png")';
    } else {
        pwdInput.type = 'password';
        togglePwd.style.backgroundImage = 'url("/static/img/Icon-eye.png")';
    }
});

togglePwdConfirm?.addEventListener('click', function () {

    if (pwdInputConfirm.type === 'password') {
        pwdInputConfirm.type = 'text';
        togglePwdConfirm.style.backgroundImage = 'url("/static/img/Icon-private.png")';
    } else {
        pwdInputConfirm.type = 'password';
        togglePwdConfirm.style.backgroundImage = 'url("/static/img/Icon-eye.png")';
    }
});

const userNowPwdValue = document.getElementById("userNowPwdValue");
const userChaPwdValue = document.getElementById("userChaPwdValue");
const userChaPwdConfirmValue = document.getElementById("userChaPwdConfirmValue");
const userPhoneValue = document.getElementById("userPhoneValue");

// 비밀번호는 8자~20자
userNowPwdValue?.addEventListener("keyup", () => {

    if (!(userNowPwdValue.value.length > 8 && userNowPwdValue.value.length < 20) && userNowPwdValue.value.length !== 0) {
        setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "nowNowPwdWrap", "msgCheckPwd", "red");
        return false;
    } else {
        setMessage('', "nowNowPwdWrap", "msgCheckPwd", "rgb(230, 230, 230)");
    }
})

// 비밀번호는 8자~20자 / 공백 / 숫자, 영문, 특수문자 조합
userChaPwdValue?.addEventListener("keyup", () => {

    if (!(userChaPwdValue.value.length > 8 && userChaPwdValue.value.length < 20) && userChaPwdValue.value.length !== 0) {
        setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "chaPwdMainWrap", "msgChangePwd", "red");
        return false;
    } else if (userChaPwdValue.value.search(/\s/) !== -1) {
        setMessage('비밀번호는 공백 없이 입력해주세요.', "chaPwdMainWrap", "msgChangePwd", "red");
        return false;
    } else if (!pwd.test(userChaPwdValue.value) && userChaPwdValue.value.length !== 0) {
        setMessage('영문, 숫자, 특수문자를 혼합하여 입력해주세요.', "chaPwdMainWrap", "msgChangePwd", "red");
        return false;
    } else {
        setMessage('', "chaPwdMainWrap", "msgChangePwd", "rgb(230, 230, 230)");
    }
})

// 비밀번호는 8자 이상 20자(21개 변경) 이하 / 숫자, 영문 대소문자, 특수문자 중 2가지 이상 조합
userChaPwdConfirmValue?.addEventListener("keyup", () => {
    if (!(userChaPwdConfirmValue.value.length > 8 && userChaPwdConfirmValue.value.length < 20) && userChaPwdConfirmValue.value.length !== 0) {
        setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "chaPwdConfirmWrap", "msgChangeConfirmPwd", "red");
        return false;
    } else if (userChaPwdConfirmValue.value.search(/\s/) !== -1) {
        setMessage('비밀번호는 공백 없이 입력해주세요.', "chaPwdConfirmWrap", "msgChangeConfirmPwd", "red");
        return false;
    } else if (!pwd.test(userChaPwdConfirmValue.value) && userChaPwdConfirmValue.value.length !== 0) {
        setMessage('영문, 숫자, 특수문자를 혼합하여 입력해주세요.', "chaPwdConfirmWrap", "msgChangeConfirmPwd", "red");
        return false;
    } else if (userChaPwdConfirmValue.value !== userChaPwdValue.value) {
        setMessage('비밀번호가 일치하지 않습니다.', "chaPwdConfirmWrap", "msgChangeConfirmPwd", "red");
        return false;
    } else {
        setMessage('', "chaPwdConfirmWrap", "msgChangeConfirmPwd", "rgb(230, 230, 230)");
    }
})

/* user phone no*/
userPhoneValue?.addEventListener("keyup", () => {
    if (!(userPhoneValue.value.length > 9 && userPhoneValue.value.length < 15) && userPhoneValue.value.length !== 0) {
        setMessage('유효한 연락처가 아닙니다', "pTagChaDetailWrap", "msgPhoneNo", "red");
        return false;
    } else {
        setMessage('', "pTagChaDetailWrap", "msgPhoneNo", "rgb(230, 230, 230)");
    }
})

/* change btn */
const changePwd = document.getElementById('changePwd');
const changePhoneNo = document.getElementById('changePhoneNo');

changePwd?.addEventListener("click", () => {
    const pTagbeforePwd = document.querySelector('.pTagbeforePwd');
    const pTagDetailPwd = document.querySelector('.pTagDetailPwd');
    init();
    changeBtn(changePwd, pTagbeforePwd, pTagDetailPwd);
});

changePhoneNo?.addEventListener("click", () => {
    const pTagbeforePhoneNo = document.querySelector('.pTagbeforePhoneNo');
    const pTagDetailPhoneNo = document.querySelector('.pTagDetailPhoneNo');
    const userNameValue = document.getElementById('userNameValue');
    init();
    changeBtn(changePhoneNo, pTagbeforePhoneNo, pTagDetailPhoneNo);
    // userNameValue.style.border = "1px solid rgb(230, 230, 230)";
});


/* save btn */
const pwdsave = document.getElementById('pwdsave');
const phonenosave = document.getElementById('phonenosave');

pwdsave?.addEventListener("click", () => {
    const nowPwdValue = document.getElementById('userNowPwdValue').value;
    const changePwdValue = document.getElementById('userChaPwdValue').value;
    const url = "/user/update";
    const data = {
        'user_prev_pwd': nowPwdValue,
        'user_pwd': changePwdValue,
        'user_email': getCookie('user_email')
    };

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

            init();
            changePwd.click();
            alert('비밀번호가 성공적으로 수정되었습니다.');

        })
        .catch(function (error) {
            // 오류 처리
            alert(error);
            console.error('error msg : ', error);
        });
});

phonenosave?.addEventListener("click", () => {

    const userPhoneValue = document.getElementById('userPhoneValue').value;
    const valueLength = userPhoneValue.replaceAll('-', '').length;
    const url = "/user/update";
    const data = {
        'user_phone_no': userPhoneValue,
        'user_email': getCookie('user_email')
    };

    if (valueLength === 0) {
        setMessage('연락처를 입력해주세요.', "pTagChaDetailWrap", "msgPhoneNo", "red");
        return false;
    }

    if (!(valueLength > 9 && valueLength < 15) && valueLength !== 0) {
        setMessage('유효한 연락처가 아닙니다', "pTagChaDetailWrap", "msgPhoneNo", "red");
        return false;
    }

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
            init();
            changePhoneNo.click();
            document.querySelector('.pTagChaDetailPhoneNo').innerText = userInfo.user_phone_no;
            alert('전화번호가 성공적으로 수정되었습니다.');

        })
        .catch(function (error) {
            // 오류 처리
            alert(error);
            console.error('error msg : ', error);
        });
});


function init() {
    document.getElementById('userNowPwdValue').value = '';
    document.getElementById('userChaPwdValue').value = '';
    document.getElementById('userChaPwdConfirmValue').value = '';
    toggleNowPwd.click();
    togglePwd.click();
    togglePwdConfirm.click();
    setMessage('', "nowNowPwdWrap", "msgCheckPwd", "rgb(230, 230, 230)");
    setMessage('', "chaPwdMainWrap", "msgChangePwd", "rgb(230, 230, 230)");
    setMessage('', "chaPwdConfirmWrap", "msgChangeConfirmPwd", "rgb(230, 230, 230)");
}

/* 유효성(입력된 값에 따른 변화 style,value) */
function setMessage(msg, elementid, msgid, color) {
    document.getElementById(msgid).innerHTML = msg;
    document.getElementById(elementid).style.border = "1px solid " + color;
    // elementid.focus();
}

function oninputPhone(target) {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}

