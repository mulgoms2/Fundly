$(document).ready(function () {
    let page = 1;
    function fetchData() {
        $.ajax({
            type: "GET",
            url: `/pay/list?page=${page}`,
            success: function (data) {
                // 더보기 버튼 보여줄 것인지의 여부
                data.pageHandler.showNext? ($('.boxViewMoreWrapper').show()) : ($('.boxViewMoreWrapper').hide());

                // 결제수단 데이터가 있는 경우, 리스트 렌더링
                if (data.pageHandler.totalCnt != null && data.pageHandler.totalCnt != 0) {
                    $('.emptyList').hide();
                    data.payMeansDtoList.forEach(function(payMeansDto) {
                        appendPayMeansList(payMeansDto);
                    });
                } else { // 없는 경우, 없음 표시
                    $('.emptyList').show();
                    $('.boxViewMoreWrapper').hide();
                }
            },
            error: function () {
                alert("결제수단 조회에 실패했습니다. 다시 시도해주세요.");
            }
        })
    }

    function appendPayMeansList(payMeansDto) {
            let newElement = document.createElement('div');
            newElement.classList.add('listBoxElement');
            newElement.innerHTML = `
            <div class="thumbnail">
                <div class="cardImg">
                    <img class="imgBox" src="${payMeansDto.file_path}${payMeansDto.file_name}${payMeansDto.file_extension}" alt="결제수단 이미지">
                </div>
            </div>
            <div class="content">
                <div class="companyName">${payMeansDto.card_co_type}
                    ${payMeansDto.default_pay_means_yn === 'Y' ? '<span class="default_tag">기본</span>' : ''}
                </div>
                <div class="cardNumber">************ ${payMeansDto.card_no}</div>
            </div>
            <div class="func">
                <button type="button" class="meatballBtn">
                    <img src="/static/img/meatball.svg" alt="btn" />
                </button>
                <div class="clickBtnContent">
                    <button type="button" class="defaultSetBtn" data-user-id="${payMeansDto.user_id}" data-pay-means-id="${payMeansDto.pay_means_id}">기본 결제수단 지정</button>
                    <button type="button" class="removeBtn" data-user-id="${payMeansDto.user_id}" data-pay-means-id="${payMeansDto.pay_means_id}">삭제</button>
                </div>
            </div>
        `;
            document.getElementById('pay_means_data').appendChild(newElement);
    }

    // 페이지 로드 시 데이터 가져오기
    fetchData();

    // 다른 영역 클릭 시, 요소 숨기기
    $(document).click(function (event) {
        if (!$(event.target).closest('.meatballBtn').length) {
            $('.clickBtnContent').hide();
        }
    });

    // 동적으로 생성된 요소에 이벤트 바인딩하기 : 부모에 이벤트를 등록하고, 선택자를 통해 동적으로 생성된 자식 요소에도 이벤트를 적용한다.
    // 미트볼 버튼 클릭 이벤트
    $('#pay_means_data').on('click', '.meatballBtn', function() {
        let clickBtnContent = $(this).siblings('.clickBtnContent');
        clickBtnContent.toggle();
    });

    // 삭제 버튼 클릭 이벤트
    $('#pay_means_data').on('click', '.removeBtn', function () {
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

    // 기본 결제수단 지정 클릭 이벤트
    $('#pay_means_data').on('click', '.defaultSetBtn', function () {
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

    // 더보기 버튼 클릭 이벤트
    $("#viewMoreBtn").click(function () {
        page++;
        fetchData();
    })
})