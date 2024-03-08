const saveProject = async (e) => {
//     버튼이 눌리면 프로젝트 변경 사항을 서버에 보낸다.
    const formData = new FormData();
    const sel_name = document.getElementById("selName").value;
    const sel_intro = document.getElementById("selIntro").value;

    formData.append("pj_sel_name", sel_name);
    formData.append("pj_sel_short_intro", sel_intro);

    const response = await fetch("/project/editor/creator",{
        method: "post",
        headers: {},
        body: formData,
    });

    const isSucceed = await response.json();

    if (isSucceed)
        alert("저장이 완료되었습니다.")
};

const handleImgInput = (e) => {
    uploadProfileImg();
    e.target.value = "";
};

const uploadProfileImg = async () => {
    const endPoint = "/project/editor/creator/image";

    const response = await fetch(endPoint, {
        method : "post",
        headers:{},
        body: getImageFormData("pjImgUp"),
    });

    const saved_url = await response.text();

    printImgTag("profImg", saved_url);
};

const printImgTag = (tagId, imgSrc) => {
    document.getElementById(tagId).src = imgSrc;
};
const uploadAccountImage = () => {
};

const getImageFormData = (tagId) => {
    const formData = new FormData();
    const imgFile = document.getElementById("pjImgUp").files[0];

    formData.append("image", imgFile);

    return formData;
};