const printSubCategory = () => {
    const category = document.querySelector("#category");
    const subCtg = document.querySelector("#subCategory");

    if (category.value === "반려동물") {
        const 먹이 = document.createElement('option');
        const 장난감 = document.createElement('option');
        먹이.innerText = "먹이";
        장난감.innerText = "장난감";

        subCtg.appendChild(먹이);
        subCtg.appendChild(장난감);
    }
};

const handleTagInput = (e) => {
    const regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
    let inputValue = e.target.value;

    console.log(regExp.test(inputValue));
    
    if (regExp.test(inputValue)) {
        console.log("log");
        alert("특수문자 및 공백은 입력하실 수 없습니다.")
        e.target.value = "";
        return;
    }

    if (e.code === "Enter") {
        if (maxCount(5)) {
            alert("태그는 최대 5개 까지만 저장할 수 있습니다.")
            clearInput(e);
            return;
        }
        const tagString = e.target.value;
        console.log(tagString);
        if (tagString.trim().length === 0) {
            clearInput(e);
            return;
        }
        // const searchTag = makeTag(tagString);
        //
        // printTag(searchTag);

        const span = document.createElement("span");
        span.className = "searchTag";
        span.innerText = tagString;

        const button = document.createElement("button");
        button.classList.add();

        document.querySelector("#tagContainer").appendChild(span);

        clearInput(e);
    }
};

const printTag = (tag) => {
    document.querySelector("#tagContainer").innerHTML += tag;
};

const maxCount = (count) => {
    const tagCount = document.querySelector("#tagContainer").children.length;

    return count < tagCount + 1;
};

const clearInput = (e) => {
    e.target.value = "";
};

const makeTag = (content) => {
    return `<span class="searchTag">${content}<button id="eraseBtn" class="eraseBtn"><i class="fa-solid fa-x fa-2xs"></i></button></span>`;
};

const deleteSearchTag = (e) => {
    e.currentTarget.parentElement.outerHTML = "";
};

const concatSearchTags = () => {
    const tagList = document.querySelector("#tagContainer").children;
    const tagArr = [...tagList].map(span => span.innerText);
    // 태그를 컴마로 구문된 문자열로 합친다.

    return tagArr.toString();
};

const updateProjectInfo = async () => {
    const longTitle = document.querySelector("#longTitle").value;
    const shotTitle = document.querySelector("#shortTitle").value;
    const pjIntro = document.querySelector("#pjIntro").value;
    const category = document.querySelector("#category").value;
    const searchTags = concatSearchTags();

    const formData = new FormData();
    formData.append("ctg", category);
    // formData.append("sub_ctg");
    formData.append("pj_long_title", longTitle);
    formData.append("pj_short_title", shotTitle);
    formData.append("pj_short_intro", pjIntro);
    formData.append("pj_tag", searchTags);

    const response = await fetch("/project/editor/infoUpdate", {
        method: "post",
        headers: {},
        body: formData
    });

    const result = await response.json();
    if (result) {
        alert("저장이 완료되었습니다.");
    }
};