let optArr = []; //window.onload 안에 있으면 함수에서 못 갖다 쓴다.
//let itemArr = [];
//아이템 페이지의 요소들
//header.js에 옮김
const itemPage = document.querySelector("#item"); //아이템 페이지 div단락
const giftPage = document.querySelector("#gift"); //선물 페이지 div단락
const strPage = document.querySelector("#str"); //선물 만들기 첫 페이지
const strBtn = document.querySelector("#strBtn"); //아이템 만들기 버튼(아이템 페이지로 이동)
const gftBtn = document.querySelector("#gftBtn"); //상단 선물페이지 이동 버튼
const itmBtn = document.querySelector("#itmBtn"); //상단 아이템페이지 이동 버튼
const itmName = document.querySelector("#itmName"); //아이템 이름 input
const radioElems = document.querySelector('.pjBox.item').querySelectorAll("input[type=radio]"); //라디오버튼
const itmInitBtn = document.querySelector("#itmInit");//초기화버튼
const itmSaveBtn = document.querySelector("#itmSave");//저장버튼
const itmModBtn = document.querySelector("#itmMod");//수정버튼
const itmCnclBtn = document.querySelector("#itmModCncl");//수정 취소버튼

//선물 페이지의 요소들
const dropdown = document.querySelector(".dropdown");
const giftName = document.querySelector('#giftName');
const radioBtns = document.querySelector('.pjBox.gift').querySelectorAll('input[type=radio]');
const maxInputs = document.querySelectorAll('.maxInput');
const pjForm = document.querySelector('.pjBox.item').querySelector('.pjForm');
const giftInitBtn = document.querySelector("#gftInit"); //선물 초기화 버튼
const giftCnclBtn = document.querySelector("#gftModCncl"); //선물 초기화 버튼
const giftSaveBtn = document.querySelector("#gftSave"); //선물 저장버튼
const giftModBtn = document.querySelector("#gftMod"); //선물 수정버튼





window.onscroll = function(){ //window.onscroll
    let scroll = window.scrollY;
    const fixedCont = document.querySelector('.fixedContentWrapper');
    // alert(fixedCont);
    //console.log(fixedCont);
    const subHead = document.querySelector('div.subHead');
    if(scroll >= 104){
        fixedCont.classList.add('fixed');
        subHead.classList.add('fixed');
    } else {
        fixedCont.classList.remove('fixed');
        subHead.classList.remove('fixed');
    }
}

window.onload = function () {

    // reward.jsp 뷰에는 세가지 페이지가 존재(itemPage, giftPage, strPage)
    // 상태에 따라 세가지 뷰를 css로 none / block 처리해서 보여주게 된다.

    // 처음 뷰를 요청시 디폴트 페이지는
    // 선물 등록 페이지(giftPage) / 또는 선물-아이템 시작페이지(strPage)
    mkHidden([itemPage]);//즉, 아이템 페이지는 처음엔 숨겨두었다가(디폴트x) 클릭하면 보여주게 됨.
    loadPage("pj1"); //DB에서 해당 프로젝트에 등록된 아이템의 수를 조회해 giftPage 또는 strPage를 보여주는 함수
    // todo 현재 프로젝트 아이디는 하드코딩 상태.
    //  나중에 어떻게 프로젝트 아이디를 넘길지 생각하기.
    //   1. tiles의 헤더부분에 hidden input으로 pj_id를 가지고 있기
    //   2. url에 pathVariable로 달고 다니기
    //   3. 세션정보로 부터 pj_id를 구하는 함수를 만들고 이를 이용하기 등..else?

    //선물버튼 클릭시
    gftBtn.addEventListener("click", async function () {
        colorChange(this, itmBtn);
        //서버에서 등록된 아이템 수를 가져와서 0인지 체크해야함.
        const pj_id = "pj1" //하드코딩... 나중에 pj_id를 어디서 가져올지 생각해야해
        const cnt = await fetchItemCnt(pj_id)

        if (cnt === 0) { //등록된 아이템이 없으면
            mkHidden([itemPage, giftPage]) //선물 등록 페이지를 보여주지 않는다.
            //window.scrollTo(0,0); //최상단으로 이동
            mkVisible(strPage); //선물-아이템 시작페이지를 보여줌
        } else {
            mkHidden([itemPage, strPage])
            //window.scrollTo(0,0); //최상단으로 이동
            mkVisible(giftPage);
            //todo 다시 서버에 비동기 요청해서 리스트를 가져와야함.
            // 그냥 mkVisible만하면 변동 사항이 view의 리스트에 반영되지 못함(ex.아이템 삭제시 해당 아이템을 포함하는 선물도 삭제)
            loadGiftList(pj_id);
        }
    })

    //아이템 버튼 클릭시
    itmBtn.addEventListener("click", async function () {
        colorChange(this, gftBtn); //활성, 비활성 버튼의 색을 바꿔준다.
        // const cnt = document.querySelectorAll("#itemList > div");
        const pj_id = "pj1" //하드코딩... 나중에 pj_id를 어디서 가져올지 생각해야해
        const cnt = await fetchItemCnt(pj_id)
        //window.scrollTo(0,0); //최상단으로 이동
        if (cnt === 0) { //등록된 아이템의 수에 따라 숨길 페이지가 다르다.
            mkHidden([strPage])
        } else {
            mkHidden([giftPage])
        }
        mkVisible(itemPage);
        loadItemList(pj_id); //아이템 리스트를 가져와서 뿌려준다.
    })

    if (strBtn !== null) { //strPage가 보여지는 상황인 경우
        strBtn.addEventListener("click", function () { //아이템 버튼 클릭과 같은 역할.
            colorChange(itmBtn, gftBtn);
            // location.href = "#item";
            // window.scrollTo(0,0); //최상단으로 이동
            mkHidden([giftPage, strPage]);
            mkVisible(itemPage);
        })
    }

    pjForm.addEventListener('change',function(){
        if(!validCheck()){
            itmSaveBtn.disabled = true;
        } else itmSaveBtn.disabled = false;
    }) //todo 이 함수는.. 제대로 기능하는지 모르겠다. 나중에 고치든지 지우든지.


    itmName.addEventListener("input", function () {
        lengthCheck(this, 50, '아이템 이름');
    })
    itmName.addEventListener("keyup", makeBlur);

    // initBtn.addEventListener("click",function(){ //초기화 버튼
    //     itmName.value = "";
    //     itmName.parentElement.nextElementSibling.innerHTML = '<p>0/50</p>'
    //     const checked = document.querySelector("input[type=radio]:checked")
    //     checked.checked = false; //라디오버튼 체크 해제
    //
    //     const checkedDiv = document.querySelector("."+checked.id);
    //     checkedDiv.style.display = "none"; //해당 라디오버튼의 div 안보이게
    //
    //     const checkedTxt = checkedDiv.querySelector("textarea");
    //     checkedTxt.value = ""; //해당 textarea의 값 비우기
    //     // console.dir(txt);
    //
    //     optArr.length = 0; //배열도 초기화
    //     showList(mkOptList(optArr));
    // })

    //아이템 페이지의 초기화 버튼
    itmInitBtn.addEventListener("click", init);

    //아이템 페이지의 아이템 저장 버튼
    itmSaveBtn.addEventListener("click", function () {
        let validForm = itemValidCheck(); //const면..item_id와 pj_id를 추가할 수 없다.
        if(!validForm){
            alert("아이템 양식에 맞춰서 다시 작성해주세요")
            return;
        }

        fetch("/project/item",{
            method: "POST",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
            body: JSON.stringify(validForm)
        })
            .then( response => {
                if(!response.ok) {
                    throw response.text()
                }
                return response.json()
            })
            .then(data => {
                alert('아이템이 성공적으로 등록되었습니다.')
                init(); //입력창 초기화
                itmSaveBtn.disabled = true;

                //갱신된 아이템리스트를 가져와서 다시 뿌려주기
                const ItemArr = data
                const itemList = document.querySelector('#itemList')
                showList(mkItmList(ItemArr),itemList);

            })
            .catch(error => error).then(error => {
                alert("[등록 실패] " + error);
                console.log(error);
        })
            // .then(error => {
            //     alert('아이템 등록에 실패했습니다.')
            //     console.log(error)
            // })
        });

    //todo 여기 다시 체크해서 back에서 에러 메시지 잘 전송되는지 체크하기
            // error: function (result) {
            //     alert('아이템 등록에 실패했습니다.')
            //     // const parseResult = JSON.parse(result);
            //     // console.dir(parseResult);
            //     console.dir(result); //상태코드 400을 가지고 있는, errorResult를 포함한 어떤(?) 객체..
            //     console.dir(result.errorDetails); //이거는 undefined
            //     console.dir(result.responseJSON.errorDetails); //이렇게 접근해야 에러메시지를 읽을 수가 있었다...
            //     const errArr = result.responseJSON.errorDetails;
            //     let errorMessage = ''
            //     for(err of errArr){
            //         errorMessage += err.errorMessage+"\n";
            //     }
            //     // 왜 이렇게까지 꺼내야 하는걸까?
            //     alert(errorMessage); //각 필드에 에러메시지를 뜨게 하는게 목표인데, 일단은 alert까지라도.
            // }

    // });

    itmModBtn.addEventListener("click",function(){
        let validForm = itemValidCheck(); //const면..item_id와 pj_id를 추가할 수 없다.
        if(!validForm){
            alert("아이템 양식에 맞춰서 다시 작성해주세요")
            return;
        }

        validForm.item_id = this.getAttribute('data-item_id'); //버튼에 정보를 저장해둠.
        validForm.pj_id = this.getAttribute('data-pj_id');
        console.log('validForm')
        console.log(validForm);
        fetch("/project/item",{
            method: "PATCH",
            headers: {
                "content-type": "application/json",
                "accept": "application/json" //써줘야한다. 안써주면 에러남.
            },
            body: JSON.stringify(validForm)
        })
            .then( response => {
                if(!response.ok) {
                    throw response.text()
                }
                return response.json()
            })
            .then(data => {
                alert('아이템이 성공적으로 수정되었습니다.')
                init(); //입력창 초기화
                const tit = document.querySelector('div.item div.first > p.tit')
                tit.innerHTML = '아이템 등록하기';
                //수정된 리스트를 다시 뿌려주기
                const ItemArr = data
                const itemList = document.querySelector('#itemList')
                showList(mkItmList(ItemArr),itemList);

            })
            .catch(error=> error).then(error => console.log(error))
    })

    itmCnclBtn.addEventListener('click', function(){ //수정 취소 버튼
        // border 색 원상복귀
        const orange = document.querySelector('#itemList > div.orange');
        orange.classList.remove('orange');
        //입력창 초기화
        init();
        //버튼 초기화
        itmSaveBtn.style.display = 'block';
        itmModBtn.style.display = 'none';
        itmInitBtn.style.display = 'block';
        itmCnclBtn.style.display = 'none';
        const tit = document.querySelector('div.item div.first > p.tit')
        tit.innerHTML = '아이템 등록하기';
    })



    //모든 라디오input에 이벤트 걸기
    // 라디오 input 선택이 바뀌면
    //1. 모든 라디오 input들과 매칭되는 모든 div의 display여부와 이전 input들이 초기화된다. (일괄 적용)
    //2. checekd된 라디오 input(딱 하나)에 대하여 매칭되는 div를 display block하고, 해당 textarea에 글자수 체크 이벤트 걸기
    for (elem of radioElems) { //change말고 click으로 바꿔봄
        elem.addEventListener("click", function () { //라디오 선택이 달라지면 해당되는 이전 입력들이 초기화 된다.
            //console.dir(radioElems);
            //this.previousElementSibling.style.border = '.5px solid #ff5757' //label border 컬러 변경. 색이 유지가 안되네....css로 처리완.
            const inputs = document.querySelectorAll(".radio"); //매칭된 div 요소들을 가져와서
            //const inputs = document.querySelectorAll("."+elem.id); // 이렇게 하면 안됨.
            inputs.forEach(input => { //div요소 각각에 대해
                //input.style.display = 'block';
                const textarea = input.querySelector("textarea"); //자식 textarea를 가져와서
                // if(textarea !== null){ // textarea요소가 있으면(즉, 옵션없음이 아니면)
                textarea.value = "";
                textarea.parentElement.nextElementSibling.innerHTML = '' //초기화 작업
                //
                // textarea.addEventListener("input", function() {
                //     lengthCheck(this, 100, "옵션");
                // })
                optArr.length = 0; //배열도 초기화
                const multiResult = document.querySelector("#multiResult");
                showList(mkOptList(optArr), multiResult);
                input.style.display = "none";
            }); //라디오 input과 매칭되는 textarea를 모두 값을 지우고 보이지 않게 한다.
                // 라디오를 여러번 누를 경우를 대비해서.
                // alert("."+this.id);


            const input = document.querySelector("." + this.id); //선택된 요소에 대해서 적용
            if (input !== null) {//'옵션없음'에 해당하지 않으면 (즉, 매칭되는 div요소가 존재)
                input.style.display = "block";
                const textarea = input.querySelector("textarea");
                textarea.addEventListener("input", function () {
                    lengthCheck(this, 100, "옵션");
                    // optArr.push(textarea.value.trim());
                    // alert(optArr);
                    // console.dir(optArr);
                })
            }
        })
        //여기 공통 코드 묶어서 정리하기
        //  if(elem.id==="singleOpt"){ //
        //      const txt = document.querySelector("."+elem.id);
        //      const textarea = txt.querySelector("textarea");
        //      optArr.push(textarea.value.trim());
        //      console.log(optArr);
        //  }
        if(elem.id === "singleOpt"){
            const txt = document.querySelector("." + elem.id);
            const textarea = txt.querySelector("textarea");
            textarea.addEventListener('change', function(){
                if(textarea.value.trim()!==''){
                    optArr.push(textarea.value.trim());
                    optArr.splice(0,optArr.length-1); //항상 배열의 길이가 1이되도록 유지.
                    if(itmName.value.trim()!==''){
                        itmSaveBtn.disabled = false; //아이템 이름까지 입력한 상태여야 저장버튼 활성화됨.
                    }
                }
            })


        }
        if (elem.id === "multiOpt") {
            const txt = document.querySelector("." + elem.id);
            const textarea = txt.querySelector("textarea");
            textarea.addEventListener("keyup", function () {
                // optArr.push(textarea.value);
                // mkOptList(optArr);
                // const resultList = document.querySelector("#multiResult>div");
                // showList(mkOptList(optArr),resultList);
                // alert(this);
                if(window.event.keyCode === 188) { //쉼표를 구분자로 자를 예정이라 쉼표는 들어갈 수 없게 한다.
                    alert('콤마(,)는 입력하실 수 없습니다.')
                    this.value = this.value.substring(0,this.value.length-1);
                    return; //todo 나중에 서버로 값을 넘기기 전에도 쉼표가 있는지 다시 한번 체크해야할듯.
                }
                enterEvent(this, optArr);
            })
        }
    }//아이템 페이지에 거는 이벤트들




    //선물 페이지에 거는 이벤트들
    dropdown.addEventListener("click", function () {
        this.classList.toggle('border');

        //todo reward.jsp 처음 로딩되었을 때, item버튼을 누르지 않으면, pj_id를 읽어올 수 없는 상황이다.
        // pj_id를 읽어오는 방법을 바꿔야함***
        //const pj_id = document.querySelector('#itemList').querySelector('div').getAttribute('data-pj_id');
        const pj_id = "pj1"
        const itmDropdown = document.querySelector('#itmDropdown');
        const div = itmDropdown.querySelector('div');
        console.log('div');
        console.log(div);

        //선택버튼이 눌렸으면 체크상태가 유지된 채로 dropdown이 적용되고
        if (div !== null) { //div는 만들어진 상태인데,
            if (itmDropdown.classList.contains('optChecked')) { //선택완료 버튼이 눌린 상태이고,
                if (div.style.display === 'none') {
                    const checkedElems = document.querySelectorAll('input[type=checkbox]:checked');
                    console.dir(checkedElems);

                    $.ajax({
                        type: 'GET',
                        url: '/project/item/' + pj_id,
                        headers: {"content-type": "application/json"},
                        success: function (result) {
                            // alert('성공');
                            //console.dir(result) 가져온 리스트를 확인한다.
                            const arr = result;
                            // const list = mkItmDrop(result) //List인 result를 바로 넣으면 작동 안함.
                            // Java의 List는 어떻게 JS의 배열로 받아지는걸까?
                            const list = mkItmDrop(arr);
                            console.dir(list);
                            showList(list, itmDropdown); //드롭다운으로 선택한 아이템 보여주기
                            const checkboxes = document.querySelectorAll('input[type=checkbox]');
                            console.dir(checkboxes);
                            for (checkbox of checkboxes) { //원래 체크 되어 있던 요소들을 다시 체크 해주기
                                for (elem of checkedElems) {
                                    if (checkbox.getAttribute('data-item_id') === elem.getAttribute('data-item_id')) {
                                        checkbox.checked = true;
                                    }
                                }
                            }
                            // div.classList.add('optChecked');
                            document.querySelector('.footer').querySelector('p').innerHTML = checkedElems.length + '개의 아이템 선택';
                            //이걸 안붙이면 반영이 안되더라......
                        },
                        error: function (result) {
                            alert('실패')
                        }
                    })
                } else {
                    div.style.display = 'none';
                }
            } else { // div는 있고 선택완료버튼은 누른적 없으면
                div.remove();
            }
        } else { //div자체가 없으면-
            $.ajax({
                type: 'GET',
                url: '/project/item/' + pj_id,
                headers: {"content-type": "application/json"},
                success: function (result) {
                    // alert('성공');
                    //console.dir(result) 가져온 리스트를 확인한다.
                    const arr = result;
                    // const list = mkItmDrop(result) //List인 result를 바로 넣으면 작동 안함.
                    // Java의 List는 어떻게 JS의 배열로 받아지는걸까............?
                    const list = mkItmDrop(arr);
                    console.dir(list);
                    showList(list, itmDropdown); //드롭다운으로 선택한 아이템 보여주기
                },
                error: function (result) {
                    alert('실패')
                }
            })
        }
    })
    giftName.addEventListener("input",function(){
        lengthCheck(this, 50, '선물 이름');
    })
    giftName.addEventListener("keyup", makeBlur);


    for(radio of radioBtns){
        radio.parentElement.addEventListener('click',function(){
            this.querySelector('input[type=radio]').checked = true;
            this.style.border = '1px solid #f86453';  //클릭된 div는 테두리 색을 바꾸고
            this.parentElement.querySelector('input[type=radio]:not(:checked)').parentElement.style.border = '.5px solid #ececec';
            const span = this.querySelector('span');
            console.log(span);
            if(span){ //'있음' div의 경우
                console.dir(span);
                span.style.visibility='visible'; //따옴표 안쓰면 안먹힘 ^^
            } else { //'없음'
                // console.dir(this);
                // console.dir(this.parentElement)
                // console.dir(this.parentElement.querySelector('span'));
                // const span = this.parentElement.querySelector('span');
                this.parentElement.querySelector('input.maxInput').value="";
                this.parentElement.querySelector('span').style.visibility = 'hidden';
                // console.dir(this.parentElement.querySelector('p.notice'));
                this.closest('div.limRadio').nextElementSibling.style.display='none';
            }
        })
    }

    // for(radio of radioBtns){
    //     radio.parentElement.addEventListener('click',function(){
    //         this.style.border = '1px solid #f86453;';
    //         const span = this.querySelector('span');
    //         console.log(span);
    //         if(span){ //'있음' div의 경우
    //             console.dir(span);
    //             span.style.visibility='visible'; //따옴표 안쓰면 안먹힘 ^^
    //         } else { //'없음'
    //             // console.dir(this);
    //             // console.dir(this.parentElement)
    //             // console.dir(this.parentElement.querySelector('span'));
    //             // const span = this.parentElement.querySelector('span');
    //             this.parentElement.querySelector('input.maxInput').value="";
    //             this.parentElement.querySelector('span').style.visibility = 'hidden';
    //             // console.dir(this.parentElement.querySelector('p.notice'));
    //             this.parentElement.querySelector('p.notice').style.display='none';
    //         }
    //     })
    // }

    //선물 저장버튼 누르기
    giftSaveBtn.addEventListener("click", function(){
        //입력 필드값에 대한 유효성 검사
        //giftValidCheck()는 유효성을 통과하면 form객체를 반환 / 유효성을 통과하지 않으면 false반환.
        const validForm = giftValidCheck(); //
        //const validForm = false; 테스트용
        if(!validForm) {
            alert("입력값이 유효하지 않습니다. 양식에 맞게 제출해주세요.") //프론트단에서의 validation
            return; //이때는 user가 입력한 값을 보존하기 위해 form 초기화 함수 호출x
        }
        //유효성 검사를 통과한 경우에만
        //fetch를 이용해서 비동기 요청으로 JSON.stringify로 데이터 보내기
        fetch("/project/gift", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"}, //써줘야한다. 안써주면 에러남.
            body: JSON.stringify(validForm), //giftForm에 해당하는 요소들을 서버에 보내기
        })
            .then((response) => {
                if(!response.ok){
                    throw response.text();
                    //text도 promise를 반환한다. 서버에서 보낸 string은 왜 json으로 변환하지 못할까.
                    //throw error만 가능한게 아니구나. throw 키워드 자체가 catch블럭으로 연결시키는 듯.
                }
                return response.json() //200번 응답코드일때만.
            })
            .then((data) => {
                alert("선물이 성공적으로 등록되었습니다.");
                //console.log("here check")
                //console.log(data);
                const giftArr = data //서버로부터 giftList를 받아옴
                const giftList = document.querySelector('#giftList')
                //선물리스트 data를 가지고 html태그를 만드는 함수 호출해서 화면에 뿌리기
                showList(mkGiftList(giftArr),giftList);

                //다음 입력을 위해 입력 필드 초기화 함수 호출
                giftInit();
            })
            .catch(error => error).then(error => {
                alert("[등록 실패] "+error);
                console.log(error);
        })
            // 중복된 선물 이름을 입력한 경우에도, 다른 입력값을 보존하기 위해 입력 필드 초기화 함수는 호출하지 않는다.
    })

    //선물페이지의 초기화 버튼 - 모든 입력필드 초기화.
    giftInitBtn.addEventListener("click",giftInit);
    giftCnclBtn.addEventListener("click",function(){
        // border 색 원상복귀
        const orange = document.querySelector('#giftList > div.orange');
        orange.classList.remove('orange');
        //입력창 초기화
        giftInit();
        //버튼 초기화
        giftSaveBtn.style.display = 'block';
        giftModBtn.style.display = 'none';
        giftInitBtn.style.display = 'block';
        giftCnclBtn.style.display = 'none';
        const tit = document.querySelector('div.gift div.first > p.tit')
        tit.innerHTML = '선물 등록하기';
    })

    //선물페이지의 선물 수정버튼
    giftModBtn.addEventListener('click',function (){
        let validForm = giftValidCheck(); //const면..item_id와 pj_id를 추가할 수 없다.
        if(!validForm){
            alert("선물 양식에 맞춰서 다시 작성해주세요")
            return;
        }

        validForm.gift_id = this.getAttribute('data-gift_id'); //버튼에 정보를 저장해둠.
        validForm.pj_id = this.getAttribute('data-pj_id');
        console.log('validForm')
        console.log(validForm);
        fetch("/project/gift",{
            method: "PATCH",
            headers: {
                "content-type": "application/json",
                "accept": "application/json" //써줘야한다. 안써주면 에러남.
            },
            body: JSON.stringify(validForm)
        })
            .then( response => {
                if(!response.ok) {
                    throw response.text()
                }
                return response.json()
            })
            .then(data => {
                alert('선물이 성공적으로 수정되었습니다.')
                giftInit(); //입력창 초기화
                const tit = document.querySelector('div.gift div.first > p.tit')
                tit.innerHTML = '선물 등록하기';
                //수정된 리스트를 다시 뿌려주기
                const giftArr = data
                const giftList = document.querySelector('#giftList')
                showList(mkGiftList(giftArr),giftList);

            })
            .catch(error=> error).then(error => console.log(error))
    })


}// window.onload



const fetchItemCnt = function(pj_id){
    return fetch("/project/itemCnt/"+pj_id,{
        method: "GET",
    })
        .then(response => response.json())
} // 서버로부터 현재 해당 프로젝트에 등록된 아이템의 수를 불러오는 함수

const fetchItems = function(pj_id){
    return fetch("/project/item/"+pj_id,{
        method: "GET",
    })
        .then(response => response.json())
} // 현재 프로젝트에 등록된 모든 아이템을 불러오기

const loadItemList = function(pj_id) {
    fetch("/project/item/"+pj_id, {
        method: "GET",
        headers: {
            "accept": "application/json"
        }
    })
        .then(response => {
            if(!response.ok){
                throw response.text()
            }
            return response.json()
        })
        .then(data => {
            const ItemArr = data
            const itemList = document.querySelector('#itemList')
            showList(mkItmList(ItemArr),itemList);
        })
        .catch(error => error)
}

const loadGiftList = function(pj_id) {
    //alert("loadGiftList called.");

    fetch("/project/gift?pj_id="+pj_id, {
        method: "GET",
        headers: {
            "accept": "application/json"
        }
    })
    .then(response => {
        if(!response.ok){
            throw response.text()
        }
        return response.json()
    })
    .then(data => {
        console.log(data)
        const giftArr = data
        const giftList = document.querySelector('#giftList')
        showList(mkGiftList(giftArr),giftList);
    })
    .catch(error => error)
} //todo : loadItemList와 loadGiftList는 로직이 같다. 하나의 함수로 만드는 것을 시도해보기

async function loadPage(pj_id){

    const cnt = await fetchItemCnt(pj_id); //서버로부터 해당 프로젝트에 등록된 아이템 수를 가져온다.
    console.log("cnt here")
    //console.log(cnt);
    //console.log(typeof cnt); number

    //등록된 아이템의 유무에 따라 보여줄 페이지(선물-아이템 시작페이지 / 선물 등록페이지) 나뉨
    if (cnt==0) { //등록된 아이템이 없으면,
        mkHidden([giftPage]); //선물-아이템 시작페이지(strPage)가 뜨도록 giftPage는 숨김처리
    } else { //등록된 아이템이 있으면
        //alert("cnt!==0")
        mkHidden([strPage]); //시작페이지를 감추고,
        loadGiftList(pj_id) //서버로부터 등록된 "선물"리스트를 가져와서 뿌려주기
    }
}

const colorChange = function(activeBtn, inactiveBtn){
    //활성화된 버튼
    activeBtn.querySelector('i').style.color = '#f86453';
    activeBtn.querySelector('span').style.color = '#3d3d3d';
    activeBtn.querySelector('span').style.fontWeight = '700';
    //비활성화된 버튼
    inactiveBtn.querySelector('i').style.color = '#c4c4c4';
    inactiveBtn.querySelector('span').style.color = '#c4c4c4';
    inactiveBtn.querySelector('span').style.fontWeight = '600';
}

const tglHidden = function (elements) {
    elements.forEach(element => {
        element.classList.toggle("hidden");
    })
}

//header.js로 옮김//
const mkHidden = function (elements) {
    elements.forEach(element => {
        if (element != null)
            element.style.display = "none";
    })
}
const mkVisible = function (element) {
    if (element != null)
        element.style.display = "flex";
}

const lengthCheck = function (elem, maxLength, string) {
    elem.maxLength = maxLength;
    // elem.trim().maxLength = maxLength;
    const len = elem.parentElement.nextElementSibling
    // const len = elem.nextElementSibling;
    // alert(len);
    elem.parentElement.style.border = ".5px solid #ff5757";
    len.style.color = '#ff5757';
    len.style.fontSize = '12px';
    // alert(elem.value)
    if (elem.value.trim().length === 0) {
        elem.focus();
        len.innerHTML = '<p class="notice">' + string + '을 입력해주세요.<p>'
        len.style.color = "#ff5757"
    } else if (elem.value.trim().length > maxLength) {
        elem.focus();
        len.innerHTML = '<p class="notice">' + string + '은 ' + maxLength + '자 이내여야 합니다.<p>'

    } else {
        elem.parentElement.style.border = ".5px solid black";
        len.innerHTML = '<p>' + elem.value.trim().length + '/' + maxLength + '</p>';
        len.style.color = "#9e9e9e";
        len.style.fontSize = '12px';
    }
}
const makeBlur = function () {
    if (window.event.keyCode === 13) {
        // window.event.preventDefault();
        this.blur();
        this.parentElement.style.border = ".5px solid #f1f1f1";
    }
}

const enterEvent = function (elem, optArr) {
    if (window.event.keyCode === 13) {
        //window.event.returnValue=false; //?
        // window.event.preventDefault();
        if (elem.value.trim() === "") {
            alert('옵션을 입력해주세요.')
            elem.value = "";
            return;
        }
        optArr.push(elem.value.trim());
        const list = mkOptList(optArr);
        const multiResult = document.querySelector("#multiResult");
        showList(list, multiResult);

        elem.value = "";
        elem.parentElement.nextElementSibling.innerHTML = '<p>0/100</p>'
        if(optArr.length>=2 && itmName.value.trim()!==''){ //객관식 옵션을 두개 이상 입력했고, 아이템 이름도 입력했으면-
            itmSaveBtn.disabled = false;
        }

    }
}

//엔터키를 누르면, optArr에 아이템을 추가하고 아이템 리스트를 보여주는 함수를 호출
const mkOptList = function (optArr) {
    let list='';
    for (opt of optArr) {
        list += '<div onclick=removeOpt(optArr,this)>'
        list += '<span>' + opt + '</span>'
        list += '<i class="fas fa-solid fa-xmark"></i>'
        list += '</div>'
    }
    return list;
}
const mkItmList = function (itmArr) {
    let list = ''
    for (itm of itmArr) { //필요없는 data- attribute들은 나중에 정리하자.
        list += '<div class="modi" style="cursor:pointer" onclick="modifyItem(event,this);" data-item_id=' + itm.item_id + ' data-pj_id=' + itm.pj_id + '>'
        //list += '<input type="hidden" value='+itm.item_id+'>' //item_id를 hidden으로 가져온다.
        //list += '<input type="hidden" value='+itm.pj_id+'>' //hidden으로 넣지 말고 data- attribute에 넣을까..? 굳이 input태그를 하나 더 쓰는게 맞을까?
        list += '<div class="itmTit" style="border:none;">'
        list += '<p style="font-weight: 600" >'
        list += itm.item_name + '</p>'
        list += '<div class="trash"><i class="far fa-regular fa-trash-can trash" onclick=removeItm(this) data-item_id=' + itm.item_id + ' data-pj_id=' + itm.pj_id + '></i></div>'
        list += '</div>'
        list += '<p class="itmT">' + itm.item_option_type + '</p>'
        list += '<ul class="itmL">'
        if (itm.item_option != null) { //옵션없음이 아닌 경우(객관식, 주관식 옵션)
            if(itm.item_option_type === '객관식 옵션'){
                const opts = toArray(itm.item_option); //todo toArray함수 수정하기
                for (opt of opts) {
                    list += '<li>' + opt + '</li>'
                }
            } else { //주관식 옵션일 경우
                list += '<li>' + itm.item_option + '</li>'
            }
        }
        list += '</ul>'
        list += '</div>'
    }


    return list;
}

const mkItmDrop = function (arr) {
    let list = '<div id="drop">'
    list += '<ul>'
    for (itm of arr) {
        list += '<li>'
        list += '<input type="checkbox" class="checkedItem" onchange="changeFoot()" data-item_id=' + itm.item_id + '>'
        list += '<div>'
        list += '<span>' + itm.item_name + ' (' + itm.item_option_type + ') </span>'
        list += '<em>0개의 선물에 포함됨</em>'
        list += '</div>'
        list += '</li>'
    }
    list += '</ul>'
    list += '<div class="footer">'
    list += '<p>0개의 아이템 선택</p>'
    list += '<button id="selected" type="button" onclick="selectItem(this)"><p>선택완료</p></button>'
    list += '</div>'
    list += '</div>'
    return list;
}

const mkCheckedItm = function(arr) { //체크된 아이템들을 아래에 출력
    // let list = '<div style="display:flex;flex-direction: column">'
    let list = '<div>'
    for(itm of arr){
        // list += '<div style="display:flex;flex-direction: row; align-items: center">'
        list += '<div class="giftItem">'
        list += '<div class="left">' //이 div는 왼쪽에 두고, 오른쪽에는 수량 조절기능을 넣음
        list += '<ul>'
        // list += '<li style="display:flex;flex-direction:column">'
        list += '<li>'
        list += '<p>'+itm.item_name+'</p>'
        list += '<p>'+itm.item_option_type+'</p>'
        if(itm.item_option!=null) {
            list += '<ul>'
            if(itm.item_option_type === '객관식 옵션'){
                let temp = toArray(itm.item_option);

                for(opt of temp){
                    list += '<li class="opt">' + opt + '</li>'
                }
            } else { //주관식 옵션일 경우
                list += '<li class="opt">' + itm.item_option + '</li>'
            }
            list += '</ul>'
        }
        list += '</li>'
        list += '</ul>'
        list += '</div>' //left close
        list += '<div class="right">'
        list += '<div class="qty" style="display:inline-block">'
        list += '<button type="button" class="minus" onclick="minus(this)" disabled><i class="fas fa-regular fa-minus"></i></button>'
        list += '<input data-item_id = ' + itm.item_id + ' class="itmNum" type="number" value="1" onkeyup="numCheck(this)">'
        list += '<button type="button" class="plus" onclick="plus(this)"><i class="fas fa-regular fa-plus"></i></button>'
        list += '</div>' //div close
        list += '<button class="cancel" type="button" onclick="removeBtn(this)" data-item_id='+itm.item_id+'>삭제</button>'
        list += '</div>' //right close
        list += '</div>'
    }
    list += '</div>'

    return list;
}

const mkGiftList = function (giftArr) { //내가 만든 선물 리스트
    let list = "<div><strong>1,000원+</strong><span>선물 없이 후원하기</span></div>"
    for (gift of giftArr) {
        //console.log('here')
        //console.log(gift)
        list += '<div class="modi" style="cursor:pointer" onclick="modifyGift(event,this)" data-gift_id=' + gift.gift_id + ' data-pj_id=' + gift.pj_id + '>'
        list += '<div class="giftTit" style="border:none;">'
        list += '<strong>'
        list += comma(gift.gift_money) + '원+</strong>'
        list += '<div><i class="far fa-regular fa-trash-can" onclick="removeGift(this)" data-gift_id=' + gift.gift_id + ' data-pj_id=' + gift.pj_id + '></i></div>'
        list += '</div>' //
        list += '<p class="giftT">' + gift.gift_name + '</p>'
        list += '<ul class="giftL">'
        for(let i=0; i<gift.item_id.length; i++){
            list += '<li>' + gift.item_name[i] + 'x' + gift.item_qty[i] + '</li>'
        }
        list += '</ul>'
        const shipDate = new Date(gift.gift_ship_due_date);
        list += '<span>예상전달일 : '+'<em>'+ shipDate.getFullYear()+'년 '+ (shipDate.getMonth()+1)+'월 '+shipDate.getDate()+'일</em></span>'
        list += '<div class="check">'
        list += '<div class="left"><i class="fas fa-solid fa-check"></i>'
        list += gift.gift_sold_qty+' 개 선택</div>'
        const currQty = gift.gift_curr_qty;
        if(currQty!==null){
            list += '<div class="right">'+currQty+'개 남음</div>'
        }
        list += '</div>'
        list += '</div>'
    }
    return list;
}



const minus = function(elem){ //클릭했을 때
    const itmNum = elem.nextElementSibling;
    const value = parseInt(itmNum.value); //타입이 String이라서 int로 바꿔줌..
    //console.log(typeof(itmNum.value)); //타입이 String이다;;;;;
    //console.log(typeof(value));
    if(value===2){ //값이 2이면
        elem.disabled = true;
        itmNum.value--;
        //버튼 비활성화가 안된다.
    }
    else if(value<2) { //값이 1 이하라면 (혹시나 값을 input에 직접 입력하는 경우때문에 넣음)
        elem.disabled = true; //값을 줄이지 못하도록 버튼을 비활성화
    }
    else { //3이상의 값이면
        elem.disabled = false;
        itmNum.value--;
    }
}
const plus = function(elem){
    const itmNum = elem.previousElementSibling;
    const minus = itmNum.previousElementSibling;
    if(itmNum.value++>=1){ //+를 누르면 무조건 값이 증가하니까 -버튼의 비활성이 풀린다.
        minus.disabled = false;
    }
}

const validNum = function(elem){
    //key이벤트를 다루려면 input태그여도 oninput이 아닌 onkeyup과 같은 키보드 이벤트를 걸어야 한다.
    const key = window.event.keyCode;
    // alert(key);
    // console.dir(typeof key)
    if(key===13){
        elem.blur();
        return;
    }
    if(!((48<=key && key<=57) || key===8 || key ===37 || key === 39)) { //숫자키 또는 백스페이스 또는 오른 왼 방향키가 아닌 키를 누르면
        //event.returnValue= false;
        //type이 number라서 e는 눌린다..;;; 여기서 조건으로 차단을 하긴 하지만 아예 입력 자체가 안되게 하긴 어렵나보다.
        alert('1이상의 정수로만 입력해주세요.');
        // console.dir(elem);
        elem.value='';
        return;
    }
    if(elem.value.trim()===''){
        alert('1이상 숫자를 입력하세요');
        // elem.value = 1;
    }
}

const validRNum = function(elem,max){ //선물 선착순 수량, 인당 수량, 선물 금액 유효성 검사에 모두 쓰는 함수
    validNum(elem);
    // console.log(elem);
    const num = parseInt(uncomma(elem.value)); //금액때문에 uncomma를 한 값을 쓴다.
    const limValue = document.querySelector('#maxLimVal').value;
    const lim = document.querySelector('input[name=limit]:checked');
    console.log(lim);
    const notice = elem.closest('section').querySelector('p.notice');
    console.log(notice);

    if(num<1){
        notice.innerText = '1 이상을 입력하세요'
        notice.style.display = 'block'
        elem.focus();
        return;
    }

    console.log(elem.closest('section').className);
    if(elem.closest('section').className==='giftNum'){
        console.log(elem===lim)
        if(lim.id==='lim') { //선착순 선물 수량이 정해져 있는 경우,
            console.log(num);
            console.log(limValue);
            if(num>limValue) {
                notice.innerText = '선물 수량을 초과할 수 없습니다'
                notice.style.display = 'block';
                return;
            } else {
                notice.style.display = 'none';
            }
        }
    }

    notice.innerText = comma(max) + ' 이하로 입력하세요';
    if(num>max){
        notice.style.display = 'block'
    } else {
        notice.style.display = 'none'
    }




}

    // // if(elem.closest('div').querySelector('input')){ //이렇게 조건을 안주면 이 요소가 없는 경우 에러가 나서 다음 코드가 안먹힘 ㅠㅠ
    //     if(elem.closest('div').querySelector('input').id==='maxLim'){
    //         if(num>limValue) {
    //             //alert('선물 수량을 초과할 수 없습니다.')
    //             alert("check");
    //             console.log(notice);
    //             notice.innerText = '선물 수량을 초과할 수 없습니다'
    //             notice.style.display = 'block';
    //             //elem.value='';
    //         } else {
    //             notice.style.display = 'none';
    //         }
    //         return;
    //     }
    // // } //선물이 한정수량일 경우(앞의 라디어 버튼) 선물 수량을 초과해서 입력할 수 없도록 경고.
    //
    // if(num>max){
    //     // elem.parentElement.parentElement.querySelector('p.notice').style.display='block';
    //     elem.closest('div.check').nextElementSibling.style.display='block';
    // } else {
    //     elem.closest('div.check').nextElementSibling.style.display='none';
    // }

const numCheck = function(elem){
    // const key = window.event.keyCode;
    // // alert(key);
    // // console.dir(typeof key)
    // if(key===13){
    //     elem.blur();
    //     return;
    // }
    // if(!(48<=key && key<=57 || key===8 || key ===37 || key === 39)) { //숫자키 또는 백스페이스 또는 오른 왼 방향키가 아닌 키를 누르면
    //     alert('1이상의 정수로만 입력해주세요.');
    //     elem.value=1;
    //     return;
    // }
    validNum(elem);
    const num = parseInt(elem.value);
    const minus = elem.previousElementSibling;
    //일단은 -버튼을 비활성화 시켜야 한다. input에 focus가 가면 비활성화가 풀리면서 버튼을 눌러서 마이너스값을 누를 수도 있음.
    minus.disabled = true;
    //alert(num);
    console.dir(num);
    if(num<=1){
        minus.disabled = true;
        if(num<1){
            alert('1이상의 수량을 입력하세요.');
            elem.value = 1;
        }
    }
    if(num>=2) {
        minus.disabled = false;
    }
}

//정규식 꼼꼼히 체크하고 이해하기
function inputNumberFormat(elem) {
    elem.value = comma(uncomma(elem.value));
}

function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}
const noOffset = function(date){
    const offset = date.getTimezoneOffset() * 60000;
    return new Date(date.getTime() - offset);
}

const calcDate = function(elem){
    const hidden = elem.parentElement.querySelector('input[type=hidden]')
    console.log(hidden);
    let from = new Date(hidden.value);
    console.log("before from")
    console.log(from)
    const shipDate = document.querySelector('#shipDate');
    if(elem.value > 1825){
        shipDate.querySelector('span').innerText = '예상전달일이 유효하지 않습니다.'
        return;
    }
    const tmp = from.getTime()+elem.value*24*60*60*1000;
    let to = new Date(tmp);
    console.log(to)
    // console.log("to")
    // console.log(typeof to)
    // console.log(to)

    //const to = new Date(temp);
    const dayNames = ['일','월','화','수','목','금','토']
    // console.dir(from);
    // console.dir(typeof to);
    // console.dir(to);
    // console.dir(new Date(to));
    // shipDate.querySelector('span').innerText = to.toLocaleDateString()+' ('+ dayNames[to.getDay()]+')'
    shipDate.querySelector('span').innerHTML = "<span id='shipDay'>"+ to.toLocaleDateString()+"</span><span>   ("+ dayNames[to.getDay()]+") </span>";
}

const validDays = function(elem,max){
    validNum(elem);
    const val = elem.value.trim();
    const cal = document.querySelector('div.cal');
    console.log(shipDate);
    // if (val < 1) {
    //     alert('1이상의 숫자를 입력해주세요');
    // }
    if(val > max){
        cal.parentElement.querySelector('p.notice').style.display = 'block';
    } else {
        cal.parentElement.querySelector('p.notice').style.display = 'none';
    }
}


const removeBtn = function(elem){
    const target = elem.parentElement.parentElement;
    console.dir(target);
    //1. checked도 해제해주어야한다.
    const checked = document.querySelectorAll('input[type=checkbox]:checked');
    for(check of checked){
        if(elem.getAttribute('data-item_id')===check.getAttribute('data-item_id')){
            check.checked = false;
        }
    }
    //2. 체크한 아이템을 리스트에서 삭제하는 메서드(DB에서 삭제하는게 아니라 그냥 태그의 삭제)
    target.remove();
}



const changeFoot = function () { //체크박스의 체크 상태에 따라 footer에 찍히는 문자열을 바꾸는 메서드.
    const footer = document.querySelector('.footer');
    const detail = footer.querySelector('p')
    const checked = document.querySelectorAll('input[type=checkbox]:checked')
    const length = checked.length;
    detail.innerHTML = '<b style="color:#3d3d3d">'+ length +'</b>' + '개의 아이템 선택';
}

const selectItem = function (elem) {
    const checkedElems = document.querySelectorAll('input[type=checkbox]:checked');
    const itmDropdown = document.querySelector('#itmDropdown');
    const itemIdArr = []; //checked된 item_id들을 담을 배열

    const div = elem.parentElement.parentElement;
    //console.log('here');
    //console.log(div);
    // div.classList.add('optChecked');
    //div.parentElement.classList.add('optChecked');
    itmDropdown.classList.add('optChecked');
    // 아이템 선택 드롭다운이 접힌다.
    div.style.display = 'none';
    //아래쪽에 선택한 아이템의 상세가 뜬다 + 수량 선택 버튼이 생김
    for(elem of checkedElems){
        itemIdArr.push(elem.getAttribute('data-item_id'));
    }
    let queryString = "";
    for(itemId of itemIdArr){
        queryString += 'item_id='+itemId+'&'
    }
    if(!queryString) return; //아무것도 선택하지 않으면 함수 종료

    const selectItm = document.querySelector('#selectItm');
    const detail = selectItm.querySelector('p')

    $.ajax({
        type: 'GET',
        url: '/project/items?'+queryString,
        // headers: {"content-type": "application/json"},
        // data: JSON.stringify({'item_id':item_id}),
        success: function (result) {
            alert('콘솔을 확인하세요.')
            console.dir(result);
            const arr = result;
            if(arr!=null){
                const list = mkCheckedItm(arr);
                console.dir(list);
                console.dir(selectItm);
                showList(list, selectItm);
            } else{
                detail.innerHTML = '';
            }
        },
        error: function (result) {
            alert('실패')
        }
    });
    //console.log(itemIdArr);
    ////아이템 목록 중 체크한 아이템 목록이 해당 선물에 속한 아이템이 됨.
    //선택완료 버튼을 누르면 드롭다운에도 checked가 계속 반영됨.
}


const toArray = function (string) {
    // let arr = [];//아이템 옵션을 담을 배열
    // if (!string.includes(',')) {
    //     arr.push(string); //주관식의 경우에는 단순히 문자열을 넣기만xxx 그건 의도한 결과인거지..
    // } else {
    //     arr = string.split(','); //객관식인 경우는 쉼표로 나누어서 넣기
    // }
    // return arr;
    return arr = string.split(',');

}

const showList = function (list, elem) {
    elem.innerHTML = list
    // console.dir(optArr);
}

//아이템 삭제 메서드
const removeItm = function (elem) {
    const div = elem.closest('div.modi')
    if(div.classList.contains("orange")){ //현재 수정상태라면,
        if(!confirm("아이템 수정을 취소하고 삭제하시겠습니까?")){
            return;
        }
    }
    if (!confirm("이 아이템을 삭제하시겠습니까? 삭제하면 해당 아이템이 포함된 *개의 선물에서도 삭제됩니다.")) return;
    //ajax로 컨트롤러를 통해 db에서 아이템 삭제 후 리스트를 다시 불러와서 보여줘야함.
    // const item_id = elem.querySelector("input[type=hidden]").value;
    const item_id = elem.getAttribute('data-item_id');
    alert(item_id);
    // alert(item_id);
    $.ajax({
        type: 'DELETE',
        url: '/project/item?item_id=' + item_id,
        headers: {"content-type": "application/json"},
        // data: JSON.stringify({'item_id':item_id}),
        success: function (result) {
            alert('아이템이 성공적으로 삭제되었습니다.');

            //elem.remove(); //아이템 목록에서 삭제 //이거 없어도 될듯. 어차피 새로운 리스트를 다시 가져올거니까.

            console.log("removeItm");
            console.dir(result);
            const itemArr = result; //Java List타입 객체를 JS 배열에 넣을 수 있나보다..
            console.dir(itemArr);
            const itemList = document.querySelector('#itemList');
            const list = mkItmList(itemArr);
            showList(list, itemList);
            init();
        },
        error: function (result) {
            alert('아이템 삭제에 실패했습니다.')
        }
    });
}


// todo 선물 리스트에서 선물을 삭제하는 함수(실제 DB에 delete호출해서 반영함)
//  1.DB에서 삭제 후, 다시 Gift List를 불러와서 보여줄것인가
//  2.아니면, DB에서 삭제 후 리스트를 새로 받아오진 않고, 응답코드가 200일때만 html요소만 삭제할것인가
//  뭐가 더 좋은 방법일까?

const removeGift = function(elem){
    //비동기 방식으로 서버에서 해당 gift_id에 해당하는 선물 지우기 (+아이템 디테일 리스트도 같이 삭제Tx)

    const div = elem.closest('div.modi')
    if(div.classList.contains("orange")){ //현재 수정상태라면,
        if(!confirm("선물 수정을 취소하고 삭제하시겠습니까?")){
            return;
        }
    }
    if(!confirm("선물을 삭제하시겠습니까?")) return;

    fetch("/project/gift?gift_id="+elem.getAttribute("data-gift_id")+"&pj_id="+elem.getAttribute("data-pj_id"), {
        method: "DELETE",
        headers: {
            "content-type": "application/json",
            "accept": "application/json"}, //써줘야한다. 안써주면 에러남.
        // body: elem.getAttribute("data-gift_id"), //giftForm에 해당하는 요소들을 서버에 보내기

    })
        .then((response) => {
            if(!response.ok){
                throw response.text();
                //text도 promise를 반환한다. 서버에서 보낸 string은 왜 json으로 변환하지 못할까.
                //throw error만 가능한게 아니구나. throw 키워드 자체가 catch블럭으로 연결시키는 듯.
            }
            return response.json() //200번 응답코드일때만.
        })
        .then((data) => {
            alert("선물이 성공적으로 삭제되었습니다.");
            //console.log("here check")
            //console.log(data);
            const giftArr = data //서버로부터 giftList를 받아옴
            const giftList = document.querySelector('#giftList')
            //선물리스트 data를 가지고 html태그를 만드는 함수 호출해서 화면에 뿌리기
            showList(mkGiftList(giftArr),giftList);
            giftInit();

        })
        .catch(error => error).then(error => {
        alert(error);
        console.log(error);
    })
    // 중복된 선물 이름을 입력한 경우에도, 다른 입력값을 보존하기 위해 입력 필드 초기화 함수는 호출하지 않는다.
    //해당 태그를 지우기
    //elem.remove();

}

//아이템 - 옵션 삭제 메서드
const removeOpt = function (arr, elem) { //옵션이 담기거나, 아이템이 담긴 배열
    if (!confirm('옵션을 삭제하시겠습니까?')) return;
    //배열에서 삭제
    const opt = elem.querySelector('span').innerHTML;
    const index = arr.indexOf(opt);
    alert("index=" + index);
    arr.splice(index, 1);
    //요소 삭제
    elem.remove();
}

const modifyItem = async function(event, elem){
    if(event.target.classList.contains('trash')) return; //삭제버튼이면 이벤트 걸리지 않게.

    //if(!confirm('아이템을 수정하시겠습니까?')) return;

    //1. 해당 div border 색 변경
    const divs = document.querySelectorAll('#itemList > div')
    for(div of divs){
        if(div === elem){ //해당 버튼만 색이 바뀌고
            div.classList.add("orange");
            console.log(div.classList)
            //div.style.border = '.5px solid #F86453';
        } else { //나머지는 원래 색으로
            div.classList.remove("orange");
            //div.style.border = '.5px solid #ececec';
        }
    }


    //2. 제목과 버튼 수정 (만들기 -> 수정하기 / 저장하기 -> 수정하기)
    const tit = document.querySelector('div.item div.first > p.tit')

    //console.log('here')
    //console.log(tit);
    tit.innerHTML = '아이템 수정하기';

    itmModBtn.style.display = 'block'; //수정버튼을 block
    itmSaveBtn.style.display = 'none'; //저장버튼은 none
    itmCnclBtn.style.display = 'block'; //수정취소버튼을 block
    itmInitBtn.style.display = 'none'; //초기화버튼을 none



    //3. 입력 필드에 해당 값들을 뿌려준다.
    //서버를 한번 들렀다오는게 과연 현명한 일일까?
    //그냥 innerText를 바로 입력 필드에 옮길 수도 있을텐데.. 뭔가 귀찮다.
    const itmName = document.querySelector('#itmName');
    const optTypes = document.querySelectorAll('input[name=optType]');
    const item = await (function (item_id){
        return fetch("/project/item/select/"+item_id,{
            method: "GET",
            headers: {
                "accept": "application/json"
            }
        }).then(response => response.json())
    })(elem.getAttribute("data-item_id")) //아이템id로 서버에서 해당 아이템 조회

    //console.log(item);

    //3-1.mod버튼에 item_id를 넣어준다. 수정버튼이 일체형(?)이 아니라 나뉘어 있으므로.
    // (나중에 수정버튼 눌렀을 때 item_id를 전달해주어야 하므로)
    itmModBtn.setAttribute("data-item_id",item.item_id)
    itmModBtn.setAttribute("data-pj_id",item.pj_id)

    //3-2.아이템 이름
    //itmName.input(); input은 click(),blur(),focus() 처럼 메서드가 있진 않은듯.
    itmName.value = item.item_name;
    lengthCheck(itmName,50,'아이템');

    //3-3.아이템 옵션 타입(객관식, 주관식, 옵션없음)
    for(optType of optTypes) {
        if(optType.value === item.item_option_type){
            optType.click(); //클릭이벤트를 발생시킨다.
        }
    }
    //3-4.아이템 옵션 상세
    if(item.item_option_type === '객관식 옵션'){ //객관식 옵션의 경우
        console.log(optArr);
        const multiResult = document.querySelector('#multiResult');
        const options = item.item_option.split(',');
        for(option of options){
            optArr.push(option)
        }
        console.log(optArr);
        showList(mkOptList(optArr), multiResult);
    }

    if(item.item_option_type === '주관식 옵션'){
        const textarea = document.querySelector('div.singleOpt > div.txtCont > textarea.input');
        console.log("check");
        console.log(textarea);
        textarea.value = item.item_option;
    }
}

const modifyGift = async function(event, elem){
    if(event.target.classList.contains('trash')) return; //삭제버튼이면 이벤트 걸리지 않게.

    //1. 해당 div border 색 변경
    const divs = document.querySelectorAll('#giftList > div')
    console.log(divs);
    for(div of divs){
        if(div === elem){ //해당 버튼만 색이 바뀌고
            div.classList.add("orange");
            console.log(div.classList)
        } else { //나머지는 원래 색으로
            div.classList.remove("orange");
        }
    }

    //2. 제목과 버튼 수정 (만들기 -> 수정하기 / 저장하기 -> 수정하기)
    const tit = document.querySelector('div.gift div.first > p.tit')
    const shipDate = document.querySelector('#shipDate')
    const days = document.querySelector('#shipCalc');
    const payDay = document.querySelector('#payDay').value;
    const giftMoney = document.querySelector('#giftMoney');
    const limits = document.querySelectorAll('input[name=limit]')
    const maxLimits = document.querySelectorAll('input[name=maxLimit]')

    tit.innerHTML = '선물 수정하기';

    giftModBtn.style.display = 'block'; //수정버튼을 block
    giftSaveBtn.style.display = 'none'; //저장버튼은 none
    giftCnclBtn.style.display = 'block'; //수정취소버튼을 block
    giftInitBtn.style.display = 'none'; //초기화버튼을 none

    //3. 입력필드에 해당 값들을 뿌려준다.
    const giftName = document.querySelector('#giftName');
    const gift = await (function (gift_id){
        return fetch("/project/gift/select/"+gift_id,{
            method: "GET",
            headers: {
                "accept": "application/json"
            }
        }).then(response => response.json())
    })(elem.getAttribute("data-gift_id")) //선물id로 서버에서 해당 아이템 조회
    //console.log("gift selected")
    //console.log(gift);

    //3-1.mod버튼에 gift_id를 넣어준다. 수정버튼이 일체형(?)이 아니라 나뉘어 있으므로.
    // (나중에 수정버튼 눌렀을 때 gift_id를 전달해주어야 하므로)
    giftModBtn.setAttribute("data-gift_id",gift.gift_id)
    giftModBtn.setAttribute("data-pj_id",gift.pj_id)

    //3-2.선물 이름
    giftName.value = gift.gift_name;
    lengthCheck(giftName,50,'선물');

    //3-3.선택한 아이템
    //dropdown.click();
    //아이템 테이블로부터 프로젝트의 모든 아이템 데이터를 가져온다 (마음에 안든다)
    const itemArr = await fetchItems(gift.pj_id)
    let arr = []; //가져온 아이템들을 담을 배열
    for(item of itemArr){
        for(itmId of gift.item_id){
            if(item.item_id === itmId){ //해당 아이템이 선물에 포함된 아이템이면
                arr.push(item)
            }
        }
    }
    console.log(arr)

    const list = mkCheckedItm(arr);
    const selectItm = document.querySelector("#selectItm");
    showList(list, selectItm); //선택된 아이템 목록을 만들기

    const itmNums = document.querySelectorAll('input.itmNum')

    for(itmNum of itmNums){
        for(i=0; i<gift.item_id.length; i++){
            if(itmNum.getAttribute('data-item_id') == gift.item_id[i]){ //타입이 하나는 String이라 ==로 비교
                itmNum.value = gift.item_qty[i] //아이템 수량도 매칭해서 넣어주기
                const minus = itmNum.previousElementSibling;
                if(itmNum.value>2) minus.disabled = false;
            }
        }
    }


    const itmDropdown = document.querySelector('#itmDropdown');
    showList(mkItmDrop(itemArr), itmDropdown);
    const checkedItmArr = document.querySelectorAll('input[type=checkbox].checkedItem');
    for(checkedItm of checkedItmArr){
        for(itmId of gift.item_id){
            if(checkedItm.getAttribute('data-item_id') == itmId)
                checkedItm.checked = true;
        }
    }
    itmDropdown.classList.add('optChecked');
    const drop = itmDropdown.querySelector('#drop')
    drop.style.display = 'none'


    //3-4.수량제한여부 및 수량
    for(lim of limits){ // 선물 수량 제한 여부
        if(lim.value === gift.gift_qty_lim_yn){
            lim.click();
        }
    }
    if(gift.gift_total_qty){
        document.querySelector('#maxLimVal').value = gift.gift_total_qty;
    }


    if(!gift.gift_max_qty_per_person){ //인당 수량 제한 여부
        document.querySelector('#maxUnlim').click();
    } else {
        document.querySelector('#maxLim').click();
        document.querySelector('#maxLimPer').value = gift.gift_max_qty_per_person;
    }

    //3-5.예상전달일
    const shipDay = new Date(gift.gift_ship_due_date);
    const year = shipDay.getFullYear();
    const month = shipDay.getMonth() + 1;
    const date = shipDay.getDate();
    const week  = ['일','월','화','수','목','금','토']
    const day = shipDay.getDay();
    shipDate.querySelector('span').innerHTML = "<span id='shipDay'>"+ year+"-"+month+"-"+date+"</span><span>   ("+ week[day]+") </span>";
    days.value = (shipDay - new Date(payDay))/(1000*60*60*24);


    //3-6.선물금액
    giftMoney.value = comma(gift.gift_money);



}

const validCheck = function () {
    // 이게 최선일까??
    const checked = document.querySelector('input[type=radio]:checked');
    let textarea;
    const length = itmName.value.trim().length;
    //console.log(length);
    // if(!(0<length&&length<51)) {
    //     alert('아이템 이름의 글자수를 50자 이내로 맞춰주세요.')
    // }
    if (checked === null) return false; // 라디오 옵션을 아무것도 체크하지 않으면 false
    console.log(checked);
    if (checked.value !== '옵션 없음') {
        textarea = document.querySelector("." + checked.id).querySelector('textarea');
        console.log(textarea);
        // alert(textarea); elem테스트용
    }
    if (length === 0) return false; // 아이템 이름을 입력 안하면 false
    if (checked.value === '객관식 옵션' && optArr.length < 2) return false; //아이템 이름을 입력해도 객관식 조건을 다 입력하지 않으면 false
    // if(checked.value==='주관식' && textarea.value.trim().length === 0) return false; //아이템 이름을 입력해도 주관식 조건을 다 입력하지 않으면 false
    if (checked.value === '주관식 옵션') {
        if(textarea.value.trim()==='') return false; //주관식 옵션 빈 문자열 입력하면 통과x

        //빈 문자열이 아닐 경우,
        //optArr.push(textarea.value.trim());
        //console.log(optArr);
        //if (optArr.length !== 1) return false; //주관식일 경우, 저장 버튼을 누를 때 배열에 넣는다. 사실 주관식은 배열에 넣을 필요는 없지만... (리팩토링 필요)
        //alert(optArr.length);
        console.dir(optArr);
    } //아이템 이름을 입력해도 주관식 조건을 다 입력하지 않으면 false

    return true;
}

const init = function () {
    itmSaveBtn.disabled = true;
    itmName.value = "";
    itmName.parentElement.nextElementSibling.innerHTML = ''
    // itmName.parentElement.nextElementSibling.remove();
    const checked = document.querySelector("input[type=radio]:checked")
    checked.checked = false; //라디오버튼 체크 해제

    const checkedDiv = document.querySelector("." + checked.id);
    if (checkedDiv !== null) {
        checkedDiv.style.display = "none"; //해당 라디오버튼의 div 안보이게
        const checkedTxt = checkedDiv.querySelector("textarea");
        checkedTxt.value = ""; //해당 textarea의 값 비우기
        // console.dir(txt);

    }
    optArr.length = 0; //배열도 초기화
    const multiResult = document.querySelector("#multiResult");
    showList(mkOptList(optArr), multiResult);

    //버튼도 초기화
    itmSaveBtn.style.display = 'block';
    itmInitBtn.style.display = 'block';
    itmModBtn.style.display = 'none';
    itmCnclBtn.style.display = 'none';
}



//선물 입력 field의 초기화 함수
const giftInit = function(){
    //checkbox 해제 및 selectItm 감추기
    const checkedElems = document.querySelectorAll('input[type=checkbox]:checked');
    //console.log("checkedElems")
    //console.log(checkedElems);
    for(elem of checkedElems){
        elem.checked = false;
    }
    const selectItm = document.querySelector("#selectItm");
    const div = selectItm.querySelector('div')
    //console.log(selectItm);  //selectItm을 none처리하면 안됨.
    //console.log(div);
    if(div){ //div가 없을 때 div.style하면 에러나서 이후 코드 실행 안되므로 추가.
        div.style.display = 'none';
    }
    // 모든 input요소 초기화 (라디오 체크드 해제 포함)
    const inputs = document.querySelectorAll('.gift .pjForm input:not([type=radio]):not([type=hidden])')
    //console.log(inputs)

    for(input of inputs){
        input.value = ''
    }
    const radios = document.querySelectorAll('.gift .pjForm input[type=radio]:checked')
    for(radio of radios){
        //console.log(radio)
        radio.checked = false;
        radio.parentElement.style.border = '.5px solid #ececec';
        console.log(radio.value)
        if(radio.value ==='y') {
            radio.parentElement.querySelector('span').style.visibility = 'hidden';
        }
    }
    //라디오value가 'y'이면 input도 none처리해야함. color change도 취소

    const shipDate = document.querySelector('#shipDate');
    shipDate.querySelector('span').innerHTML = '';

    //버튼도 초기화
    giftSaveBtn.style.display = 'block';
    giftModBtn.style.display = 'none';
    giftInitBtn.style.display = 'block';
    giftCnclBtn.style.display = 'none';
}



//선물 등록을 하기 전에 1.입력필드를 가져와서 2.유효성 검사를 하는 함수. 유효성에 통과한 경우에만 form을 반환.
const hasValue = function(obj){ //널체크 함수
    return !(typeof obj === 'undefined' || obj === null)
}

const itemValidCheck = function(){
    //유효성이 검증된 아이템 데이터들을 가진 객체
    const validForm={};

    //아이템 이름 검증
    const item_name = document.querySelector('#itmName').value.trim();
    if(!hasValue(item_name)){ //이름이 없거나
        return false;
    } else if(item_name.length < 1 || item_name.length > 50 ) {
        return false; //유효 글자수가 아니면
    } else { //유효성 검사 통과
        validForm.item_name = item_name;
    }

    //옵션조건 검증
    const item_option_type = document.querySelector('input[name=optType]:checked').value;
    if(!['옵션 없음','객관식 옵션','주관식 옵션'].includes(item_option_type)){
        return false; //해당 옵션이 아닌 경우 return false;
    } else {
        validForm.item_option_type = item_option_type;
    }

    //옵션 상세 검증(객관식, 주관식 옵션에 따라 내용이 다름)
    if(item_option_type === '객관식 옵션'){
        console.log(optArr)
        if(optArr.length<2) { //옵션은 2개 이상이어야 한다.
            return false;
        } else {
            for(opt of optArr){
                if(opt.length<1 || opt.length>100 || opt.includes(',')){
                    return false;
                } //각 옵션에 대해서도 글자수 제한을 벗어나거나 쉼표를 포함하면 유효성 통과x (쉼표는 구분자로 쓰여서)
            }
            validForm.item_option = optArr.toString();
        }
    } else if(item_option_type === '주관식 옵션'){
        if(optArr[0].length<1 || optArr[0].length>100){
            return false;
        } else {
            validForm.item_option = optArr.toString();
        }
    } else { //옵션없음의 경우
        validForm.item_option = null;
    }

    console.log(validForm);
    return validForm;
}
const giftValidCheck = function(){
    const validForm = {
        item_id: [], //선물을 구성하는 아이템 리스트 (아이템 아이디 배열)
        item_qty: [], //선물을 구성하는 각 아이템의 수량
        gift_name: '',
        //pj_id: '', //pj_id는 form에 담지 않고 pathVariable로 서버에서 set하는 것 고려
        gift_qty_lim_yn: "",
        // gift_total_qty: "",
        // gift_max_qty_per_person: "", 해당 필드는 NotNull이 아니므로 해당시 동적으로 생성되도록 한다.
        //gift_ship_due_date: "",
        //gift_ship_need_yn: "",
        gift_money: "",
        //dba_reg_id: "",//dba_reg_id는 form에 담지 않고 session을 이용해 서버에서 set하는 것 고려
       // pj_pay_due_dtm: "",
    }; // 유효성 검사를 마친 필드들을 저장할 object

    //gift를 구성할 item들에 대한 검증
    const giftItemList = document.querySelectorAll(".giftItem");
    for(let i=0; i<giftItemList.length; i++){
        //console.log(giftItemList[i])
        validForm.item_id[i] = giftItemList[i].querySelector("button.cancel").getAttribute("data-item_id")
        //이 값은 서버에서 넘어온 값이니까 유효성 체크는 생략..

        let itmQty = giftItemList[i].querySelector(".itmNum").value;
        if(!hasValue(itmQty)) {
            console.log(hasValue(itmQty))
            return false;
        }
        //console.log(typeof itmQty); //왜 input[type=number]인데 string으로 나오지?

        itmQty = Number(itmQty);
        if(!Number.isInteger(itmQty)||itmQty<1||itmQty>1000) {
            return false; //아이템 수량이 정수가 아니거나, 1~1000사이의 수량이 아니면 유효성 검사 탈락
        }
        validForm.item_qty[i] = itmQty; //아이템 수량이 유효성 검사 통과하면, 배열에 담는다.
    }


    //giftName에 대한 검증
    const giftName = document.querySelector('#giftName').value;
    if(!hasValue(giftName)||giftName.trim()==='') { //아예 입력을 하지 않은 경우 또는 공백을 입력했을 때
        return false;
    }
    else if(giftName.length<1 || giftName.length >50) { //입력은 했지만 값이 유효범위가 아닐때
        return false;
    }
    validForm.gift_name = giftName;

    //gift_qty_lim_yn 검증 (사실 radio버튼이지만, client의 script 조작이 발생할 수 있다 가정하고 유효성 검사.)
    const checked =  document.querySelector('input[name=limit]:checked');
    if(!hasValue(checked)) return false; //라디오 버튼 체크 안하고 제출하면 반려

    const giftLimQty = checked.value; //
    //console.log(giftLimQty);
    if(!["y","Y","n","N"].includes(giftLimQty)){
        return false; // 입력값은 y 또는 n이어야 함
    }
    validForm.gift_qty_lim_yn = giftLimQty;
    // console.log(validForm);

    //gift_total_qty 검증
    // const totalQty = document.querySelector('input[name=limit]:checked > .maxInput').value;
    if(giftLimQty==="y"){ //제한 선물 수량은 선착순일 때만 존재하는 값
        let totalQty = document.querySelector('#maxLimVal').value;
        totalQty = Number(totalQty);
        // console.log(totalQty);
        if(!Number.isInteger(totalQty)||totalQty<1||totalQty>1000) {
            return false;
        }
        validForm.gift_total_qty = totalQty;
    }

    //gift_max_qty_per_person 검증
    const maxLimit = document.querySelector('input[name=maxLimit]').value;
    if(!hasValue(maxLimit)){
        return false;
    } else if(!["y","Y","n","N"].includes(maxLimit)){
        return false; // 입력값은 y 또는 n이어야 함
    }

    if(maxLimit==="y"){ //1인당 제한 수량이 있을 경우,
        let maxPer = document.querySelector('#maxLimPer').value;
        maxPer = parseInt(maxPer)
        if(giftLimQty==="y"){//선착순 선물이고
            let totalQty = document.querySelector('#maxLimVal').value;
            totalQty = parseInt(totalQty);
            if(maxPer<1 || maxPer > totalQty) {
                return false; //1이상의 정수가 아니거나, 선착순 총 수량 보다 많을경우
            }
        } else { //선착순 선물이 아닐 경우라도
            if(maxPer<1 || maxPer > 1000) {
                return false; //1~1000사이의 정수만 유효
            }
        }
        validForm.gift_max_qty_per_person = maxPer;
    }
    //gift_ship_due_date 유효성 검사
    //배송일은 최종 결제일로부터 1~1825일 사이어야 한다.
    let shipCalc = document.querySelector('#shipCalc').value;
    // console.log(shipCalc);
    if(!hasValue(shipCalc)){
        return false;
    }else if(shipCalc<1 || shipCalc>1825){
        return false;
    }
    let payDay = document.querySelector('#payDay').value;
    payDay = noOffset(new Date(payDay));
    // console.log(payDay);
    // console.log(typeof payDay);
    validForm.pj_pay_due_dtm = payDay.toISOString().substring(0,19);
    //사실 이 모든게 datepicker에서 iso String형식으로 날짜를 넘기길래 따라한건데..
    //offset은 내가 생각못한 변수였다. 이렇게까지 불편하게 iso String을 써야하는 근본적인 이유가 있나?

    let shipDay = document.querySelector("#shipDay").innerHTML
    shipDay = noOffset(new Date(shipDay)); //toISOString을 쓰면 offset때문에 시간차이가 생김.

    console.log("shipDay")
    console.log(shipDay);
    console.log(shipDay.toString());
    validForm.gift_ship_due_date = shipDay.toISOString().substring(0,19);
    console.log(validForm.gift_ship_due_date)

    //gift_money 유효성 검사
    let giftMoney = document.querySelector('#giftMoney').value;
    giftMoney = Number(uncomma(giftMoney))
    if(!Number.isInteger(giftMoney)||giftMoney<1000 || giftMoney>10000000){
        return false;
    }
    validForm.gift_money = giftMoney;

    //alert("check your console")
    console.log(validForm);
    return validForm; //모든 유효성 검사를 통과한, 유효한 값을 가진 객체를 반환
}


