window.onload = () => {
    const projectSubmitForm = document.getElementById("pjSubmitForm");

    projectSubmitForm.addEventListener("submit", submitProject);
};

const submitProject = (e) => {
    if (!window.confirm("최종 제출 이후에는 프로젝트 심사가 진행됩니다. 심사 중에는 프로젝트를 수정할 수 없습니다. 제출하시겠습니까?")) {
        e.preventDefault();
    }
}