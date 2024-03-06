const selectStrBtn = document.querySelector('.selectStr');
const selectBox = document.querySelector('#timeSelect');
const datepicker = document.querySelector('.datepicker');
let today = new Date();
const saveBtn = document.querySelector('button.save');

// 목표금액 field
const goalMoney = document.querySelector('.goalMoney')
const receiveMoney = document.querySelector('.receiveMoney')
const feeCalc = document.querySelector('.feeCalc');


window.onload = function(){
    const dateInput = document.createElement('input');
    $('#dateInput').daterangepicker({
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " ~ ",
            "applyLabel": "확인",
            "cancelLabel": "취소",
            "fromLabel": "From",
            "toLabel": "To",
            "customRangeLabel": "Custom",
            "weekLabel": "W",
            "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
        },
        "maxSpan": {
            "days": 60 //텀블벅 정책상 펀딩기간은 max 60일
        },
        "showTopBar": false,
        // "minDate": new Date(today.setDate(today.getDate() + 10)), //이 옵션은 왠지 안먹힌다....
        "isInvalidDate": function(date){ // 반환값이 true이면 invalid하게 처리함.
            const selected = new Date(date);
            // console.log("today="+today)
            // console.log(selected)
            const diff = (selected.getTime() - today.getTime()) / (1000*60*60*24)
            // console.log(diff)
            return diff < 10 || diff > 180 //심사기간을 고려하여 now()로부터 10일 이후부터 시작일 설정 가능, 종료일은 180일 이내(펀딩 기간은 max 60일)
        },
        "drops": "auto",

    });

    $('#dateInput').on('apply.daterangepicker', function(ev, picker){
        $(this).parent().attr('data-str_dtm', picker.startDate.format('YYYY-MM-DD'))
        $(this).parent().attr('data-end_dtm', picker.endDate.format('YYYY-MM-DD'))

    })

    //시간 선택
    selectStrBtn.addEventListener("click",function(){
        this.classList.toggle('hidden');
        console.log(selectBox);
        selectBox.classList.toggle('hidden')
    })

    datepicker.addEventListener('click', function(){
        this.querySelector('#dateInput').classList.toggle('hidden');
    })


    saveBtn.addEventListener('click', function(){
        const formData = new FormData();

    })


    goalMoney.addEventListener('input', function(){
        //input또는 keyup이벤트

        let goal = this.value;
        //유효성 검사 (사용자에게 올바른 값을 입력하도록 유도하기)
        moneyNotice(goal, 500000, 9999999999)
        //수령액 및 수수료 계산기
        calcMoney(goal, 10)
        //comma 적용
        this.value = comma(uncomma(goal));

    })
}

const moneyNotice = function(money, min, max){
    //500,000원 이상 9,999,999,999원 이하
    const notice = document.querySelector('.notice')
    money = uncomma(money)
    if(money < min) {
        notice.innerHTML = comma(min)+'원 이상의 금액을 입력해주세요.'
    } else if (money > max) {
        notice.innerHTML = comma(max)+'원 이하의 금액을 입력해주세요'
    } else
        notice.innerHTML = ''
}

const calcMoney = function(goalMoney, rate){
    goalMoney = uncomma(goalMoney)
    //VAT을 포함시켜서 수수료를 계산 (수수료 10% -> VAT 포함 총 11% )
    feeCalc.innerHTML = comma(Math.floor(goalMoney * (rate * 1.1) / 100));
    receiveMoney.innerHTML = comma(goalMoney - uncomma(feeCalc.innerHTML));
}
function comma(money) {
    money = String(money);
    return money.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}








