<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" >
/*
$(document).ready(() => {
	$(".reply-inner").click(e => {
		console.log(e.target.getAttribute('class')); // 클릭 이벤트 발생시 e.target의 클래스 출력
		let rep = $(e.target).closest('.reply-detail').find(".reply-write");
		rep.toggle();
	});
});
*/

	$(document).ready(() => {
		$(".reply-inner").click(e => {
			console.log(e.target.getAttribute('class')); // 클릭 이벤트 발생시 e.target의 클래스 출력
			$(e.target).closest('.reply-detail').find(".reply-replyWrite").toggle();
		});
	});
	
/* 	$(document).ready(function (){
		$(".btns-delete").click(function(){
			location.href="";
		});
	}); */
	function rep_delete(brd_id, art_id, rep_id) {
		location.href = '${pageContext.request.contextPath}/board/share/delete?art_id='+art_id+'&brd_id='+brd_id+'&rep_id='+rep_id;
	}
	

/*
$(document).ready(function() {
	$(".reply-detail").click(function() {
		$(this).find(".reply-write").toggle();
	});
});
*/
		//$(".reply-write")
		//$(".reply-write").hide();
		//$(e.target).closest('.reply-list').siblings('.reply-write')[0].toggle();

/* 	function repWrite(pIndex){
		let rep = document.getElementById("reply-write"+pIndex).style.display;
		
		document.getElementById("reply-write"+pIndex).style.display = (rep == "none")? "" : "none";
	} */
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
</style>
</head>
<body>
	<div class="board-articleList">
		<div class="view-content">
		
			<div class="view-member">
				<span><img alt="회원 프사" src="${pageContext.request.contextPath}/image/share/${article.member.mem_image}" style="width: 120px; height: 120px;"></span>
				<span>${article.member.mem_nickname}</span>
				<span>${article.member.mem_gender}</span>
			</div>
			<hr />
			
			<div class="view-article">
				<div class="article-head">
				
					<div class="article-category">
						<span class="category-name">${article.brd_name}</span>
						<input type="hidden" value="${category}">
						<span><button onclick="location.href='${pageContext.request.contextPath}/board/share?category=${category}';">목록</button></span>
					</div>
					<hr />
					
					<div class="article-title">
						<span><button class="btn">${article.status_name}</button></span>
						<span>${article.art_title}</span>
						<span>마감일 : <fmt:formatDate value="${article.trade.trd_enddate}" pattern="yyyy-MM-dd"/></span>
						
						<span>아이콘1</span>
						<span>아이콘2</span>
						<span>아이콘3</span>
					</div>
					<hr />
					<div class="article-info">
						<div class="info-tag">
							<span>
								<c:forEach begin="1" end="5" varStatus="status">
									<c:set var="art_tag" value="art_tag${status.index}"/>
										<c:if test="${article[art_tag] != null}">
											<sapn>${article[art_tag]}</sapn>
										</c:if>
								</c:forEach>
							</span>
						</div>
						<hr />
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
				
				<div class="article-body">
					<div class="article-content">
						<span>내용</span>
						<hr />
						<span>${article.art_content}</span>
					</div>
					<div class="article-vote">
						<span><button>추천 ${article.art_good}</button></span>
						<span><button>비추천 ${article.art_bad}</button></span>
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
									<span><img alt="회원 프사" src="${pageContext.request.contextPath}/image/share/${reply.member.mem_image}" style="width: 80px; height: 80px;"></span>
								</div>
								<div class="reply-inner" style="flex-grow: 1">
									<div class="reply-member" style="display: flex;">
										<span>${reply.member.mem_nickname}</span>
										<span>작성일 : <fmt:formatDate value="${reply.rep_regdate}" pattern="yy-MM-dd :HH:mm:ss"/></span>
										<span>최종 접속일 : <fmt:formatDate value="${reply.member.mem_latest}" pattern="yy-MM-dd :HH:mm:ss"/></span>
									</div>
									<div class="reply-content">
										<span>${reply.rep_content}</span>
									</div>
								</div>
								<c:if test="${article.mem_id == memberInfo.mem_id}">
									<div class="reply-button">
										<span><button>작성버튼</button></span>
										<span>
											<button class="btns-delete" onclick="rep_delete(${article.brd_id},${article.art_id},${reply.rep_id})">
												삭제
											</button>
										</span>
									</div>
								</c:if>
							</div>
							<div class="reply-replyWrite" style="display: none; margin-left: 10%">
								<form action="${pageContext.request.contextPath}/board/share/replyForm" method="post">
									<span><input type="hidden" name="brd_id" 	value="${article.brd_id}"></span>
									<span><input type="hidden" name="art_id" 	value="${article.art_id}"></span>
									<span><input type="hidden" name="category" 	value="${category}"></span>
									<span><input type="hidden" name="rep_id" 	value="${reply.rep_id}"></span>
									<span><input type="hidden" name="rep_parent"value="${reply.rep_parent}"></span>
									<span><input type="hidden" name="rep_step"	value="${reply.rep_step}"></span>
									<span><input type="text" name="rep_content" placeholder="댓글을 작성하세요."></span>
									<span><input type="submit" value="등록"></span>
								</form>
							</div>
						</div>
					</c:forEach>
					<div class="reply-write">
						<c:choose>
							<c:when test="${memberInfo != null}">
								<form action="${pageContext.request.contextPath}/board/share/replyForm" method="post">
									<span><input type="hidden" name="brd_id" 	value="${article.brd_id}"></span>
									<span><input type="hidden" name="art_id" 	value="${article.art_id}"></span>
									<span><input type="hidden" name="category" 	value="${category}"></span>
									<span><input type="text" name="rep_content" placeholder="댓글을 작성하세요."></span>
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