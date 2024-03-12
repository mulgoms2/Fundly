<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="q" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <style>
    .gmYOwM{
      position: relative;
      display: block;
      width: 100%;
      -webkit-box-flex: 1;
      flex-grow: 1;
      background: rgb(255, 255, 255);
    }
    .cwTZWc {
      border-bottom: 1px solid rgb(230, 230, 230);
      position: relative;
    }
    .jPogNc {
      width: 100%;
    }
    .jWaTvq {
      border-bottom: 1px solid rgb(230, 230, 230);
    }
    .gLMSbr {
      padding: 32px 16px 18px;
    }
    .dwqwNI {
      max-width: 1160px;
      margin: 0px auto;
    }
    .diQRdx {
      height: 56px;
      display: flex;
      overflow: auto hidden;
      position: relative;
    }
    .jmMXca {
      padding: 0px;
      font-size: 18px;
    }


    .jmMXca {
      display: inline-flex;
      word-break: keep-all;
      /*padding: 0px 20px;*/
      /*font-size: 14px;*/
      padding: 0px;
      font-size: 18px;
    }
    .hauegL {
        margin-right: 32px;
    }


    .hauegL {
        margin: 0px 24px 0px 0px;
        display: flex;
        -webkit-box-align: center;
        align-items: center;
        height: 100%;
    }
    a{
        text-decoration: none;
    }
    .tbb .fnt-lt {
        font-weight: 300;
    }

    .gLMSbr span {
        font-size: 36px;
        line-height: 54px;
        letter-spacing: -0.035em;
    }
    .kctMgq {
      flex-direction: column;
      padding: 24px 24px 0px;
    }


    .kctMgq .list-wrapper {
      width: 100%;
      border: 0px;
      border-radius: 4px;
      border: 1px solid rgb(230, 230, 230);
    }
    .bnfiEq {
      width: 100%;
      list-style: none;
      padding: 0px;
      margin: 0px;
      border-bottom: 4px solid rgb(230, 230, 230);
    }
    .cwiHPf {
      width: 100%;
      list-style: none;
      padding: 0px;
      margin: 0px;
      border-bottom: 0px;
    }

    .cwiHPf li {
      display: flex;
      /*min-height: 103px;*/
      /*padding: 16px;*/
      border-bottom: 1px solid rgb(230, 230, 230);
      min-height: 131px;
      padding: 16px 24px;
    }
    .cwiHPf li a {
      display: flex;
      width: 100%;
      flex-wrap: wrap;
      -webkit-box-align: center;
      align-items: center;
      -webkit-box-pack: justify;
      justify-content: space-between;
    }


    .cwiHPf li .title-box {
      display: block;
      flex: 2 1 0%;
      margin: 0px 16px 0px 0px;
      /* margin: 0px 40px 0px 0px; */
    }


    .cwiHPf li .group {
      display: flex;
      -webkit-box-align: center;
      align-items: center;
      /*font-size: 11px;*/
      font-weight: bold;
      color: rgb(158, 158, 158);
      line-height: 1.54;
      letter-spacing: -0.2px;
      /*margin: 0px 0px 2px;*/
      font-size: 13px;
      margin: 0px 0px 4px;
    }
    .cwiHPf li .group em {
      display: inline-block;
      font-size: 10px;
      font-weight: bold;
      font-style: normal;
      color: rgb(255, 255, 255);
      line-height: 1.6;
      border-radius: 2px;
      padding: 1px 6px;
      margin: 0px 0px 0px 6px;
    }


    .cwiHPf li .title-box .title {
      display: -webkit-box;
      flex: 1 1 0%;
      /*font-size: 16px;*/
      line-height: 27px;
      font-weight: normal;
      color: rgb(61, 61, 61);
      overflow: hidden;
      text-overflow: ellipsis;
      -webkit-line-clamp: 2;
      -webkit-box-orient: vertical;
      /*letter-spacing: -0.36px;*/
      /*margin: 0px 0px 6px;*/
      font-size: 18px;
      letter-spacing: -0.02em;
      margin: 0px 0px 12px;
    }


    .cwiHPf li .date {
      display: block;
      /*font-size: 11px;*/
      /*line-height: 18px;*/
      color: rgb(109, 109, 109);
      /*letter-spacing: -0.2px;*/
      font-size: 13px;
      line-height: 20px;
      letter-spacing: -0.015em;
    }

    .cwiHPf li .img-box {
      /*width: 96px;*/
      /*height: 72px;*/
      overflow: hidden;
      width: 133px;
      height: 99px;
    }


    .cwiHPf li .img-box img {
      width: 100%;
      /*height: 72px;*/
      height: 99px;
    }


  /*  진행중 종료 css*/

    .cwiHPf li .group em {
        display: inline-block;
        font-size: 10px;
        font-weight: bold;
        font-style: normal;
        color: rgb(255, 255, 255);
        line-height: 1.6;
        border-radius: 2px;
        padding: 1px 6px;
        margin: 0px 0px 0px 6px;
    }
    .cwiHPf li .group em.complete {
      background: rgb(61, 61, 61);
    }
    .cwiHPf li .group em.in_progress {
        background: rgb(255, 87, 87);
    }

  </style>
</head>
<body>
<div class="style__Container-sc-7of8vt-0 gmYOwM">
  <div class="Page__PageComponent-sc-1l7nky8-0 jPogNc Notices__StyledPage-sc-1dlk0ho-0 cwTZWc tbb">
    <div class="Notices__SettingTitle-sc-1dlk0ho-1 jWaTvq">
      <div class="Container__ContainerComponent-o2c7wa-0 dwqwNI Notices__StyledContainer-sc-1dlk0ho-3 gLMSbr">
        <span class="fnt-lt">공지사항</span>
      </div>
      <div class="Container__ContainerComponent-o2c7wa-0 dwqwNI">
        <div class="TabContainer__ContainerComponent-sc-1vfhfos-0 diQRdx Notices__StyledTabContainer-sc-1dlk0ho-2 lfPVkB">
          <div class="TabContainer__TabWrapper-sc-1vfhfos-1 jmMXca">
                            <span class="TabContainer__Tab-sc-1vfhfos-2 hauegL current">
                                <a href="<c:url value="/noticeList"/> ">공지사항</a>
                            </span>
            <span class="TabContainer__Tab-sc-1vfhfos-2 hauegL ">
                                <a href="<c:url value="/eventList"/> ">이벤트</a>
                            </span>
          </div>
        </div>
      </div>
    </div>

    <div class="Container__ContainerComponent-o2c7wa-0 dwqwNI StyledTBB__column2Wrapper-trchgn-2 CommonStyled__Wrapper-bpcmiq-0 List__StyledWrapper-sc-172nbbl-2 gYkBev cwoqcI kctMgq">
      <div class="list-wrapper">
        <div class="List__StyledList-sc-1ldfygd-0 cwiHPf">

          <c:forEach var="EventDto" items="${EventList}">
            <li class>
              <a href="<c:url value='/eventDetail?event_seq=${EventDto.event_seq}&page=${page}'/>">
                                <span class="title-box">
                                    <span class="group">이벤트
                                         <c:choose>
                                           <c:when test="${EventDto.event_str_date.isBefore(now) && EventDto.event_end_date.isAfter(now)}">
                                             <em class="in_progress">진행 중</em>
                                           </c:when>
                                           <c:otherwise>
                                             <em class="complete">종료</em>
                                           </c:otherwise>
                                         </c:choose>

                                    </span>
                                    <span class="title"> ${EventDto.event_title}</span>
                                    <span class="date">${EventDto.mod_dtm!=null? EventDto.mod_dtm :EventDto.reg_dtm}</span>
                                </span>
                <div class="img-box">
                  <img src="">
                </div>
              </a>
            </li>
          </c:forEach>
        </div>
      </div>
      <div class="pg" style=" display: flex;  margin: 20px 400px 40px;justify-content: space-around;">
        <c:if test="${ph.showPrev}">
          <a href="<c:url value='/admin/list?page=${ph.beginPage-1}'/>">&lt;</a>
        </c:if>
        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
          <a href="<c:url value='/admin/list?page=${i}'/> ">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
          <a href="<c:url value='/admin/list?page=${ph.endPage+1}'/>">&gt;</a>
        </c:if>
      </div>
      <input type="text" id="searchInput"  placeholder="검색어를 입력하세요">
    </div>


  </div>
</div>

</body>
</html>
