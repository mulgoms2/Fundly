
const likestate = document.getElementById("likestate");
const stateOrderby = document.getElementById("stateOrderby");

const stateList = document.querySelector('.stateList');
const orderList = document.querySelector('.orderList');

stateList.addEventListener("click",()=> {
    toggleContent("likestate");
    if(stateOrderby.style.display==="block"){ toggleContent("stateOrderby");}
    if(MyPageList.style.display==="block"){ toggleContent("MyPageList");}
},{capture:true});

orderList.addEventListener('click', () => {
    toggleContent("stateOrderby");
    if(likestate.style.display==="block"){ toggleContent("likestate");}
    if(MyPageList.style.display==="block"){ toggleContent("MyPageList");}
},{capture:true});

// userIf.removeEvent("click");
// userIf.addEventListener("click",()=> {
//     if(loginInfo.outerText !== '로그인/회원가입' ){
//         toggleContent("MyPageList");
//         if(likestate.style.display==="block"){ toggleContent("likestate");}
//         if(stateOrderby.style.display==="block"){ toggleContent("stateOrderby");}
//     }else{
//         return window.location.href='/login/login';
//     }
// },{capture:true});

window.addEventListener("click",(e)=> {
    // if (e.target.classList !== 'likestate' ||e.target.classList!=='stateOrderby') {\
    if(e.target.id ===''){
        if (likestate.style.display === "block") {
            toggleContent("likestate");
        } else if (stateOrderby.style.display === "block") {
            toggleContent("stateOrderby");
        }
        else if (document.getElementById('MyPageList').style.display === "block") {
            toggleContent("MyPageList");
        }
    }
})
