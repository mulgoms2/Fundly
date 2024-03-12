window.addEventListener("load", () => {
    initProjectTemplate();
});
const initProjectTemplate = () => {
    // 서버에 프로젝트를 요청한다.

    console.log("helo")
    const project_list = fetchProjectList();
    // 프로젝트 리스트를 반환받는다.

    // 프로젝트 리스트에서 프로젝트 하나씩을 태그로 만든다.

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

    console.log(response);
    const projectList = await response.json();

    // 받아온 프로젝트 리스트를 반환한다.
    console.log(projectList);

    return projectList;
};
const makePjTemplateTag = (projectDto) => {
    //  private String pj_id;
    //     private String thumbnail_img_url;
    //     private String category;
    //     private String long_title;
    //     private String funding_percentage;
    // const imgSrcUrl = projectDto.thumbnail_img_url;
    const long_title = projectDto.title;

    return `<div class="mnBan">
                    <div class="banImg">
                        <a href="#">
                            <img src=${imgSrcUrl} alt="">
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
<!--                                <a href="#">아트북디자인더하트</a>-->
                            </span>
                        </div>
                        <div class="subTit">
<!--                       프로젝트 타이틀  -->
<!--                            <a href="#">12+1지신 노리개 부적 뱃지와 2024 새해 맞이</a>-->
                        </div>
                        <div class="pstTag">
<!--                        펀딩 달성률 -->
                            <span>557% 달성</span>
                        </div>
                    </div>
                </div>`;
};


document.getElementById("ban1").innerHTML = makePjTemplateTag();