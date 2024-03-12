
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
<form id="push" action="" method="">
<input type="text" name="event_seq" value="" hidden="hidden">
<input type="text" name="event_title" value="" placeholder="제목">
<input type="text" name="reg_id" value="" placeholder="작성자">
    <label for="date">시작일를 선택하세요:
        <input type="datetime-local" name="event_str_date" id="date" max="2099-06-20" min="2023-06-05" value="2023-06-15">
    </label>
    <label for="date">종료일를 선택하세요:
        <input type="datetime-local" name="event_end_date" id="dateEnd" max="2099-06-20" min="2023-06-05" value="2023-06-15">
    </label>
<textarea class="pjStr" name="event_cont">

</textarea>
    <button onclick="goToWrite()" style>등록</button>
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
            alert("file_picker_callback");
            var input = document.createElement('input');
            input.setAttribute('type', 'file');
            input.setAttribute('accept', 'image/*');
            console.log(input);
            /*
Note: In modern browsers input[type="file"] is functional without
even adding it to the DOM, but that might not be the case in some older
or quirky browsers like IE, so you might want to add it to the DOM
just in case, and visually hide it. And do not forget do remove it
once you do not need it anymore.
*/

            input.onchange = function () {
                var file = this.files[0];

                alert("onchange");
                var reader = new FileReader();
                reader.onload = function () {
                    alert("reader.onload");
                    /*
  Note: Now we need to register the blob in TinyMCEs image blob
  registry. In the next release this part hopefully won't be
  necessary, as we are looking to handle it internally.
*/
                    var id = 'blobid' + new Date().getTime();
                    console.log('id='+id)
                    var blobCache =
                        tinymce.activeEditor.editorUpload.blobCache;
                    alert("blobCache"+blobCache);
                    console.log('blobCache'+blobCache)
                    var base64 = reader.result.split(',')[1];
                    alert("base64"+base64)
                    var blobInfo = blobCache.create(id, file, base64);
                    console.log(blobInfo)
                    blobCache.add(blobInfo);
                    alert("blobInfo"+blobInfo);

                    /* call the callback and populate the Title field with the file name */
                    files(blobInfo.blobUri(), { title: file.name });
                    alert("cb"+files);
                    console.log(files)
                };
                reader.readAsDataURL(file);

            };

            input.click();
        },
        content_style:
            'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',
    });
    function goToWrite(){
        let form = document.getElementById("push");
        form.setAttribute("action","<c:url value="/admin/eventWrite"/>");
        form.setAttribute("method","post");
        form.submit();
    }
</script>
</body>
</html>
