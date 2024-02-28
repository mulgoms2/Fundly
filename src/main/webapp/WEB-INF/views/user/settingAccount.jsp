<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="flexOnly">
      <div class="container">

      <%--  이메일  --%>
        <div class="accountSetting">
          <div class="accountFormHeader ">
            <div class="detailTitle">
              <span class="pTag">이메일</span>
<%--              <button class="ButtonTag" id="changeEmail" >변경</button>--%>
            </div>

            <div class="pTagbefore">
              <div class="pTagChaDetailEmail fontDetail">
                ${userInfo.user_email}
              </div>
              <div class="mailChkeckV">
                <div class="checkIcon">
                  <svg viewBox="0 0 48 48"><path fill-rule="evenodd" clip-rule="evenodd" d="M41.6 8L18.9 30.8L6.2 19L2 23.5L19.1 39.4L46 12.4L41.6 8Z"></path></svg>
                </div>
                인증된 이메일입니다.
              </div>
            </div>

            <div class="pTagDetail">
              <div class="pTagChaDetail">


              </div>

<%--              <div class="pTagChaBtn"><Button class="filesave" id="saveEmail" ><span>저장</span></Button></div>--%>
            </div>

          </div>
        </div>

        <%--  비밀번호  --%>
        <div class="accountSetting">
          <div class="accountFormHeader ">
            <div class="detailTitle">
              <span class="pTag">비밀번호</span>
              <button class="ButtonTag" id="changePwd" >변경</button>
            </div>

            <div class="pTagbefore">
              <div class="pTagChaDetailPwd fontDetail">
<%--                  ${userInfo.user_pwd}--%>
                  <p>2주 전 갱신됨</p>

              </div>
            </div>

            <div class="pTagDetail">
              <div class="pTagChaDetail">


              </div>
              <div class="pTagChaBtn"><Button class="filesave" id="savePwd" ><span>저장</span></Button></div>
            </div>
          </div>
        </div>

        <%--  연락처  --%>
        <div class="accountSetting">
          <div class="accountFormHeader ">
            <div class="detailTitle">
              <span class="pTag">연락처</span>
              <button class="ButtonTag" id="changePhoneNo" >변경</button>
            </div>

            <div class="pTagbefore">
              <div class="pTagChaDetailPhoneNo">
                  ${userInfo.user_phone_no}

              </div>
                <div class="phoneChkeckV">
                    <div class="checkIcon">
                        <svg viewBox="0 0 48 48"><path fill-rule="evenodd" clip-rule="evenodd" d="M41.6 8L18.9 30.8L6.2 19L2 23.5L19.1 39.4L46 12.4L41.6 8Z"></path></svg>
                    </div>
                    인증된 연락처입니다.
                </div>
            </div>

            <div class="pTagDetail">
              <div class="pTagChaDetail">
              </div>
              <div class="pTagChaBtn"><Button class="filesave" id="savehoneNo" ><span>저장</span></Button></div>
            </div>
          </div>
        </div>

      </div>

      <div class="userSettingContainer">
        <div class="userInfoMod"></div>
        <div class="userInfoComment">
          <p>이메일과 연락처는 어디에 쓰이나요?</p>
          <div class="userInfoCommentContent">
            이메일과 연락처로 프로젝트, 후원 및 결제 관련 알림을 드립니다. 배송 받는 분의 연락처는 개별 후원내역에서 변경해주세요.
            <span><a href="/mypage/fundingProject">내 후원현황 바로가기</a></span>
          </div>
        </div>
      </div>
    </div>

