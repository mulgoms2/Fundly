const makePjTemplateTag = (imgSrcUrl) => {
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
                                <a href="#">아트북디자인더하트</a>
                            </span>
                        </div>
                        <div class="subTit">
                            <a href="#">12+1지신 노리개 부적 뱃지와 2024 새해 맞이</a>
                        </div>
                        <div class="pstTag">
                            <span>557% 달성</span>
                        </div>
                    </div>
                </div>`;
};

document.getElementById("ban1").innerHTML = makePjTemplateTag();