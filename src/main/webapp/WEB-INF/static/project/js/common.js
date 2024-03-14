window.addEventListener("load", () => {
    const projectSubmitForm = document.getElementById("pjSubmitForm");

    projectSubmitForm.addEventListener("submit", submitProject);
});

const submitProject = (e) => {
    if (!window.confirm("최종 제출 이후에는 프로젝트 심사가 진행됩니다. 심사 중에는 프로젝트를 수정할 수 없습니다. 제출하시겠습니까?")) {
        e.preventDefault();
    }
}

const postProject = async (endPoint, formData) => {
    const response = await fetch(endPoint, {
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

const countText = (text) => {
    return text.length;
};
const displayCount = (count, maxNum, where) => {
    where.innerText = `${count}/${maxNum}`;
};
const deleteLastChar = (str) => {
    return str.slice(0, -1);
};
const handleTextCount = (e, MAX_COUNT, tag_id) => {
//     글자수를 체크한다
    const text = e.target.value;
    const count = countText(text);
    const count_tag = document.getElementById(tag_id);


//     글자수를 제한한다
    if (count > MAX_COUNT) {
        alert(`최대 ${MAX_COUNT}글자 까지만 입력하실 수 있습니다.`);
        e.target.value = deleteLastChar(text);
        return;
    }
//     글자수릂 표시한다.
    displayCount(count, MAX_COUNT, count_tag);
};