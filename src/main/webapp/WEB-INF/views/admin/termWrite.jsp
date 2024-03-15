
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="https://cdn.tiny.cloud/1/uh0icij1g3eyzvh7ppnwlga6kxke0lnffev72sw6bz89u7rb/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

<body>
<form id="push" action="<c:url value='/admin/termWrite'/>" method="post" onsubmit="return goToWrite()">
    <input type="text" name="term_seq" value="" hidden="hidden">
    <select name="term_title" id="" >
        <option value="서비스 이용약관">서비스 이용약관</option>
        <option value="광고서비스 이용약관">광고서비스 이용약관</option>
        <option value="개인정보처리방침">개인정보처리방침</option>
        <option value="커뮤니티 운영원칙">커뮤니티 운영원칙</option>
        <option value="프로젝트 심사 기준">프로젝트 심사 기준</option>
        <option value="수수료 정책">수수료 정책</option>
        <option value="개인정보 제3자 제공 동의">개인정보 제3자 제공 동의</option>

    </select>
    <input type="text" name="reg_id" value="${reg_id}" hidden="hidden">
    <label for="date">시작일를 선택하세요:
        <input type="datetime-local" name="term_srt_date" id="date" max="2099-06-20" min="2024-01-01" value="">
    </label>
    <textarea class="pjStr" name="term_cont">

    </textarea>
    <button type="button" onclick="checkAndSubmit()" style>등록</button>
</form>

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
        // files, editor, welEditable //cb, value, meta
        file_picker_callback: function (files, editor, welEditable) {
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
                    files(blobInfo.blobUri(), { title: file.name });
                    console.log(files)
                };
                reader.readAsDataURL(file);

            };

            input.click();
        },
        content_style:
            'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',
    });
    function checkAndSubmit() {
        if (goToWrite()) {
            var form = document.getElementById("push");
            form.setAttribute("action", "<c:url value='/admin/termWrite'/>");
            form.setAttribute("method", "post");
            form.submit();
        }
    }

    function goToWrite() {
        var startDateInput = document.getElementById("date");
        var startDateValue = startDateInput.value;

        if (!startDateValue) {
            alert("시작일을 선택하세요.");
            return false;
        } else {
            return true;
        }
    }
    function goToModify(){
        let form = document.getElementById("push");
        form.setAttribute("action","<c:url value="/admin/modify"/>");
        form.setAttribute("method","post");
        form.submit();
    }
    function goToDelete(){
        let form = document.getElementById("push");
        form.setAttribute("action","<c:url value="/admin/delete"/>");
        form.setAttribute("method","post");
        form.submit();
    }
</script>
</body>
</html>
