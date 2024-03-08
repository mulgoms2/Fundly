$(document).ready(function () {
    let selectedPayMeans; // 대표 결제수단
    let payMeansList;
    let payMeansCnt;
    fetchData(); // 페이지 로드 시 데이터 가져오기

    function fetchData() {
        $.ajax({
            type: "GET",
            url: '/pay/list',
            success: function (data) {
                // 결제수단 데이터가 있는 경우, cardList 렌더링
                if (data.pageHandler.totalCnt != null && data.pageHandler.totalCnt != 0) {
                    $('#emptyList').hide();
                    $('#cardList').show();

                    // 결제수단 리스트 저장
                    payMeansList = data.payMeansDtoList;
                    payMeansCnt = payMeansList.length;

                    // 주문페이지에 대표 결제수단 렌더링
                    renderPayOnOrderPage(payMeansList[0]);

                    console.log("첫 렌더링 : " + selectedPayMeans.pay_means_id)

                } else { // 없는 경우, emptyList 렌더링
                    $('#emptyList').show();
                    $('#cardList').hide();
                }
            },
            error: function () {
                alert("결제수단 조회에 실패했습니다. 다시 시도해주세요.");
            }
        })
    }

    // 주문페이지에 대표 결제수단 렌더링
    function renderPayOnOrderPage(payMeans) {
        // 대표 결제수단으로 저장
        updateSelectedPayMeans(payMeans)
        // 주문페이지에 대표 결제수단 렌더링
        appendPayOnOrderPage()
    }

    // 결제수단변경 팝업창에 결제수단 리스트 렌더링
    function renderPayOnChangePopup(payMeansList) {
        // 리스트 렌더링하기 전 초기화
        $('#payMeansList').html('');
        // 결제수단 리스트 렌더링
        payMeansList.forEach(function(payMeansDto) {
            appendPayOnChangePopup(payMeansDto);
        });
        // listElemWrap 클래스의 마지막 요소는 border-bottom 스타일 속성 제거
        $('.listElemWrap:last').removeClass('listElemWrapBorderBtm');
    }

    // 결제수단변경 팝업창에 결제수단 개수 렌더링
    function renderPayCntOnChangePopup(payMeansCnt) {
        $('.pHeader em').html(payMeansCnt);
    }

    // 선택된 결제수단을 저장하는 메서드
    function updateSelectedPayMeans(payMeans) {
        selectedPayMeans = payMeans;
    }

    // 결제수단변경 팝업창에 결제수단 리스트 추가
    function appendPayOnChangePopup(payMeansDto) {
        let newElement = document.createElement('div');
        newElement.classList.add('listElemWrap', 'listElemWrapBorderBtm');
        newElement.innerHTML = `
                <div class="radioSelectorWrap">
                    <label class="ListElemLabel">
                        <input name="payment" type="radio" class="radioSelector" value='${JSON.stringify(payMeansDto)}'
                        ${selectedPayMeans === payMeansDto? 'checked' : ''}>
                    </label>
                </div>
                <div class="payInfoWrap">
                    <div class="cardImg">
                        <img class="imgBox" src="${payMeansDto.file_path}${payMeansDto.file_name}${payMeansDto.file_extension}" alt="결제수단 이미지">
                    </div>
                    <div class="cardDetailWrap">
                        <div class="companyName">${payMeansDto.card_co_type}
                            ${payMeansDto.default_pay_means_yn === 'Y' ? '<span class="default_tag">기본</span>' : ''}
                        </div>
                        <div class="cardNumber">************ ${payMeansDto.card_no}</div>
                    </div>
                </div>
        `;
        document.getElementById('payMeansList').appendChild(newElement);
    }

    // 주문페이지에 대표 결제수단 데이터 맵핑
    function appendPayOnOrderPage() {
        // 대표카드 정보 렌더링
        $('.cardImg').html(`<img class="imgBox" src="${selectedPayMeans.file_path}${selectedPayMeans.file_name}${selectedPayMeans.file_extension}" alt="결제수단 이미지">`); // 카드 이미지
        $('.cardContentTitle').html(`${selectedPayMeans.card_co_type}`); // 카드사 이름
        $('.cardContentNumber').html(`************ ${selectedPayMeans.card_no}`); // 카드번호

        // 기본결제수단 여부에 따른 렌더링
        if (selectedPayMeans.default_pay_means_yn === 'Y') { // 기본결제수단인 경우
            $('.cardContentTitle').append(`<span class="basicMark cardDefaultTag">기본</span>`) // '기본' 태그 보여주기
            $('.defaultPayCheckboxWrapper').hide();
        } else { // 기본결제수단이 아닌 경우
            $('.defaultPayCheckboxWrapper').show();
        }

        // 카드 타입에 따른 렌더링
        if (selectedPayMeans.card_type === 0) { // 신용카드인 경우
            $('.payNoticeBannerWrapper').hide();
        } else { // 체크카드인 경우
            $('.payNoticeBannerWrapper').show();
        }
    }

    // 주문페이지 '변경' 버튼 클릭 이벤트
    $("#payChangeBtn").click(function (e) {
        e.preventDefault();
        // 결제수단변경 팝업 open
        $('#popChange').bPopup();
        // 결제수단 리스트 렌더링
        renderPayOnChangePopup(payMeansList);
        // 결제수단 개수 렌더링
        renderPayCntOnChangePopup(payMeansCnt);

        console.log("변경 click : " + selectedPayMeans.pay_means_id);
    })

    // 결제수단변경 팝업 '선택 완료' 버튼 클릭 이벤트
    $("#paySelectBtn").click(function () {
        // 체크된 input 요소의 value 에서 결제수단 데이터를 가져온다.
        let checkedPayMeans = JSON.parse($('input[name="payment"]:checked').val()); // 문자열을 JSON 객체로 변환
        // 팝업 close
        $('#popChange').bPopup().close();
        // 주문페이지에 대표 결제수단을 렌더링한다.
        renderPayOnOrderPage(checkedPayMeans)

        console.log("선택완료 click : ", selectedPayMeans.pay_means_id);
    })

    // TODO: 주문페이지 '후원하기' 버튼 클릭 이벤트
    $("#orderBtn").click(function () {
        // 선택된 결제수단 데이터: selectedPayMeans
        console.log(selectedPayMeans)

        // '기본 결제수단으로 등록'을 선택한 경우
        if ($('#updateDefaultCheckbox').is(':checked')) {
            // 기본 결제수단으로 update
            $.ajax({
                type: "POST",
                url: "/pay/update",
                data: {user_id: selectedPayMeans.user_id, pay_means_id: selectedPayMeans.pay_means_id},
                success: function () {
                    alert("기본 결제수단 지정에 성공했습니다.");
                    location.reload();
                },
                error: function () {
                    alert("기본 결제수단 지정에 실패했습니다.");
                }
            })
        }
    })
});