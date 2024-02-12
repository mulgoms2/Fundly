// /* JSP SCRIPT */
// var bbsidx = ${bbsidx};
// var useridx = ${useridx};
//
// var likeBtn = document.getElementById("likeBtn");
// likeBtn.onclick = function(){ changeHeart(); }
//
// /* 좋아요 버튼 눌렀을떄 */
// function changeHeart(){
//     $.ajax({
//         type : "POST",
//         url : "/clickLike",
//         dataType : "json",
//         data : "bbsidx="+bbsidx+"&useridx="+useridx,
//         error : function(){
//             Rnd.alert("통신 에러","error","확인",function(){});
//         },
//         success : function(jdata) {
//             if(jdata.resultCode == -1){
//                 Rnd.alert("좋아요 오류","error","확인",function(){});
//             }
//             else{
//                 if(jdata.likecheck == 1){
//                     $("#likeBtn").attr("src","/home/img/ico_like_after.png");
//                     $("#likecnt").empty();
//                     $("#likecnt").append(jdata.likecnt);
//                 }
//                 else if (jdata.likecheck == 0){
//                     $("#likeBtn").attr("src","/home/img/ico_like_before.png");
//                     $("#likecnt").empty();
//                     $("#likecnt").append(jdata.likecnt);
//
//                 }
//             }
//         }
//     });
// }
//
// // $(document).ready(function() {
// //        $(".like").on("click", function(){
// //
// //            $.ajax({
// //                    url : "detail.jsp",
// //                    type: 'POST',
// //                    data: {'pj_id':'${pj_id}', 'buyer_id':'${user_id}'},
// //            success:function(data){
// //
// //                if(data==1){
// //                    like2 = true;
// //                    alert("상품 찜 하셨습니다.");
// //                    $('#like').attr("src","빨간하트");
// //                    var result = confirm('찜목록으로 이동하시겠습니까?');
// //                    if (result) {
// //                        //yes
// //                        //찜 리스트 페이지 생성 후 -> 찜리스트 페이지 이동으로 변경
// //                        location.href='/user/like';
// //
// //                    }
// //
// //                }
// //                else if(data == -1){
// //                    alert("로그인이 필요한 서비스입니다. ");
// //
// //
// //                }
// //                else {
// //                    like2 =false;
// //                    alert("상품 찜 취소하셨습니다. ");
// //                    $('#like').attr("src","빈하트");
// //                }
// //
// //            },
// //            error:function(error){
// //                console.log(error);
// //            }
// //
// //
// // 		 	});
// //        });
// //    });