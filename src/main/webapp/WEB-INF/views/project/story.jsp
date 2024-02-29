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
                            <textarea name="pjIntro" id="intro" class="pjStr"></textarea>
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
                            <textarea name="pjBudget" id="budget" class="pjStr"></textarea>
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
                            <textarea name="pjSched" id="sched" class="pjStr"></textarea>
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
                            <textarea name="pjSelIntro" id="selIntro" class="pjStr"></textarea>
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
                            <textarea name="pjReward" id="reward" class="pjStr"></textarea>
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
        language: "ko_KR",
        menubar: false,
        branding: false, //branding, elementpath, statusbar => false로 줘야 하단에 tinyMCE 마크 단락 안보임
        elementpath: false,
        statusbar: false,
        resize: false,
        ui_mode: "split",
        plugins: ['advlist', 'lists', 'link', 'image', 'charmap', 'preview', 'searchreplace', 'fullscreen', 'media', 'table', 'code', 'help', 'emoticons', 'codesample', 'quickbars' ],
        toolbar: 'undo redo blocks bold italic forecolor backcolor alignleft aligncenter alignright alignjustify image | media outdent indent bullist numlist emoticons | table link | fullscreen removeformat searchreplace customInsertButton customDateButton',
        toolbar_mode: 'sliding',
        /* enable title field in the Image dialog*/
        image_title: true,
        // image_upload_handler: storyImgUpHandler,
        images_upload_url: '/project/story/image',
        // URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)
        //images_reuse_filename: true, //업로드 되어 리턴된 새로운 이미지 주소를 현재 에디터에 바로 반영해주는 옵션
        /* enable automatic uploads of images represented by blob or data URIs*/
        automatic_uploads: true,

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



    window.onload = function(){
        const saveBtn = document.querySelector('button.save');
        console.log("saveBtn");
        console.log(saveBtn)
        saveBtn.classList.add('story');
        const storySaveBtn = document.querySelector('button.story');
        console.log(storySaveBtn);


        storySaveBtn.addEventListener('click', function(){
            const pj_intro = tinymce.get('intro') //에디터에 입력한 value
            const pj_budget = tinymce.get('budget')
            const pj_sched = tinymce.get('sched');
            const pj_sel_intro =  tinymce.get('selIntro');
            const pj_gift_intro =  tinymce.get('reward');

            console.log("pj_intro")
            console.log(pj_intro)

            const storyForm = {
                "pj_intro": pj_intro.getContent(),
                "pj_budget": pj_budget.getContent(),
                "pj_sched": pj_sched.getContent(),
                "pj_sel_intro": pj_sel_intro.getContent(),
                "pj_gift_intro": pj_gift_intro.getContent()
            }

            console.log(storyForm);
            fetch("/project/story",{
                method: "POST",
                headers: {},
                body: JSON.stringify(storyForm),
            })
                .then(response => {
                    if(!response.ok){
                        throw response
                    }
                    return response.json()
                })
                .then(data => {
                    // textarea에 등록된 data를 뿌려준다.
                    console.log("data received")
                    console.log(data)

                    pj_intro.setContent('abcbabc')
                    pj_budget.setContent(data.pj_budget)
                    pj_sched.setContent(data.pj_sched)
                    pj_sel_intro.setContent(data.pj_sel_intro)
                    pj_gift_intro.setContent('abcabcabcabc')
                })
                .catch(error => error)



        })
    }

</script>
