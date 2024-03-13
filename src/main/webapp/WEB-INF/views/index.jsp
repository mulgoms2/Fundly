<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<div class="coNt">
    <!--왼쪽 배너-->
    <div class="lFt">
        <div class="bigBan">
            <a href="#">
                <img src="/static/img/mdban01.webp" alt="">
            </a>
        </div>
        <div class="txtInfo">
            <a href="#">
                <div class="banTxt">
                    <div class="txTb">미리 준비하는 2024</div>
                    <div class="deSc">
                        선선해지는 가을이 왔다는 건 어느덧 내년을 준비할 시간이 됐다는 것.
                        텀블벅 캘린더∙다이어리로 올 한해의 추억을 기록하고, 2024년 계획을 세워보는 건 어떨까요?
                    </div>
                </div>
            </a>
        </div>
        <a class="linkView">
            <span>프로젝트 292개 전체보기</span>
            <img src="static/img/arrow.png" alt="">
        </a>
    </div>
    <div class="rGt">
        <div class="gRd"><!--Grid를 이용하기 위해 div로 한번 더 묶어줌-->
            <%--            이 자리에  프로젝트를 파싱한다 . --%>
        </div>
    </div>
</div>
<script src="/static/main/index.js"></script>
<script>
    const errmsg = "${errmsg}";
    if (errmsg === "USER_ERROR") alert("로그인 확인해 주세요.");
</script>