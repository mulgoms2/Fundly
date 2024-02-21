$(document).ready(function() {
    // 초기값 설정
    var productPriceStr = $('#formattedPrice').text(); // 상품 가격 문자열
    var productPrice = parseInt(productPriceStr.replace(/,/g, '')); // 쉼표 제거 후 정수 변환
    $('#formattedPrice').text(productPrice.toLocaleString()); // 쉼표 추가된 문자열로 다시 설정
    var totalPrice = productPrice;
    var maxTotalPrice = 10000000; // 최대 허용 금액


    // 감소 버튼 클릭 시
    $('#decrBtn').click(function() {
        var num = parseInt($('#numIpt').val());
        if (num > 1) {
            num--;
            $('#numIpt').val(num);
            calculateTotal();
        }
    });

    // 증가 버튼 클릭 시
    $('#incrBtn').click(function() {
        var num = parseInt($('#numIpt').val());
        num++;
        $('#numIpt').val(num);
        calculateTotal();
    });

    // 추가 후원금 입력 시
    $('#valueIpt').on('input', function() {
        calculateTotal();
    });

    // 총 금액 계산 함수
    function calculateTotal() {
        var quantity = parseInt($('#numIpt').val());
        var donation = parseInt($('#valueIpt').val()) || 0;
        if(isNaN(quantity)) {
            quantity = 1;
            $('#numIpt').val(quantity);
        }
        if(isNaN(donation)) {
            donation = 0;
            $('#valueIpt').val('');
        }
        totalPrice = (productPrice * quantity) + donation;
        if(totalPrice > maxTotalPrice) {
            alert('총 금액은 10,000,000원이 넘을 수 없습니다. 다시 조정해주세요.');
            $('.gftCalcbtn').prop('disabled', true).css('background-color', 'grey');
        } else {
            $('.gftCalcbtn').prop('disabled', false).css('background-color', '');
        }
        $('#calcTotal').text(totalPrice.toLocaleString()); // 쉼표 추가
    }

    // 초기 총 금액 설정
    calculateTotal();

    // 상품 클릭 시 선물 선택 활성화
    $('.gftEl').click(function() {
        $('.gftslctGroup').addClass('on');
    });

    // close 버튼 클릭 시 선물 선택 비활성화
    $(".xBtnWrap > .b-close.btnSlct").click(function() {
        $(".gftslctGroup").removeClass('on');
    });

    // 선물 추가하기 버튼 클릭 시
    $('.gftAddbtn').click(function() {
        // class="gftnotFnd"로 지정된 영역으로 스크롤 이동
        $('html, body').animate({
            scrollTop: $('#gftnotFnd').offset().top
        }, 1000);
    });

    // 게시글 더보기 클릭 시 게시글 상세페이지로 이동할 수 있게 처리
    $(".moreBtn").click(function(){
        $(".commuBox").addClass("on");
        $(".cardRow > .cardItem").hide();
    })

    //게시글 작성 클릭 시 나오는 뷰
    $('#iptTowrite').click(function() {
        $('.coMmu > .commuGroup').hide();
        $('.coMmu > .gftInfo').hide();
        $('.commuCmt').show();
    })
});
