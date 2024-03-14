window.addEventListener("load", () => {
    const saveBtn = document.getElementById("saveBtn");
    saveBtn.addEventListener("click", handleSaveBtnClick);

    const imgInput = document.getElementById("pjImgUp");
    imgInput.addEventListener("input", handleImgInput);

    const nameInput = document.getElementById("selName");
    nameInput.addEventListener("input", e =>
        handleTextCount(e, 20, "nameCount")
    );

    const selIntro = document.getElementById("selIntro");
    selIntro.addEventListener("input", (e) =>
        handleTextCount(e, 50, "introCount")
    );
});

const handleSaveBtnClick = () => {
    //     버튼이 눌리면 프로젝트 변경 사항을 서버에 보낸다.
    const endPoint = "/project/editor/creator";
    const formData = getCreatorInfoFormData();

    postProject(endPoint, formData);
}

const getCreatorInfoFormData = () => {
    const formData = new FormData();
    const sel_name = document.getElementById("selName").value;
    const sel_intro = document.getElementById("selIntro").value;

    formData.append("pj_sel_name", sel_name);
    formData.append("pj_sel_short_intro", sel_intro);

    return formData;
};

const handleImgInput = async (e) => {
    const endPoint = "/project/editor/creator/image";
    const imgFormData = getImageFormData("pjImgUp");
    const src_url = await fetchImage(endPoint, imgFormData);

    printImgTag("profImg", src_url);
    clearInput(e);
};