<%--
  Created by IntelliJ IDEA.
  User: lemon
  Date: 2024-02-01
  Time: 오후 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>


<%--<script src="https://cdn.tiny.cloud/1/uh0icij1g3eyzvh7ppnwlga6kxke0lnffev72sw6bz89u7rb/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>--%>
<div class="pjWrap">
    <div class="pjCont">
        <!-- 카테고리 -->
        <div class="pjBox story">
            <dl class="pjInfo">
                <dt class="pjInfoTit">프로젝트 계획
                    <span class="icon">
                        <i class="fas fa-solid fa-asterisk"></i>
                    </span>
                </dt>
                <div class="pjNotice">
                    <i class="fas fa-solid fa-circle-info"></i>
                    <p class="pjNoticeTit">
                        텍스트 에디터 사용법</p>
                    <p class="pjNoticeCont">Enter( )를 누르면 문단이 구분됩니다. 문단 내에서 간격 없이 줄바꿈 하려면 shift(⇧) + enter(↵) 를 사용해주세요.</p>
                    <p class="pjNoticeCont">ctrl+c/v를 이용해 선택한 이미지를 입력창 내에서 복사/붙여넣기 할 수 있습니다..</p>
                    <p class="pjNoticeCont">본문 텍스트와 이미지는 되도록 분리해서 작성해주세요. 통 이미지 사용은 불가합니다.</p>
                </div>
            </dl>
            <div class="pjForm story">
                <div class="purpose form">
                    <div class="tit sub">
                        <p>프로젝트 소개</p>
                    </div>
<%--                    <b>storyForm.isEmpty="${storyForm.isEmpty}"</b>--%>
<%--                    <b>storyForm.edit=${storyForm.edit}</b> 확인용--%>
                    <c:if test="${!storyForm.isEmpty && !storyForm.edit}">
                    <div class="saved">
                        <hr>
                            ${storyForm.pj_intro}
                    </div>
                    </c:if>
                    <c:if test="${storyForm.isEmpty || storyForm.edit}">
<%--                        작성된 내용이 아무것도 없거나, 편집 상태일때 텍스트 에디터를 보여준다.--%>
                    <div class="descp sub">
                        <p>무엇을 만들기 위한 프로젝트인지 분명히 알려주세요.</p>
                    </div>
                    <div class="textWrp sub">
                        <div class="textCon">
                            <textarea name="pjIntro" id="intro" class="pjStr">
                                ${storyForm.pj_intro}
                            </textarea>
                        </div>
                    </div>
                    </c:if>
                </div>
                <div class="budget form">
                    <div class="tit sub">
                        <p>프로젝트 예산</p>
                    </div>
                    <c:if test="${!storyForm.isEmpty && !storyForm.edit}">
                    <div class="saved">
                        <hr>
                        ${storyForm.pj_budget}
                    </div>
                    </c:if>
                    <c:if test="${storyForm.isEmpty || storyForm.edit}">
                    <div class="descp sub">
                        <p>펀딩 목표 금액을 제작에 어떻게 사용할 것인지 구체적으로 알려주세요. ‘인건비', ‘배송비’, ‘인쇄비’, ‘대관료’ 등 세부 항목별로 작성해야 합니다.</p>
                    </div>
                    <div class="textWrp sub">
                        <div class="textCon">
                            <textarea name="pjBudget" id="budget" class="pjStr">
                                ${storyForm.pj_budget}
                            </textarea>
                        </div>
                    </div>
                    </c:if>
                </div>
                <div class="sched form">
                    <div class="tit sub">
                        <p>프로젝트 일정</p>
                    </div>
                    <c:if test="${!storyForm.isEmpty && !storyForm.edit}">
                    <div class="saved">
                        <hr>
                        ${storyForm.pj_sched}
                    </div>
                    </c:if>
                    <c:if test="${storyForm.isEmpty || storyForm.edit}">
                    <div class="descp sub">
                        <p>작업 일정을 구체적인 날짜와 함께 작성하세요. 후원자가 일정을 보면서 어떤 작업이 진행될지 알 수 있어야 합니다. 펀딩 종료 이후의 제작 일정을 반드시 포함하세요.</p>
                    </div>
                    <div class="textWrp sub">
                        <div class="textCon">
                            <textarea name="pjSched" id="sched" class="pjStr">
                                    ${storyForm.pj_sched}
                            </textarea>
                        </div>
                    </div>
                    </c:if>
                </div>
                <div class="intro form">
                    <div class="tit sub">
                        <p>프로젝트 팀 소개</p>
                    </div>
                    <c:if test="${!storyForm.isEmpty && !storyForm.edit}">
                    <div class="saved">
                        <hr>
                        ${storyForm.pj_sel_intro}
                    </div>
                    </c:if>
                    <c:if test="${storyForm.isEmpty || storyForm.edit}">
                    <div class="descp sub">
                        <p>프로젝트를 진행하는 팀(혹은 개인)을 알려주세요. 이 프로젝트를 완수할 수 있다는 점을 후원자가 알 수 있어야 합니다. 이전 프로젝트, 기타 활동 내용, SNS 등을 공개해보세요.</p>
                    </div>
                    <div class="textWrp sub">
                        <div class="textCon">
                            <textarea name="pjSelIntro" id="selIntro" class="pjStr">
                                    ${storyForm.pj_sel_intro}
                            </textarea>
                        </div>
                    </div>
                    </c:if>
                </div>
                <div class="reward form">
                    <div class="tit sub">
                        <p>선물 설명</p>
                    </div>
                    <c:if test="${!storyForm.isEmpty && !storyForm.edit}">
                    <div class="saved">
                        <hr>
                        ${storyForm.pj_gift_intro}
                    </div>
                    </c:if>
                    <c:if test="${storyForm.isEmpty || storyForm.edit}">
                    <div class="descp sub">
                        <p>후원자가 후원 금액별로 받을 수 있는 선물을 상세하게 알려주세요. 표로 정리하거나 예시 이미지를 포함하는 것도 방법입니다.</p>
                    </div>
                    <div class="textWrp sub">
                        <div class="textCon">
                            <textarea name="pjReward" id="reward" class="pjStr">
                                    ${storyForm.pj_gift_intro}
                            </textarea>
                        </div>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/static/project/js/story.js"></script>

