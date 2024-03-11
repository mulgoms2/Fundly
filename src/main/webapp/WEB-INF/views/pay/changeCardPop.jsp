<%--
  Created by IntelliJ IDEA.
  User: greta
  Date: 2024/03/07
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<div class="bPopWrap pFontStyle" id="popChange">
    <div class="pTit pTitle">
        결제수단 변경
        <div class="xBtnWrap">
            <button class="b-close">닫기</button>
        </div>
    </div>
    <div>
        <div class="pHeader">
            <span>
                <em></em>개의 결제수단이 있습니다.
            </span>
            <button id="payRegBtn" class="pHeaderRegBtn" type="button">+ 추가</button>
        </div>
        <form action="" id="form" method="post">
            <div id="payMeansList" class="listBoxWrap"></div>
        </form>
        <div class="paginationWrap">
            <ul class="paginationUl" >
                <li class="prevPageWrap">
                    <div class="prevPage">
                        <svg viewBox="0 0 48 48">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M32.8912 45.3014L12 23.913L32.8912 2.52471C33.5866 1.8251 34.7804 1.8251 35.4777 2.52471C36.1741 3.22333 36.1741 4.42368 35.4777 5.22224L17.1731 23.913L35.4777 42.7018C36.1741 43.4024 36.1741 44.6018 35.4777 45.4013C35.0788 45.8011 34.6819 46 34.1845 46C33.7855 45.9011 33.2881 45.7002 32.8912 45.3014Z"></path>
                        </svg>
                    </div>
                </li>
                <li class="pageNav"></li>
                <li class="nextPageWrap">
                    <div class="nextPage">
                        <svg viewBox="0 0 48 48">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M13.9071 46C13.4118 46 12.9164 45.8001 12.6192 45.4003C11.9257 44.7007 11.9257 43.5014 12.6192 42.7019L30.8493 24.0125L12.5201 5.22317C11.8266 4.52357 11.8266 3.32425 12.5201 2.5247C13.2136 1.8251 14.3034 1.8251 15.096 2.5247L36 24.0125L15.195 45.4003C14.7988 45.8001 14.3034 46 13.9071 46Z"></path>
                        </svg>
                    </div>
                </li>
            </ul>
        </div>
        <div class="popBottom">
            <button id="paySelectBtn" class="taskBtn">선택 완료</button>
        </div>
    </div>
</div>