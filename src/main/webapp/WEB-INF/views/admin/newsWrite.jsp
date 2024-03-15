
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Summernote with Bootstrap 4</title>
    <script src="https://cdn.tiny.cloud/1/uh0icij1g3eyzvh7ppnwlga6kxke0lnffev72sw6bz89u7rb/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
</head>
<body>
<form id="push" action="" method="">
    <input type="text" name="news_seq" value="${newsModifyInfo.news_seq}" hidden="hidden">
    <input type="text" name="news_title" value="${newsModifyInfo.news_title ==null ? newsDto.news_title: newsModifyInfo.news_title}" placeholder="제목">
    <input type="text"  name="reg_id" value="${reg_id}" hidden="hidden">
    <textarea class="pjStr" name="news_cont">
        ${newsModifyInfo.news_cont==null ? newsDto.news_cont: newsModifyInfo.news_cont}
    </textarea>
    <button onclick="goToWrite()" style=${newsModifyInfo!=null ? "display:none" : ""}>등록</button>
    <button onclick="goToModify()" style=${newsModifyInfo!=null ? "" : "display:none"} >수정완료</button>
    <button onclick="goToDelete()">삭제</button>
</form>
<button onclick="location.href='<c:url value="/admin/list"/>'"  style=${newsModifyInfo!=null ? "" : "display:none"}>취소</button>

<script>
    function goToWrite(){
        let form = document.getElementById("push");
        form.setAttribute("action","<c:url value="/admin/write"/>");
        form.setAttribute("method","post");
        form.submit();
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
    let msg = "${msg}";
    if( msg != "") {alert("게시물 등록에 실패하였습니다. 다시 시도해 주세요.");}
</script>
</body>
</html>