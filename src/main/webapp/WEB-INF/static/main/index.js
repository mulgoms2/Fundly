window.addEventListener("load", () => {
    initProjectTemplate();
});
const initProjectTemplate = async () => {
    // 서버에 프로젝트를 요청한다.
    const project_list = await fetchProjectList();
    // 프로젝트 리스트를 반환받는다.

    // 프로젝트 리스트에서 프로젝트 하나씩을 태그로 만든다.
    project_list.forEach(projectDto => {
        // 프로젝트 하나하나를 태그로 만든다.
        const pj_tag = makePjTemplateTag(projectDto);
        // 그리드 안에 넣는다.

        //그리드가 있어야 한다.
        const grid = document.querySelector(".gRd");
        grid.innerHTML += pj_tag;
    });

    // 하나씩 태그로 만들 때 일어나는 일
    // 프로젝트 객체로부터 이미지 태그 url을 비롯한 각각의 정보를 받아낸다.

    // 해당 정보들을 토대로 태그를 생성한다.

    // 생성된 태그를 grid에 넣어준다.
};

const fetchProjectList = async () => {
    // 서버로 프로젝트 get 요청을 보낸다.
    const category = "반려동물";
    const endPoint = "/project/" + category;
    const response = await fetch(endPoint, {
        method: "get",
    });

    const projectList = await response.json();

    // 받아온 프로젝트 리스트를 반환한다.
    console.log(projectList);

    return projectList;
};
const makePjTemplateTag = (projectDto) => {

    const pj_id = projectDto.pj_id;
    const imgSrcUrl = projectDto.thumbnail_img_url;
    const long_title = projectDto.long_title;
    const category = projectDto.category;
    const funding_percentage = projectDto.funding_percentage;
    const sel_name = projectDto.sel_name;
    const detail_page = "/detail/" + pj_id;

    return `<div class="banBox">
                <div class="mnBan">
                    <div class="banImg">
                        <a href=${detail_page}>
                            <img src="${imgSrcUrl}" alt="">
                        </a>
                    </div>
                    <div class="faLike">
                        <button class="likeBtn"><!--하트 처리 미완성-->
                        </button>
                    </div>
                </div>
                <div class="banTxt">
                    <div class="txtGr">
                        <div class="subTxt">
                            <span>
                               <a href="#">${category}</a>
                            </span>
                            <span class="bar">
                               <a href="#">${sel_name}</a>
                            </span>
                        </div>
                        <div class="subTit">
<!--                       프로젝트 타이틀  -->
                            <a href=${detail_page}>${long_title}</a>
                        </div>
                        <div class="pstTag">
                            <span>${funding_percentage}%</span>
                        </div>
                    </div>
                </div>
              </div>
             `;
};
