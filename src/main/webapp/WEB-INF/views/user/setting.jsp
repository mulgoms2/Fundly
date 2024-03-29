<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<%--설정--%>
<div class="main setting">
    <div class="userSetting">
        <div class="title">설정</div>
        <div class="tapContainer">
            <div class="tapContainerList" id="divtapContainer">
                <span class="tapItem fontcolor" data-tab="tab1">프로필</span>
                <span class="tapItem fontcolor" data-tab="tab2">계정</span>
                <span class="tapItem fontcolor" data-tab="tab3">결제수단</span>
                <span class="tapItem fontcolor" data-tab="tab4">배송지</span>
                <span class="tapItem fontcolor" data-tab="tab5">알림</span>
            </div>
        </div>
    </div>
    <!-- 탭 값 -->
    <div class="tapContainerMain">
        <div id="tab1" class="tabContent"><jsp:include page="/user/profileBasic"/></div>
        <div id="tab2" class="tabContent"><jsp:include page="/user/account"/></div>
        <div id="tab3" class="tabContent"><jsp:include page="../pay/settingPayMeans.jsp"/></div>
<%--        <div id="tab4" class="tabContent"><jsp:include page="/user/adress"/></div>--%>
        <div id="tab4" class="tabContent">배송지페이지</div>
        <div id="tab5" class="tabContent">알림페이지</div>
    </div>
</div>
<script>
    function showTab(tabId) {
        $('.tabContent').hide(); // 전체 숨김
        $("#" + tabId).show(); // 선택된 탭만 보여줌
    }

    let tabId = 'tab1';
    showTab(tabId);

    $(document).ready(function() {
        // tab 클릭 이벤트 처리
        $('.tapItem').click(function(){
            tabId = $(this).attr('data-tab');
            showTab(tabId);
        })
    })
</script>