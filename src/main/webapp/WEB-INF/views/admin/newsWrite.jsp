
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Summernote with Bootstrap 4</title>
<%--    <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>--%>

<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">--%>
<%--    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>--%>

<%--    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>--%>
    <script src="https://cdn.tiny.cloud/1/uh0icij1g3eyzvh7ppnwlga6kxke0lnffev72sw6bz89u7rb/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
</head>
<body>
<form id="emdfhr" action="<c:url value="/admin/write"/>" method="post">
    <input type="text" name="news_title" value="" placeholder="제목">
    <input type="text" name="reg_id" value="" placeholder="작성자">
    <textarea class="pjStr" name="news_cont">

                            </textarea>
    <button type="submit">등록</button>
</form>
<script>
    // $(document).ready(function() {
    //     $('#summernote').summernote({
    //         height: 500,
    //         width: 1200,
    //         lang: 'ko-KR',
    //         toolbar: [
    //             ['fontsize', ['fontsize']],
    //             ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
    //             ['color', ['color']],
    //             ['table', ['table']],
    //             ['para', ['ul', 'ol', 'paragraph']],
    //             ['height', ['height']],
    //             ['insert', ['picture']]
    //         ],
    //         fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋음체', '바탕체'],
    //         fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72', '96'],
    //         focus: true,
    //         callbacks: {
    //             onImageUpload: function(files, editor, welEditable) {
    //                 for (var i = 0; i < files.length; i++) {
    //                     imageUploader(files[i], this)   ;
    //                 }
    //             },
    //             onChange: function(contents, $editable) {
    //                 $('#summernoteContent').val(contents); // summernote 내용을 숨은 입력 필드에 설정
    //             }
    //         }
    //     });
    // });
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
</body>
</html>