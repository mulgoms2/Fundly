let optArr = []; //window.onload 안에 있으면 함수에서 못 갖다 쓴다.
let itemArr = [];
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
const initBtn = document.querySelector("#optInit");//초기화버튼
const saveBtn = document.querySelector("#optSave");//저장버튼

//선물 페이지의 요소들
const dropdown = document.querySelector(".dropdown");
const giftName = document.querySelector('#giftName');
const radioBtns = document.querySelector('.pjBox.gift').querySelectorAll('input[type=radio]');
const maxInputs = document.querySelectorAll('.maxInput');
const pjForm = document.querySelector('.pjBox.item').querySelector('.pjForm');
const giftInitBtn = document.querySelector(".gift .pjForm .btnWrap .init"); //선물 초기화 버튼
const giftSaveBtn = document.querySelector(".gift .pjForm .btnWrap .save"); //선물 저장버튼





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
    console.log(itmBtn);
    console.log(gftBtn);

    //window.onload시 #itemList에 있는 item수를 세고
    const cnt = document.querySelectorAll("#itemList > div");
    console.dir(cnt);

    mkHidden([itemPage]);//이건 cnt와 상관없이
    if (cnt.length === 0) {
        mkHidden([giftPage]); //아이템이 하나도 없으면 선물시작페이지(strPage)가 뜨도록 giftPage는 숨김처리
    } else {
        mkHidden([strPage]);
    }
    // else {
    //     mkHidden()
    // }

    //console.dir(${itemList.});//
    //동작 안함. 이유를 생각하기!!!

    if (strBtn !== null) {
        strBtn.addEventListener("click", function () {
            // giftPage.style.display = "none";
            itmBtn.querySelector('i').style.color = '#f86453';
            itmBtn.querySelector('span').style.color = '#3d3d3d';
            itmBtn.querySelector('span').style.fontWeight = '700';
            gftBtn.querySelector('i').style.color = '#c4c4c4';
            gftBtn.querySelector('span').style.color = '#c4c4c4';
            gftBtn.querySelector('span').style.fontWeight = '600';
            location.href = "#item";
            window.scrollTo(0,0); //최상단으로 이동
            mkHidden([giftPage, strPage]);
            mkVisible(itemPage);
        })
    }
    pjForm.addEventListener('change',function(){
        if(!validCheck()){
            saveBtn.disabled = true;
        } else saveBtn.disabled = false;
    })

    gftBtn.addEventListener("click", function () {
        const cnt = document.querySelectorAll("#itemList div");
        // console.dir(cnt); //
        this.querySelector('i').style.color = '#f86453';
        this.querySelector('span').style.color = '#3d3d3d';
        this.querySelector('span').style.fontWeight = '700';
        itmBtn.querySelector('i').style.color = '#c4c4c4';
        itmBtn.querySelector('span').style.color = '#c4c4c4';
        itmBtn.querySelector('span').style.fontWeight = '600';
        //alert(cnt.length);
        // 원래는 ${itemList.size()}로 비교하려했는데 실시간 반영이 안되더라.
        //비동기라서 화면 전환이 되는ㄱㅔ 아니다보니--%>
        if (cnt.length === 0) { //등록된 아이템이 없으면 선물 페이지를 보여주지 않는다.
            mkHidden([itemPage, giftPage])
            location.href = "#str"
            window.scrollTo(0,0); //최상단으로 이동
            mkVisible(strPage);
        } else {
            mkHidden([itemPage, strPage])
            location.href = "#gift"
            window.scrollTo(0,0); //최상단으로 이동
            mkVisible(giftPage);
        }
    })



    itmBtn.addEventListener("click", function () {
        const cnt = document.querySelectorAll("#itemList > div");
        console.dir(cnt); //

        this.querySelector('i').style.color = '#f86453';
        this.querySelector('span').style.color = '#3d3d3d';
        this.querySelector('span').style.fontWeight = '700';
        gftBtn.querySelector('i').style.color = '#c4c4c4';
        gftBtn.querySelector('span').style.color = '#c4c4c4';
        gftBtn.querySelector('span').style.fontWeight = '600';
        location.href = "#item";
        window.scrollTo(0,0); //최상단으로 이동
        if (cnt.length === 0) { //등록된 아이템의 수에 따라 숨길 페이지가 다르다.
            mkHidden([strPage])
        } else {
            mkHidden([giftPage])
        }
        mkVisible(itemPage);
    })

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

    initBtn.addEventListener("click", init);

    saveBtn.addEventListener("click", function () {
        // $(".save").click(function(){
        // alert(this);
        // if (!validCheck()) {
        //     alert('필수 입력 항목을 전부 입력해주세요');
        //     return;
        // }
        //그냥 전체 Form에서 입력이벤트를 감지해서 saveBtn의 활성/비활성을 조절한다.

        const item_option_type = $('input[type=radio]:checked');
        let item_option;
        if (item_option_type.val() !== '옵션 없음') {
            item_option = optArr.toString()
        }
        // } else if (item_option_type.val() === '주관식') {
        //     item_option = $('.'+item_option_type.id+" textarea").val();
        // }
        // console.dir('---save---')
        // console.dir(item_option_type.val());
        // console.dir(item_option);
        // console.dir(itmName.value);
        $.ajax({
            type: 'POST',
            url: '/project/item',
            headers: {"content-type": "application/json"},
            data: JSON.stringify({
                //todo 입력창에 input이나 keyup이벤트로 client에게 올바른 입력을 유도하는 것 외에도 서버로 값을 넘기기 전에 유효성 체크를 하는 과정이 필요하다.
                'item_name': itmName.value,
                // 'item_name': '', 테스트용
                'item_option_type': item_option_type.val(),
                'item_option': item_option
            }),
            dataType: "json",
            success: function (result) {
                alert('아이템이 성공적으로 등록되었습니다.');
                init(); //기존 입력창을 초기화한다.
                saveBtn.disabled = true;
                // itemArr.push(result);
                console.dir(result);
                itemArr = result; //Java List타입 객체를 JS 배열에 넣을 수 있는건가?! 이게 되네.
                console.dir(itemArr);
                // const itemList = $('#itemList'); //여기에 오타있나? 제이쿼리로 가져오면 왜 못읽지.
                const itemList = document.querySelector('#itemList');
                console.dir(itemList)
                const list = mkItmList(itemArr);
                console.dir(list);
                showList(list, itemList);
                console.dir(result);
            },
            error: function (result) {
                alert('아이템 등록에 실패했습니다.')
                // const parseResult = JSON.parse(result);
                // console.dir(parseResult);
                console.dir(result); //상태코드 400을 가지고 있는, errorResult를 포함한 어떤(?) 객체..
                console.dir(result.errorDetails); //이거는 undefined
                console.dir(result.responseJSON.errorDetails); //이렇게 접근해야 에러메시지를 읽을 수가 있었다...
                const errArr = result.responseJSON.errorDetails;
                let errorMessage = ''
                for(err of errArr){
                    errorMessage += err.errorMessage+"\n";
                }
                // 왜 이렇게까지 꺼내야 하는걸까?
                alert(errorMessage); //각 필드에 에러메시지를 뜨게 하는게 목표인데, 일단은 alert까지라도.
            }
        });
    });



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
                        saveBtn.disabled = false; //아이템 이름까지 입력한 상태여야 저장버튼 활성화됨.
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
                enterEvent(this, optArr);
            })
        }
    }//아이템 페이지에 거는 이벤트들




    //선물 페이지에 거는 이벤트들
    dropdown.addEventListener("click", function () {
        this.classList.toggle('border');

        const pj_id = document.querySelector('#itemList').querySelector('div').getAttribute('data-pj_id');
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

    giftSaveBtn.addEventListener("click", function(){ //선물 저장버튼 누르기
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
                alert("check your console");
                //console.log("here check")
                //console.log(data);
                const giftArr = data //서버로부터 giftList를 받아옴
                for(gift of giftArr){
                    console.log(gift)
                    //선물리스트 data를 가지고 html태그를 만드는 함수 호출해서 화면에 뿌리기
                }
                //다음 입력을 위해 입력 필드 초기화 함수 호출
                giftInit();
            })
            .catch(error => error).then(error => alert(error))
            // 중복된 선물 이름을 입력한 경우에도, 다른 입력값을 보존하기 위해 입력 필드 초기화 함수는 호출하지 않는다.
    })

    //초기화 버튼 - 모든 입력필드 초기화.
    giftInitBtn.addEventListener("click",giftInit);



}// window.onload


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
            saveBtn.disabled = false;
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
    for (itm of itmArr) {
        list += '<div style="cursor:pointer" onclick=removeItm(itemArr,this) data-item_id=' + itm.item_id + ' data-pj_id=' + itm.pj_id + '>'
        //list += '<input type="hidden" value='+itm.item_id+'>' //item_id를 hidden으로 가져온다.
        //list += '<input type="hidden" value='+itm.pj_id+'>' //hidden으로 넣지 말고 data- attribute에 넣을까..? 굳이 input태그를 하나 더 쓰는게 맞을까?
        list += '<div class="itmTit" style="border:none;">'
        list += '<p style="font-weight: 600" >'
        list += itm.item_name + '</p>'
        list += '<div><i class="far fa-regular fa-trash-can"></i></div>'
        list += '</div>'
        list += '<p class="itmT">' + itm.item_option_type + '</p>'
        list += '<ul class="itmL">'
        if (itm.item_option != null) { //옵션없음이 아닌 경우(객관식, 주관식 옵션)
            const opts = toArray(itm.item_option);
            for (opt of opts) {
                list += '<li>' + opt + '</li>'
            }
        }
        list += '</ul>'
        list += '</div>'
    }
    return list;
}

const mkItmDrop = function (arr) {
    let list = '<div>'
    list += '<ul>'
    for (itm of arr) {
        list += '<li>'
        list += '<input type="checkbox" onchange="changeFoot()" data-item_id=' + itm.item_id + '>'
        list += '<div>'
        list += '<span>' + itm.item_name + ' (' + itm.item_option_type + ') </span>'
        list += '<em>0개의 선물에 포함됨</em>'
        list += '</div>'
        list += '</li>'
    }
    list += '</ul>'
    list += '<div class="footer">'
    list += '<p>0개의 아이템 선택</p>'
    list += '<button type="button" onclick="selectItem(this)"><p>선택완료</p></button>'
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
            let temp = toArray(itm.item_option);
            list += '<ul>'
            for(opt of temp){
                list += '<li class="opt">' + opt + '</li>'
            }
            list += '</ul>'
        }
        list += '</li>'
        list += '</ul>'
        list += '</div>' //left close
        list += '<div class="right">'
        list += '<div class="qty" style="display:inline-block">'
        list += '<button type="button" class="minus" onclick="minus(this)" disabled><i class="fas fa-regular fa-minus"></i></button>'
        list += '<input class="itmNum" type="number" value="1" onkeyup="numCheck(this)">'
        list += '<button type="button" class="plus" onclick="plus(this)"><i class="fas fa-regular fa-plus"></i></button>'
        list += '</div>' //div close
        list += '<button class="cancel" type="button" onclick="removeBtn(this)" data-item_id='+itm.item_id+'>삭제</button>'
        list += '</div>' //right close
        list += '</div>'
    }
    list += '</div>'

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
        elem.value=1;
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
        if(lim.id==='lim') { //선착순 선물 수량이 정해져 있는 경우,
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

const calcDate = function(elem){
    const hidden = elem.parentElement.querySelector('input[type=hidden]')
    console.log(hidden);
    const from = new Date(hidden.value);
    const shipDate = document.querySelector('#shipDate');
    if(elem.value > 1825){
        shipDate.querySelector('span').innerText = '예상전달일이 유효하지 않습니다.'
        return;
    }
    const temp = from.getTime()+elem.value*24*60*60*1000;
    const to = new Date(temp);
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
    if (val < 1) {
        alert('1이상의 숫자를 입력해주세요');
        elem.value = 1;
    }
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
    console.log('here');
    console.log(div);
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
    alert(queryString);
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
    let arr = [];//아이템 옵션을 담을 배열
    if (!string.includes(',')) {
        arr.push(string); //주관식의 경우에는 단순히 문자열을 넣기만
    } else {
        arr = string.split(','); //객관식인 경우는 쉼표로 나누어서 넣기
    }
    return arr;
}

const showList = function (list, elem) {
    elem.innerHTML = list
    // console.dir(optArr);
}

//아이템 삭제 메서드
const removeItm = function (itemArr, elem) {
    if (!confirm("이 아이템을 삭제하시겠습니까? 삭제하면 해당 아이템이 포함된 *개의 선물에서도 삭제됩니다.")) return;
    //ajax로 컨트롤러를 통해 db에서 아이템 삭제해야 후 리스트를 다시 불러와서 보여줘야함.
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
            elem.remove(); //아이템 목록에서 삭제
            console.log("removeItm");
            console.dir(result);
            itemArr = result; //Java List타입 객체를 JS 배열에 넣을 수 있는건가?! 이게 되네.
            console.dir(itemArr);
            // const itemList = $('#itemList'); //여기에 오타있나? 제이쿼리로 가져오면 왜 못읽지.
            const itemList = document.querySelector('#itemList');
            const list = mkItmList(itemArr);
            showList(list, itemList);
        },
        error: function (result) {
            alert('아이템 삭제에 실패했습니다.')
        }
    });
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
    saveBtn.disabled = true;
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
}

//선물 입력 field의 초기화 함수
const giftInit = function(){
    //checkbox 해제 및 selectItm 감추기
    const checkedElems = document.querySelectorAll('input[type=checkbox]:checked');
    console.log("checkedElems")
    console.log(checkedElems);
    for(elem of checkedElems){
        elem.checked = false;
    }
    const selectItm = document.querySelector("#selectItm");
    const div = selectItm.querySelector('div')
    console.log(selectItm);  //selectItm을 none처리하면 안됨.
    console.log(div);
    if(div){ //div가 없을 때 div.style하면 에러나서 이후 코드 실행 안되므로 추가.
        div.style.display = 'none';
    }
    // 모든 input요소 초기화 (라디오 체크드 해제 포함)
    const inputs = document.querySelectorAll('.gift .pjForm input:not([type=radio]):not([type=hidden])')
    console.log(inputs)

    for(input of inputs){
        input.value = ''
    }
    const radios = document.querySelectorAll('.gift .pjForm input[type=radio]:checked')
    for(radio of radios){
        console.log(radio)
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
}



//선물 등록을 하기 전에 1.입력필드를 가져와서 2.유효성 검사를 하는 함수. 유효성에 통과한 경우에만 form을 반환.
const giftValidCheck =function(){
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
        console.log(giftItemList[i])
        validForm.item_id[i] = giftItemList[i].querySelector("button.cancel").getAttribute("data-item_id") //이 값은 서버에서 넘어온 값이니까 유효성 체크는 생략..
        let itmQty = giftItemList[i].querySelector(".itmNum").value;
        //console.log(typeof itmQty); //왜 input[type=number]인데 string으로 나오지?
        itmQty = parseInt(itmQty);
        if(!Number.isInteger(itmQty)||itmQty<1||itmQty>1000) {
            return false; //아이템 수량이 정수가 아니거나, 1~1000사이의 수량이 아니면 유효성 검사 탈락
        }
        validForm.item_qty[i] = itmQty; //아이템 수량이 유효성 검사 통과하면, 배열에 담는다.
    }


    //giftName에 대한 검증
    const giftName = document.querySelector('#giftName').value;
    if(giftName.length<1 || giftName.length >50) {
        return false;
    }
    validForm.gift_name = giftName;

    //gift_qty_lim_yn 검증 (사실 radio버튼이지만, client의 script 조작이 발생할 수 있다 가정하고 유효성 검사.)
    const giftLimQty = document.querySelector('input[name=limit]:checked').value; //
    console.log(giftLimQty);
    if(!["y","Y","n","N"].includes(giftLimQty)){
        return false; // 입력값은 y 또는 n이어야 함
    }
    validForm.gift_qty_lim_yn = giftLimQty;
    // console.log(validForm);

    //gift_total_qty 검증
    // const totalQty = document.querySelector('input[name=limit]:checked > .maxInput').value;
    if(giftLimQty==="y"){ //제한 선물 수량은 선착순일 때만 존재하는 값
        let totalQty = document.querySelector('#maxLimVal').value;
        totalQty = parseInt(totalQty);
        // console.log(totalQty);
        if(totalQty<1||totalQty>1000) {
            return false;
        }
        validForm.gift_total_qty = totalQty;
    }

    //gift_max_qty_per_person 검증
    const maxLimit = document.querySelector('input[name=maxLimit]').value;
    if(!["y","Y","n","N"].includes(maxLimit)){
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
    if(shipCalc<1 || shipCalc>1825){
        return false;
    }
    let payDay = document.querySelector('#payDay').value;
    payDay = new Date(payDay);
    // console.log(payDay);
    // console.log(typeof payDay);
    validForm.pj_pay_due_dtm = payDay.toISOString().substring(0,19);

    let shipDay = document.querySelector("#shipDay").innerHTML
    shipDay = new Date(shipDay);
    // console.log(shipDay);
    validForm.gift_ship_due_date = shipDay.toISOString().substring(0,19);

    //gift_money 유효성 검사
    let giftMoney = document.querySelector('#giftMoney').value;
    giftMoney = parseInt(uncomma(giftMoney))
    if(giftMoney<1000 || giftMoney>10000000){
        return false;
    }
    validForm.gift_money = giftMoney;

    alert("check your console")
    console.log(validForm);
    return validForm; //모든 유효성 검사를 통과한, 유효한 값을 가진 객체를 반환
}


