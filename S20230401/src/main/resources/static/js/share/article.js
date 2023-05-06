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

// 게시글 기능 - 추천, 비추천 ----------------------------------------------------------ajax사용 추천 취소기능 추가 요망
$(document).ready(() => {
    // 추천 비추천 여부 확인
    checkVote();
    // 클릭 이벤트
    $('#btns-good, #btns-goodcancel, #btns-bad, #btns-badcancel').click(e => { 
        if(memId > 0){
            // 변수 선언
            let btns;   let action;     let message;
            let id = e.target.getAttribute('id');
            let buttonTypes = {
                'btns-good'      : {message:'추천 하시겠습니까?', btns:'good', action:'.btns-good'},
                'btns-goodcancel': {message:'취소 하시겠습니까?', btns:'good', action:'.btns-good'},
                'btns-bad'       : {message:'비추천 하시겠습니까?', btns:'bad', action:'.btns-bad'},
                'btns-badcancel' : {message:'취소 하시겠습니까?', btns:'bad', action:'.btns-bad'},
            };

            // 클릭한 버튼의 id와 일치하는 조건에 할당
            if(id in buttonTypes){
                ({message, btns, action} = buttonTypes[id]);
            }
            console.info(btns, action, message);

            if(confirm(message)){
                $.ajax({
                    url:contextPath+'/board/share/vote/'+btns+'?art_id='+artId+'&brd_id='+brdId+'&category='+category,
                    success:function(data){
                        console.log(data)
                        $(e.target).closest('#btns-vote')
                        .find(action).toggle().find('span').text(data).end();
                    }
                })
            }
        }else alert('로그인 하세요');
    });
});

// 추천, 비추천 여부 확인
function checkVote(){
    let cookies = document.cookie.split(';');
    for(let cookie of cookies){
        cookie = cookie.trim();
        // 쿠키 name에 유저가 있는지 확인
        console.info('share|'+memId+'=', '굿', cookie.includes(artId+brdId+'good'), '베드', cookie.includes(artId+brdId+'bad'))
        if(cookie.startsWith('share|'+memId+'=')){
            // 쿠키 value에 추천 비추천 확인
            if(cookie.includes(artId+brdId+'good')){
                document.getElementById('btns-good').style.display = 'none';
                document.getElementById('btns-goodcancel').style.display = '';
            }
            if(cookie.includes(artId+brdId+'bad')){
                document.getElementById('btns-bad').style.display = 'none';
                document.getElementById('btns-badcancel').style.display = '';
            }
        }

        // let cookieGood = document.cookie.match(new RegExp('\\b'+artId+brdId+'good\\b'));
        // let cookieBad = document.cookie.match(new RegExp('\\b'+artId+brdId+'bad\\b'));
        // console.log(cookieGood);
        // console.log(cookieBad);
        
        // // 이미 추천 한 경우
        // if(cookieGood){
        //     document.getElementById('btns-good').style.display = 'none';
        //     document.getElementById('btns-goodcancel').style.display = '';
        // }
        // // 이미 비추천 한 경우
        // if(cookieBad){
        //     document.getElementById('btns-bad').style.display = 'none';
        //     document.getElementById('btns-badcancel').style.display = '';
        // }
    }
}



// 거래 신청 (modal.jsp)
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
