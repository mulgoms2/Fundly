const purpose = document.querySelector('div.purpose');
const purposeTit = document.querySelector('div.purpose div.tit');
const budget = document.querySelector('div.budget');
const budgetTit = document.querySelector('div.budget div.tit');
const sched = document.querySelector('div.sched');
const schedTit = document.querySelector('div.sched div.tit');
const intro = document.querySelector('div.intro');
const introTit = document.querySelector('div.intro div.tit');
const reward = document.querySelector('div.reward');
const rewardTit = document.querySelector('div.reward div.tit');
const storySaveBtn = document.querySelector('button.save');
const storyModifyBtn = document.querySelector('button.modify');

const divs = document.querySelectorAll('div.sub:not(.tit)');

//텍스트 에디터의 각 요소들

//tinyMCE 텍스트 에디터 설정 및 커스텀
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
    images_upload_url: '/project/story/image',
    // URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)
    // images_reuse_filename: true, //이걸 true로 주면 ? 물음표가 뒤에 붙는다. 왜지?
    /* enable automatic uploads of images represented by blob or data URIs*/
    automatic_uploads: true,

    file_picker_types: 'image',
    /* and here's our custom image picker*/

    file_picker_callback: function (cb, value, meta) {
        const input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');

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

                console.log("blobCache")
                console.log(blobCache)

                /* call the callback and populate the Title field with the file name */
                cb(blobInfo.blobUri(), { title: file.name });

            };
            reader.readAsDataURL(file);
        };

        input.click();
    },
    content_style:
        'img { width: 620px; height: auto; } body { font-family:Helvetica,Arial,sans-serif; font-size:14px; }', //인라인 스타일 속성으로 들어가게 된다. 이미지 사이즈를 가로 620px으로 고정하기.

});


window.onload = function(){
    console.log(divs);
    console.log(storyModifyBtn);

    storySaveBtn.addEventListener('click', function(){
        // console.log("pj_intro")
        // console.log(pj_intro)
        const pj_intro = tinymce.get('intro') //에디터에 입력한 value
        const pj_budget = tinymce.get('budget')
        const pj_sched = tinymce.get('sched');
        const pj_sel_intro =  tinymce.get('selIntro');
        const pj_gift_intro =  tinymce.get('reward');


        const storyForm = {
            "pj_id": "pj1", //지금은 하드코딩인데.. 어디서 가져올지 생각하기
            "pj_intro": pj_intro.getContent(),
            "pj_budget": pj_budget.getContent(),
            "pj_sched": pj_sched.getContent(),
            "pj_sel_intro": pj_sel_intro.getContent(),
            "pj_gift_intro": pj_gift_intro.getContent()
        }

        console.log(storyForm);
        fetch("/project/story",{
            method: "POST",
            headers: {
                "content-type": "application/json",
                "accept": "application/json"
            },
            body: JSON.stringify(storyForm),
        })
            .then(response => {
                if(!response.ok){
                    throw response
                }
                return response.json()
            })
            .then(data => {
                // 미리보기처럼 작성한 텍스트를 div에 넣어 보여준다.
                alert("프로젝트 계획이 성공적으로 저장되었습니다.")
                console.log("data received")
                console.log(data)

                for(div of divs){
                    //div.style.display = 'none';
                    div.classList.add('hidden')
                }

                purpose.innerHTML = purposeTit.outerHTML+'<div class="saved"><hr>'+data.pj_intro+'</div>';
                budget.innerHTML = budgetTit.outerHTML+'<div class="saved"><hr>'+data.pj_budget+'</div>';
                sched.innerHTML = schedTit.outerHTML+'<div class="saved"><hr>'+data.pj_sched+'</div>';
                intro.innerHTML = introTit.outerHTML+'<div class="saved"><hr>'+data.pj_sel_intro+'</div>';
                reward.innerHTML = rewardTit.outerHTML+'<div class="saved"><hr>'+data.pj_gift_intro+'</div>';

                //수정버튼이 보이게한다.
                storySaveBtn.style.display = 'none';
                storyModifyBtn.style.display = 'block';



                // pj_intro.setContent(data.pj_intro)
                // pj_budget.setContent(data.pj_budget)
                // pj_sched.setContent((data.pj_sched == null)? "" : data.pj_sched);
                // //setContent 함수는 매개변수가 null이면 return하는 것 같다. null이 반영이 안돼서 이렇게 해줘야 반영이 됨.
                // //(그냥 테스트용으로 반영하는 것)
                //
                // // console.log("here")
                // // console.log(data.pj_sched)
                // // console.log(pj_sched.getContent()) //왜 여기 세개 console.log는 실행이 안되지?
                // pj_sel_intro.setContent(data.pj_sel_intro)
                // pj_gift_intro.setContent(data.pj_gift_intro)

            })
            .catch(error => error)
    })

    storyModifyBtn.addEventListener('click',function(){
        console.log(this)
        //수정버튼을 누르면 다시 텍스트에디터에 data가 담긴 form이 나타난다. display:none인 상태의 요소는 가져올 수가 없구나..
        for(div of divs){
            div.classList.remove('hidden');
        }
        // const savedDiv = document.querySelectorAll('div.saved');
        // for(saved of savedDiv){
        //     saved.remove();
        // }
        const pj_id = "pj1"
        location.href = "/project/story?pj_id="+pj_id
        // fetch("/project/story?pj_id="+pj_id,{
        //     method: "GET"
        // })
    })


}
