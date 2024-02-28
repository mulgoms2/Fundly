$(document).ready(function() {
    $('.rdoBox > .ipt').click(function(){
        var $this = $(this).parent('.rdoBox');
        $this.addClass('on');
        $this.find('.ipt').val('체크됨');
        $this.find('.ipt').attr('checked', true);
        $this.siblings('.rdoBox').removeClass('on');
        $this.siblings('.rdoBox').find('.ipt').val('체크안됨');
        $this.siblings('.rdoBox').find('.ipt').attr('checked', false);
    });
});