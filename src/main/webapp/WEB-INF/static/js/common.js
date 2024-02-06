/*------------------------------------
date: 2020.00.00
developed by: smartport
developer url: smartport
------------------------------------*/




$(function () {
    // left-menu 
    var menu = $('.lftMenuWrap')
    var menuPos= menu.position();
    $('.mnBtn').click(function () {
        menu.css({"left":"-270px"});
        $('.mnBtnbl').css({"display":"block"});
        $(this).css({"display":"none"});
        $('.conWrap').css({"width":"100%", "position" : "absolute"});
    });
    $('.mnBtnbl').click(function () {
        menu.css({"left":"0"});
        $('.mnBtn').css({"display":"block"});
        $(this).css({"display":"none"});
        $('.conWrap').css({"width":"calc(100% - 270px)", "position" : "relative"});
    });

// common start


    // left lnb
    $(".lnb > li > a").click(function(){
        var $sub = $(this).siblings(".sub");
        $(".sub").not($sub).slideUp("fast");
        $sub.slideToggle("fast");
        $(".lnb > li").not($(this).parent("li")).removeClass("on");
        $(this).parent("li").toggleClass("on");
        return false;
    });


    //* check/radio *//
    $('.chkBox > .ipt').click(function(){
        var $this = $(this).parent('.chkBox');
        // remove check
        if ( $this.hasClass('on') ){
            $this.removeClass('on');
            $this.find('.ipt').val('체크안됨');
            $this.find('.ipt').attr('checked', false);
            // All
            if( $this.hasClass('all') ){
                var $chk = $this.parents('.chkWrap').find('.chkBox');
                $chk.removeClass('on');
                $chk.find('.ipt').val('체크안됨');
                $chk.find('.ipt').attr('checked', false);
            }
        // add check
        } else {
            $this.addClass('on');
            $this.find('.ipt').val('체크됨');
            $this.find('.ipt').attr('checked', true);
            // All
            if( $this.hasClass('all') ){
                var $chk = $this.parents('.chkWrap').find('.chkBox');
                $chk.addClass('on');
                $chk.find('.ipt').val('체크됨');
                $chk.find('.ipt').attr('checked', true);
            }
        }
        // All check
        var chklen = $this.parents('.chkWrap').find('.chkBox').length - 1;
        var chkOnlen = $this.parents('.chkWrap').find('.chkBox.on').not('.chkBox.all').length;
        var chkAll = $this.parents('.chkWrap').find('.chkBox.all');
        if ( chklen == chkOnlen ){
            chkAll.addClass('on');
            chkAll.find('.ipt').val('체크됨');
            chkAll.find('.ipt').attr('checked', true);
        } else {
            chkAll.removeClass('on');
            chkAll.find('.ipt').val('체크안됨');
            chkAll.find('.ipt').attr('checked', false);
        }
    });
    $('.rdoBox > .ipt').click(function(){
        var $this = $(this).parent('.rdoBox');
        $this.addClass('on');
        $this.find('.ipt').attr('checked', true);
        $this.siblings('.rdoBox').removeClass('on');
        $this.siblings('.rdoBox').find('.ipt').attr('checked', false);
    });


    //* calendar(jqueryUI) *//
    $('.dateBox .val').datepicker({
        showAnim: "slideDown",
        changeMonth: true,
        changeYear: true,
        showOn: "both", // or "button" or "focus"
        //buttonImage: "../image/bg_datepicker.png",
        buttonImageOnly: false,
        buttonText: "날짜 선택",
        selectOtherMonths: true,
        closeText: "닫기"
    });
    $.datepicker.setDefaults({
        numberOfMonths: [1,2],
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년 '
    });


    //* daterangepicker*//
    $('.dateRgBox .val').daterangepicker({
        //"startDate": "09/14/2018",
        //"endDate": "09/18/2018",
    });
    $('.dateRgBox.Sg .val').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        //minYear: 2017,
        //maxYear: 2018
    });
    $('.dateRgBoxTm .val').daterangepicker({
        singleDatePicker: true,
        timePicker: true,
        locale: {
            format: 'YYYY/MM/DD     hh:mm:ss',
            // separator: '    ~    ',
        }
    });

    //* select - jqueryUI *//
    $( ".slctBoxJq > div > select").selectmenu();


    //* tabMenu *//
    $('.tabBtn > .btn').click(function(){
        var $this = $(this);
        var idx = $this.index();
        if ( $this.hasClass('on') ){
            return false;
        } else {
            $this.addClass('on').siblings('.btn').removeClass('on');
            $this.parents('.tabMenu').children('.tabCont').children('.cont').eq(idx).fadeIn(0).addClass('on').siblings('.cont').fadeOut(0).removeClass('on');
        }
    });


    //* close : click body *//
    $(document).mousedown(function(e){
        var container = $('.tag');
        if (!container.is(e.target) && container.has(e.target).length === 0){
            container.removeClass('tag');
            $('.smenu, .slctBox').removeClass('on').children('ul').fadeOut(100);
            $('.popCont').fadeOut(150).removeClass('on fixCont');
            $('.fixBG').fadeOut(200);
        }
    });
    
    
    //* layer popup *//
    $('.btnWrap .backList').click(function(){
        $('.mdBackground').addClass('show');
    });
    $('.lstBtnWrap .btnRemove').click(function(){
        $('.mdBackground').addClass('show');
    });
    $('.btnWrap .cancel').click(function(){
        $('.mdBackground').removeClass('show');
    });
    $('.btnWrap .btnBack').click(function(){
        $('.mdBackground').removeClass('show');
    });


// common end




});
