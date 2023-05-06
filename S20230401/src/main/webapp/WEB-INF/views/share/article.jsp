<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<%@ include file="/WEB-INF/views/share/modal.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
	// 그 전 페이지의 정보 삭제
	sessionStorage.clear();
	// 세션 스토리지에 pageContext 저장 URL용
	sessionStorage.setItem('contextPath', '${pageContext.request.contextPath}');
	sessionStorage.setItem('artId', '${article.art_id}');
	sessionStorage.setItem('brdId', '${article.brd_id}');
	sessionStorage.setItem('category', '${category}');
	sessionStorage.setItem('memId', '${memberInfo.mem_id}')
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/share/article.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share/article.css">

</head>
<body>
	<div class="view-content">
			
		<!-- 게시글 -->
		<div class="view-article">

			<!-- 임시 로그인 -->
			<c:if test="${memberInfo == null}"><div class="login" style="text-align: right;"><a href="${pageContext.request.contextPath}/login"><h1>로그인좀 해보시겠소..?</h1></a></div></c:if>

			<!-- 카테고리 정보 -->
			<div class="article-header">
				<input type="hidden" id="art_id" 	name="art_id" 	value="${article.art_id}">
				<input type="hidden" id="brd_id" 	name="brd_id" 	value="${article.brd_id}">
				<input type="hidden" id="category" 	name="category" value="${category}">
				<input type="hidden" id="mem_id" 	name="mem_id" 	value="${article.mem_id}">
				<input type="hidden" id="login_member" 		name="login_member" 	value="${memberInfo.mem_id}">
				<input type="hidden" id="login_authority" 	name="login_authority" 	value="${memberInfo.mem_authority}">
				
				<!-- 카테고리 표시 -->
				<div class="article-category" style="display: flex;">
					<span class="category-name">
						<button style="border-radius: 5px; font-size: x-large; height: auto; background-color: #6c757d;">${article.brd_name}</button>
					</span>
					<span style="display: flex; flex-grow: 1; justify-content: flex-end;">
						<!-- 글 수정 삭제 -->
						<c:if test="${article.mem_id == memberInfo.mem_id || memberInfo.mem_authority >= 108}">
							<button id="btns-artUpdate" onclick="art_Update()">수정</button>
							<button id="btns-artDelete" onclick="art_Delete()">삭제</button>
						</c:if>
						<button onclick="location.href='${pageContext.request.contextPath}/board/share?category=${category}';">목록</button>
					</span>
				</div>
				<hr />
			</div>
			
			<!-- 글 제목 및 상태 -->
			<div class="article-title">
				<span>
					<c:if test="${article.status_name != null}"><button class="btn">${article.status_name}</button></c:if>
					${article.art_title}
				</span>
			</div>
			<hr />
			
			<!-- 작성자 -->
			<div class="article-memberRow" style="display: flex; align-items: center;">
				<span><img alt="profile" src="${pageContext.request.contextPath}/uploads/profile/${article.member.mem_image}" style="width: 60px; height: 60px; border-radius: 30px; margin-right: 20px;"></span>
				<div class="article-member" style="display: flex; align-items: center;">
					<span>${article.member.mem_nickname}</span>
				</div>
				<div class="article-memberInfo" style="font-size: 14px; flex-grow: 1; text-align: right;">
					<span>추천 ${article.art_good}</span>
					<span>비추천 ${article.art_bad}</span>
					<span>댓글 ${article.rep_cnt == null ? 0:article.rep_cnt}</span>
					<span>조회수 ${article.art_read}</span>
					<span>작성일 <fmt:formatDate value="${article.art_regdate}" pattern="yy.MM.dd HH:mm:ss"/></span>
				</div>

			</div>
			<hr />
			
			<!-- 태그 출력 및 검색 -->
			<div class="article-info">
				<div class="view-tag">
					<form action="${pageContext.request.contextPath}/board/share/searchForm">
						<input type="hidden" name="category" value="${category}">
						<input type="hidden" name="brd_id" value="${brd_id}">
						<input type="hidden" name="search" value="articleTag">
						<c:forEach begin="1" end="5" varStatus="status">
							<c:set var="art_tag" value="art_tag${status.index}"/>
								<c:if test="${article[art_tag] != null}">
									<button class="btns-tag" name="keyWord" value="${article[art_tag]}">${article[art_tag]}</button>
								</c:if>
						</c:forEach>
					</form>
				</div>
			</div><hr />
			
				
			<!-- 거래 관련 내용 -->
			<div class="article-trade">
				
				<div class="share-trdHeader">
					<span>${article.trade.trd_cost > 0 ? article.trade.trd_cost : '무료나눔'}</span>
					<span>가입일 : <button class="btn" type="button"><fmt:formatDate value="${article.art_regdate}" pattern="yy-MM-dd"/></button></span>
					<span><button>마감일 : <fmt:formatDate value="${article.trade.trd_enddate}" pattern="yyyy-MM-dd"/></button></span>
				</div>
				<div class="share-trdContent">
					<span>지역제한 : ${article.trade.region.reg_name}</span>
					<span>상세장소 : ${article.trade.trd_loc}</span>
					<hr />
					<span>최대 인원 : ${article.trade.trd_max}명</span>
					<span>최소 나이 : ${article.trade.trd_minage>0? article.trade.trd_minage:'제한없음' }</span>
					<span>최대 나이 : ${article.trade.trd_maxage>0? article.trade.trd_maxage:'제한없음'}</span>
					<span>성별 제한 : ${article.trade.trd_gender==201? '남자만':article.trade.trd_gender==202? '여자만':'제한없음'}</span>
				</div><hr />
					
				<!-- 거래 대기열 회원 목록 -->
				<div class="share-waiting">
					<c:forEach var="waiting" items="${waitingList}">
						<div class="waiting-memberInfo" style="display: flex; align-items: flex-start;">
							<img src="${pageContext.request.contextPath}/uploads/profile/${waiting.member.mem_image}" alt="profile" style="width: 60px; height: 60px; border-radius: 30px; margin-right: 20px;">
							<div class="waiting-details">
								<h3 style="margin: 0 0 8px;">${waiting.member.mem_username}</h3>
								<span>${waiting.member.mem_nickname}</span>
								<span>최근 접속일 : <fmt:formatDate value="${waiting.member.mem_latest}" pattern="yy-MM-dd"/></span>
							</div>
							<div style="margin-left: auto;">
								<button>승인</button>
								<button>거절</button>
							</div>
						</div><hr />
					</c:forEach>
				</div>
					

				<!-- 거래 신청, 찜 -->
				<div class="share-button">
					<c:choose>
						<c:when test="${userFavorite > 0}">
							<button class="btns-action" id="btns-favoriteDel">찜 취소</button>
						</c:when>
						<c:otherwise>
							<button class="btns-action" id="btns-favorite">찜</button>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userWaiting > 0}">
							<button class="btns-action" id="btns-applyDel">신청 취소</button>
						</c:when>
						<c:otherwise>
							<button class="btns-action" id="btns-apply">신청</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div><hr />
			
		<!-- 본문 내용 -->
		<div class="article-body">
			<div class="article-content">
				<label for="content">내용</label>
				<hr />
				<textarea name="art_content" disabled="disabled">${article.art_content}</textarea>
			</div>

			<!-- 추천 비추천 -->
			<div class="share-button" id="btns-vote">
				<button class="btns-good" id="btns-good">추천 <span>${article.art_good}</span></button>
				<button class="btns-good" id="btns-goodcancel" style="display: none; background-color: #0193F8;">추천 <span>${article.art_good}</span></button>
				<button class="btns-bad" id="btns-bad">비추천 <span>${article.art_bad}<span></button>
				<button class="btns-bad" id="btns-badcancel" style="display: none; background-color: red;">비추천 <span>${article.art_bad}</span></button>
			</div>
		</div><hr />
		
		
		<!-- 댓글 부분 -->		
		<div class="reply-list">
			<div class="list-toggle">
				<c:choose>
					<c:when test="${article.rep_cnt > 0}">
						<button id="btns-show" style="display: none;">▶ 펼치기</button>
						<button id="btns-hide">▼ 접기</button>
						<span>댓글 수 : (${article.rep_cnt})</span>
						<hr />
					</c:when>
					<c:otherwise>
						<p style="text-align: center;">이 글은 아직 댓글이 없어요<br> 댓글 남겨 주실꺼죠??</p>
					</c:otherwise>
				</c:choose>
			</div>
			
			<!-- 댓글 리스트 -->
			<c:forEach var="reply" items="${replyList}" varStatus="status">
				<div class="reply-detail">
					<div class="reply-view" style="display: flex; ${(reply.rep_id != reply.rep_parent) ? 'margin-left: 20px;' : ''}">
						
						<div class="reply-image">
							<span><img alt="profile" src="${pageContext.request.contextPath}/uploads/profile/${reply.member.mem_image}" style="width: 80px; height: 80px;"></span>
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
						<c:if test="${reply.mem_id == memberInfo.mem_id || memberInfo.mem_authority > 108}">
							<div class="reply-button">
								<span>
									<button class="btns-repWrite">작성</button>
								</span>
								<span>
									<button class="btns-repUpdate">수정</button>
									<button class="btns-repComplete" style="display: none;" onclick="rep_Update(${status.index})">완료</button>
								</span>
								<span>
									<button class="btns-delete" onclick="rep_delete(${article.brd_id},${article.art_id},${reply.rep_id})">삭제</button>
									<button class="btns-cancel" style="display: none;">취소</button>
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
</body>
</html>