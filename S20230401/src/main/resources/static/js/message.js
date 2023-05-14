/**
 * 
 */
// contextPath 할당
const contextPath = window.location.pathname.split('/')[0];
// 쪽지 작성
function writebox(){
    document.querySelector('.content-write').style.display = 'block';
    document.querySelector('.content-receiver').style.display = 'none';
    document.querySelector('.content-sender').style.display = 'none';
    document.querySelector('.content-storage').style.display = 'none';
    document.querySelector('.content-recycle').style.display = 'none';
}
// 받은 쪽지함
function receivebox(){
    document.querySelector('.content-write').style.display = 'none';
    document.querySelector('.content-receiver').style.display = 'block';
    document.querySelector('.content-sender').style.display = 'none';
    document.querySelector('.content-storage').style.display = 'none';
    document.querySelector('.content-recycle').style.display = 'none';
}

// 보낸 쪽지함
function sentbox(){
    document.querySelector('.content-write').style.display = 'none';
    document.querySelector('.content-receiver').style.display = 'none';
    document.querySelector('.content-sender').style.display = 'block';
    document.querySelector('.content-storage').style.display = 'none';
    document.querySelector('.content-recycle').style.display = 'none';
}

// 쪽지 보관함
function storagebox(){
    document.querySelector('.content-write').style.display = 'none';
    document.querySelector('.content-receiver').style.display = 'none';
    document.querySelector('.content-sender').style.display = 'none';
    document.querySelector('.content-storage').style.display = 'block';
    document.querySelector('.content-recycle').style.display = 'none';
}

// 휴지통
function recyclebox(){
    document.querySelector('.content-write').style.display = 'none';
    document.querySelector('.content-receiver').style.display = 'none';
    document.querySelector('.content-sender').style.display = 'none';
    document.querySelector('.content-storage').style.display = 'none';
    document.querySelector('.content-recycle').style.display = 'block';
}
// 글 내용 토글
$(()=>{
    $('.list-title').click((e)=>{
        $(e.target).closest('.message-list').find('.list-content').toggle();
    })
});

function messageReply(receiver_id, sender_id){
    writebox();
    document.querySelector('#mem_username').setAttribute('value', sender_id);
    document.querySelector('#mem_receiver_id').setAttribute('value', receiver_id);
    $('#mem_username').trigger('input');
}


// 체크
$(()=>{
    let checked = false;
    $('#mem_username').on('input', ()=>{
        let mem_username = $('#mem_username').val();
        if(mem_username.length > 0){
            $.ajax({
                url:`${contextPath}/message/checkUser`,
                type:'POST',
                data:{mem_username: mem_username},
                success: (data)=>{
                    if(data > 0){
                        $('#mem_result').text('가능');
                        $('#mem_receiver_id').val(data);
                        checked = true;
                    }
                    else{
                        $('#mem_result').text('불가능');
                        checked = false;
                    }
                }
            })
        }
    });
    $('#writeForm').submit((e)=>{
        if(!checked) {
            e.preventDefault();
            alert('받는 사람이 존재하지 않습니다.')
        }
    });
});