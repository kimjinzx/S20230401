<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상세게시글</h2>
	<input type="button" value="목록" onclick="location.href='listArticle?brd_id=1000'">
	
	<table>
	<c:forEach var="art" items="${article }">
		<tr>
			<th>거래상태</th>
			<td>${art.comm_value }
		</tr>
		<tr>
			<th>제목</th>
			<td>${art.art_title}</td>
		</tr>
		<tr>
			<th>태그1</th>
			<td>${art.art_tag1 }</td>
		</tr>
		<tr>
			<th>태그2</th>
			<td>${art.art_tag2 }</td>
		</tr>
		<tr>
			<th>태그3</th>
			<td>${art.art_tag3 }</td>
		</tr>
		<tr>
			<th>태그4</th>
			<td>${art.art_tag4 }</td>
		</tr>
		<tr>
			<th>태그5</th>
			<td>${art.art_tag5 }</td>
		</tr>
		<tr>
			<th>장소</th>
			<td>${art.trd_loc }</td>
		</tr>
		<tr>
			<th>모집인원</th>
			<td>${art.trd_max }</td>
		</tr>
		<tr>
			<th>최소나이</th>
			<td>${art.trd_minage }</td>
		</tr>
		<tr>
			<th>최대나이</th>
			<td>${art.trd_maxage }</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${art.art_good }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${art.art_content }</td>
		</tr>					
	
	</c:forEach>

	</table>
</body>
</html>