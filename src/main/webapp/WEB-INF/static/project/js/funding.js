const selectStrBtn = document.querySelector('.selectStr');
const selectBox = document.querySelector('#timeSelect');
const datepickers = document.querySelectorAll('.datepicker');
const startPicker = document.querySelector('.datepicker.start');
const endPicker = document.querySelector('.datepicker.end');
let today = new Date();
const applyBtn = document.querySelector('button.applyBtn');

window.onload = function(){
    //요소를 동적으로 생성해서 body에 append하고 감춰두기
    const dateInput = document.createElement('input');
    dateInput.setAttribute('type', 'text');
    dateInput.setAttribute('id', 'dateInput');
    dateInput.setAttribute('value','날짜를 선택해주세요');
    dateInput.setAttribute('style','')
    document.body.appendChild(dateInput);
    //dateInput.style.display = 'none';
    dateInput.classList.add('hidden');

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
        "drops": "auto"
    });



    //시간 선택
    selectStrBtn.addEventListener("click",function(){
        this.classList.toggle('hidden');
        console.log(selectBox);
        selectBox.classList.toggle('hidden')
    })

    for(datepicker of datepickers){
        datepicker.addEventListener('click', function(){
            this.querySelector('span').innerHTML = ''
            this.querySelector('span').appendChild(dateInput);
            $('#dateInput').show()
            dateInput.click();

        })
    }
    dateInput.addEventListener('click',function(){
        dateInput.classList.toggle('hidden');
    })

}








