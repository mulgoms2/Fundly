/* tap */
let tabId = "itemProfile";
showTab(tabId);

function showTab(tabId) {
    $('.tabContent').hide(); // 전체 숨김
    $("#" + tabId).show(); // 선택된 탭만 보여줌
}

$(document).ready(function() {
    // tab 클릭 이벤트 처리
    $('.tapItem').click(function(){
        tabId = $(this).attr('data-tab');
        showTab(tabId);
    })
})