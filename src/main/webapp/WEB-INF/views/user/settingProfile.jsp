<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div class="flexOnly">
      <div class="container">

        <div class="profileSetting">
          <div class="profileimgFormHeader ">
            <div class="detailTitle">
              <span class="pTag">프로필 사진</span>
              <button class="ButtonTag" id="changeImg" >변경</button>
            </div>

            <div class="pTagbefore">
              <div class="pTagChaDetailImg">
                <div class="pTagImg">
                  <div class="pTagImgWrap">
                    <div class="pTagImgFile" src=""></div>
                  </div>
                </div>
              </div>
            </div>

            <div class="pTagDetail">
              <div class="pTagChaDetail">
                <div class="pTagChaDetailImg">
                  <div class="pTagImg">
                    <div class="pTagImgWrap">
                      <div class="pTagImgFile" src=""></div>
                    </div>
                  </div>

                  <div class="pTagImgDetail">
                    <button class="filein"><span>파일업로드</span></button><br>
                    <span class="fileDetail">250 x 250 픽셀에 최적화되어 있으며, 10Mb 이하의 JPG, GIF, PNG 파일을 지원합니다.</span>
                  </div>
                </div>
              </div>

              <div class="pTagChaBtn"><Button class="filesave" id="imgsave" ><span>저장</span></Button></div>
            </div>

          </div>
        </div>

        <div class="profileSetting">
          <div class="userNameFormHeader">

            <div class="detailTitle">
              <span class="pTag">이름</span>
              <button class="ButtonTag" id="changeName">변경</button>
            </div>

            <div class="pTagbeforeName fontDetail">
              ${userInfo.user_name}
            </div>

            <div class="pTagDetailName">
              <div class="userNameDetail">
                <input type="text" inputmode="text" placeholder="이름을 입력해주세요." autocapitalize="off"
                       autocomplete="off" id="userNameValue" value = "${userInfo.user_name}" name ="name">
              </div>

              <div class="pTagChaBtn"><Button class="filesave" id="namesave"><span>저장</span></Button></div>

            </div>

          </div>
        </div>

        <div class="profileSetting">
          <div class="userIntroFormHeader">

            <div class="detailTitle">
              <span class="pTag">소개</span>
              <button class="ButtonTag" id ="changeIntro">변경</button>
            </div>

            <div class="pTagbeforeIntro fontDetail">
              <pre>${userInfo.user_intro}</pre>
            </div>

            <div class="pTagDetailIntro">
                <div class="userIntroDetail">
                  <textarea id="introValue">${userInfo.user_intro}</textarea>
                </div>

                <div class="pTagChaBtn"><Button class="filesave" id="introsave"><span>저장</span></Button></div>

            </div>

            </div>

          </div>
      </div>

      <div class="userSettingContainer">
        <div class="userInfoMod"></div>
        <div class="userInfoComment">
          <p>어떤 정보가 프로필에 공개되나요?</p>
          <div class="userInfoCommentContent">
            프로필 사진과, 이름, 사용자 이름, 소개글, 웹사이트 및 회원님과 관련된 프로젝트 등이 프로필 페이지에 공개 됩니다. 프라이버시 설정을 활성화하시면 후원한 프로젝트 목록을 숨길 수 있습니다.
            <span><a href="/mypage/profile">내 프로필 바로가기</a></span>
          </div>
        </div>
      </div>
    </div>

