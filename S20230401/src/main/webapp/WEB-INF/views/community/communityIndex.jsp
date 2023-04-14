<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	<table>
		<tr><th>번호</th><th>제목</th><th>작성일</th><th>조회수</th></tr>
		<c:forEach var="article" items="${listArticle }">
		<tr><td>${num }</td><td>${article.art_title}</td>
			<td>${article.art_regdate }</td>
			<td>${article.art_read }</td>
			</tr>
			<c:set var ="num" value="${num -1 }"></c:set>
		</c:forEach>
	</table>

	<c:if test ="${page.startPage > page.pageBlock }">
		<a href="articleList?currentpage=${page.startPage-page.pageBlock }">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
		<a href="articleList?currentPage=${i }"	>[${i }]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a href="articleList?currentPage=${page.startPage+page.pageBlock }">[다음]</a>
	</c:if>
</body>
</html>