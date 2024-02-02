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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <script src="https://cdn.tiny.cloud/1/uh0icij1g3eyzvh7ppnwlga6kxke0lnffev72sw6bz89u7rb/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
</head>
<body>
<h1>TinyMCE Quick Start Guide</h1>
<form id="txtForm">
<textarea name="pjPurpose">
    text editor
</textarea>
<textarea name="pjBudget">
    text editor
</textarea>
<textarea name="pjSched">
    text editor
</textarea>
<textarea name="pjIntro">
    text editor
</textarea>
    <input type="submit">
</form>

<script>
    // var tinyEditor = tinymce.init({
    //     selector: 'textarea',
    //     min_height: 500,
    //     max_height: 1000,
    //     menubar: false,
    //     paste_as_text: true,
    //     fullpage_default_font_size: "14px",
    //     branding: false,
    //     plugins: "autolink code link autoresize paste contextmenu image preview",
    //     toolbar: "undo redo | fontsizeselect | forecolor | bold italic strikethrough underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link custom_image | code preview",
    //     fontsize_formats: '10px 12px 14px 16px 18px 20px 22px 24px 28px 32px 36px 48px',
    //     setup: function(editor) {
    //         editor.ui.registry.addButton('custom_image', {
    //             icon: 'image',
    //             tooltip: 'insert Image',
    //             onAction: function () {
    //                 documentUpload({
    //                     multiple: false,
    //                     accept: '.jpg, .png',
    //                     callback: function (data) {
    //                         if (data.rs_st === 0) {
    //                             var fileInfo = data.rs_data;
    //                             tinymce.execCommand('mceInsertContent', false,
    //                                 /**
    //                                  "<img src='" + fileInfo.fullPath + "' data-mce-src='" + fileInfo.fullPath + "' data-originalFileName='" + fileInfo.orgFilename + "' >");
    //                                  **/
    //                                 "<img src='" + fileInfo.thumbnailPath + "' data-mce-src='" + fileInfo.thumbnailPath + "' data-originalFileName='" + fileInfo.orgFilename + "' >");
    //                         }
    //                     }
    //                 });
    //             }
    //         });
    //     }
    // });
    // };
    // tinymce.init({
    //     selector: 'textarea',  // change this value according to your HTML
    //     menu: {
    //         file: { title: 'File', items: 'newdocument restoredraft | preview | export print | deleteallconversations' },
    //         edit: { title: 'Edit', items: 'undo redo | cut copy paste pastetext | selectall | searchreplace' },
    //         view: { title: 'View', items: 'code | visualaid visualchars visualblocks | spellchecker | preview fullscreen | showcomments' },
    //         insert: { title: 'Insert', items: 'image link media addcomment pageembed template codesample inserttable | charmap emoticons hr | pagebreak nonbreaking anchor tableofcontents | insertdatetime' },
    //         format: { title: 'Format', items: 'bold italic underline strikethrough superscript subscript codeformat | styles blocks fontfamily fontsize align lineheight | forecolor backcolor | language | removeformat' },
    //         tools: { title: 'Tools', items: 'spellchecker spellcheckerlanguage | a11ycheck code wordcount' },
    //         table: { title: 'Table', items: 'inserttable | cell row column | advtablesort | tableprops deletetable' },
    //         help: { title: 'Help', items: 'help' }
    //     }
    // });
    tinymce.init({
        selector: 'textarea',
        plugins: 'ai tinycomments mentions anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed permanentpen footnotes advtemplate advtable advcode editimage tableofcontents mergetags powerpaste tinymcespellchecker autocorrect a11ychecker typography inlinecss',
        toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
        tinycomments_mode: 'embedded',
        tinycomments_author: 'Author name',
        mergetags_list: [
            { value: 'First.Name', title: 'First Name' },
            { value: 'Email', title: 'Email' },
        ],
        images_file_types: 'jpg,jpeg,png',
        file_picker_types: 'file image media',
        // ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant"))


    });
    //
    // tinymce.init({
    //     selector: 'textarea',  // change this value according to your HTML
    //     theme: 'modern',
    //     plugins: 'ai tinycomments mentions anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed permanentpen footnotes advtemplate advtable advcode editimage tableofcontents mergetags powerpaste tinymcespellchecker autocorrect a11ychecker typography inlinecss',
    //     // plugins: [
    //     //     'advlist autolink lists link image charmap print preview hr anchor pagebreak',
    //     //     'searchreplace wordcount visualblocks visualchars code fullscreen',
    //     //     'insertdatetime media nonbreaking save table contextmenu directionality',
    //     //     'emoticons template paste textcolor colorpicker textpattern imagetools image'
    //     // ],
    //     toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    //     // toolbar2: 'print preview media | forecolor backcolor emoticons | code',
    //     image_title: true,
    //     automatic_uploads: true,
    //     images_file_types: 'jpg,jpeg,png',
    //     file_picker_types: 'image',
    //     block_unsupported_drop: true,
    //     // file_picker_callback: function(cb, value, meta){}
    //
    //     })

    //     file_picker_callback: (callback, value, meta) => {
    //         // Provide file and text for the link dialog
    //         if (meta.filetype == 'file') {
    //             callback('mypage.html', { text: 'My text' });
    //         }
    //
    //         // Provide image and alt text for the image dialog
    //         if (meta.filetype == 'image') {
    //             callback('myimage.jpg', { alt: 'My alt text' });
    //         }
    //
    //         // Provide alternative source and posted for the media dialog
    //         if (meta.filetype == 'media') {
    //             callback('movie.mp4', { source2: 'alt.ogg', poster: 'image.jpg' });
    //         }
    //     }
    // });

    let txtForm = document.querySelector("#txtForm");
    txtForm.addEventListener("submit",function(e){
        e.preventDefault();
        if(txtForm.value = ""){
            return false;
        }
        txtForm.action = '<c:url value="/txt/post"/>';
        txtForm.method = 'POST';
        txtForm.submit();
    })
</script>
</body>
</html>

