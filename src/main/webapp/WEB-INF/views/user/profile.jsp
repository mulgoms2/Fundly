<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-11
  Time: 오전 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>텀블벅</title>
    <link rel="stylesheet" href="/static/main/style.css">
    <link rel="stylesheet" href="/static/main/common.css">
    <link rel="stylesheet" href="/static/user/userProfile.css">
</head>
<body>
<div class="header">
    <div class="hd">
        <h1 class="logo">
            <a href="#"></a>
        </h1>
        <div class="lftSmn">
            <div class="pjtUp">
                <a href="#">프로젝트 올리기</a>
            </div>
            <div class="like">
                <i class="fa-regular fa-heart"></i>
            </div>
            <div class="alm">
                <i class="fa-regular fa-bell"></i>
            </div>
            <div class="userIf">
                <div class="infoGr">
                    <div class="ifImg">
                        <span></span>
                    </div>
                    <div class="ifTxt">김땡땡</div>
                </div>
            </div>
        </div>
    </div>
    <!--스크롤 시 고정되는 header-->
    <div class="sd">
        <div class="lftCtmn">
            <!--메뉴 슬라이드-->
            <div class="cateMn">
                <div class="mnSld">
                    <div class="hmMenu">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    카테고리
                </div>
                <div class="home">홈</div>
                <div class="popul">인기</div>
                <div class="new">신규</div>
                <div class="final">마감임박</div>
            </div>
            <!--검색창-->
            <div class="schMn">
                <input type="text" placeholder="검색어를 입력해주세요.">
                <div class="schIcon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="line"></div>

<div class="main">
    <!-- 프로필 & 탭 -->
    <div class="pageComponent">
        <div class="profilerHeader">

            <div class="profileInfo">
                <div class="ifImg"><span></span></div>
                <div class="userInfo">
                    <div class="userInfoDetail">
                        <div class="username">김땡땡</div>
                        <span class ="setting">
                  <a href=""></a>
                </span>
                    </div>
                    <div class="userJoindate">0주 전 가입</div>
                </div>
            </div>

            <div class="tapContainer" id="divtapContainer">
                <span class="tapItem fontcolor"><a href="" id="itemProfile">프로필</a></span>
                <span class="tapItem fontcolor"><a href="" id="itemProjectReview">프로젝트 후기</a></span>
                <span class="tapItem fontcolor"><a href="" id="itemMakeProject">올린 프로젝트</a></span>
                <span class="tapItem fontcolor"><a href="" id="itemProjectOrder">후원한 프로젝트</a></span>
            </div>
        </div>


    </div>

    <div class="line"></div>

    <!-- 탭 값 -->
    <div class="container">
        <div class="tap_itemProfile fontcolor" id="tap_itemProfile">등록된 소개가 없습니다.</div>
    </div>

    <!-- 프로젝트후기, 올린프로젝트, 후원한 프로젝트 -->




</div>

<div class="line"></div>

<div class="footer">
    <div class ="menu">
        <div class="infoList">
            <div class="infoItem">
                <div>펀들리</div>
                <a href="">공지사항</a>
                <a href="">서비스 소개</a>
            </div>

            <div class="infoItem">
                <div>이용안내</div>
                <a href="">헬프 센터</a>
                <a href="">첫 후원 가이드</a>
                <a href="">창작자 가이드</a>
                <a href="">수수료 안내</a>
            </div>

            <div class="infoItem">
                <div>정책</div>
                <a href="">이용약관</a>
                <a href="">개인정보처리방침</a>
                <a href="">프로젝트 심사 기준</a>
            </div>
        </div>

        <div class="customerInfo">
            <div>고객지원</div>
            <p>평일 9:30 ~ 17:00 (12:00 ~ 14:00 제외) </p>
            <button class="chat"> 텀블벅에 문의 </button>
        </div>
    </div>

    <div class="line"></div>

    <div class="company">
        <div class="companyInfo">
            <div class="companyInfoItem ">
                <strong>회사명</strong>
                <span>(주) 펀들리</span>
            </div>
            <div class="companyInfoItem">
                <strong>주소</strong>
                <span>서울특별시 펀들리구 펀들리대로 123</span>
            </div>
            <div class="companyInfoItem">
                <strong>대표</strong>
                <span>펀들리</span>
            </div>
            <div class="companyInfoItem">
                <strong>사업자등록번호</strong>
                <span>123-45-67891</span>
            </div>
            <div class="companyInfoItem">
                <strong>통신판매업 신고번호</strong>
                <span>2023-서울펀들리-0001호</span>
            </div>
            <div class="companyInfoItem">
                <strong>대표번호</strong>
                <span>02-1234-5678</span>
            </div>
            <div class="companyInfoItem ">
                <strong>메일주소</strong>
                <span>Fundly@fundly.kr</span>
            </div>
            <div class="copyright">© 2024 Fundly Inc.</div>
        </div>
    </div>

    <div class="notice">
        <div class="noticeMsg">펀들리는 플랫폼 제공자로서 프로젝트의 당사자가 아니며, 직접적인 통신판매를 진행하지 않습니다. 프로젝트의 완수 및 선물제공의 책임은 해당 프로젝트의 창작자에게 있으며, 프로젝트와 관련하여 후원자와 발생하는 법적 분쟁에 대한 책임은 해당 창작자가 부담합니다.

        </div>
    </div>
</div>
</div>
</body>

<script>
    const tapContainer = document.getElementById('divtapContainer');
    const tap_itemProfile = document.getElementById('tap_itemProfile');

    tapContainer.addEventListener("click",(e)=>{
        const tapItems = document.querySelectorAll('.tapItem');

        tapItems.forEach(item => {
            item.classList.add('on');
            // item.classList.remove('on'); // 모든 탭의 활성화 클래스를 제거합니다.
        });

        if(e.target.id=="itemProfile"){
            e.target.parentElement.classList.add('on');
            tap_itemProfile.style.display = "block";

            // tap_itemProfile.style.display = "block";
            // // itemProjectReview.style.display = "none";
            // // alert('프로필');

        }
        else if(e.target.id=="itemProjectReview"){
            // tap_itemProfile.style.display = "none";
            // // itemProjectReview.style.display = "block";
            // alert('후기');

        } else if(e.target.id=="itemMakeProject"){
            // tap_itemProfile.style.display = "none";
            // // itemProjectReview.style.display = "none";
            // alert('올린');

        } else if(e.target.id=="itemProjectOrder"){
            // tap_itemProfile.style.display = "none";
            // // itemProjectReview.style.display = "none";
            // alert('후원');
        }
    })
</script>
</html>
