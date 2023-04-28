<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
/* 		const add_textbox = () => {
		    const box = document.getElementById("box");
		    const newP = document.createElement('p');
		    newP.innerHTML = "<input type='text'> <input type='button' value='삭제' onclick='remove(this)'>";
		    box.appendChild(newP);
		}
		const remove = (obj) => {
		    document.getElementById('box').removeChild(obj.parentNode);
		} */
		
	
	
</script>
</head>
<body>
	<h2>상세게시글</h2>
	
	<p>
	<input type="button" value="전체" 	onclick="${pageContext.request.contextPath}location.href='together?category=1000'">
	<input type="button" value="밥 & 카페" onclick="${pageContext.request.contextPath}location.href='together?category=1010'">
	<input type="button" value="스포츠" 	onclick="${pageContext.request.contextPath}location.href='together?category=1020'">
	<input type="button" value="쇼핑" 	onclick="${pageContext.request.contextPath}location.href='together?category=1030'">
	<input type="button" value="문화생활" 	onclick="${pageContext.request.contextPath}location.href='together?category=1040'">
	<input type="button" value="취미생활" 	onclick="${pageContext.request.contextPath}location.href='together?category=1050'">
	<input type="button" value="기타" 	onclick="${pageContext.request.contextPath}location.href='together?category=1060'">
	
	<p>
	<input type="button" value="목록" 	onclick="location.href='${pageContext.request.contextPath }together?category=1000';">
	<input type="button" value="수정하기" 	onclick="location.href='${pageContext.request.contextPath }/board/updateFormArticle?brd_id=${detailArticle.brd_id }&art_id=${detailArticle.art_id }';">
	<input type="button" value="삭제하기"  onclick="location.href='${pageContext.request.contextPath }/board/deleteArticle?brd_id=${detailArticle.brd_id }&art_id=${detailArticle.art_id }';">
	
	<table>
		<tr>
			<th>거래상태</th>
			<td>${detailArticle.c1_comm_value }
		</tr>
		<tr>
			<th>제목</th>
			<td>${detailArticle.art_title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${detailArticle.mem_nickname }</td>
		</tr>
		
		<tr>
			<th>태그1</th>
			<td>${detailArticle.art_tag1 }</td>
		</tr>
		<tr>
			<th>태그2</th>
			<td>${detailArticle.art_tag2 }</td>
		</tr>
		<tr>
			<th>태그3</th>
			<td>${detailArticle.art_tag3 }</td>
		</tr>
		<tr>
			<th>태그4</th>
			<td>${detailArticle.art_tag4 }</td>
		</tr>
		<tr>
			<th>태그5</th>
			<td>${detailArticle.art_tag5 }</td>
		</tr>
		<tr>
			<th>장소</th>
			<td>${detailArticle.trd_loc }</td>
		</tr>
		<tr>
			<th>지역제한</th>
			<td>${detailArticle.reg_name }</td>
		</tr>
		<tr>
			<th>모집인원</th>
			<td>${detailArticle.trd_max }</td>
		</tr>
		<tr>
			<th>최소나이</th>
			<td>${detailArticle.trd_minage }</td>
		</tr>
		<tr>
			<th>최대나이</th>
			<td>${detailArticle.trd_maxage }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
			<c:set var="gender" value ="${detailArticle.c2_comm_id }"/>
			<c:choose>
				<c:when test="${gender eq 201 }">남성</c:when>
				<c:when test="${gender eq 202 }">여성</c:when>
				<c:otherwise>성별무관</c:otherwise>
			</c:choose>	
			</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${detailArticle.art_good }</td>
		</tr>
		<tr>
			<th>작성 시간</th>
			<td>${detailArticle.rest_regdate }일 전</td>
		</tr>
		<tr>
			<th>마감 일자 </th>
			<td>
			<c:set var="date" value="${detailArticle.trd_finish }"/>
			<c:choose>
				<c:when test="${date eq null}"></c:when>
				<c:otherwise>${date }까지</c:otherwise>
			</c:choose>
			<td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${detailArticle.art_read }</td>
		<tr>
			<th>관심목록 수</th>
			<td>${detailArticle.favoriteCount }</td>
		</tr>
		<tr>
			<td></td>
			<th><input type="button" value="관심목록"  onclick="location.href='">
			<input 	   type="button" value="신청하기" 	onclick="location.href='"></th>
		<tr>
			<th>내용</th>
      		<td>${detailArticle.art_content }</td>
		</tr>
		<tr>
			<th>댓글</th>
			<td>(${detailArticle.repCount })</td>
		</tr>
	</table>
	
	
	<div class="replyList" style="margin: 10px; border: 1px solid black; padding: 10px;">
		<div class="insertReply">
			<form action="/board/insertReply" method="post">
			<!-- form 'action' = '데이터가 도착할 URL을 써준다' 'method' = '데이터를 전달할 방식을 써준다'-->
			<!-- get 방식으로 넘길 땐 주소값 뒤에 ? 로 파라미터 값을 적어주고 / post 방식으로 넘길 땐 form 안에 input으로 값을 적어서 넘겨준다-->
				<span><input type="hidden" name="art_id"  		value="${detailArticle.art_id }"></span>
				<span><input type="hidden" name="brd_id" 		value="${detailArticle.brd_id }"></span>
				<span><input type="hidden" name="mem_id" 		value="${detailArticle.mem_id }"></span>
				<span><input type="hidden" name="rep_id" 		value="${reply.rep_id }"></span>
				<span><input type="hidden" name="rep_parent" 	value="${reply.rep_parent }"></span>
				<span><input type="text"   name="rep_content"   placeholder="댓글을 입력하세요"
							 style="margin : 10px; width:1225px; height:100px; font-size:12px;"></input></span>
			<!-- name = 데이터를 전달 받는 column 이름, value= 들어갈 데이터의 값, id = javascript로 꾸밀 때 지정해주는 이름(?) -->		
				<div class="replySubmit" style="margin: 5px; float: right;">
					<input type="submit" value="입력">
				</div>
			</form>
		</div>	
				<!-- submit 버튼을 통해 데이터가 입력(?)된다 -->
	<c:forEach var="reply" items="${replyList }">
		<div class="replyInfo" >
			<span><img src="${pageContext.request.contextPath}/image/picture/${reply.mem_image}" width ="30" height ="30" alt="-"></span>
			<span>${reply.mem_nickname }</span>
			<span>(${reply.mem_username })</span>
			<span><fmt:formatDate value="${reply.rep_regdate}" pattern="yy년 MM월 dd일 : HH:mm:ss"/></span>
		</div>	
			<!-- 날짜형식 바꿔야함!! -->
					
			<div class="replyContent" style= "margin: 10px; border: 1px solid black; padding: 15px; width: 1200px; height: 60px;" >		
				<span style="font-size: 12px;">${reply.rep_content }</span>
					<div class="replyButton" id ="box" style= "float: right;">
						<form action="/board/updateReply" method="post">	
							<span><input type="hidden" name="art_id"  		value="${detailArticle.art_id }"></span>
							<span><input type="hidden" name="brd_id" 		value="${detailArticle.brd_id }"></span>
							<span><input type="hidden" name="rep_id" 		value="${reply.rep_id }"></span>
							<span><input type="text" name="rep_content" value="${reply.rep_content }"></span>
							<span><input type="submit" value="수정"></span>
						</form>
						<form action="/board/deleteReply" method="post">	
							<span><input type="hidden" name="art_id"  		value="${detailArticle.art_id }"></span>
							<span><input type="hidden" name="brd_id" 		value="${detailArticle.brd_id }"></span>
							<span><input type="hidden" name="rep_id" 		value="${reply.rep_id }"></span>
							<span><input type="submit" value="삭제"></span>
						</form>	
							<span><input type="button" value="댓글 작성"    onclick=""></span>
					</div>
			</div>
<!-- 		<form action="/board/insertReply" method="post">
		        <div id="box">
            		<input type="text"> <input type="button" value="추가" onclick="add_textbox()">
        		</div>
 			<span><input name="rep_content" placeholder="댓글을 입력하세요"
						 style="width:1200px; height:100px; font-size:10px;"></input></span>
			<span><input type="submit" value="댓글작성"></span>
		</form>	 -->	
	</c:forEach>
	</div>

</body>
</html>