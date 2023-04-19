<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="${pageContext.request.contextPath}/board/share/writeArticle" method="post">
		<input type="hidden" value="${category}"><br>
		제목<input type="text" name="art_title"><br>
		내용<input type="text" name="art_content"><br>
		태그1<input type="text" name="art_tag1"><br>
		태그2<input type="text" name="art_tag2"><br>
		mem_username<input type="text" name="member.mem_username">
		<input type="submit" value="작성">
		<input type="reset" value="취소">
	</form>
</body>
</html>