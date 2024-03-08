const selectStrBtn = document.querySelector('.selectStr');
const selectBox = document.querySelector('#timeSelect');
const datepicker = document.querySelector('.datepicker');
let today = new Date();
const saveBtn = document.querySelector('button.save');

// 목표금액 field
const goalMoney = document.querySelector('.goalMoney')
const receiveMoney = document.querySelector('.receiveMoney')
const feeCalc = document.querySelector('.feeCalc');
const range = document.querySelector('.ntc.range')


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

        //기간을 계산하는 함수
        $('.ntc.range').text("펀딩 기간 : " + calcRange(picker.startDate, picker.endDate)+"일");
        console.log(calcFinalPayment(picker.endDate));
        const finalDay = calcFinalPayment(picker.endDate)
        $('.ntc.end').text("결제 종료 예정일 : " + finalDay.year + "-" + finalDay.month + "-" + finalDay.date)
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

    goalMoney.addEventListener('input', function(){
        let goal = this.value;

        //유효성 검사 (사용자에게 올바른 값을 입력하도록 유도하기)
        moneyNotice(goal, 500000, 9999999999)
        //수령액 및 수수료 계산기
        calcMoney(goal, 10)
        //comma 적용
        this.value = comma(uncomma(goal));

    })

    saveBtn.addEventListener('click', function(){
        const formData = new FormData();

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
const comma = function(money) {
    money = String(money);
    return money.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
const uncomma = function(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

const calcRange = function(startDate, endDate){
    const diff = new Date(endDate).getTime() - new Date(startDate).getTime()
    return Math.floor(diff / (1000*60*60*24));
}

const calcFinalPayment = function(endDate){
    let finalDay = new Date(endDate)
    finalDay = new Date(finalDay.setDate(finalDay.getDate() + 1));
    return {
        "year": finalDay.getFullYear(),
        "month": finalDay.getMonth()+1,
        "date": finalDay.getDate()
    }
}

const calc7daysFundingResult = function(){}
//(공휴일 api를 이용해 공휴일 데이터를 미리 db에 저장해둔다.)
//daterangepicker에서 apply이벤트가 발생하면
//서버로 결제 종료일 정보를 보내고,
//db에서 결제 종료일+30일의 내의 공휴일 list를 받아온다.
//
//결제 종료일의 요일에 따라 7일 내에 포함된 주말의 수가 달라지므로 분기처리 필요
//결제 종료일 + (주말제외 7일)에 해당하는 날짜를 배열(arr)로 만들기
//결제 종료일 + (주말제외 7일)인 date
//for(date of arr){
   //if(list.contains(date){
    // date.setDate(date.getDate() + 1);
    //}

//return date;







