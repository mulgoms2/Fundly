<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<!-- 모든 input에 같은 클래스 적용 (outline 등 속성 적용 위해) ex. pjInput-->
<body>
<div class="pjWrap">
    <div class="pjCont">
        <!-- 창작자 이름 -->
        <div class="pjBox cName">
            <dl class="pjInfo">
                <dt class="pjInfoTit">창작자 이름<span class="icon"><b>*</b></span>
                </dt>
                <dd class="pjInfoDscpt">창작자 개인이나 팀을 대표할 수 있는 이름을 써주세요.
                </dd>
            </dl>
            <div class="pjForm">
                <div class="pjInputWrap">
                    <input type="text" class="pjInput" placeholder="창작자님의 이름을 입력해주세요."/>
                </div>
            </div>
        </div>
        <!-- 프로필 이미지 -->
        <div class="pjBox cProf">
            <dl class="pjInfo">
                <dt class="pjInfoTit">프로필 이미지<span class="icon"><b>*</b></span>
                </dt>
                <dd class="pjInfoDscpt">창작자 개인이나 팀의 사진을 올려주세요.
                </dd>
            </dl>
            <div class="pjForm">
                <div src=""></div>
                <input accept=".jpg, .png, .gif, .jpeg" type="file">
                <button class="pjImgUp">이미지 파일 업로드</button>
                <p>
                    "파일 형식은 jpg 또는 png 또는 gif,"
                    <br>
                    "사이즈는 가로 200px, 세로 200px 이상으로 올려주세요."
                </p>
            </div>
        </div>
        <!--창작자 소개-->
        <div class="pjBox intro">
            <dl class="pjInfo">
                <dt class="pjInfoTit">프로젝트 요약 <span class="icon"><b>*</b></span>
                </dt>
                <dd class="pjInfoDscpt">후원자 분들이 프로젝트를 빠르게 이해할 수 있도록
                    명확하고 간략하게 소개해주세요.
                </dd>
            </dl>
            <div class="pjForm">
                <div class="pj">
                    <div class="title"></div>
                    <div class="image">
                        <img src="" alt="">
                    </div>
                    <div class="pjInputWrap">
                        <textarea class="pjInput"></textarea>
                    </div>
                </div>
            </div>
        </div>

        <!--입금계좌-->
        <div class="pjBox acc">
            <dl class="pjInfo">
                <dt class="pjInfoTit">프로젝트 대표이미지 <span class="icon"><b>*</b></span>
                </dt>
                <dd class="pjInfoDscpt">후원자들이 프로젝트의 내용을 쉽게 파악하고 좋은 인상을 받을 수 있도록 이미지 가이드라인을 따라 주세요
                </dd>
            </dl>
            <div class="pjForm">
                <ul>
                    <li>
                        <p>계좌 종류</p>
                        <div class="btnWrap">
                            <button type="btn"><div >개인</div></button>
                            <button type="btn"><div>사업자</div></button>
                        </div>
                    </li>
                    <li>
                        <div>
                            <p>생년월일</p>
                            <input type="text">
                        </div>
                    </li>
                    <li>
                        <div>
                            <div class="half">
                                <p>거래은행</p>
                                <select id="bank">
                                    <option value="IBK">기업은행</option>
                                    <option value="KB">국민은행</option>
                                    <option value="NH">농협은행</option>
                                    <option value="SH">신한은행</option>
                                    <option value="WR">우리은행</option>
                                </select>
                            </div>
                            <div class="half">
                                <p>예금주명</p>
                                <input type="text" placeholder="예금주명을 정확히 입력해주세요.">
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="accNum">
                            <p>계좌번호</p>
                            <input type="text" placeholder="숫자로만 입력해주세요.">
                        </div>
                    </li>
                    <li>
                        <div class="accUp">
                            <p>통장 사본 첨부</p>
                            <input accept=".jpg, .jpeg, .png, .pdf" type="file">
                            <button type="button">파일 업로드</button>
                        </div>
                    </li>
                    <li>
                        <div class="btnWrap">
                            <button class="btn">취소</button>
                            <button class="btn">등록</button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>