<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고내용 팝업창</title>
</head>
<body>
신고
  
<form action="${pageContext.request.contextPath }/board/writeArticle" method="POST" name="frm">  
	<table border="1">
	  <tr>
	    <td><input type="hidden" name="art_id" value="${article.art_id}"></td>
	    <td><input type="hidden" name="brd_id" value="${article.brd_id}"></td>
	    <td><input type="hidden" name="mem_id" value="${memberInfo.mem_id}"></td>
	  </tr>
	</table>
</form>
</body>
</html>