<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="board-articleList">
					<div class="view-content">
					
						<div class="view-member">
							<span><img alt="회원 프사" src=""></span>
							<span>${article.member.mem_nickname}</span>
							<span>${article.member.mem_gender}</span>
						</div>
						<hr />
						
						<div class="view-article">
							<div class="article-head">
							
								<div class="article-category">
									<span class="category-name">${article.brd_name}</span>
									<span><button >목록</button></span>
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
														<button class="btn">${article[art_tag]}</button>
													</c:if>
											</c:forEach>
										</span>
									</div>
									<hr />
									<div class="article-trade">
										<span>
											${article.trade.trd_cost > 0 ? article.trade.trd_cost : '무료나눔'}
										</span><br>
										<span>장소 :${article.trade.trd_loc }</span>
										
										<span>조회 ${article.art_read}</span>
										<span>추천 ${article.art_good}</span>
										<span>비추천 ${article.art_bad}</span><br>
										<span>공구</span><br>
										<span>공구 닉네임</span><br>
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
									<span>추천${article.art_good }</span>
									<span>비추천${article.art_bad }</span>
								</div>
								<hr />
								
								<span>${article.member.mem_nickname}</span>
								<span>${article.gen_name}</span>
								<span><button class="btn" type="button"><fmt:formatDate value="${article.art_regdate}" pattern="D"/>일 전</button></span>
								
								
							</div>
							
							<div class="article-reply">
								<span>댓글(${article.rep_cnt})</span>
								<div class="reply-list">
									
								</div>
								
								<div class="reply-write">
									<form action="article/share/reply">
										<span><input type="text" name="rep_content" placeholder="댓글을 작성하세요."></span>
										<span><input type="submit" value="등록"></span>
									</form>
								</div>
							</div>
						</div>
					</div>
			</div>
</body>
</html>