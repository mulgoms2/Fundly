function changeBtn(btnName,before,detail){

    if(btnName.innerHTML==="변경"){

        before.style.display="none";
        detail.style.display="block";

        btnName.innerHTML="취소";
        btnName.style.background= "rgb(252, 252, 252)";
        btnName.style.border = "1px solid rgb(228, 228, 228)";
    }else{
        before.style.display="block";
        detail.style.display="none";

        btnName.innerHTML="변경";
        btnName.style.background= "rgb(208, 208, 208)";
        btnName.style.border= "none";
    }
}

// 특정 쿠키의 값을 가져오기
function getCookie(name) {
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        if (cookie.startsWith(name + '=')) {
            return cookie.substring(name.length + 1);
        }
    }
    return null;
}