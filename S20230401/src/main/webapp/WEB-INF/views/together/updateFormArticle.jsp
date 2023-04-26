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
			<tr>
				<th>카테고리</th>
				<td><select name="brd_id">
						<c:forEach var="category" items="${categories }">
							<option ${category.comm_id == article.brd_id ? 'selected=selected' : ''} value = "${category.comm_id}">${category.comm_value }</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="art_title"
					value="${article.art_title }"></td>
			</tr>
			<tr>
				<th>태그1</th>
				<td><input type="text" name="art_tag1"
					value="${article.art_tag1 }"></td>
			</tr>
			<tr>
				<th>태그2</th>
				<td><input type="text" name="art_tag2"
					value="${article.art_tag2 }"></td>
			</tr>
			<tr>
				<th>태그3</th>
				<td><input type="text" name="art_tag3"
					value="${article.art_tag3 }"></td>
			</tr>
			<tr>
				<th>태그4</th>
				<td><input type="text" name="art_tag4"
					value="${article.art_tag4 }"></td>
			</tr>
			<tr>
				<th>태그5</th>
				<td><input type="text" name="art_tag5"
					value="${article.art_tag5 }"></td>
			</tr>
			<tr><th>지역제한</th>
					<td>
					<select id ="reg_parent" name="reg_id1">
						<option value="">제한없음</option>
					<c:forEach var="parentRegion" items="${parentRegions }">
						<option ${parentRegion.reg_id  == article.reg_id ? 'selected=selected' : ''} value="${parentRegion.reg_id }">${parentRegion.reg_name }</option>
					</c:forEach>
					</select>
					</td>
			<tr>
				<th></th>	
					<td>
					<select id ="reg_id" name="reg_id2">
						<option value="">제한없음</option>
					<c:forEach var="region" items="${regions }">
						<option ${region.reg_id  == article.reg_id ? 'selected=selected' : ''} value="${region.reg_id }">${region.reg_name }</option>
					</c:forEach>
					</select>
					</td></tr>
			<tr>
				<th>장소(세부)</th>
				<td><input type="text" name="trd_loc"
					value="${article.trd_loc }"></td>
			</tr>
			<tr>
				<th>마감일자</th>
 				<c:set var="enddate">
					<fmt:formatDate value="${article.trd_enddate }" pattern="yyyy-MM-dd"/>
				</c:set> 
				<td><input type="date" name="trd_enddate1" value="${enddate }" pattern="yyyy-MM-dd" required="required"></td>
			</tr>
			<tr>
				<th>모집인원</th>
				<td><input type="number" name="trd_max" min="1" max="100"
					value="${article.trd_max }"></td>
			</tr>
			<tr>
				<th>최소나이</th>
				<td><input type="number" name="trd_minage" min="1" max="100"
					value="${article.trd_minage }"></td>
			</tr>
			<tr>
				<th>최대나이</th>
				<td><input type="number" name="trd_maxage" min="1" max="100"
					value="${article.trd_maxage }"></td>
			</tr>
							<tr><th>성별</th><td> 	
<%-- 					<select id = "trd_gender" name="trd_gender">
						<option value="null">제한없음</option>
						<c:forEach var="gender" items="${genders }">
							<option value="${gender.comm_id }">${gender.comm_value }</option>
						</c:forEach>
					<c:set var="gender" value ="${gender.comm_id }"/>
					<c:choose>
						<c:when test="${gender == 201}">남성</c:when>
						<c:when test="${gender == 202}">여성</c:when>
					</c:choose>	
					</select></td></tr> --%>
					<select id = "trd_gender" name="trd_gender">
						<option value="" 	${article.trd_gender == null ? 'selected' : '' }>제한없음</option>
						<option value="201" ${article.trd_gender == 201 ?  'selected' : '' }>남성</option>
						<option value="202" ${article.trd_gender == 202 ?  'selected' : '' }>여성</option>
					</select></td></tr>
			<tr>
				<th>내용</th>
				<td><textarea name="art_content" cols="50" rows="10"
						placeholder="내용을 입력하세요" required="required">${article.art_content }</textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="확인"></td>
			</tr>
		</table>
		<input type="button" value="목록"
			onclick="location.href='together?category=1000'">
	</form>
</body>
</html>