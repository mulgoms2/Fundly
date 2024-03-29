<%--
  Created by IntelliJ IDEA.
  User: lemon
  Date: 2024-02-03
  Time: 오후 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.tiny.cloud/1/uh0icij1g3eyzvh7ppnwlga6kxke0lnffev72sw6bz89u7rb/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
</head>
<body>
<div class="pjWrap">
<div class="pjCont">
<div class="pjBox stry">
    <!-- 상단 제목부분 -->
    <div>
        <div class="stryTit">
            <div class="innerTit">프로젝트 계획</div>
        </div>
        <div>
            <a href="javascript:void(0)">
                <p>작성 가이드</p>
            </a>
            <div>
                <button type="button" id="sendBtn">저장</button>
            </div>
        </div>
    </div>
    <!-- 하단 내용부분-->
    <div>
        <div class="pjNotice">
            <div class="pjNoticeTit">텍스트 에디터 사용법</div>
            <div>Enter( )를 누르면 문단이 구분됩니다. 문단 내에서 간격 없이 줄바꿈 하려면 shift(⇧) + enter(↵) 를 사용해주세요.</div>
            <div>ctrl+c/v를 이용해 선택한 이미지를 입력창 내에서 복사/붙여넣기 할 수 있습니다.</div>
            <div>본문 텍스트와 이미지는 되도록 분리해서 작성해주세요. 통 이미지 사용은 불가합니다.</div>
        </div>
        <div class="stryForm">
            <div class="purpose">
                <div>
                    <p>프로젝트 소개</p>
                    <p>무엇을 만들기 위한 프로젝트인지 분명히 알려주세요.</p>
                </div>
                <div>
                    <div id="purposeVal" class="pjStrVal" style="display:none;">
                    </div>
                    <form name="purpose">
                            <textarea class="pjStr">
                            <p>막연하다면 아래의 질문에 대한 답이 내용에 포함되도록 작성해보세요.</p>

                            Q. 무엇을 만들기 위한 프로젝트인가요?

                            Q. 프로젝트를 간단히 소개한다면?

                            Q. 이 프로젝트가 왜 의미있나요?

                            Q. 이 프로젝트를 시작하게 된 배경이 무엇인가요?

                            </textarea>
                    </form>
                </div>
            </div> <!--purpose 끝-->
            <div class="budget">
                <div>
                    <p>프로젝트 예산</p>
                    <p>펀딩 목표 금액을 제작에 어떻게 사용할 것인지 구체적으로 알려주세요. ‘인건비', ‘배송비’, ‘인쇄비’, ‘대관료’ 등 세부 항목별로 작성해야 합니다.</p>
                </div>
                <div>
                    <form name="budget">
                            <textarea class="pjStr">
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
                    </form>
                </div>
            </div> <!-- budget 끝 -->
            <div class="sched">
                <div>
                    <p>프로젝트 일정</p>
                    <p>작업 일정을 구체적인 날짜와 함께 작성하세요. 후원자가 일정을 보면서 어떤 작업이 진행될지 알 수 있어야 합니다. 펀딩 종료 이후의 제작 일정을 반드시 포함하세요.</p>
                </div>
                <div>
                    <form name="sched">
                            <textarea class="pjStr">
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
                    </form>
                </div>
            </div>
            <div class="intro">
                <div>
                    <p>프로젝트 소개</p>
                    <p>프로젝트를 진행하는 팀(혹은 개인)을 알려주세요. 이 프로젝트를 완수할 수 있다는 점을 후원자가 알 수 있어야 합니다. 이전 프로젝트, 기타 활동 내용, SNS 등을 공개해보세요.</p>
                </div>
                <div>
                    <form name="intro">
                            <textarea class="pjStr">

                            </textarea>
                    </form>
                </div>
            </div><!--intro 끝-->
            <div class="reward">
                <div>
                    <p>선물 설명</p>
                    <p>후원자가 후원 금액별로 받을 수 있는 선물을 상세하게 알려주세요. 표로 정리하거나 예시 이미지를 포함하는 것도 방법입니다.</p>
                </div>
                <div>
                    <form name="intro">
                            <textarea class="pjStr">

                            </textarea>
                    </form>
                </div>
            </div><!--reward 끝-->
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
<script>
    $("#sendBtn").click(function(){
        tinyMCE.triggerSave(); //tinyMCE는 submit하기 전에 textarea에 값을 꼭 binding해주어야 한다.

        let purpose = $("form[name=purpose]").children('textarea').val(); //동적인 값을 가져올 수는 없을까? 하드코딩 된 값만 가져옴.ㅠㅠ
        let budget = $("form[name=budget]").children('textarea').val();
        let sched = $("form[name=sched]").children('textarea').val();
        let intro = $("form[name=intro]").children('textarea').val();
        let reward = $("form[name=reward]").children('textarea').val();
        // let purpose = $('.pjStr:eq(0)').val();


        alert(purpose);

        let stryObj = {
            purpose : purpose,
            budget : budget,
            sched : sched,
            intro : intro,
            reward : reward
        }


        $.ajax({
            type:'POST',
            url:'/txt/post',
            headers: {"content-type": "application/json"},
            data: JSON.stringify({stryObj:stryObj}),
            success: function(result){
                alert("성공적으로 저장되었습니다.")
                showStory(result);
            },
            error: function(){alert("error has occured")}
        });
    });

    let showStory = function(${stryObj}){
        // tinyMCE.activeEditor.setMode('readonly'); 이거 안먹힘

        // const form = document.querySelectorAll('form')[0]
        // forms.forEach(function(form){
        //     form.style.display = "none";
        // });

        const form = document.querySelector("form[name=purpose]");
        console.dir(form);
        console.dir(${stryObj});


        const pjStrVal = document.querySelector('.pjStrVal');
        pjStrVal.style.display = "block";
        <%--pjStrVal.innerHTML = ${stryObj};--%>

        <%--purpose.html(${stryObj.purpose});--%>
        <%--budget.html(${stryObj.budget});--%>
        <%--sched.html(${stryObj.sched});--%>



        <%--intro = ${stryObj.intro}--%>
        <%--reward = ${stryObj.reward}--%>

    }

    </script>


</body>
</html>