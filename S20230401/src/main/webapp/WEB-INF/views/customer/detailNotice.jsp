<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>공지사항</h2>
	<table border="1" style="width: 500px;">
		<tr><th>작성자</th><td>${article.mem_nickname}</td></tr>
		<tr><th>이미지</th><td><img src="${pageContext.request.contextPath}/${article.mem_image }" alt="예시" style="max-height: 30px; max-width: 30px;"></td></tr>
		<tr><th>작성일</th><td><fmt:formatDate value="${article.art_regdate }" pattern="yy-MM-dd"/></td></tr>
		<tr><th>조회수</th><td>${article.art_read}</td></tr>
		<tr><th>제목</th><td >${article.art_title }</td></tr>
		<tr><th height="300">내용</th><td valign="top">${article.art_content }</td></tr>
		<tr><td colspan="2">
			<input type="button" value="수정" onclick="location.href=''">
			<input type="button" value="삭제" onclick="location.href=''">
			<input type="button" value="목록" onclick="location.href='notice?category=1510'"></td>
		<tr><th>추천수</th><td>${article.art_good}</td></tr>
		<tr><th>비추천수</th><td>${article.art_bad}</td></tr>
	</table>
	
	${replyCount}개의 댓글
	<c:forEach var="reply" items="${replyList }">
	<table border="1">
		<tr><th>작성자</th><th>프사</th><th>댓글내용</th><th>추천</th><th>비추천</th><th>작성시간</th></tr>
		<tr><td>${reply.mem_nickname}</td>
		<td><img src="${pageContext.request.contextPath}/${reply.mem_image }" alt="예시" style="max-height: 30px; max-width: 30px;"></td>
		<td>${reply.rep_content }</td><td>${reply.rep_good }</td><td>${reply.rep_bad }</td>
		<td><fmt:formatDate value="${reply.rep_regdate }" pattern="yy-MM-dd"/></td>
	</table>
	</c:forEach>
	

</body>
</html>