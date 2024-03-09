window.onload = () => {
    const projectSubmitForm = document.getElementById("pjSubmitForm");

    projectSubmitForm.addEventListener("submit", submitProject);
};

const submitProject = (e) => {
    if (!window.confirm("최종 제출 이후에는 프로젝트 심사가 진행됩니다. 심사 중에는 프로젝트를 수정할 수 없습니다. 제출하시겠습니까?")) {
        e.preventDefault();
    }
}

const postProject = async (endPoint, formData) => {
    const response = await fetch(endPoint ,{
        method: "post",
        headers: {},
        body: formData,
    });

    const isSucceed = await response.json();

    if (isSucceed)
        alert("저장이 완료되었습니다.")
}

const fetchImage = async (endPoint, imgFormData) => {
    const response = await fetch(endPoint, {
        method: "post",
        headers: {},
        body: imgFormData,
    });

    return await response.text();
};

const getImageFormData = (tagId) => {
    const formData = new FormData();
    const imgFile = document.getElementById(tagId).files[0];

    formData.append("image", imgFile);

    return formData;
};
const printImgTag = (tagId, imgSrc) => {
    document.getElementById(tagId).src = imgSrc;
};
const clearInput = (e) => {
    e.target.value = "";
};