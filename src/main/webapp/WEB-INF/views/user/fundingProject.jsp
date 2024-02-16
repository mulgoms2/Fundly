<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>
    <%-- 후원한 프로젝트 --%>
    <div class="main">
        <div class="title">후원한 프로젝트</div>
        <div class="fundInfo">
            <div class="searchInfo">
                <div class="info">
                    <span class="font1">0</span>건의 후원 내역이 있습니다.
                </div>
                <div class="search">
                    <span class="searchBar">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <input type="text" inputmode="text" placeholder="프로젝트, 선물, 창작자를 검색하세요" autocapitalize="off"
                               autocomplete="off" class="txtSerach" value="">
                    </span>
                </div>
            </div>
            <div class="fundInfoList">
                <div class="fundInfoItem">
                    <div class="orderimg"></div>
                    <span>후원 내역이 없습니다</span>
                </div>
            </div>
        </div>
    </div>