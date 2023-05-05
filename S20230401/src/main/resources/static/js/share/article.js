/**
 * 
 */
// 세션 스토리지에서 contextPath 호출하여 저장
const contextPath = sessionStorage.getItem("contextPath");
const artId = sessionStorage.getItem("artId");
const brdId = sessionStorage.getItem("brdId");
const category = sessionStorage.getItem("category");
const memId = sessionStorage.getItem("memId");
console.info(memId);

// 찜하기
$(document).ready(()=>{
    // 찜
    $('#btns-favorite').click(()=>{
        if(memId > 0){
            if(confirm('즐겨찾기 하시겠습니까?')){
                location.href = contextPath+'/board/share/favorite/add?art_id='+artId+'&brd_id='+brdId+'&category='+category;
            }
        }else alert('로그인 하세요');
    });
    // 취소
    $('#btns-favoriteDel').click(()=>{
        if(memId > 0){
            if(confirm('즐겨찾기 삭제 하시겠습니까?')){
                location.href = contextPath+'/board/share/favorite/del?art_id='+artId+'&brd_id='+brdId+'&category='+category;
            }
        }else alert('로그인 하세요')
    });
});

// 댓글 펼치기
$(document).ready(()=>{
    $('#btns-show, #btns-hide').click(e=>{
        $(e.target).closest('.reply-list')
        .find('#btns-show').toggle().end()
        .find('#btns-hide').toggle().end()
        .find('.reply-detail').toggle().end();
    });
});
// 댓글 기능 - 댓글 수정
$(document).ready(() => {
    $('.btns-repUpdate, .btns-cancel, .btns-repComplete').click(e => {
        var condition = $('.rep-content').prop('disabled');
        $(e.target)
        .closest('.reply-view')
        .find('.rep-content').prop('disabled', condition ? false : true).focus().end()
        .find('.btns-repUpdate').toggle().end()
        .find('.btns-delete').toggle().end()
        .find('.btns-repComplete').toggle().end()
        .find('.btns-cancel').toggle();
    });
});

// 댓글 수정 완료
function rep_Update(pIndex){
    let rep_content = $('#rep-content'+pIndex).val();
    let art_id = $('#art_id'+pIndex).val();
    let brd_id = $('#brd_id'+pIndex).val();
    let rep_id = $('#rep_id'+pIndex).val();
    
    $.ajax({
        url:contextPath+'/board/share/update',
        data:{art_id, brd_id, rep_id, rep_content},
        dataType:'json',
        success:function(data){
            console.log(data)
            $('body').load(location.href);
            }
    });
}

// 댓글 기능 - 댓글 삭제
function rep_delete(brd_id, art_id, rep_id) {
    location.href = contextPath+'/board/share/repDelete?art_id='+art_id+'&brd_id='+brd_id+'&rep_id='+rep_id;
}

// 댓글 기능 - 대댓글 작성 토글
$(document).ready(() => {
    $(".btns-repWrite").click(e => {
        console.log(e.target.getAttribute('class')); // 클릭 이벤트 발생시 e.target의 클래스 출력
        $(e.target).closest('.reply-detail').find(".reply-replyWrite").toggle();
    });
});

// 게시글 기능 - 게시글 삭제
function art_Delete(){
    let mem_id = $('#mem_id').val();
    let login_member = $('#login_member').val();
    let login_authority = $('#login_authority').val();
    if(mem_id != login_member && login_authority < 108){
        alert('권한이 없습니다');
        return false;
    }
    if(confirm('삭제 하시겠습니까?')){
        location.href=contextPath+'/board/share/artDelete/'+artId+'?brd_id='+brdId+'&category='+category;
        alert("삭제 되었습니다.");
    }
}
// 게시글 기능 -게시글 수정
function art_Update(){
    if(confirm('수정 하시겠습니까?')){
        location.href=contextPath+'/board/share/artUpdate?art_id='+artId+'&brd_id='+brdId+'&category='+category;
    }
}

// // 게시글 기능 - 추천, 비추천 -----------------------------------------------------------ajax사용 추천 취소기능 추가 요망
// $(document).ready(() => {
//     // 추천
//     $('#btns-good, #btns-goodcancel, #btns-bad, #btns-badcancel').click(e => {
//         if(e.target.getAttribute('id')==='btns-good'){
//             if(confirm('추천 하시겠습니까?')){
//                 $(e.target).closest('.article-vote')
//                 .find('#btns-good').toggle().end()
//                 .find('#btns-goodcancel').toggle().end();
//                 location.href=contextPath+'/board/share/vote/good?art_id='+artId+'&brd_id='+brdId+'&category='+category;
//             }
//         }else if(e.target.getAttribute('id')==='btns-goodcancel'){
//             if(confirm('취소 하시겠습니까?')){
//                 $(e.target).closest('.article-vote')
//                 .find('#btns-good').toggle().end()
//                 .find('#btns-goodcancel').toggle().end();
//                 location.href=contextPath+'/board/share/vote/good?art_id='+artId+'&brd_id='+brdId+'&category='+category;
//             }
//         }
//         // 비추천
//         else if(e.target.getAttribute('id')==='btns-bad'){
//             if(confirm('비추천 하시겠습니까?')){
//                 $(e.target).closest('.article-vote')
//                 .find('#btns-bad').toggle().end()
//                 .find('#btns-badcancel').toggle().end();
//                 location.href=contextPath+'/board/share/vote/bad?art_id='+artId+'&brd_id='+brdId+'&category='+category;
//             }
//         // 비추천 취소
//         }else if(e.target.getAttribute('id')==='btns-badcancel'){
//             if(confirm('취소 하시겠습니까?')){
//                 $(e.target).closest('.article-vote')
//                 .find('#btns-bad').toggle().end()
//                 .find('#btns-badcancel').toggle().end();
//                 location.href=contextPath+'/board/share/vote/badcancel?art_id='+artId+'&brd_id='+brdId+'&category='+category;
//             }
//         }

//     });
// });
// 게시글 기능 - 추천, 비추천 ----------------------------------------------------------ajax사용 추천 취소기능 추가 요망
$(document).ready(() => {
    let btns;
    let action;
    let message;
    $('#btns-good, #btns-goodcancel, #btns-bad, #btns-badcancel').click(e => { 
        if(memId > 0){
            if(e.target.getAttribute('id')==='btns-good'){
                message = '추천 하시겠습니까?';
                btns = 'good';
                action = '.btns-good';
            }else if(e.target.getAttribute('id')==='btns-goodcancel'){
                message = '취소 하시겠습니까?';
                btns = 'good';
                action = '.btns-good';
            }else if(e.target.getAttribute('id')==='btns-bad'){
                message = '비추천 하시겠습니까?';
                btns = 'bad';
                action = '.btns-bad';
            }else if(e.target.getAttribute('id')==='btns-badcancel'){
                message = '취소 하시겠습니까?';
                btns = 'bad';
                action = '.btns-bad';
            }
            console.info(btns);
            console.info(action);
            console.info(message);
            if(confirm(message)){
                $.ajax({
                    url:contextPath+'/board/share/vote/'+btns+'?art_id='+artId+'&brd_id='+brdId+'&category='+category,
                    success:function(data){
                        console.log(data)
                        $(e.target).closest('.article-vote')
                        .find(action).toggle().find('span').text(data).end();
                    }
                })
            }
        }else alert('로그인 하세요');
        });
});





// 거래 신청 ---------------------------------------------------------
$(document).ready(()=>{ 
    $('#btns-applyDel').click(()=>{
        if(memId > 0){
            if(confirm('취소 하시겠습니까?'))
            location.href=contextPath+'/board/share/waiting/del?art_id='+artId+'&brd_id='+brdId+'&category='+category;
        }else alert('로그인 하세요');
    });
    $('#btns-apply').click((e)=>{
        if(memId > 0){
            $('.myModal').css('display', 'flex'); // flex로 변경
        }else alert('로그인 하세요');
    });
    // 클릭시 닫기 이벤트
    $('.myModal').click(e=>{
        console.info(e.target.getAttribute('id'));
        console.info(e.target.getAttribute('class'));
        // 바깥 영역, 취소 버튼, X 버튼
        if(!$(e.target).closest('.myModal-window').length || e.target.getAttribute('id')==='btns-modalCancel' || e.target.getAttribute('id')==='modal-close'){
            $('#myModal').css('display', 'none');
        }else if(e.target.getAttribute('id')==='btns-modalApply')
            if(confirm('거래 신청 하시겠습니까?')){
                location.href=contextPath+'/board/share/waiting/add?art_id='+artId+'&brd_id='+brdId+'&category='+category;
            }
        e.stopPropagation();
    });
});
