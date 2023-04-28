<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" >

	// 대댓글 작성
	$(document).ready(() => {
		$("#btns-repWrite").click(e => {
			console.log(e.target.getAttribute('class')); // 클릭 이벤트 발생시 e.target의 클래스 출력
			$(e.target).closest('.reply-detail').find(".reply-replyWrite").toggle();
		});
	});
	// 게시글 삭제
	function art_delete(art_id, brd_id){
		if(confirm('삭제 하시겠습니까?')){
			//location.href='${pageContext.request.contextPath}/board/share/artDelete?art_id='+art_id+'&brd_id='+brd_id;
			alert("응 아직 삭제 안돼");
		}else{
			alert("삭제 취소");
		}
	}
	
	
	// 댓글 삭제
	function rep_delete(brd_id, art_id, rep_id) {
		location.href = '${pageContext.request.contextPath}/board/share/repDelete?art_id='+art_id+'&brd_id='+brd_id+'&rep_id='+rep_id;
	}
	
	// 댓글 수정 버튼 이벤트
/* 	$(document).ready(() => {
		$('.btns-repUpdate, .btns-cancel, .btns-repComplete').click(e => {
			var condition = $('rep-content').attr('disabled', false);
			.find('.rep-content').attr('disabled', condition? false : true).focus()
	}); */
	
	// 댓글 수정 버튼 이벤트
	$(document).ready(() => {
		$('#btns-repUpdate, #btns-cancel, #btns-repComplete').click(e => {
			var condition = $('rep-content').attr('disabled', false);
			$(e.target)
			.closest('.reply-view')
			.find('.rep-content').attr('disabled', condition? false : true).focus()
			.end()
			.find('#btns-repUpdate').toggle()
			.end()
			.find('#btns-delete').toggle()
			.end()
			.find('#btns-repComplete').toggle()
			.end()
			.find('#btns-cancel').toggle();
		});
	});
	
	// 댓글 수정 취소 / 토글 고려
	$(document).ready(() => {
		$('#btns-cancel').click(e => {
			$(e.target)
			.closest('.reply-view')
			.find('.rep-content').attr('disabled', true)
			.end()
			.find('#btns-repUpdate').show()
			.end()
			.find('#btns-delete').show()
			.end()
			.find('#btns-repComplete').hide()
			.end()
			.find('#btns-cancel').hide();
		});
	});
	
	// 댓글 추천
/* 	$(document).ready(() => {
		$('#btns-good, #btns-goodcancel').click(e => {
			$(e.target)
			.closest('.article-vote')
			.find('#btns-good').toggle()
			.end()
			.find('#btns-goodcancel').toggle()
			.end()
			.find('#btns-bad').toggle()
			.end()
			.find('#btns-badcancel').toggle();
		});
	}); */
	// 글 추천	
	$(document).ready(() => {
		$('#btns-good').click(e => {
			$(e.target).closest('.article-vote')
			.find('#btns-good').toggle().end()
			.find('#btns-goodcancel').toggle().end();
			location.href='${pageContext.request.contextPath}/board/share/vote?art_id='+${art_id}+'&brd_id='+${article.brd_id}+'&category='+${category};
			});
		});
	// 비추천
	$(document).ready(() => {
		$('#btns-goodcancel').click(e => {
			$(e.target).closest('.article-vote')
			.find('#btns-good').toggle().end()
			.find('#btns-goodcancel').toggle().end();
			location.href='${pageContext.request.contextPath}/board/share/vote?art_id='+${art_id}+'&brd_id='+${article.brd_id}+'&category='+${category};
			});
		});
	
	
	// 댓글 수정 완료
/* 	$(document).ready(() => {
		$('.btns-update-complete').click((e) => {
			e.preventDefault(); 			 // 이벤트 동시사용 방지
			});
		}); */
	function rep_Update(pIndex){
		var rep_content = $('#rep-content'+pIndex).val();
		var art_id = $('#art_id'+pIndex).val();
		var brd_id = $('#brd_id'+pIndex).val();
		var rep_id = $('#rep_id'+pIndex).val();
		console.log(rep_content);
		console.log(art_id);
		console.log(brd_id);
		console.log(rep_id);
		
		$.ajax({
			url:"${pageContext.request.contextPath}/board/share/update",
			data:{art_id, brd_id, rep_id, rep_content},
			dataType:'json',
			success:function(data){
				console.log(data)
				$('body').load(location.href);
				}
		});
	}
</script>
<style type="text/css">
	.board-articleList{
		width: 80%;
		margin: auto;
	}
	.reply-detail{
		display: flex;
		flex-direction: column;
		padding: 10px;
	}
	.reply-view{
		border: 1px solid #0193F8;
		border-radius: 2.5px;
	}
	.reply-member{
		border-bottom: 1px solid #0193F8;
		
	}
	.reply-login > span{
		display: block;
		text-align: center;
	}
	.rep-content{
		border: none;
		resize: none;
		width: 90%;
		height: 90%;
		background-color: transparent;
	}
</style>
</head>
<body>
	<div class="board-articleList">
		<div class="view-content">
		
			<div class="view-member">
				<span><img alt="회원 프사" src="${pageContext.request.contextPath}/uploads/profile/${article.member.mem_image}" style="width: 120px; height: 120px;"></span>
				<span>${article.member.mem_nickname}</span>
				<span>${article.member.mem_gender}</span>
			</div>
			<hr />
			
			<!-- 게시글 -->
			<div class="view-article">
				<div class="article-head">
					
					<!-- 카테고리 정보 -->
					<form action="board/share/updateForm">
						<input type="hidden" name="art_id" value="${article.art_id}">
						<input type="hidden" name="brd_id" value="${article.brd_id}">
						<input type="hidden" name="category" value="${category}">
						
						<div class="article-category">
							<span class="category-name">
								<button>${article.brd_name}</button>
								<button onclick="location.href='${pageContext.request.contextPath}/board/share?category=${category}';">목록</button>
							</span>
						</div>
							
						<!-- 글 수정 삭제 -->
						<c:if test="${article.mem_id == memberInfo.mem_id || memberInfo.mem_authority > 108}">
							<div class="article-update">
								<button type="submit">수정</button>
								<button id="btns-artdelete" onclick="art_delete(${article.art_id},${article.brd_id}">삭제</button>
							</div>
						</c:if>
					</form>
					<hr />
						
					<!-- 글 제목 및 상태 -->
					<div class="article-title">
						<span><button class="btn">${article.status_name}</button></span>
						<span>${article.art_title}</span>
						<span>마감일 : <fmt:formatDate value="${article.trade.trd_enddate}" pattern="yyyy-MM-dd"/></span>
						
						<span>
							<button>아이콘1</button>
							<button>아이콘2</button>
							<button>아이콘3</button>
						</span>
					</div>
					<hr />
					<div class="article-info">
						<div class="info-tag">
							<c:forEach begin="1" end="5" varStatus="status">
								<c:set var="art_tag" value="art_tag${status.index}"/>
									<c:if test="${article[art_tag] != null}">
										<span>${article[art_tag]}</span>
									</c:if>
							</c:forEach>
						</div>
						<hr />
						
						<!-- 거래 관련 내용 -->
						<div class="article-trade">
							<span>
								${article.trade.trd_cost > 0 ? article.trade.trd_cost : '무료나눔'}
							</span><br>
							<span>상세장소 :${article.trade.trd_loc}</span>
							<span>지역제한 :${article.trade.region.reg_name}</span>
							<span>조회 ${article.art_read}</span>
							<span>추천 ${article.art_good}</span>
							<span>비추천 ${article.art_bad}</span><br>
							<span>${article.member.mem_nickname}</span>
							<span>${article.gen_name}</span>
							<span>가입일 : <button class="btn" type="button"><fmt:formatDate value="${article.art_regdate}" pattern="yy-MM-dd :HH:mm:ss"/></button></span>
						</div>
					</div>
					<hr />
				</div>
				
				<!-- 본문 내용 -->
				<div class="article-body">
					<div class="article-content">
						<span>내용</span>
						<hr />
						<span>${article.art_content}</span>
					</div>
					
					<!-- 추천 비추천 -->
					<div class="article-vote">
						<span>
							<button id="btns-good">추천 ${article.art_good}</button>
							<button id="btns-goodcancel" style="display: none;">추천 토그리${article.art_good}</button>
						</span>
						<span>
							<button id="btns-bad">비추천 ${article.art_bad}</button>
							<button id="btns-badcancel" style="display: none;">비추천 토그리${article.art_bad}</button>
						</span>
					</div>
				</div>
				
				<hr />
				<div class="reply-count">
					<span>댓글 수 : (${article.rep_cnt})</span>
				</div>
				<hr />
				
				<div class="reply-list">
					<c:forEach var="reply" items="${replyList}" varStatus="status">
						<div class="reply-detail">
							<div class="reply-view" style="display: flex; ${(reply.rep_id != reply.rep_parent) ? 'margin-left: 20px;' : ''}">
								
								<div class="reply-image">
									<span><img alt="프로필 사진" src="${pageContext.request.contextPath}/uploads/profile/${reply.member.mem_image}" style="width: 80px; height: 80px;"></span>
								</div>
								
								<!-- 댓글 내용 -->
								<div class="reply-inner" style="flex-grow: 1">
									<div class="reply-member" style="display: flex;">
										<span>${reply.member.mem_nickname}</span>
										<span>작성일 : <fmt:formatDate value="${reply.rep_regdate}" pattern="yy-MM-dd :HH:mm:ss"/></span>
										<span>최종 접속일 : <fmt:formatDate value="${reply.member.mem_latest}" pattern="yy-MM-dd :HH:mm:ss"/></span>
									</div>
									
									<!-- 댓글 수정 -->
									<div class="reply-content">
										<textarea class="rep-content" id="rep-content${status.index}" disabled="disabled" autofocus="autofocus">${reply.rep_content}</textarea>
									</div>
								</div>
								
								
								<!-- 댓글 버튼 -->
								<c:if test="${article.mem_id == memberInfo.mem_id || memberInfo.mem_authority > 108}">
									<div class="reply-button">
										<span>
											<button id="btns-repWrite">작성</button>
										</span>
										<span>
											<button id="btns-repUpdate">수정</button>
											<button id="btns-repComplete" style="display: none;" onclick="rep_Update(${status.index})">완료</button>
										</span>
										<span>
											<button id="btns-delete" onclick="rep_delete(${article.brd_id},${article.art_id},${reply.rep_id})">삭제</button>
											<button id="btns-cancel" style="display: none;">취소</button>
										</span>
									</div>
								</c:if>
							</div>
							
						<!-- 댓글의 댓글 작성 -->
						<div class="reply-replyWrite" style="display: none; margin-left: 10%">
							<form action="${pageContext.request.contextPath}/board/share/replyForm" method="post">
								<span><input type="hidden" id="brd_id${status.index}" name="brd_id" 	value="${article.brd_id}"></span>
								<span><input type="hidden" id="art_id${status.index}" name="art_id" 	value="${article.art_id}"></span>
								<span><input type="hidden" id="rep_id${status.index}" name="rep_id" 	value="${reply.rep_id}"></span>
								<span><input type="hidden" name="category" 	value="${category}"></span>
								<span><input type="hidden" name="rep_parent"value="${reply.rep_parent}"></span>
								<span><input type="hidden" name="rep_step"	value="${reply.rep_step}"></span>
								<textarea style="border: none; resize: none; width: 90%; height: 90%;" placeholder="댓글을 입력하세요" name="rep_content"></textarea>
								<span><input type="submit" value="등록"></span>
							</form>
						</div>
					</div>
					</c:forEach>
					
					<!-- 새로운 댓글 작성 -->
					<div class="reply-write">
						<c:choose>
							<c:when test="${memberInfo != null}">
								<form action="${pageContext.request.contextPath}/board/share/replyForm" method="post">
									<span><input type="hidden" name="brd_id" 	value="${article.brd_id}"></span>
									<span><input type="hidden" name="art_id" 	value="${article.art_id}"></span>
									<span><input type="hidden" name="category" 	value="${category}"></span>
									<textarea style="border: none; resize: none; width: 90%; height: 90%;" placeholder="댓글을 입력하세요" name="rep_content"></textarea>
									<span><input type="submit" value="등록"></span>
								</form>
							</c:when>
							<c:otherwise>
								<div class="reply-login">
									<span>본 게시물에 댓글을 작성하실 권한이 없습니다. 로그인 하신 후 댓글을 다실 수 있습니다.</span>
									<span>ShareGo <a href="${pageContext.request.contextPath }/login">로그인</a></span>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				</div>
			</div>
		</div>
</body>
</html>