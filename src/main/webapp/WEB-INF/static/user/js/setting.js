const tapContainer = document.getElementById('divtapContainer');

tapContainer.addEventListener("click",(e)=>{
    const tapItems = document.querySelectorAll('.tapItem');

    tapItems.forEach(item => {
        // item.classList.add('on');
        // item.classList.remove('on'); // 모든 탭의 활성화 클래스를 제거합니다.
    });

    if(e.target.id=="itemProfile"){
        // e.target.parentElement.classList.add('on');
        // tap_itemProfile.style.display = "block";

        // tap_itemProfile.style.display = "block";
        // // itemProjectReview.style.display = "none";
        // // alert('프로필');

    }
    else if(e.target.id=="itemProjectReview"){
        // tap_itemProfile.style.display = "none";
        // // itemProjectReview.style.display = "block";
        // alert('후기');

    } else if(e.target.id=="itemMakeProject"){
        // tap_itemProfile.style.display = "none";
        // // itemProjectReview.style.display = "none";
        // alert('올린');

    } else if(e.target.id=="itemProjectOrder"){
        // tap_itemProfile.style.display = "none";
        // // itemProjectReview.style.display = "none";
        // alert('후원');
    }
})