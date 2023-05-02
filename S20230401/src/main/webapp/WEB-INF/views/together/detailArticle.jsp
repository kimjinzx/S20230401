<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.min.js" ></script>

<script>
	
	$(() => {
		$('.reply-modify').click(e => {
			if ($(e.target).closest('.reply-box').find('.rep_content2').css('display') == 'none') {
				$(e.target).closest('.reply-box').find('.rep_content1').toggle();
				$(e.target).closest('.reply-box').find('.rep_content2').toggle();
			} else {
				// 수정작업 ex... AJAX
				let rawData = {art_id : ${detailArticle.art_id}, brd_id:${detailArticle.brd_id},
							   rep_id : $(e.target).closest('.reply-box').find('input[name="rep_id"]').val(),
							   rep_content : $(e.target).closest('.reply-box').find('.rep_content2').val()};
				
				let sendData = JSON.stringify(rawData);
				
				$.ajax({
					  url : "/board/updateReply",
					  type : 'post',
					  data : sendData,
					  dataType :'json',
					  contentType : 'application/json',
					  success : data => {
						  
						  if (data.result == 1) {	// 성공
							  	$(e.target).closest('.reply-box').find('.rep_content1').text(data.content);
							  	$(e.target).closest('.reply-box').find('.rep_content2').val(data.content);
							  	
								$(e.target).closest('.reply-box').find('.rep_content1').toggle();
								$(e.target).closest('.reply-box').find('.rep_content2').toggle();
						  } else 
							  alert('수정 실패.');
					  }
				  }
				);
			}
		});
	});
	
</script>
<!-- closest => 가장 가까운 (.->클래스, #->아이디,) 이름을 가진 놈 찾아 타겟으로 만듦.
	find => 아래의 태그 찾음.
	css => 해당 속성을 찾아서 속성의 값을 알려줌 (두개면 앞에 놈의 값을 뒤에놈으로 바꿔줌)  -->

<!--  <script>

	function createButton() {
    // 버튼 엘리먼트의 disply 속성을 'block'으로 변경.
    	const replyButton = document.getElementById('replyButton');
    	replyButton.style.display = 'block';
    }
	
	function deleteButton() {
	// 버튼 엘리먼트의 disply 속성을 'none'으로 변경.
		const replyButton = document.getElementById('replyButton');
		replyButton.style.display = 'none';
	} 	
</script>-->
     
</head>
<body>
	<h2>상세게시글</h2>
	
	<p>
	<input type="button" value="전체" 	onclick="${pageContext.request.contextPath}location.href='together?category=1000'">
	<input type="button" value="밥 & 카페" onclick="${pageContext.request.contextPath}location.href='together?category=1010'">
	<input type="button" value="스포츠" 	onclick="${pageContext.request.contextPath}location.href='together?category=1020'">
	<input type="button" value="쇼핑" 	onclick="${pageContext.request.contextPath}location.href='together?category=1030'">
	<input type="button" value="문화생활" 	onclick="${pageContext.request.contextPath}location.href='together?category=1040'">
	<input type="button" value="취미생활" 	onclick="${pageContext.request.contextPath}location.href='together?category=1050'">
	<input type="button" value="기타" 	onclick="${pageContext.request.contextPath}location.href='together?category=1060'">
	
	<p>
	<input type="button" value="목록" 	onclick="${pageContext.request.contextPath }location.href='together?category=1000';">
	<input type="button" value="수정하기" 	onclick="${pageContext.request.contextPath }location.href='/board/updateFormArticle?brd_id=${detailArticle.brd_id }&art_id=${detailArticle.art_id }';">
	<input type="button" value="삭제하기"  onclick="${pageContext.request.contextPath }location.href='/board/deleteArticle?brd_id=${detailArticle.brd_id }&art_id=${detailArticle.art_id }';">
	
	<table>
		<tr>
			<th>거래상태</th>
			<td>${detailArticle.c1_comm_value }
		</tr>
		<tr>
			<th>제목</th>
			<td>${detailArticle.art_title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${detailArticle.mem_nickname }</td>
		</tr>
		
		<tr>
			<th>태그1</th>
			<td>${detailArticle.art_tag1 }</td>
		</tr>
		<tr>
			<th>태그2</th>
			<td>${detailArticle.art_tag2 }</td>
		</tr>
		<tr>
			<th>태그3</th>
			<td>${detailArticle.art_tag3 }</td>
		</tr>
		<tr>
			<th>태그4</th>
			<td>${detailArticle.art_tag4 }</td>
		</tr>
		<tr>
			<th>태그5</th>
			<td>${detailArticle.art_tag5 }</td>
		</tr>
		<tr>
			<th>장소</th>
			<td>${detailArticle.trd_loc }</td>
		</tr>
		<tr>
			<th>지역제한</th>
			<td>${detailArticle.reg_name }</td>
		</tr>
		<tr>
			<th>모집인원</th>
			<td>${detailArticle.trd_max }</td>
		</tr>
		<tr>
			<th>최소나이</th>
			<td>${detailArticle.trd_minage }</td>
		</tr>
		<tr>
			<th>최대나이</th>
			<td>${detailArticle.trd_maxage }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
			<c:set var="gender" value ="${detailArticle.c2_comm_id }"/>
			<c:choose>
				<c:when test="${gender eq 201 }">남성</c:when>
				<c:when test="${gender eq 202 }">여성</c:when>
				<c:otherwise>성별무관</c:otherwise>
			</c:choose>	
			</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${detailArticle.art_good }</td>
		</tr>
		<tr>
			<th>작성 시간</th>
			<td>${detailArticle.rest_regdate }일 전</td>
		</tr>
		<tr>
			<th>마감 일자 </th>
			<td>
			<c:set var="date" value="${detailArticle.trd_finish }"/>
			<c:choose>
				<c:when test="${date eq null}"></c:when>
				<c:otherwise>${date }까지</c:otherwise>
			</c:choose>
			<td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${detailArticle.art_read }</td>
		<tr>
			<th>관심목록 수</th>
			<td>${detailArticle.favoriteCount }</td>
		</tr>
		<tr>
			<td></td>
			<th><input type="button" value="관심목록"  onclick="location.href='">
				<input type="button" value="신청하기" 	onclick="location.href='"></th>
		<tr>
			<th>내용</th>
      		<td>${detailArticle.art_content }</td>
		</tr>
	</table>
	<p>
	
	<span><br>댓글 (${detailArticle.repCount })</span>
	<p>
	<!-- 댓글입력 -->
		<div class="insertReply">
			<form action="/board/insertReply" id = "insertReply" method="post">
			<!-- form 'action' = '데이터가 도착할 URL을 써준다' 'method' = '데이터를 전달할 방식을 써준다'-->
			<!-- get 방식으로 넘길 땐 주소값 뒤에 ? 로 파라미터 값을 적어주고 / post 방식으로 넘길 땐 form 안에 input으로 값을 적어서 넘겨준다-->
				<input type="hidden" name="art_id"  	value="${detailArticle.art_id }">
				<input type="hidden" name="brd_id" 		value="${detailArticle.brd_id }">
				<input type="hidden" name="mem_id" 		value="${detailArticle.mem_id }">
				<input type="hidden" name="rep_id" 		value="${reply.rep_id }">
				<input type="hidden" name="rep_parent" 	value="${reply.rep_parent }">
				<c:choose>
					<c:when test="${memberInfo.mem_id != null}">
					<input type="text"   name="rep_content"   placeholder="댓글을 입력하세요"
								 style="margin : 10px; width:1225px; height:20px; font-size:12px;"></input>
					<div style = "display: block;" id="replyButton">
					<input type="submit" id = "replySubmit" value="댓글">
					<!-- submit 버튼을 통해 데이터가 입력(?)된다 -->
					<input type="reset"  id = "replyReset"  value="취소">
					</div>
					</c:when>
					<c:otherwise>
					<span> 로그인이 필요합니다 </span>
					</c:otherwise>
				</c:choose>
				
			<!-- name = 데이터를 전달 받는 column 이름, value= 들어갈 데이터의 값, id = javascript로 꾸밀 때 지정해주는 이름(?) -->		
			</form>
		</div>
		<p>

	<!-- 댓글리스트 -->
	
		<c:forEach var="reply" items="${replyList }">
			<div class="reply-box" style="${reply.rep_step > 1 ? 'margin-left: 30px' : ''}">    
				<input type="hidden" name="art_id"  	value="${reply.art_id }">
				<input type="hidden" name="brd_id" 		value="${reply.brd_id }">
				<input type="hidden" name="mem_id" 		value="${reply.mem_id }">
				<input type="hidden" name="rep_id" 		value="${reply.rep_id }">
				<span><img src="${pageContext.request.contextPath}/image/picture/${reply.mem_image}" width ="30" height ="30" alt="-"></span>
				<span>${reply.mem_nickname }</span>
				<span>(${reply.mem_username })</span>
				<fmt:formatDate value="${reply.rep_regdate}" pattern="yy년 MM월 dd일 : HH:mm:ss"/>
			    <p>
				      <span class="rep_content1"> ${reply.rep_content}</span>
				      <input type="text" class="rep_content2" style="display: none; margin : 10px; width:1225px; height:20px; font-size:12px;'" value="${reply.rep_content }">
				      
				      <span style="float: right; ;">비추천수 : ${reply.rep_bad }  </span>
				      <span style="float: right; ;">추천수 : ${reply.rep_good }&nbsp;</span>
				     
				<p>      
			    <c:choose>
				    <c:when test="${memberInfo.mem_id == reply.mem_id}">
				      <!-- <input type="button" value="수정" onclick="updateReply(this)"> -->
				      <input class="reply-modify" type="button" value="수정">
				      <input type="button" value="삭제" onclick="${pageContext.request.contextPath }location.href='/board/deleteReply?brd_id=${reply.brd_id}&art_id=${reply.art_id}&rep_id=${reply.rep_id}&mem_id=${reply.mem_id}';">
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>	
				</p>
			</div>
			
			<div class="insertRe-Reply" style = "display: block; ${reply.rep_step > 1 ? 'margin-left: 30px' : ''}">
				<form action="/board/insertReply" id="insertRe-Reply" method="post">
					<input type="hidden" name="art_id"  	value="${detailArticle.art_id }">
					<input type="hidden" name="brd_id" 		value="${detailArticle.brd_id }">
					<input type="hidden" name="mem_id" 		value="${detailArticle.mem_id }">
					<input type="hidden" name="rep_id" 		value="${reply.rep_id }">
					<input type="hidden" name="rep_parent" 	value="${reply.rep_parent }">
					<c:choose>
						<c:when test="${memberInfo.mem_id != null}">
						<input type="text"   name="rep_content"   placeholder="댓글을 입력하세요"
									 style="margin : 10px; width:1225px; height:20px; font-size:12px;"></input>
							<div style = "display: block;" id="replyButton">
								<input type="submit" id = "replySubmit" value="댓글">
								<!-- submit 버튼을 통해 데이터가 입력(?)된다 -->
								<input type="reset"  id = "replyReset"  value="취소">
							</div>
						</c:when>
						<c:otherwise>
						<span> 로그인이 필요합니다 </span>
						</c:otherwise>
					</c:choose>
				
			<!-- name = 데이터를 전달 받는 column 이름, value= 들어갈 데이터의 값, id = javascript로 꾸밀 때 지정해주는 이름(?) -->		
					<p>
				</form>
			</div>
		</c:forEach>
						

</body>
</html>