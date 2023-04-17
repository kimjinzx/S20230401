<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>사진은 없엉</td>
		</tr>
		<tr>
			<td>카테고리 : ${article.board.comm.comm_value }</td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath}/article/share?art_id=${article.art_id}&brd_id=${article.brd_id}">${article.art_title}</a></td>
		</tr>
		<tr>
			<td>가격 : ${article.trade.trd_cost }원</td>
		</tr>
		<tr>
			<td>닉네임 : ${article.member.mem_nickname }</td>
		</tr>
		<tr>
			<td>성별 : 
				<c:if test="${article.member.mem_gender == 201}">남자</c:if>
				<c:if test="${article.member.mem_gender == 202}">여자</c:if>
			</td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${article.art_regdate }" pattern="MM-dd"/></td>
		</tr>
		<tr>
			<td>조회 ${article.art_read }</td>
		</tr>
		<tr>
			<td>내용 : ${article.art_content }</td>
		</tr>
		<tr>
			<td>추천 : ${article.art_good }</td>
		</tr>
		<tr>
			<td>비추천 : ${article.art_bad }</td>
		</tr>
		<tr>
			<td>이름 : ${article.member.mem_name }</td>
		</tr>
		<tr>
			<td>아이디 : ${article.member.mem_username }</td>
		</tr>
		<tr>
			<td>비밀번호 : ${article.member.mem_password }</td>
		</tr>
		<tr>
			<td>이메일 : ${article.member.mem_email }</td>
		</tr>
	</table>
</body>
</html>