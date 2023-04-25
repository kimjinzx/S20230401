<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
		<div align="center">
			<h2>글쓰기</h2>
			<form action="${pageContext.request.contextPath}/board/customer/writeCustomer" method="post" name="frm">
				<table>
					<tr><th>제목</th><td>
					<input type="text" name="art_title" placeholder="제목을 입력해 주세요" required="required">

			
					<tr><th>작성자</th><td>
					<input type="text" name="mem_nickname"  value="${mem_nickname}" readonly>

				
					<tr><th>내용</th><td>
					<textarea rows="5" name="art_content" placeholder="내용을 입력해 주세요" required="required"></textarea>
					
					<td colspan="2">
					<input type="submit" value="등록" />
					<input type="button" value="목록" onclick="location.href='../CustomerIndex.jsp'"/>
					</td>
				</table>
			</form>
			</div>
	</article>
</body>
</html>