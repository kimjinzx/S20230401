<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지</h1>
	<h3>게시글수: ${totalNotice}</h3>
	<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	
	<table>
		<tr><th>글번호</th><th>제목</th><th>작성자</th><th>&nbsp;</th><th>작성일</th><th>조회수</th><th>추천수</th><th>비추천수</th></tr>
		<c:forEach var="article" items="${listNotice }">
			<tr>
			<td>${num }</td>
			<td><a href="${pageContext.request.contextPath}/board/customer/detailNotice?art_id=${article.art_id}&brd_id=${article.brd_id}">${article.art_title}</a></td>
			<td>${article.mem_nickname }</td>
			<td><img src="${pageContext.request.contextPath}/${article.mem_image }" alt="예시" style="max-height: 30px; max-width: 30px;">
			<td><fmt:formatDate value="${article.art_regdate }" pattern="yy-MM-dd"/></td>
			<td>${article.art_read}</td>
			<td>${article.art_good}</td>
			<td>${article.art_bad}</td>
			</tr>
			<c:set var="num" value="${num - 1 }"></c:set>
		</c:forEach>
	</table>	
	
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="listNotice?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a href="listNotice?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a href="listNotice?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>	
</body>
</html>