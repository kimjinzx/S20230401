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
	
	<p>
	<input type="button" value="전체" 	onclick="location.href='together?category=1000'">
	<input type="button" value="밥 & 카페" onclick="location.href='together?category=1010'">
	<input type="button" value="스포츠" 	onclick="location.href='together?category=1020'">
	<input type="button" value="쇼핑" 	onclick="location.href='together?category=1030'">
	<input type="button" value="문화생활" 	onclick="location.href='together?category=1040'">
	<input type="button" value="취미생활" 	onclick="location.href='together?category=1050'">
	<input type="button" value="기타" 	onclick="location.href='together?category=1060'">
	
	<p>
	<input type="button" value="목록" 	onclick="location.href='together?category=1000'">
	<input type="button" value="수정하기" 	onclick="location.href='">
	<input type="button" value="삭제하기" 	onclick="location.href='">
	
	<table>
	<c:forEach var="art" items="${detailArticle }">
		<tr>
			<th>거래상태</th>
			<td>${art.comm_value }
		</tr>
		<tr>
			<th>제목</th>
			<td>${art.art_title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${art.mem_nickname }</td>
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
			<th>지역제한</th>
			<td>${art.reg_name }</td>
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
			<th>작성 시간</th>
			<td>${art.rest_regdate }일 전</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${art.art_read }</td>
		<tr>
			<th>관심목록 수</th>
			<td>${art.favoriteCount }</td>
		</tr>
		<tr>
			<td></td>
			<th><input type="button" value="관심목록"  onclick="location.href='">
			<input 	   type="button" value="신청하기" 	onclick="location.href='"></th>
		<tr>
			<th>내용</th>
      		<td>${art.art_content }</td>
		</tr>
		<tr>
			<th>댓글</th>
			<td>(${art.repCount })</td>
		</tr>
		<tr>
			<td></td>
			<td><textarea cols="50" rows="5" placeholder="댓글을 입력하세요"></textarea></td>	
		</tr>
		<tr>
			<td></td>	
			<td><input type="button" value="작성"></td>
		</tr>
	</c:forEach>
	
	
	<c:forEach var="reply" items="${replyList }">
			<table>
			<tr>
				<td>${reply.mem_nickname }
				<td><img src="${pageContext.request.contextPath}/image/picture/${reply.mem_image}" width ="50" height ="50" alt="사진이 없습니다"></td>
				<td>${reply.rep_content }</td>
			</tr>
			</table>				
	</c:forEach>

	</table>
</body>
</html>