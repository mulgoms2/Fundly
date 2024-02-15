

$(function () {
// common start
    //* tabMenu *//
    $('.tabBtn > .btn').click(function(){
        var $this = $(this);
        var idx = $this.index();
        if ( $this.hasClass('on') ){
            return false;
        } else {
            $this.addClass('on').siblings('.btn').removeClass('on');
            $this.parents('.tabMenu').siblings('.tabCont').children('.cont').eq(idx).fadeIn(0).addClass('on').siblings('.cont').fadeOut(0).removeClass('on');
        }
    });

    //* .fixBG *//
    $('.fixBG').click(function(){
        $(this).fadeOut(200);
        $('.popCont').fadeOut(200).removeClass('tag');
    });


    //* .popDf *//
    $('.popWrap .taskButton , .popWrap .xBtnWrap').click(function(){
        if( $(this).hasClass('close') ) {
            $(this).parents('.popWrap').find('.popCont').fadeOut(100).removeClass('on fixCont tag');
            $('.fixBG').fadeOut(200);
        } else {
            $(this).parents('.popWrap').find('.popCont').fadeIn(100).addClass('on fixCont tag');
            $('.fixBG').fadeIn(200);
        }
    });

    $('#popReportBtn').bind('click', function(e) { //프로젝트 신고하기
        e.preventDefault();
        var bPopup = $('#popReport').bPopup();
    });


    //* scroll button 클릭 시 영역 이동 *//
    $(".scrBtnWrap > .btn").click(function() {
        var $this = $(this);
        // 버튼에 'on' 클래스 추가
        if ( $this.hasClass('on') ){
            return false;
        } else {
            $this.addClass('on').siblings('.btn').removeClass('on'); // 버튼의 형제들에게서 'on' 클래스 제거
        }
        // 해당 섹션으로 스크롤
        var target = $(this).data("target");
        $('html, body').animate({
            scrollTop: $(target).offset().top
        }, 0);
    });


    //* 헤더 고정 *//
    $(window).scroll(function() {
        if ($(this).scrollTop() > 870) { // 스크롤 높이가 850px를 넘으면
            $('.tabMenu').addClass('fix'); // 헤더를 화면 상단에 고정
            $('.tabScr').addClass('fix'); // 헤더를 화면 상단에 고정
        } else {
            $('.tabMenu').removeClass('fix'); // 그렇지 않으면 헤더를 숨김
            $('.tabScr').removeClass('fix'); // 그렇지 않으면 헤더를 숨김
        }
    });

    //* 탭메뉴_레벨2 스크롤 고정 *//
    $(window).scroll(function() {
        if ($(this).scrollTop() > 1340) { // 스크롤 높이가 1340px를 넘으면
            $('.gftSlct').addClass('fix'); // 상품을 화면 상단에 고정
        } else {
            $('.gftSlct').removeClass('fix'); // 그렇지 않으면 상품을 숨김
        }
    });

    //* 스와이퍼 *//
    var swiper = new Swiper(".mySwiper", {
        cssMode: true,
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        pagination: {
            el: ".swiper-pagination",
        },
        mousewheel: true,
        keyboard: true,
    });

    //* 하트 클릭 시 색상 변경 *//
    // $('.icoImg').click(function(){
    //     if ( $(this).hasClass('on') ){
    //         return false;
    //     } else {
    //         $(this).addClass('on').siblings('.icoImg').removeClass('on'); // 버튼의 형제들에게서 'on' 클래스 제거
    //     }
    // });

    //* 카운트 올라가는 버튼(미완성) *//
    var unitPrice = 29800; // 각 단위당 가격 설정
    var totalPrice = unitPrice; // 총 가격 초기값 설정

    // var products = {
    //     "product1": 10, // 제품1의 기본 가격 설정 (천 원)
    //     "product2": 20, // 제품2의 기본 가격 설정 (천 원)
    //     // 필요에 따라 다른 제품 추가 가능
    // };

    // 가격 포맷팅 함수
    function formatPrice(price) {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    // - 버튼 클릭 시 처리
    $("#decrBtn").click(function() {
        var input = $("#numIpt");
        var currentValue = parseInt(input.val());
        if (currentValue > 1) {
            input.val(currentValue - 1);
            totalPrice -= unitPrice;
            $("#formattedPrice").text(formatPrice(totalPrice));
        }
    });

    // + 버튼 클릭 시 처리
    $("#incrBtn").click(function() {
        var input = $("#numIpt");
        var currentValue = parseInt(input.val());
        input.val(currentValue + 1);
        totalPrice += unitPrice;
        $("#formattedPrice").text(formatPrice(totalPrice));
    });

    // 초기화
    $("#formattedPrice").text(formatPrice(totalPrice));

    // 입력 값 검증
    $("#valueIpt").on("input", function() {
        var inputValue = $(this).val();
        if (parseInt(inputValue) > 10000000) {
            alert("입력된 값이 10,000,000을 초과할 수 없습니다.");
            $(this).val(10000000);
        }
        else {
            var total = unitPrice * inputValue;
            $("#formattedPrice").text(formatPrice(total));
        }
    });

    // 총 가격을 업데이트하는 함수
    function updateTotalPrice() {
        var productPrice = parseInt($("#formattedPrice").text().replace(/,/g, '')); // 제품 가격을 가져옴
        var additionalPrice = parseInt($("#valueIpt").val()); // 추가 후원금을 가져옴

        // 추가 후원금이 유효한 숫자인지 확인
        if (!isNaN(additionalPrice)) {
            // 제품 가격과 추가 후원금을 더하여 총 가격을 계산함
            var totalPrice = productPrice + additionalPrice;
            // 총 가격을 표시함
            $("#calcTotal").text(totalPrice.toLocaleString());
        } else {
            // 추가 후원금이 숫자가 아닌 경우, 총 가격을 초기화함
            $("#calcTotal").text('0');
        }
    }

    // 페이지 로드시 총 가격 업데이트
    updateTotalPrice();

    // 제품 가격이 변경될 때마다 총 가격 업데이트
    $("#formattedPrice").on("DOMSubtreeModified", function() {
        updateTotalPrice();
    });

    // 추가 후원금이 변경될 때마다 총 가격 업데이트
    $("#valueIpt").on("input", function() {
        updateTotalPrice();
    });

    // 상품 클릭 시 선물 선택 활성화
    $('.gftEl').click(function() {
        $('.gftslctGroup').show();
        // $('.prdtSlct').hide();
    });

    // close 버튼 클릭 시 선물 선택 비활성화
    $('.b-close btnSlct').click(function() {
        $('.gftslctGroup').hide();
    });
});
