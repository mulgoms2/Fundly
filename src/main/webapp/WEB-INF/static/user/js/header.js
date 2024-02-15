/* 로그인/회원가입 or 로그인 회원 정보 */
const loginInfo = document.getElementById('loginInfo');

loginInfo.addEventListener("click",() => {

    if(loginInfo.outerText !== '로그인/회원가입'){
        toggleContent("MyPageList");
    }else{
        return window.location.href='/login/login';
    }
},{capture:true});

/* toggle */
function toggleContent(toggle){
    const content = document.getElementById(toggle);
    if(content.style.display==="none" || content.style.display === ""){
        content.style.display="block";
    } else{
        content.style.display="none";
    }
}