<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.container {
				height: 500px;
				width: 800px;
				background-color: #FFB85A;
				}
	
</style>
</head>
<body>
<body>
<!-- 확인 버튼 구현  -->
<form action="${pageContext.request.contextPath }/dutchpay/dutchpayUpdatePro" method="post"> 
	<div class="container">
	작성자 : <input type="hidden" name="member_image" >${updateForm.mem_image }
		   <input type="hidden" name="member_username" >${updateForm.mem_username }<p>
	작성날짜 : <input type="hidden" name="art_regdate" >${updateForm.art_regdate }<p>
	<table>
		<tr><th>제목</th><td>
		<input type="text" name="title" value="${updateForm.art_title }" required="required" ></td></tr>
		<tr><th>카테고리</th><td>
			<select name="brd_id">
			<c:forEach var="C_ud" items="${category_ud }">
				<option value="${C_ud.comm_id }">${C_ud.comm_value }</option>
			</c:forEach>
			</select></td>
		</tr>
		<tr><th>태그</th>
		<td><input type="text" name="tag1" value="${updateForm.art_tag1}">
			<input type="text" name="tag2" value="${updateForm.art_tag2}">
			<input type="text" name="tag3" value="${updateForm.art_tag3}"><br>	
			<input type="text" name="tag4" value="${updateForm.art_tag4}">
			<input type="text" name="tag5" value="${updateForm.art_tag5}">
		</td></tr>
		<tr><th>가격</th><td>
		<input type="number" name="trd_cost" value="${updateForm.trd_cost}"   required="required"></td></tr>
		<tr><th>모집인원</th><td>
		<input type="number" name="trd_max" value="${updateForm.trd_max}"	  required="required"> 명 (작성자 제외)</td></tr>
		<%-- <tr><th>마감일자</th><td>
		<input type="date" name="trd_enddate" value="${updateForm.trd_enddate}"	  required="required"></td></tr> --%>
		<tr><th>지역</th><td>
			<select name="reg_id">
			<c:forEach var="L_ud" items="${loc_ud }">
				<option value="${L_ud.reg_id }">${L_ud.reg_name }</option>
			</c:forEach>
			</select></td>
		</tr> 
		<tr><th>거래 상세장소</th><td><input  type="text" name="trd_loc" value="${updateForm.trd_loc}"></td></tr>
		<tr><th>내용</th><td>
		 	<textarea rows="10" cols="50">${updateForm.art_content}</textarea>
  		</td></tr>

	</table> <!--  brd_id가져오기 -->
		<input type="submit" value="수정확인">
		<input type="button" value="취소" onclick="location.href='${pageContext.request.contextPath }/board/dutchpay?category=1100'">
	</div>

</form>
</body>
</html>