const selectStrBtn = document.querySelector('.selectStr');
const selectBox = document.querySelector('#timeSelect');
const datepicker = document.querySelector('.datepicker');
let today = new Date();
const saveBtn = document.querySelector('button.save');

// 목표금액 field
const goalMoney = document.querySelector('.goalMoney')
const receiveMoney = document.querySelector('.receiveMoney')
const feeCalc = document.querySelector('.feeCalc');
const startTime = document.querySelector('select[name=startTime]')
const range = document.querySelector('span.range')
const payEndDay = document.querySelector('span.end')
const incomeDay = document.querySelector('span.payIn');


window.onload = function(){
    inputCheck(goalMoney); //일단은 강제로 input이벤트를 발생시킬 수가 없어서 이렇게 처리.

    //datepicker의 요소 혹은 이벤트를 쓸 때는 vanilla js가 아닌 jquery로 써야 적용되어서 어쩔 수 없이 혼용
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
        "startDate": $('.datepicker').attr('data-str_dtm').substring(0,10) || new Date(),
        "endDate": $('.datepicker').attr('data-end_dtm').substring(0,10) || new Date(),

    });


    $('#dateInput').on('apply.daterangepicker', function(ev, picker){
        //datepicker로부터 펀딩 일정이 결정(apply)되면, 결제종료일과 정산예정일 등을 계산한다.


        //1. datepicker의 정보를 attribute로 담아두기
        //picker.startDate, picker.endDate의 타입 자체는 Object. (Date가 아니다)
        $(this).parent().attr('data-str_dtm', picker.startDate.format('YYYY-MM-DD'))
        $(this).parent().attr('data-end_dtm', picker.endDate.format('YYYY-MM-DD'))


        //2. 펀딩 기간 계산
        $('span.range').text(calcRange(picker.startDate, picker.endDate));
        //console.log(calcFinalPayment(picker.endDate));

        const finalFundDay = new Date(picker.endDate) //펀딩 종료 일자

        //3. 결제 종료일을 계산한다.
        const finalPayDay = calcFinalPayment(finalFundDay) //type: Date
        // console.log("finalPayDay")
        // console.log(finalPayDay)
        $('span.end').text(finalPayDay.toISOString().substring(0,10));

        //4. 정산예정일을 계산한다.
        calcFinalIncomeDay(finalPayDay);

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
        inputCheck(this);
    })

    saveBtn.addEventListener('click', function(){
        //유효성 검사를 통과한 formData를 얻어온다.
        const validForm = validFormCheck();
        if(!validForm) alert('양식에 맞추어 다시 입력해주세요')
        console.log('validForm')
        console.log(validForm)
        fetch('/project/editor/funding',{
            method:"POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify(validForm)
        }).then(res => {
            if(!res.ok){
                throw res
            }
            alert('성공적으로 저장되었습니다.')
            return res.json()
        }).then(data => {
            if(data == true)
                location.href = "/project/editor/funding"
        }).catch(error => console.log(error))

    })
}

const inputCheck = function(elem){
    let goal = elem.value;

    //유효성 검사 (사용자에게 올바른 값을 입력하도록 유도하기)
    moneyNotice(goal, 500000, 9999999999)
    //수령액 및 수수료 계산기
    calcMoney(goal, 10)
    //comma 적용
    elem.value = comma(uncomma(goal));
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

//결제완료일로부터 7영업일에 해당하는 날짜를 구하는 함수(주말, 공휴일 제외 7영업일) : 입금예정일
const calcFinalIncomeDay = async function(finalPayDay){
    const holidayArr = await getHolidays(finalPayDay); //결제 종료일로부터 30일 이내의 공휴일 목록을 읽어온다.
    console.log("holidayArr")
    console.log(holidayArr)

    let workDay = 7; //default는 7영업일
    let finalIncomeDay = null; //입금예정일
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
    // console.log("workDay")
    // console.log(workDay)
    finalPayDay.setDate(finalPayDay.getDate()+workDay)

    finalIncomeDay = finalPayDay;
    // console.log("finalIncomeDay")
    // console.log(finalIncomeDay);
    incomeDay.innerText = finalIncomeDay.toISOString().substring(0,10);
}

const getHolidays = async function(finalPayDay){

    const holidayArr = await fetch('/project/holiday?finalPayDay='+finalPayDay.toISOString().substring(0,19), {
        method: "GET",
    }).then(response => response.json())

    return holidayArr;
}

const validFormCheck = function() {
    let validForm = {}
    var regexDate = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/
    var regexDateTime = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])(T)(0[0-9]|1[0-9]|2[0-3]):(0[1-9]|[0-5][0-9]):(0[1-9]|[0-5][0-9])$/

    //목표금액 유효성 체크
    const money = parseInt(uncomma(goalMoney.value))

    if (isNull(money) || isNaN(money) || money < 500000 || money > 9999999999 ) {
        return false;
    } else {
        validForm.fund_goal_money = money
        console.log(validForm)
    }

    //펀딩 시작일, 종료일 체크
    //date-picker의 형식은 yyyy-mm-dd인데, model에 담겨오는 fundingForm은 yyyy-mm-ddTHH:mm:ss
    //format때문에 fundingForm 수정 요청이 매끄럽게 넘어가지 못함
    let fund_str_dtm = datepicker.getAttribute('data-str_dtm')
    let fund_end_dtm = datepicker.getAttribute('data-end_dtm')
    let str_tm = startTime.value;

    fund_str_dtm = new Date(fund_str_dtm)
    fund_end_dtm = new Date(fund_end_dtm)

    fund_str_dtm = fund_str_dtm.toISOString().substring(0,10) + 'T' + str_tm + ':00'
    fund_end_dtm = fund_end_dtm.toISOString().substring(0,10) + 'T' + '23:59:59'

    console.log(fund_str_dtm)
    console.log(fund_end_dtm)
    console.log(str_tm)

    if (isNull(fund_str_dtm) || isNull(fund_end_dtm) || isNull(str_tm)
            || !regexDateTime.test(fund_end_dtm) || !regexDateTime.test(fund_str_dtm)) return false; //입력되지 않으면 유효성 체크 탈락
    validForm.fund_str_dtm = fund_str_dtm;
    validForm.fund_end_dtm = fund_end_dtm;
    validForm.fund_str_tm = str_tm;
    console.log(validForm)


    //예상 결제 종료일, 정산 예정일
    let pj_pay_due_dtm = payEndDay.innerText
    let fund_calc_due_dtm = incomeDay.innerText


    pj_pay_due_dtm = pj_pay_due_dtm + 'T' + "23:59:59"
    fund_calc_due_dtm = fund_calc_due_dtm + 'T' + "23:59:59"

    if (isNull(pj_pay_due_dtm) || !regexDateTime.test(pj_pay_due_dtm)
        || isNull(fund_calc_due_dtm) || !regexDateTime.test(fund_calc_due_dtm)) return false;

    validForm.pj_pay_due_dtm = pj_pay_due_dtm
    validForm.fund_calc_due_dtm = fund_calc_due_dtm
    console.log(validForm)

    console.log('validForm')
    console.log(validForm);
    return validForm;
}

const isNull = function(item){
    return typeof item === 'undefined' || item === null
}




//두 날짜 사이의 날들을 배열로 반환하는 함수
// const getDateRange = function (startDate, lastDate){ //from startDate(포함x) to lastDate
//     let dateRange = []
//     let tmp = new Date(startDate.getTime())
//     tmp.setDate(tmp.getDate()+1)
//     while(tmp <= lastDate){
//         dateRange.push(tmp.toISOString().substring(0,10))
//         tmp.setDate(tmp.getDate()+1)
//     }
//     console.log(dateRange)
//     return dateRange;
// }



//(전제 : 공휴일 api를 이용해 공휴일 데이터를 미리 db에 저장해둔다.)
//daterangepicker에서 apply이벤트가 발생하면
//서버로 결제 종료일 정보를 보내고,
//db에서 결제 종료일+30일의 내의 공휴일 list를 받아온다.
//
//결제 종료일의 요일에 따라 7일 내에 포함된 주말의 수가 달라지므로 분기처리 필요
//결제 종료일 + (주말제외 7일)에 해당하는 날짜를 배열(arr)로 만들기
//결제 종료일 + (주말제외 7일)인 date







