$(document).ready(function () {
    // 페이지 로드 시 데이터 가져오기
    fetchData();
    let selectedCard;

    function fetchData() {
        $.ajax({
            type: "GET",
            url: '/pay/list',
            success: function (data) {
                // 결제수단 데이터가 있는 경우, cardList 렌더링
                if (data.pageHandler.totalCnt != null && data.pageHandler.totalCnt != 0) {
                    $('#emptyList').hide();
                    $('#cardList').show();

                    updateSelectedCardInfo(data.payMeansDtoList[0])
                    appendPayMeans(selectedCard)

                } else { // 없는 경우, emptyList 렌더링
                    $('#emptyList').show();
                    $('#cardList').hide();
                    // $('.boxViewMoreWrapper').hide();
                }
            },
            error: function () {
                alert("결제수단 조회에 실패했습니다. 다시 시도해주세요.");
            }
        })
    }

    // 선택된 결제수단을 저장하는 메서드
    function updateSelectedCardInfo(payMeansDto) {
        selectedCard = payMeansDto;
    }

    // 선택된 결제수단 데이터를 동적으로 맵핑
    function appendPayMeans(selectedCard) {
        // 대표카드 정보 렌더링
        $('.cardImg').html(`<img class="imgBox" src="${selectedCard.file_path}${selectedCard.file_name}${selectedCard.file_extension}" alt="결제수단 이미지">`); // 카드 이미지
        $('.cardContentTitle').html(`${selectedCard.card_co_type}`); // 카드사 이름
        $('.cardContentNumber').html(`************ ${selectedCard.card_no}`); // 카드번호

        // 기본결제수단 여부에 따른 렌더링
        if (selectedCard.default_pay_means_yn === 'Y') { // 기본결제수단인 경우
            $('.cardContentTitle').append(`<span class="basicMark cardDefaultTag">기본</span>`) // '기본' 태그 보여주기
            $('.defaultPayCheckboxWrapper').hide();
        } else { // 기본결제수단이 아닌 경우
            $('.defaultPayCheckboxWrapper').show();
        }

        // 카드 타입에 따른 렌더링
        if (selectedCard.card_type === 0) { // 신용카드인 경우
            $('.payNoticeBannerWrapper').hide();
        } else { // 체크카드인 경우
            $('.payNoticeBannerWrapper').show();
        }
    }

    // 후원하기 버튼 클릭 이벤트
    $('#orderBtn').click(function (e) {
        // '기본 결제수단으로 등록'에 체크되어 있는 경우
        if ($('#updateDefaultCheckbox').prop('checked')) {
            // console.log('default checked!!!')

            // 기본 결제수단으로 update
            let userId = selectedCard.user_id;
            let payMeansId = selectedCard.pay_means_id;

            $.ajax({
                type: "POST",
                url: "/pay/update",
                data: {user_id: userId, pay_means_id: payMeansId},
                success: function () {
                    // TODO: 추후 변경
                    alert("기본 결제수단 지정에 성공했습니다.");
                    location.reload();
                },
                error: function () {
                    // TODO: 추후 변경
                    alert("기본 결제수단 지정에 실패했습니다.");
                }
            })
        } else {
            // console.log('default unchecked!!!!')
        }
    })
});