
$(function () {

    //* check *//
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
});