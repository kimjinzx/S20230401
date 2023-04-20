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
	<h2>게시글 작성</h2>
		<c:if test="${msg!=null }">${msg }</c:if>
		<form action="writeArticle" method="post" name="frm">
			<table>
				<tr><th>카테고리</th><td>
					<select name="comm_id">
						<c:forEach var="category" items="${category }">
							<option value="${category.comm_id }">${category.comm_value }</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr><th>제목</th><td><input type="text" name="art_title"></td></tr>
				<tr><th>태그1</th><td><input type="text" name="art_tag1"></td></tr>
				<tr><th>태그2</th><td><input type="text" name="art_tag2"></td></tr>
				<tr><th>태그3</th><td><input type="text" name="art_tag3"></td></tr>
				<tr><th>태그4</th><td><input type="text" name="art_tag4"></td></tr>
				<tr><th>태그5</th><td><input type="text" name="art_tag5"></td></tr>
				<tr><th>장소(세부)</th><td><input type="text" name="reg_id"></td></tr>
				<tr><th>지역제한</th><td>
					<select name="reg_id">
					<c:forEach var="region" items="${region }">
						<option value="${region.reg_id }">${region.reg_name }</option>
					</c:forEach>
					</select></td>
				
				<tr>
				<tr><th>모집인원</th><td><input type="number" name="trd_max" ></td></tr>
				<tr><th>최소나이</th><td><input type="number" name="trd_minage"></td></tr>
				<tr><th>최대나이</th><td><input type="number" name="trd_maxage"></td></tr>
				<tr>
					<th>내용</th>
      				<td><textarea cols="50" rows="10" placeholder="내용을 입력하세요"></textarea>
      				</td>
      			</tr>
				
				<tr><td colspan="2"><input type="submit" value="확인"></td></tr>
			</table>
				<input type="button" value="목록" onclick="location.href='together?brd_id=1000'">
				</form>
</body>
</html>