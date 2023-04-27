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
	
</style>
</head>
<body>
<body>
<form action="${pageContext.request.contextPath }/dutchpay/dutchpayDelete" method="post"> <!-- 아래의 목록 버튼을 누르면 전 brd_id로 넘어감 -->
	<input type="button" value="목록(돌아가기)" onclick="location.href='${pageContext.request.contextPath }/board/dutchpay?category=${detail.brd_id}'"> 
	<input type="button" value="수정" 		 onclick="location.href='${pageContext.request.contextPath }/dutchpay/dutchpayUpdateForm?art_id=${detail.art_id}&brd_id=${detail.brd_id}'"> 
	
	<input type="submit" value="게시글 삭제">
	
	<input type="hidden" name="brd_id" value="${detail.brd_id }">
	<input type="hidden" name="art_id" value="${detail.art_id }">
	
	<div class="container">
		<span>작성자 : ${detail.mem_image}   ${detail.mem_username }</span><p>
		<span>${detail.comm_value }</span>
		<span>제목 : ${detail.art_title } 작성날짜 : ${detail.art_regdate }  </span><p>
		<span>태그 : ${detail.art_tag1 }  ${detail.art_tag2 }  ${detail.art_tag3 }  ${detail.art_tag4 } ${detail.art_tag5 }</span><p>
		<span>가격 : ${detail.trd_cost }</span><p>
		<span>모집인원 : ${detail.trd_max } 명 (작성자 제외)</span><p>
		<span>지역 : ${detail.reg_name }</span><p>
		<span>거래 상세장소 : ${detail.trd_loc }</span><p>
		<span>마감일자 : ${detail.trd_enddate } 추천 ${detail.art_good }  비추천 ${detail.art_bad }  조회수${detail.art_read }  댓글수${detail.reply_count }</span><p>
		<span>내용 : ${detail.art_content }</span>
	</div>
	
	
</form>
</body>
</html>