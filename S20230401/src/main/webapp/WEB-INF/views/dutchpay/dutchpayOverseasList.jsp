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
				height: 280px;
				width: 800px;
				background-color: #FAEBFF;
				}
	

</style>
</head>
<body>
<a href="${pageContext.request.contextPath }/board/dutchpay?category=1100">전체목록</a>
<a href="${pageContext.request.contextPath }/board/dutchpay?category=1110">식료품</a>
<a href="${pageContext.request.contextPath }/board/dutchpay?category=1120">의류/잡화</a>
<a href="${pageContext.request.contextPath }/board/dutchpay?category=1130">생활용품</a>
<a href="${pageContext.request.contextPath }/board/dutchpay?category=1150">기타</a><p>
<input type="button" value="글쓰기" onclick="location.href='/dutchpay/dutchpayWriteForm'"> 
	 <c:forEach var="ATR" items="${dutchpayList }">
	<div class="container" >
		<span>${ATR.comm_value }</span>
		<span><a href="/dutchpay/dutchpayDetail?art_id=${ATR.art_id}&brd_id=${ATR.brd_id}">제목 : ${ATR.art_title }</a>작성날짜 : ${ATR.art_regdate }  </span><p>
		
		<span>태그 : ${ATR.art_tag1 }  ${ATR.art_tag2 }  ${ATR.art_tag3 }  ${ATR.art_tag4 } ${ATR.art_tag5 }</span><p>
		<span>가격 : ${ATR.trd_cost }</span><p>
		<span>거래장소 : ${ATR.reg_name }</span><p>
		<span>모집인원 수 : ${ATR.trd_max }명</span><p>
		<span>글쓴이 : ${ART.mem_image}   ${ATR.mem_username }</span><p>
		<span>마감일자 : ${ATR.trd_enddate }</span><p>
		<span>추천 ${ATR.art_good }  비추천 ${ATR.art_bad }  조회수${ATR.art_read }  댓글수</span>
	</div>
	<hr>
	</c:forEach>
	
	
</body>
</html>