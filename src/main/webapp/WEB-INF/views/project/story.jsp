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
                <div class="purpose">
                    <div class="tit">
                        <p>프로젝트 소개</p>
                    </div>
                    <div class="descp">
                        <p>무엇을 만들기 위한 프로젝트인지 분명히 알려주세요.</p>
                    </div>
                    <div class="textWrp">
                        <div class="textCon">
                            <textarea name="pjTxt" class="pjStr">
                             막연하다면 아래의 질문에 대한 답이 내용에 포함되도록 작성해보세요.

                            Q. 무엇을 만들기 위한 프로젝트인가요?

                            Q. 프로젝트를 간단히 소개한다면?

                            Q. 이 프로젝트가 왜 의미있나요?

                            Q. 이 프로젝트를 시작하게 된 배경이 무엇인가요?
                            </textarea>
                        </div>
                    </div>
                </div>
                <div class="budget">
                    <div class="tit">
                        <p>프로젝트 예산</p>
                    </div>
                    <div class="descp">
                        <p>펀딩 목표 금액을 제작에 어떻게 사용할 것인지 구체적으로 알려주세요. ‘인건비', ‘배송비’, ‘인쇄비’, ‘대관료’ 등 세부 항목별로 작성해야 합니다.</p>
                    </div>
                    <div class="textWrp">
                        <div class="textCon">
                            <textarea name="pjTxt" class="pjStr">
                            설정하신 목표 금액을 어디에 사용 예정이신지 구체적인 지출 항목으로 적어 주세요.

                            예산은 ‘제작비’가 아닌 구체적인 ‘항목’으로 적어 주세요.
                            이번 프로젝트의 실행에 필요한 비용으로만 작성해 주세요.
                            기부, 다음 프로젝트에 사용하기 등은 이번 프로젝트의 예산으로 볼 수 없어요.
                            만일 전체 제작 비용 중 일부만 모금하시는 경우라면, 나머지 제작 비용은 어떻게 마련 예정인지 추가로 작성해 주세요.

                            (예시)

                            목표 금액은 아래의 지출 항목으로 사용할 예정입니다.

                            인건비
                            배송비
                            발주비
                            디자인 의뢰비
                            수수료
                            </textarea>
                        </div>
                    </div>
                </div>
                <div class="sched">
                    <div class="tit">
                        <p>프로젝트 일정</p>
                    </div>
                    <div class="descp">
                        <p>작업 일정을 구체적인 날짜와 함께 작성하세요. 후원자가 일정을 보면서 어떤 작업이 진행될지 알 수 있어야 합니다. 펀딩 종료 이후의 제작 일정을 반드시 포함하세요.</p>
                    </div>
                    <div class="textWrp">
                        <div class="textCon">
                            <textarea name="pjTxt" class="pjStr">
                            아래의 양식을 참고하여 작성해보세요.

                            ( 예시 )

                            0월 0일: 현재 제품 시안 및 1차 샘플 제작
                            0월 0일: 펀딩 시작일
                            0월 0일: 샘플 작업 보완
                            0월 0일: 펀딩 종료일
                            0월 0일: 2차 샘플 제작
                            0월 0일: 제품 디테일 보완
                            0월 0일: 제품 발주 시작
                            0월 0일: 후가공 처리 및 포장 작업
                            0월 0일: 선물 예상 전달일
                            </textarea>
                        </div>
                    </div>
                </div>
                <div class="intro">
                    <div class="tit">
                        <p>프로젝트 팀 소개</p>
                    </div>
                    <div class="descp">
                        <p>프로젝트를 진행하는 팀(혹은 개인)을 알려주세요. 이 프로젝트를 완수할 수 있다는 점을 후원자가 알 수 있어야 합니다. 이전 프로젝트, 기타 활동 내용, SNS 등을 공개해보세요.</p>
                    </div>
                    <div class="textWrp">
                        <div class="textCon">
                            <textarea name="pjTxt" class="pjStr"></textarea>
                        </div>
                    </div>
                </div>
                <div class="reward">
                    <div class="tit">
                        <p>선물 설명</p>
                    </div>
                    <div class="descp">
                        <p>후원자가 후원 금액별로 받을 수 있는 선물을 상세하게 알려주세요. 표로 정리하거나 예시 이미지를 포함하는 것도 방법입니다.</p>
                    </div>
                    <div class="textWrp">
                        <div class="textCon">
                            <textarea name="pjTxt" class="pjStr"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    tinymce.init({
        selector: 'textarea.pjStr',
        plugins: 'image code',
        toolbar: 'undo redo | link image | code',
        /* enable title field in the Image dialog*/
        image_title: true,
        /* enable automatic uploads of images represented by blob or data URIs*/
        automatic_uploads: true,
        /*
URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)
images_upload_url: 'postAcceptor.php',
here we add custom filepicker only to Image dialog
*/
        file_picker_types: 'image',
        /* and here's our custom image picker*/
        file_picker_callback: function (cb, value, meta) {
            var input = document.createElement('input');
            input.setAttribute('type', 'file');
            input.setAttribute('accept', 'image/*');

            /*
Note: In modern browsers input[type="file"] is functional without
even adding it to the DOM, but that might not be the case in some older
or quirky browsers like IE, so you might want to add it to the DOM
just in case, and visually hide it. And do not forget do remove it
once you do not need it anymore.
*/

            input.onchange = function () {
                var file = this.files[0];

                var reader = new FileReader();
                reader.onload = function () {
                    /*
  Note: Now we need to register the blob in TinyMCEs image blob
  registry. In the next release this part hopefully won't be
  necessary, as we are looking to handle it internally.
*/
                    var id = 'blobid' + new Date().getTime();
                    var blobCache =
                        tinymce.activeEditor.editorUpload.blobCache;
                    var base64 = reader.result.split(',')[1];
                    var blobInfo = blobCache.create(id, file, base64);
                    blobCache.add(blobInfo);

                    /* call the callback and populate the Title field with the file name */
                    cb(blobInfo.blobUri(), { title: file.name });
                };
                reader.readAsDataURL(file);
            };

            input.click();
        },
        content_style:
            'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',
    });
</script>
<%--<script>--%>
<%--    tinymce.init({--%>
<%--        selector: 'textarea#file-picker',--%>
<%--        plugins: 'image code',--%>
<%--        toolbar: 'undo redo | link image | code',--%>
<%--        /* enable title field in the Image dialog*/--%>
<%--        image_title: true,--%>
<%--        /* enable automatic uploads of images represented by blob or data URIs*/--%>
<%--        automatic_uploads: true,--%>
<%--        /*--%>
<%--URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)--%>
<%--images_upload_url: 'postAcceptor.php',--%>
<%--here we add custom filepicker only to Image dialog--%>
<%--*/--%>
<%--        file_picker_types: 'image',--%>
<%--        /* and here's our custom image picker*/--%>
<%--        file_picker_callback: function (cb, value, meta) {--%>
<%--            var input = document.createElement('input');--%>
<%--            input.setAttribute('type', 'file');--%>
<%--            input.setAttribute('accept', 'image/*');--%>

<%--            /*--%>
<%--Note: In modern browsers input[type="file"] is functional without--%>
<%--even adding it to the DOM, but that might not be the case in some older--%>
<%--or quirky browsers like IE, so you might want to add it to the DOM--%>
<%--just in case, and visually hide it. And do not forget do remove it--%>
<%--once you do not need it anymore.--%>
<%--*/--%>

<%--            input.onchange = function () {--%>
<%--                var file = this.files[0];--%>

<%--                var reader = new FileReader();--%>
<%--                reader.onload = function () {--%>
<%--                    /*--%>
<%--  Note: Now we need to register the blob in TinyMCEs image blob--%>
<%--  registry. In the next release this part hopefully won't be--%>
<%--  necessary, as we are looking to handle it internally.--%>
<%--*/--%>
<%--                    var id = 'blobid' + new Date().getTime();--%>
<%--                    var blobCache =--%>
<%--                        tinymce.activeEditor.editorUpload.blobCache;--%>
<%--                    var base64 = reader.result.split(',')[1];--%>
<%--                    var blobInfo = blobCache.create(id, file, base64);--%>
<%--                    blobCache.add(blobInfo);--%>

<%--                    /* call the callback and populate the Title field with the file name */--%>
<%--                    cb(blobInfo.blobUri(), { title: file.name });--%>
<%--                };--%>
<%--                reader.readAsDataURL(file);--%>
<%--            };--%>

<%--            input.click();--%>
<%--        },--%>
<%--        content_style:--%>
<%--            'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',--%>
<%--    });--%>

<%--</script>--%>
