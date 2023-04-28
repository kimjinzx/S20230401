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
				height: 320px;
				width: 800px;
				background-color: #E0FFDB;
				}
	.repList 
			th { background-color: #bbdefb;
				}
			td { background-color: #e3f2fd;
				}
	
</style>
</head>
<body>
<body>
<form  method="post"> 
	<input type="button" value="목록(돌아가기)" onclick="location.href='${pageContext.request.contextPath }/board/dutchpay?category=${detail.brd_id}'"> 
	<input type="button" value="수정" 		 onclick="location.href='${pageContext.request.contextPath }/dutchpay/dutchpayUpdateForm?art_id=${detail.art_id}&brd_id=${detail.brd_id}'"> 
	<input type="submit" value="게시글 삭제"   formaction="${pageContext.request.contextPath }/dutchpay/dutchpayDelete"><br>
	<input type="submit" value="추천" 		formaction="">
	<input type="submit" value="비추천" 	    formaction="">
	
	<input type="hidden" name="brd_id" value="${detail.brd_id }">
	<input type="hidden" name="art_id" value="${detail.art_id }">
	
	<div class="container">
		<span>작성자 : ${detail.mem_image}   ${detail.mem_username }</span><p>
		<span>${detail.comm_value } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      제목 : ${detail.art_title } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      작성날짜 : <fmt:formatDate value="${detail.art_regdate}" pattern="yyyy년M월d일  hh시mm분"/> </span><p>
		<span>태그 : ${detail.art_tag1 }  ${detail.art_tag2 }  ${detail.art_tag3 }  ${detail.art_tag4 } ${detail.art_tag5 }</span><p>
		<span>가격 : ${detail.trd_cost }원</span><p>
		<span>모집인원 : ${detail.trd_max }명 (작성자 제외)</span><p>
		<span>지역 : ${detail.reg_name }</span><p>
		<span>거래 상세장소 : ${detail.trd_loc }</span><p>
		<span><%-- 마감일자 : <fmt:formatDate value="${detail.trd_enddate}" pattern="yyyy년M월d일  h시mm분 까지"/>  --%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  추천 : ${detail.art_good }  비추천 : ${detail.art_bad }  조회수 : ${detail.art_read } </span><p>
		<span>내용 : ${detail.art_content }</span>
	</div>
</form>
	
	<h3>댓 글</h3>
	
	<div class ="container2">
		<textarea rows="3" cols="50" placeholder="댓글을 입력해주세요"></textarea>
		<input type="submit" value="등록" formaction="">
	</div>	
	
	<form method="post">
		<a>댓글수 : ${detail.reply_count }개</a> 
		<table class="repList" border="1">
			<tr>
				<th><c:out value="댓글 순서"/></th>
				<th><c:out value="작성자"/></th>
				<th><c:out value="내용"/></th>	
				<th><c:out value="작성일자"/></th>
				<th><c:out value="추천수"/></th>
				<th><c:out value="비추천수"/></th>
			</tr>
 		<c:forEach var="Rep" items="${repList}">
			<tr>
				<td><c:out value="${Rep.rep_id }"/></td>
				<td><c:out value="${Rep.mem_nickname }"/></td>
				<td><c:out value="${Rep.rep_content }"/></td>
				<td><fmt:formatDate value="${Rep.rep_regdate}" pattern="yyyy년M월d일  hh시mm분"/>
				<td><input type="submit" value="추천" formaction="">${Rep.rep_good }</td>
				<td><input type="submit" value="비추천"   formaction="">${Rep.rep_bad }</td>
				<td><input type="submit" value="답글"   formaction=""></td>
				<td><input type="submit" value="댓글삭제" formaction=""></td>
			</tr>
		</c:forEach> 
		</table>
	</form>
</body>
</html>