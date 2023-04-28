<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
</head>
<script type="text/javascript">
function report() {

  if (confirm("정말로 신고 하시겠습니까??")) {

    window.location.href = "https://www.epeople.go.kr/index.jsp";

  }

}

function functionAlert(){
	alert("신청완료 되었습니다");
}

function functionAlert2(){
	alert("관심 등록 되었습니다");
}
 </script>
<body>
	        <h1>게시글 수정</h1>
	<form action ="${pageContext.request.contextPath }/board/information/detail" id ="detail">
		<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath }/board/information?category=1400'">
		<input type="button" value="수정하기" onclick="location.href='${pageContext.request.contextPath}/board/information/update?art_id=${article.art_id}&brd_id=${article.brd_id}&category=${category }';">
		<input type="button" value="삭제하기" onclick="location.href='${pageContext.request.contextPath }/board/information/delete?art_id=${article.art_id}&brd_id=${article.brd_id}&category=${category }';">
		<input type="hidden" name="art_id" value="${article.art_id }">
		<input type="hidden" name="brd_id" value="${article.brd_id }">
		<input type="hidden" name="category" value="${category}">
            <table>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="art_title" value="${article.art_title}"></td>
                </tr>
                <tr>
                    <th>태그1</th>
                    <td><input type="text" name="art_tag1" value="${article.art_tag1}"></td>
                </tr>
                <tr>
                    <th>태그2</th>
                    <td><input type="text" name="art_tag2" value="${article.art_tag2}"></td>
                </tr>
                <tr>
                    <th>태그3</th>
                    <td><input type="text" name="art_tag3" value="${article.art_tag3}"></td>
                </tr>
                <tr>
                    <th>태그4</th>
                    <td><input type="text" name="art_tag4" value="${article.art_tag4}"></td>
                </tr>
                <tr>
                    <th>태그5</th>
                    <td><input type="text" name="art_tag5" value="${article.art_tag5}"></td>
                </tr>
                <tr>
                    <th>추천수</th>
                    <td><input type="text" name="art_good" value="${article.art_good}"></td>
                </tr>
                <tr>
                    <th>비추천수</th>
                    <td><input type="text" name="art_bad" value="${article.art_bad}"></td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td><input type="text" name="art_read" value="${article.art_read}"></td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td><fmt:formatDate value="${article.art_regdate}" pattern="YY-MM-dd"/></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea rows="15" cols="50" name="art_content">${article.art_content}</textarea></td>
                </tr>
            </table>
       <input type=button onclick="report()" value="신고하기">
       <input type=button onclick="functionAlert2()" value="관심">
       <input type=button onclick="functionAlert()" value="신청">
       <input type=button value="추천" onclick="location.href='${pageContext.request.contextPath }/board/information/updategood?art_id=${article.art_id}&brd_id=${article.brd_id}&category=${category }';">
       <input type=button value="비추천" onclick="location.href='${pageContext.request.contextPath }/board/information/updatebad?art_id=${article.art_id}&brd_id=${article.brd_id}&category=${category }';">
</form>
	<table border="1">
	<tr>
		<th>댓글 번호</th>
		<th>닉네임</th>
		<th>댓글 내용</th>
		<th>작성 일자</th>
		<th>댓글 추천수</th>
		<th>댓글 비추천수</th>
	</tr>
		<c:forEach var="reply" items="${listReply }">
	<tr>
		<td>${reply.rep_id }</td>
		<td>${reply.mem_nickname }</td>
		<td>${reply.rep_content }</td>
		<td><fmt:formatDate value="${reply.rep_regdate}" pattern="YYYY-MM-dd"/></td>
		<td>${reply.rep_good}</td>
		<td>${reply.rep_bad}</td>
	</tr>
		</c:forEach>
	</table>

</body>
</html>