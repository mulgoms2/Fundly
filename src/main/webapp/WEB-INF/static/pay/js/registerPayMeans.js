// 팝업 이동 비활성화
$.fn.bPopup.defaults = $.extend({}, $.fn.bPopup.defaults, {
    follow: [false, false]  // 이동 비활성화
});

$(document).ready(function () {
    // 페이지 리로드 시, 결제수단등록 폼 초기화
    $('#form')[0].reset();
    resetErrMsg();

    // 기본결제수단으로지정 체크박스 값 세팅
    setDefaultPayMeansValue()

    function setDefaultPayMeansValue() {
        let value = $('#default_pay_means_yn').is(':checked') ? 'Y' : 'N';
        $('#default_pay_means_yn').val(value.trim());

        console.log($('#default_pay_means_yn').val() + " " + $('#default_pay_means_yn').val().length);
    }

    // 결제수단등록 팝업 유효성 에러 메세지 리셋
    function resetErrMsg() {
        $('#card_pwd_err_msg').text('');
        $('#own_birth_err_msg').text('');
        $('#card_no_err_msg').text('');
        $('#card_valid_date_err_msg').text('');
        $('#card_co_info_agree_yn').text('');
    }

    // 추가(등록) 버튼 클릭 시 팝업창 open
    $("#payRegBtn, #emptyList").click(function (e) {
        e.preventDefault();
        $('#popRegister').bPopup();
        resetErrMsg();
    })

    // esc 버튼 클릭 시 form 초기화
    $(document).keydown(function (event) {
        if (event.keyCode == 27 || event.which == 27) {
            $('#form')[0].reset();
            resetErrMsg();
        }
    });

    // 팝업창 닫기 버튼 클릭 시 form 초기화
    $('.b-close').click(function () {
        $('#form')[0].reset();
        resetErrMsg();
    });

    // Register pop-up form
    let currentYear = new Date().getFullYear();
    let selectedYear = currentYear;
    let selectedMonth = '01';

    // years select
    for (let i = currentYear; i <= currentYear + 10; i++) {
        let yearsSelectedOption = '';
        if (i == selectedYear) {
            yearsSelectedOption = 'selected';
        }
        let yearsOption = '<option value=' + i + ' ' + yearsSelectedOption + '>' + i + '</option>';
        $('#years').append(yearsOption);
    }

    // months select
    for (let i = 1; i <= 12; i++) {
        let monthsSelectedOption = '';
        let monthValue = i < 10 ? '0' + i : '' + i; // 일의 자리 숫자면 앞에 0붙이기
        let monthName = i + '월';
        if (monthValue === selectedMonth) {
            monthsSelectedOption = 'selected';
        }
        let monthsOption = '<option value=' + monthValue + ' ' + monthsSelectedOption + '>' + monthName + '</option>';
        $('#months').append(monthsOption);
    }

    $('#card_valid_date').val($('#years').val() + '-' + $('#months').val());

    $('#months, #years').change(function () {
        $('#card_valid_date').val($('#years').val() + '-' + $('#months').val());
    })

    $('#card_co_info_agree_yn').change(function () {
        $('#card_co_info_agree_yn_err_msg').text(
            !$(this).prop("checked") ? '결제사 정보제공에 동의하셔야 합니다.' : ''
        );
    });

    $('#default_pay_means_yn').change(function(){
        setDefaultPayMeansValue();
    });

    $("#submitBtn").click(function () {
        resetErrMsg(); // 초기화
        $('#card_no').val($('#card_no_1').val() + $('#card_no_2').val() + $('#card_no_3').val() + $('#card_no_4').val());

        let formData = {
            own_type: $('input[name="own_type"]:checked').val().trim(),
            card_no: $('#card_no').val().trim(),
            card_valid_date: $('#card_valid_date').val().trim(),
            own_birth: $('#own_birth').val().trim(),
            card_pwd: $('#card_pwd').val().trim(),
            card_co_info_agree_yn: $('#card_co_info_agree_yn').val().trim(),
            default_pay_means_yn: $('#default_pay_means_yn').val().trim()
        };

        if (!$("#card_co_info_agree_yn").prop("checked")) {
            $('#card_co_info_agree_yn_err_msg').text('결제사 정보제공에 동의하셔야 합니다.');
        } else {
            $.ajax({
                type: "POST",
                url: "/pay/register",
                data: formData,
                success: function () {
                    $('#popRegister').bPopup().close(); // 팝업창 닫기
                    alert("결제수단 등록에 성공했습니다.");
                    location.reload();
                },
                error: function (e) {
                    console.log(e)
                    const code = e.responseJSON.code;
                    let inputErrMsg = "";

                    if (code === "PWD_NOT_FOUND") {
                        inputErrMsg = "유효하지 않은 형식입니다. (숫자 2자리 입력)";
                        $('#card_pwd_err_msg').text(inputErrMsg);
                    } else if (code === "PWD_INVALID") {
                        inputErrMsg = "비밀번호가 일치하지 않습니다.";
                        $('#card_pwd_err_msg').text(inputErrMsg);
                    } else if (code === "BIRTH_NOT_FOUND") {
                        inputErrMsg = "유효하지 않은 형식입니다. (숫자 6자리 입력)";
                        $('#own_birth_err_msg').text(inputErrMsg);
                    } else if (code === "BIRTH_INVALID") {
                        inputErrMsg = "입력값이 일치하지 않습니다.";
                        $('#own_birth_err_msg').text(inputErrMsg);
                    } else if (code === "CARD_NUM_NOT_FOUND") {
                        inputErrMsg = "카드번호를 입력하셔야 합니다.";
                        $('#card_no_err_msg').text(inputErrMsg);
                    } else if (code === "CARD_NUM_INVALID") {
                        inputErrMsg = "카드번호가 일치하지 않습니다.";
                        $('#card_no_err_msg').text(inputErrMsg);
                    } else if (code === "EXPIRY_NOT_FOUND") {
                        inputErrMsg = "유효기간을 입력하셔야 합니다.";
                        $('#card_valid_date_err_msg').text(inputErrMsg);
                    } else if (code === "EXPIRY_INVALID") {
                        inputErrMsg = "유효기간이 일치하지 않습니다.";
                        $('#card_valid_date_err_msg').text(inputErrMsg);
                    } else if (code == "CARD_INFO_INVALID") {
                        alert("잘못된 카드 정보입니다. 다시 입력해 주세요.")
                    } else if (code === "CARD_UNSUPPORTED") {
                        alert("지원하지 않는 카드사입니다. 다른 카드를 등록해 주세요.");
                    } else if (code === "PWD_LIMIT_EXCEEDED") {
                        alert("비밀번호 입력 횟수를 초과하였습니다. 10분 후 다시 등록해 주세요.");
                    } else { // PG_NOT_FOUND or PORTONE_SERVER_ERROR or FUNDLY_SERVER_ERROR or UKNOWN_REG_ERROR : 관리자에게 문의하도록 알림
                        alert("결제수단 등록에 실패했습니다. 펀들리 고객센터로 문의 바랍니다.");
                    }
                }
            })
        }
    })

    // 카드 소유주 유형(개인, 법인) radio 버튼 change 이벤트 처리
    $('input[name="own_type"]').change(function () {
        // 선택된 라디오 버튼의 값 가져오기
        let selectedValue = $('input[name="own_type"]:checked').val();

        // 결과를 출력할 엘리먼트 선택
        let outputElem = $('#own_type_output');

        // 선택된 값에 따라 다르게 출력
        // '개인'인 경우
        if (selectedValue === 'personal') {
            outputElem.html(
                '<div class="formOptionTitle" id="own_type_output">소유주 생년월일</div>'
                + '<div class="formOptionContent"><span>'
                + '<input class="formInput" id="own_birth" type="text" name="own_birth" inputmode="numeric" pattern="[0-9]*" maxlength="6" autocapitalize="off" autocomplete="new-password"'
                + 'placeholder="예) 920101" onKeyup="this.value=this.value.replace(/[^0-9]/g,\'\');">'
                + '</span></div>'
            )
        } // '법인'인 경우
        else if (selectedValue === 'corporate') {
            outputElem.html(
                '<div class="formOptionTitle" id="own_type_output">사업자등록번호</div>'
                + '<div class="formOptionContent"><span>'
                + '<input class="formInput" id="own_birth" type="text" name="own_birth" inputmode="numeric" pattern="[0-9]*" maxlength="6" autocapitalize="off" autocomplete="new-password"'
                + 'placeholder="예) 1078783297" onKeyup="this.value=this.value.replace(/[^0-9]/g,\'\');">'
                + '</span></div>'
            )
        }
    })
})