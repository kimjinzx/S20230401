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
	<h2>게시글 수정</h2>
		<c:if test="${msg!=null }">${msg }</c:if>
		<form action="${pageContext.request.contextPath }/board/updateArticle" method="POST" name="frm">
		<input type="hidden" name="art_id" value="${article.art_id }">
		<input type="hidden" name="brd_id" value="${article.brd_id }">
		<input type="hidden" name="mem_id" value="${article.mem_id }">
		<input type="hidden" name="trd_id" value="${article.trd_id }">
			<table>
				<tr><th>카테고리</th><td>
					<select name="brd_id">
						<c:forEach var="category" items="${categories }">
							<option value="${category.comm_id }">${category.comm_value }</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr><th>제목</th><td> <input type="text" name="art_title" value="${article.art_title }"></td></tr>
				<tr><th>태그1</th><td><input type="text" name="art_tag1"  value="${article.art_tag1 }"></td></tr>
				<tr><th>태그2</th><td><input type="text" name="art_tag2"  value="${article.art_tag2 }"></td></tr>
				<tr><th>태그3</th><td><input type="text" name="art_tag3"  value="${article.art_tag3 }"></td></tr>
				<tr><th>태그4</th><td><input type="text" name="art_tag4"  value="${article.art_tag4 }"></td></tr>
				<tr><th>태그5</th><td><input type="text" name="art_tag5"  value="${article.art_tag5 }"></td></tr>
				<tr><th>지역제한</th><td>
					<select name="reg_id">
					<c:forEach var="region" items="${regions }">
						<option value="${region.reg_id }">${region.reg_name }</option>
					</c:forEach>
					</select></td>
				<tr>
				<tr><th>장소(세부)</th><td><input type="text"   name="trd_loc" 	value="${article.trd_loc }"></td></tr>
				<tr><th>마감일자    </th><td><input type="date"   name="trd_enddate" value="${article.trd_enddate }"></td></tr>
				<tr><th>모집인원    </th><td><input type="number" name="trd_max" min="1" max="100" value="${article.trd_max }" ></td></tr>
				<tr><th>최소나이    </th><td><input type="number" name="trd_minage" min="1" max="100" value="${article.trd_minage }"></td></tr>
				<tr><th>최대나이    </th><td><input type="number" name="trd_maxage" min="1" max="100" value="${article.trd_maxage }"></td></tr>
				<tr>
					<th>내용</th>
      				<td><textarea name = "art_content" cols="50" rows="10" placeholder="내용을 입력하세요" required ="required" value="${article.art_content }"></textarea>
      				</td>
      			</tr>
				
				<tr><td colspan="2"><input type="submit" value="확인"></td></tr>
			</table>
				<input type="button" value="목록" onclick="location.href='together?category=1000'">
		</form>
</body>
</html>