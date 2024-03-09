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
const incomeDay = document.querySelector('.ntc.payIn');


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
            return diff < 10 || diff > 360 //심사기간을 고려하여 now()로부터 10일 이후부터 시작일 설정 가능, 종료일은 1년 이내(펀딩 기간은 max 60일)
        },
        "drops": "auto",

    });

    $('#dateInput').on('apply.daterangepicker', function(ev, picker){
        //datepicker의 정보를 attribute로 담아두기
        //picker.startDate, picker.endDate의 타입 자체는 Object. (Date가 아니다)

        $(this).parent().attr('data-str_dtm', picker.startDate.format('YYYY-MM-DD'))
        $(this).parent().attr('data-end_dtm', picker.endDate.format('YYYY-MM-DD'))



        //기간을 계산하는 함수를 호출해서 innerText 표시
        $('.ntc.range').text("펀딩 기간 : " + calcRange(picker.startDate, picker.endDate)+"일");
        //console.log(calcFinalPayment(picker.endDate));


        const finalFundDay = new Date(picker.endDate) //펀딩 종료 일자


        //결제 종료일을 innerText로 표시
        const finalPayDay = calcFinalPayment(finalFundDay) //type: Date
        console.log("finalPayDay")
        console.log(finalPayDay)

        $('.ntc.end').text("결제 종료 예정일 : " + finalPayDay.toISOString().substring(0,10));


        calcFinalIncomeDay(finalPayDay); //정산예정일을 구하는 함수

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

const calcFinalPayment = function(finalDay){
    finalDay = new Date(finalDay.setDate(finalDay.getDate() + 1));
    return finalDay;
}

const calcFinalIncomeDay = async function(finalPayDay){
    const holidayArr = await getHolidays(finalPayDay); //결제 종료일로부터 30일 이내의 공휴일 목록을 읽어온다.
    // console.log("holidayArr")
    // console.log(holidayArr)

    let workDay = 7; //default는 7영업일
    let finalIncomeDay = null;
    let tempIncomeDay = new Date(finalPayDay.getTime());
    let nextDayFromPay = new Date(tempIncomeDay.setDate(tempIncomeDay.getDate()+1))

    const isWeekendOrHoliday = function(date){ //해당 날짜가 공휴일이거나 주말인지 체크하는 함수
        return [0,6].includes(date.getDay()) || holidayArr.includes(date.toISOString().substring(0,10));
    }

    for(let i=0; i<workDay; i++){
        if(isWeekendOrHoliday(nextDayFromPay)){
            ++workDay
        }
        nextDayFromPay.setDate(nextDayFromPay.getDate()+1)
    }
    console.log("workDay")
    console.log(workDay)
    tempIncomeDay.setDate(finalPayDay.getDate()+workDay)

    finalIncomeDay = tempIncomeDay;
    console.log("finalIncomeDay")
    console.log(finalIncomeDay);
    incomeDay.innerText = '정산예정일 : ' + finalIncomeDay.toISOString().substring(0,10);
}

const getHolidays = async function(finalPayDay){

    const holidayArr = await fetch('/project/holiday?finalPayDay='+finalPayDay.toISOString().substring(0,19), {
        method: "GET",
    }).then(response => response.json())
    // console.log('this')
    // console.log(holidayArr)
    return holidayArr
}

const getDateRange = function (startDate, lastDate){ //from startDate(포함x) to lastDate
    let dateRange = []
    let tmp = new Date(startDate.getTime())
    tmp.setDate(tmp.getDate()+1)
    while(tmp <= lastDate){
        dateRange.push(tmp.toISOString().substring(0,10))
        tmp.setDate(tmp.getDate()+1)
    }
    console.log(dateRange)
    return dateRange;
}



//(전제 : 공휴일 api를 이용해 공휴일 데이터를 미리 db에 저장해둔다.)
//daterangepicker에서 apply이벤트가 발생하면
//서버로 결제 종료일 정보를 보내고,
//db에서 결제 종료일+30일의 내의 공휴일 list를 받아온다.
//
//결제 종료일의 요일에 따라 7일 내에 포함된 주말의 수가 달라지므로 분기처리 필요
//결제 종료일 + (주말제외 7일)에 해당하는 날짜를 배열(arr)로 만들기
//결제 종료일 + (주말제외 7일)인 date







