<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지</h1>
	<h3>게시글수: ${totalArticle}</h3>
<%--     <form action="listSearch3">
   		<select name="search">
				<option value="s_job">업무조회</option>
				<option value="s_ename">이름조회</option>
		</select> 
   
        <input type="text" name="keyword"   placeholder="keyword을 입력하세요">
        <button type="submit">keyword검색 </button><p>
    </form> --%>

	<c:set var="num" value="${page.total-page.start+1 }"></c:set>
	
	<table>
		<tr><th >글번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>추천수</th><th>비추천수</th></tr>
		<c:forEach var="article" items="${listArticle }">
			<tr>
			<td>${num }</td><td>${article.art_title}</td><td>${article.mem_id }</td><td>${article.art_regdate }</td>
			<td>${article.art_read}</td><td>${article.art_good}</td><td>${article.art_bad}</td>
			</tr>
			<c:set var="num" value="${num - 1 }"></c:set>
		</c:forEach>
	</table>	
	
	<c:if test="${page.startPage > page.pageBlock }">
		<a href="listArticle?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
		<a href="listArticle?currentPage=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${page.endPage < page.totalPage }">
		<a href="listArticle?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
	</c:if>	
</body>
</html>