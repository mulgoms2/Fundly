let msg = "";
if (msg === "LIST_ERROR")  alert("결제수단 조회에 실패했습니다. 다시 시도해 주세요.");

$(document).ready(function () {
    // 추가 버튼 클릭 시 팝업창 open
    $("#regBtn").click(function (e) {
        e.preventDefault();
        $('#popRegister').bPopup();
    })

    // esc 버튼 클릭 시 form 초기화
    $(document).keydown(function(event) {
        if ( event.keyCode == 27 || event.which == 27 ) {
            $('#form')[0].reset();
        }
    });

    // 팝업창 닫기 버튼 클릭 시 form 초기화
    $('.b-close').click(function () {
        $('#form')[0].reset();
    })

    // 팝업창 열린 상태에서 ESC 버튼 누르면 form 초기화

    $(".meatballBtn").click(function () {
        let clickBtnContent = $(this).siblings('.clickBtnContent');
        clickBtnContent.toggle();
    });

    // 다른 영역 클릭 시, 요소 숨기기
    $(document).click(function (event) {
        if (!$(event.target).closest('.meatballBtn').length) {
            $('.clickBtnContent').hide();
        }
    });

    $(".removeBtn").click(function () {
        if (!confirm("정말로 삭제하시겠습니까?")) return; // '취소' 클릭 시
        let userId = $(this).data('user-id');
        let payMeansId = $(this).data('pay-means-id');

        let currentUnixTime = Math.floor((new Date()).getTime() / 1000); // 현재시간 unix time
        let threeMonthsAgoUnixTime = currentUnixTime - (3 * 30 * 24 * 60 * 60); // 현재 시간으로부터 3개월 전 unix time

        $.ajax({
            type: "POST",
            url: "/pay/remove",
            data: {
                user_id: userId, pay_means_id: payMeansId,
                from: threeMonthsAgoUnixTime, to: currentUnixTime
            },
            success: function () {
                alert("결제수단 삭제에 성공했습니다.");
                location.reload();
            },
            error: function () {
                alert("결제수단 삭제에 실패했습니다. 다시 시도해주세요.");
            }
        })
    })

    $(".defaultSetBtn").click(function () {
        let userId = $(this).data('user-id');
        let payMeansId = $(this).data('pay-means-id');

        $.ajax({
            type: "POST",
            url: "/pay/update",
            data: {user_id: userId, pay_means_id: payMeansId},
            success: function () {
                alert("기본 결제수단 지정에 성공했습니다.");
                location.reload();
            },
            error: function () {
                alert("기본 결제수단 지정에 실패했습니다.");
            }
        })
    })

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
            !$(this).prop("checked") ? '결제사 정보제공에 동의하셔야 합니다.': ''
        );
    });

    $("#submitBtn").click(function () {
        $('#card_no').val($('#card_no_1').val() + $('#card_no_2').val() + $('#card_no_3').val() + $('#card_no_4').val());

        let formData = {
            own_type: $('input[name="own_type"]:checked').val(),
            card_no: $('#card_no').val(),
            card_valid_date: $('#card_valid_date').val(),
            own_birth: $('#own_birth').val(),
            card_pwd: $('#card_pwd').val(),
            card_co_info_agree_yn: $('#card_co_info_agree_yn').val(),
            default_pay_means_yn: $('#default_pay_means_yn').val()
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
                    alert("결제수단 등록에 실패했습니다. 다시 시도해주세요.");
                    // console.log(e)
                    // let payMeansDto = e.responseJSON.payMeansDto;

                    // TODO: 유효성 검증 메시지
                    // 카드 비밀번호 앞 2자리: 비밀번호를 입력하셔야 합니다.
                    // 소유주 생년월일: 생년월일을 입력하셔야 합니다.
                    // 카드번호: 카드번호를 입력하셔야 합니다.
                    // 카드번호: 카드번호가 유효하지 않습니다.
                    // 카드 유효기간: 유효기간이 일치하지 않습니다.
                    // 카드 비밀번호 앞 2자리: 유효하지 않은 형식입니다.
                    // 소유주 생년월일: 입력값이 유효하지 않습니다.
                    // (완료) 결제사 정보제공에 동의하셔야 합니다.
                }
            })
        }
    })

    // 라디오 버튼의 변경 이벤트 감지
    $('input[name="own_type"]').change(function () {
        // 선택된 라디오 버튼의 값 가져오기
        let selectedValue = $('input[name="own_type"]:checked').val();

        // 결과를 출력할 엘리먼트 선택
        let outputElem = $('#own_type_output');

        // 선택된 값에 따라 다르게 출력
        if (selectedValue === 'personal') {
            outputElem.html(
                '<div class="formOptionTitle" id="own_type_output">소유주 생년월일</div>'
                + '<div class="formOptionContent"><span>'
                + '<input class="formInput" id="own_birth" type="text" name="own_birth" inputmode="numeric" pattern="[0-9]*" maxlength="6" autocapitalize="off" autocomplete="new-password"'
                + 'placeholder="예) 920101" onKeyup="this.value=this.value.replace(/[^0-9]/g,\'\');">'
                + '</span></div>'
            )
        }
        else if (selectedValue === 'corporate') {
            outputElem.html(
                '<div class="formOptionTitle" id="own_type_output">사업자등록번호</div>'
                + '<div class="formOptionContent"><span>'
                + '<input class="formInput" id="own_birth" type="text" name="own_birth" inputmode="numeric" pattern="[0-9]*" maxlength="6" autocapitalize="off" autocomplete="new-password"'
                + 'placeholder="예) 920101" onKeyup="this.value=this.value.replace(/[^0-9]/g,\'\');">'
                + '</span></div>'
            )
        }
    })
})