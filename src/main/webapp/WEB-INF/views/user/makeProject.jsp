<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-13
  Time: 오전 08:09
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value='/static/user/MakeProject.css'/>">
        <link rel="stylesheet" href="<c:url value='/static/user/usercommon.css'/>">
        <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>
    </head>

    <%--내가 만든 프로젝트--%>
    <body>
        <div class="main">
            <div class="title">내가 만든 프로젝트</div>

            <div class="projectState">
                <div class ="stateOn">전체</div>
                <div class ="stateOut">작성중</div>
                <div class ="stateOut">심사중</div>
                <div class ="stateOut">승인됨</div>
                <div class ="stateOut">반려됨</div>
                <div class ="stateOut">공개예정</div>
                <div class ="stateOut">진행중</div>
                <div class ="stateOut">종료</div>
            </div>

            <div class="projectMake">
                <div class="projectMakeListWrap">
                    <div class="projectMakeList">

                        <div class="projectMakeTotalCnt">작성 중 2</div>

                        <%-- Item --%>
                        <div class="projectMakeListItemWrap">
                            <div class="projectMakeItemWrap">
                                <div class="projectMakeItem">
                                    <div class ="projectItemImg">
                                        <div class="itemImg"></div>
                                    </div>
                                    <div class="projectItemContent">
                                        <div class="itemState">
                                            <div class="itemStateTag">작성중</div>
                                        </div>
                                        <div class="itemTitle">
                                            <span>냥냥간식월드</span>
                                        </div>
                                        <div class="itemDescription">
                                            <span>고양이 간식 제조합니다</span>
                                        </div>
                                    </div>
                                    <div class="projectItemBtn">
                                        <a href="" class="itemManagement">관리</a>
                                        <a href="" class="itemDel">삭제</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="projectMakeListItemWrap">
                            <div class="projectMakeItemWrap">
                                <div class="projectMakeItem">
                                    <div class ="projectItemImg">
                                        <div class="itemImg"></div>
                                    </div>
                                    <div class="projectItemContent">
                                        <div class="itemState">
                                            <div class="itemStateTag">작성중</div>
                                        </div>
                                        <div class="itemTitle">
                                            <span>냥냥토이월드</span>
                                        </div>
                                        <div class="itemDescription">
                                            <span>고양이 장난감 제조합니다</span>
                                        </div>
                                    </div>
                                    <div class="projectItemBtn">
                                        <a href="" class="itemManagement">관리</a>
                                        <a href="" class="itemDel">삭제</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>


            </div>




<%--            <p class="couponInfo font1">작성 중 2</p>--%>




        </div>
    </body>
</html>
