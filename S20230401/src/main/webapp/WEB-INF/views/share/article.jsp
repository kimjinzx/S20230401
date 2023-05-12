<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<%@ include file="/WEB-INF/views/share/modal.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/initializer.js"></script>
<script type="text/javascript">
   
   // 그 전 페이지의 정보 삭제
   sessionStorage.clear();
   // 세션 스토리지에 pageContext 저장 URL용
   sessionStorage.setItem('contextPath', '${pageContext.request.contextPath}');
   sessionStorage.setItem('artId', '${article.art_id}');
   sessionStorage.setItem('brdId', '${article.brd_id}');
   sessionStorage.setItem('memId', '${article.mem_id}');
   sessionStorage.setItem('category', '${category}');
   sessionStorage.setItem('loginUser', '${memberInfo.mem_id}')

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/share/article.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/presets.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share/article.css">

</head>
<body>
   <div class="view-content">
         
      <!-- 게시글 -->
      <div class="view-article">
         <!-- 임시 로그인 -->
         <c:if test="${memberInfo == null}"><div class="login" style="text-align: right;"><a href="${pageContext.request.contextPath}/login"><h1>로그인좀 해보시겠소..?</h1></a></div></c:if>
         <c:if test="${memberInfo != null}"><div class="logout" style="text-align: right;"><a href="${pageContext.request.contextPath}/logout"><h1>로그아웃</h1></a></div></c:if>

         <!-- 게시글의 정보 -->
         <div class="article-header" class="padding-0">
            <input type="hidden" id="article_id"name="art_id"    value="${article.art_id}">
            <input type="hidden" id="brd_id"    name="brd_id"    value="${article.brd_id}">
            <input type="hidden" id="category"    name="category" value="${category}">
            <input type="hidden" id="mem_id"    name="mem_id"    value="${article.mem_id}">
            <input type="hidden" id="article_nickname"          value="${article.member.mem_nickname}">
            <input type="hidden" id="artReport_id"             value="${article.report_id}">
            <input type="hidden" id="login_member"       name="login_member"    value="${memberInfo.mem_id}">
            <input type="hidden" id="login_authority"    name="login_authority"    value="${memberInfo.mem_authority}">
            <!-- 카테고리 표시 -->
            <div class="article-category display-flex justify-content-space-between align-items-center">
               <span class="category-name">
               		<a href="${pageContext.request.contextPath}/board/share?category=${article.brd_id - (article.brd_id % 100) }"><span style="color: rgba(var(--theme-font-rgb), 0.5);">나눔해요</span></a>
               		<span class="margin-hor-2_5px" style="color: rgba(var(--theme-font-rgb), 0.5);">&gt;</span>
                  <a class="font-weight-bolder" style="color: var(--subtheme)" href="${pageContext.request.contextPath}/board/share?category=${article.brd_id}">${article.brd_name}</a>
               </span>
               <span class="only-for-member display-flex justify-content-flex-end align-items-center">
                  <!-- 글 수정 삭제 -->
                  <c:if test="${article.mem_id == memberInfo.mem_id || memberInfo.mem_authority >= 108}">
                     <button id="btns-artUpdate" class="adv-hover" onclick="art_Update()">수정</button>
                     <button id="btns-artDelete" class="adv-hover" onclick="art_Delete()">삭제</button>
                  </c:if>
                  <button class="adv-hover" onclick="location.href='${pageContext.request.contextPath}/board/share?category=${category}';">목록</button>
               </span>
            </div>
         </div>
         
         <!-- 글 제목 및 상태 -->
         <div class="article-title" style="border: 2px solid rgba(128, 128, 128, 0.5); border-width: 2px 0;">
            <div class="title-status">
               <c:if test="${article.status_name != null}"><button class="btn font-weight-bold">${article.status_name}</button></c:if>
            </div>
            <div class="title-subject">
               <span class="font-size-20px font-weight-bold">${article.art_title}</span>
            </div>
            <!-- 게시글 신고 -->
            <div class="modal-report">
               <c:if test="${not empty memberInfo}">
                  <svg id="article-report" viewBox="0 0 512 512" width="30" height="30" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
               </c:if>
            </div>
         </div>
         
         <!-- 작성자 -->
         <div class="article-memberRow display-flex align-items-center" style="border-bottom: 1px solid rgba(128, 128, 128, 0.5);">
            <div class="user-profile-image-in-list">
            	<img src="${pageContext.request.contextPath}/uploads/profile/${article.member.mem_image}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/abstract-user.svg';"></span>
            </div>
            <div class="article-member" style="display: flex; align-items: center;">
               <div class="modal-report display-flex justify-content-flex-start align-items-center padding-0">
                  <span id="member_nickname" class="font-weight-bolder margin-right-10px">${article.member.mem_nickname}</span>
                  <c:if test="${not empty memberInfo}">
                     <svg id="member-report" viewBox="0 0 512 512" width="24" height="24" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
                     <input type="hidden" id="member_id" value="${article.member.mem_id}">
                     <input type="hidden" id="memReport_id" value="${article.member.report_id}">
                  </c:if>
               </div>
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
					<span id="trd_enddate"><button>마감일 : <fmt:formatDate value="${article.trade.trd_enddate}" pattern="yyyy-MM-dd HH:mm:ss"/></button></span>
					<div id="remaining_time"></div>

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
					
				<!-- 참가중인 회원 목록 -->
				<div class="share-userList">
					<h2>거래 참가자 명단</h2><hr />
					<c:forEach var="join" items="${joinList}">
						<span>참여일 : <fmt:formatDate value="${join.join_date}" pattern="yy-MM-dd HH:mm:ss"/></span>
						<div class="userList-memberInfo">
							<img src="${pageContext.request.contextPath}/uploads/profile/${join.member.mem_image}" alt="profile" style="width: 60px; height: 60px; border-radius: 30px; margin-right: 20px;">
							<div class="userList-details">
								<h3>${join.member.mem_username}</h3>
								<div class="modal-report">
									<span id="member_nickname">${join.member.mem_nickname}</span>
									<c:if test="${not empty memberInfo}">
										<svg id="member-report" viewBox="0 0 512 512" weith="30" height="30"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z"/></svg>
									</c:if>
									<input type="hidden" id="member_id" name="mem_id" value="${join.mem_id}">
									<input type="hidden" id="memReport_id" value="${join.member.report_id}">
								</div>
							</div>
							<div class="userList-btns">
								<c:choose>
									<c:when test="${article.mem_id == memberInfo.mem_id}">
										<button class="btns-action" id="btns-drop">추방</button>
									</c:when>
									<c:when test="${join.mem_id == memberInfo.mem_id}">
										<button class="btns-action" id="btns-joinCancel">취소</button>
									</c:when>
								</c:choose>
							</div>
						</div><hr />
					</c:forEach>
					<c:if test="${empty joinList}"><p style="text-align: center;">아직 참가자가 없어요</p></c:if>
				</div>
				
				<!-- 거래 대기열 회원 목록 -->
				<div class="share-userList">
					<h2>거래 신청자 명단</h2><hr />
					<c:forEach var="waiting" items="${waitingList}" varStatus="status">
						<span>신청일 : <fmt:formatDate value="${waiting.wait_date}" pattern="yy-MM-dd HH:mm:ss"/></span>
						<div class="userList-memberInfo">
							<img src="${pageContext.request.contextPath}/uploads/profile/${waiting.member.mem_image}" alt="profile" style="width: 60px; height: 60px; border-radius: 30px; margin-right: 20px;">
							<div class="userList-details">
								<h3>${waiting.member.mem_username}</h3>
								<div class="modal-report">
									<span id="member_nickname">${waiting.member.mem_nickname}</span>
									<c:if test="${not empty memberInfo}">
										<svg id="member-report" viewBox="0 0 512 512" weith="30" height="30"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z"/></svg>
									</c:if>
									<input type="hidden" id="member_id" name="mem_id" value="${waiting.mem_id}">
									<input type="hidden" id="memReport_id" value="${waiting.member.report_id}">
								</div>
							</div>
							<div class="userList-btns">
								<c:choose>
									<c:when test="${article.mem_id == memberInfo.mem_id}">
										<c:if test="${article.trade.trd_max ne joinList.size()}">
											<button class="btns-action" id="btns-accept">승인</button>
										</c:if>
										<button class="btns-action" id="btns-refuse">거절</button>
									</c:when>
									<c:when test="${waiting.mem_id == memberInfo.mem_id}">
										<button class="btns-action" id="btns-waitCancel">취소</button>
									</c:when>
								</c:choose>
							</div>
						</div><hr />
					</c:forEach>
					<c:if test="${empty waitingList}"><p style="text-align: center;">아직 신청자가 없어요</p></c:if>
				</div>
					

            <!-- 거래 신청, 찜 -->
            <div class="share-btns">
               <c:if test="${memberInfo != null}">
                  <div class="btns-favorite">
                     <c:choose>
                        <c:when test="${userFavorite > 0}">
                           <button class="btns-action" id="btns-favoriteDel">찜 취소</button>
                        </c:when>
                        <c:when test="${userFavorite == 0}">
                           <button class="btns-action" id="btns-favorite">찜</button>
                        </c:when>
                     </c:choose>
                  </div>
                  <div class="btns-trade">
                     <c:choose>
                        <c:when test="${userWaiting == 0 && userJoin == 0 && joinList.size() < article.trade.trd_max}">
                           <button class="btns-action" id="btns-apply">신청</button>
                        </c:when>
                        <c:when test="${joinList.size() == article.trade.trd_max}">
                           <button class="btns-action" id="btns-end">모집 완료</button>
                        </c:when>
                     </c:choose>
                  </div>
               </c:if>
               <c:if test="${memberInfo == null}"><div class="login" style="text-align: right;"><a href="${pageContext.request.contextPath}/login"><h1>로그인좀 해보시겠소..?</h1></a></div></c:if>
            </div>
         </div>
      </div>

			<!-- 추천 비추천 -->
			<div class="share-btns" id="btns-vote">
				<button class="btns-good" id="btns-good">추천 <span>${article.art_good}</span></button>
				<button class="btns-good" id="btns-goodcancel" style="display: none; background-color: #0193F8;">추천 <span>${article.art_good}</span></button>
				<button class="btns-bad" id="btns-bad">비추천 <span>${article.art_bad}</span></button>
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
					<input type="hidden" id="reply_id" name="rep_id" value="${reply.rep_id}">
					<input type="hidden" id="reply_nickname" value="${reply.member.mem_nickname}">
					<input type="hidden" id="repReport_id" 	value="${reply.report_id}">
					<div class="reply-view" style="display: flex; ${(reply.rep_id != reply.rep_parent) ? 'margin-left: 20px;' : ''}">
						
						<div class="reply-image">
							<span><img alt="profile" src="${pageContext.request.contextPath}/uploads/profile/${reply.member.mem_image}" style="width: 80px; height: 80px;"></span>
						</div> 
						
						<!-- 댓글 본문 -->
						<div class="reply-inner" style="flex-grow: 1">
							<!-- 유저 신고 -->
							<div class="reply-member" style="display: flex;">
								<div class="modal-report">
									<span id="member_nickname">${reply.member.mem_nickname}</span>
									<c:if test="${not empty memberInfo}">
										<svg id="member-report" viewBox="0 0 512 512" weith="30" height="30"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z"/></svg>
										<input type="hidden" id="member_id" value="${reply.member.mem_id}">
										<input type="hidden" id="memReport_id" value="${reply.member.report_id}">
									</c:if>
								</div>
								<span>작성일 : <fmt:formatDate value="${reply.rep_regdate}" pattern="yy-MM-dd :HH:mm:ss"/></span>
								<span>최종 접속일 : <fmt:formatDate value="${reply.member.mem_latest}" pattern="yy-MM-dd :HH:mm:ss"/></span>
							</div>
								
							<!-- 댓글 수정 -->
							<div class="reply-content">
								<textarea class="rep-content" id="rep-content${status.index}" disabled="disabled" autofocus="autofocus">${reply.rep_content}</textarea>
							</div>
						</div>
						
						
						<!-- 댓글 버튼 -->
						<div class="reply-button">
							<c:if test="${reply.mem_id == memberInfo.mem_id || memberInfo.mem_authority > 108}">
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
							</c:if>
							<!-- 댓글 신고 -->
							<div class="modal-report">
								<c:if test="${not empty memberInfo}">
									<svg id="reply-report" viewBox="0 0 512 512" weith="30" height="30"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z" fill="none" stroke="currentColor" stroke-miterlimit="10" stroke-width="32"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z"/></svg>
								</c:if>
							</div>
						</div>
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