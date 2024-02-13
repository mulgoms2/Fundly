<%--
  Created by IntelliJ IDEA.
  User: init
  Date: 2024-02-12
  Time: 오후 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

    </head>

    <body>
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
                    <button class="chat" onclick="chatBtn()"> 텀블벅에 문의 </button>
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
    </body>

    <script>
        const chat = document.getElementById('chat');
        const tap_itemProfile = document.getElementById('tap_itemProfile');

        function chatBtn(){
            <%--let url = "chatPop?user_id=" + "${sessionScope.user_email}" + "&pj_id=" + $("#pj").val();--%>
<%----%>
//             window.open(url, "_blank", 'width=600px,height=800px,scrollbars=yes');
        }

        /* 로그인/회원가입 or 로그인 회원 정보 */
        const loginInfo = document.getElementById('loginInfo');

        loginInfo.addEventListener("click",() => {

            if(loginInfo.outerText !== '로그인/회원가입'){
                toggleContent("MyPageList");
            }else{
                return window.location.href='/login/login';
            }
        })

        const MyPageList = document.getElementById('MyPageList');

        MyPageList.addEventListener("click",(e)=>{

            if(e.target.id == "Profile"){
                // 프로필 화면으로
                return window.location.href = '/mypage/profile';

            } else if(e.target.id == "Coupon") {
                // 응원권 화면으로
                return window.location.href = '/mypage/coupon';

            } else if(e.target.id == "fundingProject") {
                // 후원한 프로젝트 화면으로
                return window.location.href = '/mypage/fundingProject';

            // } else if(e.target.id == "Like") {
            //
            //     return window.location.href = '/like';

            } else if(e.target.id == "Alarm") {
                // 알림 화면으로
                return window.location.href = '/mypage/alarm';

            } else if(e.target.id == "Message") {
                // 메시지 화면으로
                return window.location.href = '/mypage/message';

            } else if(e.target.id == "MakeProject") {
                // 내가 만든 프로젝트 화면으로
                return window.location.href = '/mypage/makeProject';

            } else if(e.target.id == "Setting") {
                // 설정 화면으로
                return window.location.href = '/mypage/setting';

            } else if(e.target.id == "LogOut") {

                toggleContent("MyPageList");
                return window.location.href = '/login/logout';
            }
        })

        function toggleContent(MyPageList){

            var content = document.getElementById(MyPageList);

            if(content.style.display=="none"){

                content.style.display="block";
            } else{
                content.style.display="none";
            }
        }
    </script>
</html>



