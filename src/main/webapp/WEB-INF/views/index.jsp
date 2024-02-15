<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session ="false"%>

<%--<c:set var="loginInfo" value="${user_email=='' || user_email == null ? '로그인/회원가입' : user_name}"/>--%>

<%--<html>--%>
<%--<head>--%>
<%--    &lt;%&ndash;    <meta charset="UTF-8">&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <meta name="viewport" content="width=device-width, initial-scale=1.0">&ndash;%&gt;--%>
<%--    <title>텀블벅</title>--%>
<%--    <link rel="stylesheet" href="<c:url value='/static/main/common.css'/>">--%>
<%--    <link rel="stylesheet" href="<c:url value='/static/main/style.css'/>">--%>
<%--    <script src="https://kit.fontawesome.com/409fef83e5.js" crossorigin="anonymous"></script>--%>

<%--    <style>--%>

<%--    </style>--%>
<%--</head>--%>

<%--<body>--%>

<%--<div class="header">--%>
<%--    <div class="hd">--%>
<%--            <a href='<c:url value="/"/>'>--%>
<%--                <h1 class="logo">Fundly</h1>--%>
<%--            </a>--%>

<%--        <div class="lftSmn">--%>
<%--            <div class="pjtUp">--%>
<%--                <a href="#">프로젝트 올리기</a>--%>
<%--            </div>--%>
<%--            <div class="like">--%>
<%--                <i class="fa-regular fa-heart"></i>--%>
<%--            </div>--%>
<%--            <div class="alm">--%>
<%--                <i class="fa-regular fa-bell"></i>--%>
<%--            </div>--%>
<%--            <div class="userIf">--%>
<%--                <div class="infoGr" id = "loginInfo" >--%>
<%--                    <div class="ifImg">--%>
<%--                        <span></span>--%>
<%--                    </div>--%>
<%--                    <div class="ifTxt" >${loginInfo}</div>--%>
<%--&lt;%&ndash;                         onclick="toggleContent('MyPageList')">${loginInfo}</div>&ndash;%&gt;--%>

<%--                    <div class="MyPageList" id = "MyPageList">--%>
<%--                        <div class="pageItem" id = "Profile"><a href="<c:url value='/mypage/profile'/>">프로필</a></div>--%>
<%--                        <div class="pageItem" id = "Coupon"><a href="<c:url value='/mypage/coupon'/>">응원권</a></div>--%>
<%--                        <div class="line"></div>--%>
<%--                        <div class="pageItem" id = "fundingProject"><a href="<c:url value='/mypage/fundingProject'/>">후원한 프로젝트</a></div>--%>
<%--                        <div class="pageItem" id = "Likes"><a href="<c:url value='/mypage/likes'/>">관심 프로젝트</a></div>--%>
<%--                        <div class="line"></div>--%>
<%--                        <div class="pageItem" id = "Alarm"><a href="<c:url value='/mypage/alarm'/>">알림</a></div>--%>
<%--                        <div class="pageItem" id = "Message"><a href="<c:url value='/mypage/message'/>">메시지</a></div>--%>
<%--                        <div class="line"></div>--%>
<%--                        <div class="pageItem" id = "MakeProject"><a href="<c:url value='/mypage/makeProject'/>">내가 만든 프로젝트</a></div>--%>
<%--                        <div class="pageItem" id = "Setting"><a href="<c:url value='/mypage/setting'/>">설정</a></div>--%>
<%--                        <div class="line"></div>--%>
<%--                        <div class="pageItem" id = "LogOut"><a href="<c:url value='/login/logout'/>">로그아웃</a></div>--%>
<%--                    </div>--%>
<%--&lt;%&ndash;                        &lt;%&ndash;                    <div class="ifTxt">로그인/회원가입</div>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                        &lt;%&ndash;                    <div class="ifTxt">이한수</div>&ndash;%&gt;&ndash;%&gt;--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <!--스크롤 시 고정되는 header-->--%>
<%--    <div class="sd">--%>
<%--        <div class="lftCtmn">--%>
<%--            <!--메뉴 슬라이드-->--%>
<%--            <div class="cateMn">--%>
<%--                <div class="mnSld">--%>
<%--                    <div class="hmMenu">--%>
<%--                        <span></span>--%>
<%--                        <span></span>--%>
<%--                        <span></span>--%>
<%--                    </div>--%>
<%--                    카테고리--%>
<%--                </div>--%>
<%--                <div class="home">홈</div>--%>
<%--                <div class="popul">인기</div>--%>
<%--                <div class="new">신규</div>--%>
<%--                <div class="final">마감임박</div>--%>
<%--            </div>--%>
<%--            <!--검색창-->--%>
<%--            <div class="schMn">--%>
<%--                <input type="text" placeholder="검색어를 입력해주세요.">--%>
<%--                <div class="schIcon">--%>
<%--                    <i class="fa-solid fa-magnifying-glass"></i>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<div class="coNt">
    <!--왼쪽 배너-->
    <div class="lFt">
        <div class="bigBan">
            <a href="#">
                <img src="static/img/mdban01.webp" alt="">
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
            <div class="banBox"><!--1번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban01.webp" alt="">
                        </a>
                    </div>
                    <div class="faLike">
                        <button class="likeBtn"><!--하트 처리 미완성-->

                        </button>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                            <span>
                                <a href="#">아트북디자인더하트</a>
                            </span>
                        </div>
                        <div class="subTit">
                            <a href="#">12+1지신 노리개 부적 뱃지와 2024 새해 맞이</a>
                        </div>
                        <div class="pstTag">
                            <span>557% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banBox"><!--2번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban02.webp" alt="">
                        </a>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                                <span>
                                    <a href="#">실용·취미</a>
                                </span>
                            <span class="bar">
                                    <a href="#">MINCHANG</a>
                                </span>
                        </div>
                        <div class="subTit">
                            <a href="#">[출판세금] 1인출판사의 세금 실력을 특별하게 하는 법</a>
                        </div>
                        <div class="pstTag">
                            <span>101% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banBox"><!--3번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban03.webp" alt="">
                        </a>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                                <span>
                                    <a href="#">액세서리</a>
                                </span>
                            <span class="bar">
                                    <a href="#">Mirror in MIRROr</a>
                                </span>
                        </div>
                        <div class="subTit">
                            <a href="#">12+1지신 노리개 부적 뱃지와 2024 새해 맞이</a>
                        </div>
                        <div class="pstTag">
                            <span>1406% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banBox"><!--4번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban04.webp" alt="">
                        </a>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                                <span>
                                    <a href="#">디자인 문구</a>
                                </span>
                            <span class="bar">
                                    <a href="#">글리팅(glitting)</a>
                                </span>
                        </div>
                        <div class="subTit">
                            <a href="#">무하와 전통의 만남, 박지로 색칠하는 &lt;한복 글리팅&gt;</a>
                        </div>
                        <div class="pstTag">
                            <span>1422% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banBox"><!--5번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban05.webp" alt="">
                        </a>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                                <span>
                                    <a href="#">디자인 소품</a>
                                </span>
                            <span class="bar">
                                    <a href="#">아나레 ahnale</a>
                                </span>
                        </div>
                        <div class="subTit">
                            <a href="#">[한정]2024년을 행운의 해로! 마법의 도구 굿럭오마모리</a>
                        </div>
                        <div class="pstTag">
                            <span>722% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banBox"><!--6번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban06.webp" alt="">
                        </a>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                                <span>
                                    <a href="#">가방</a>
                                </span>
                            <span class="bar">
                                    <a href="#">Vao</a>
                                </span>
                        </div>
                        <div class="subTit">
                            <a href="#">한국의 MBTI를 걸쳐봐, 실용성과 특별함을 담은 꼬꼬지백</a>
                        </div>
                        <div class="pstTag">
                            <span>52% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banBox"><!--7번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban07.webp" alt="">
                        </a>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                                <span>
                                    <a href="#">주얼리</a>
                                </span>
                            <span class="bar">
                                    <a href="#">아사스</a>
                                </span>
                        </div>
                        <div class="subTit">
                            <a href="#">2024 용의 해, 내 손에 쥐어질 행운의 여의주 주얼리</a>
                        </div>
                        <div class="pstTag">
                            <span>383% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="banBox"><!--8번 배너-->
                <div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src="static/img/mnban08.webp" alt="">
                        </a>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                                <span>
                                    <a href="#">디자인 소품</a>
                                </span>
                            <span class="bar">
                                    <a href="#">인형뜨는 손</a>
                                </span>
                        </div>
                        <div class="subTit">
                            <a href="#">귀여운 아기용 용용이 뜨개인형!! [뜨개인형 DIY키트]</a>
                        </div>
                        <div class="pstTag">
                            <span>331% 달성</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--</body>--%>

<%--<script>--%>

<%--    // const MyPageList = document.getElementById('MyPageList');--%>
<%--    //--%>
<%--    // MyPageList.addEventListener("click",(e)=>{--%>
<%--    //--%>
<%--    //     if(e.target.id === "Profile"){--%>
<%--    //         // 프로필 화면으로--%>
<%--    //         return window.location.href = '/mypage/profile';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "Coupon") {--%>
<%--    //         // 응원권 화면으로--%>
<%--    //         return window.location.href = '/mypage/coupon';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "fundingProject") {--%>
<%--    //         // 후원한 프로젝트 화면으로--%>
<%--    //         return window.location.href = '/mypage/fundingProject';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "Likes") {--%>
<%--    //         // 관심 프로젝트--%>
<%--    //         return window.location.href = '/mypage/likes';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "Alarm") {--%>
<%--    //         // 알림 화면으로--%>
<%--    //         return window.location.href = '/mypage/alarm';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "Message") {--%>
<%--    //         // 메시지 화면으로--%>
<%--    //         return window.location.href = '/mypage/message';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "MakeProject") {--%>
<%--    //         // 내가 만든 프로젝트 화면으로--%>
<%--    //         return window.location.href = '/mypage/makeProject';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "Setting") {--%>
<%--    //         // 설정 화면으로--%>
<%--    //         return window.location.href = '/mypage/setting';--%>
<%--    //--%>
<%--    //     } else if(e.target.id === "LogOut") {--%>
<%--    //--%>
<%--    //         toggleContent("MyPageList");--%>
<%--    //         return window.location.href = '/login/l ogout';--%>
<%--    //     }--%>
<%--    // })--%>

<%--    function toggleContent(MyPageList){--%>

<%--        var content = document.getElementById(MyPageList);--%>

<%--        if(content.style.display==="none" || content.style.display === ""){--%>

<%--            content.style.display="block";--%>
<%--        } else{--%>
<%--            content.style.display="none";--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
<%--</html>--%>