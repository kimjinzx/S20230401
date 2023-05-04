<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.container1 { 
				margin-left: 20px;
				margin-top: 20px;
				height: 270px;
				width: 800px;
				background-color: #E0FFDB;
				}
				
	.container2 { 
				margin-left: 20px;
				height: 50px;
				width: 800px;
				background-color: #E0FFDB;
				}
				
	.repList 
			th { background-color: #bbdefb;
				}
			td { background-color: #e3f2fd;
				}
				
	.JoinList
				{ margin-left: 20px; 
				  background-color: #FFF2E6;
				}
				
	.WaitApply
				{ margin-left: 20px; 
				  margin-top: 20px;
				  margin-bottom: 20px;
				  background-color: #FFF2E6;
				}
				
</style>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("JF").onclick = function () {
	window.open("/joinForm?art_id="+${detail.art_id}+"&brd_id="+${detail.brd_id},"신청서","width=700px,height=700px,top=100px,left=400px");
		}
	};

	function goJoinAccept(p_trd_id, p_mem_id, p_brd_id, p_art_id) {
		alert('p_trd_id -> '+p_trd_id);
		alert('p_mem_id -> '+p_mem_id);
		alert('p_brd_id -> '+p_brd_id);
		alert('p_art_id -> '+p_art_id); 
		location.href="/dutchpay/JoinAccept?trd_id="+p_trd_id+"&mem_id="+p_mem_id+"&brd_id="+p_brd_id+"&art_id="+p_art_id;
	}
	
	function goJoinDeny(p_trd_id, p_mem_id, p_brd_id, p_art_id) {
		/* alert('p_trd_id -> '+p_trd_id);
		alert('p_mem_id -> '+p_mem_id);
		alert('p_brd_id -> '+p_brd_id);
		alert('p_art_id -> '+p_art_id); */
		location.href="/dutchpay/JoinDeny?trd_id="+p_trd_id+"&mem_id="+p_mem_id+"&brd_id="+p_brd_id+"&art_id="+p_art_id;
	}
	
</script> 
</head>
<body>
<form  method="post"> 

	<input type="button" value="목록(돌아가기)" onclick="location.href='${pageContext.request.contextPath }/board/dutchpay?category=${detail.brd_id}'"> 
	
	
	
	<c:choose> 
		<c:when test="${memberInfo.mem_id == detail.mem_id}"> 
			<input type="button" value="수정" 	       onclick="location.href='${pageContext.request.contextPath }/dutchpay/dutchpayUpdateForm?art_id=${detail.art_id}&brd_id=${detail.brd_id}'"> 
			<input type="submit" value="게시글 삭제"      formaction="${pageContext.request.contextPath }/dutchpay/dutchpayDelete"><br>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	
	
	<input type="submit" value="추천" 		formaction="">
	<input type="submit" value="비추천" 	    formaction="">
	<input type="submit" value="신고하기" 	    formaction="">
	
	<input type="hidden" name="brd_id" value="${detail.brd_id }">
	<input type="hidden" name="art_id" value="${detail.art_id }">
	<input type="hidden" name="trd_id" value="${detail.trd_id }">
	<input type="hidden" name="mem_id" value="${detail.mem_id }">
	
	<div class="container1">
		<span>작성자 : ${detail.mem_image}  ${detail.mem_nickname } (${detail.mem_username })</span><p>
		<span>${detail.comm_value } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      제목 : ${detail.art_title } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      작성날짜 : <fmt:formatDate value="${detail.art_regdate}" pattern="yyyy년M월d일  hh시mm분"/> </span><p>
		<span>태그 : ${detail.art_tag1 }  ${detail.art_tag2 }  ${detail.art_tag3 }  ${detail.art_tag4 } ${detail.art_tag5 }</span><p>
		<span>가격 : ${detail.trd_cost }원</span><p>
		<span>지역 : ${detail.reg_name }</span><p>
		<span>거래 상세장소 : ${detail.trd_loc }</span><p>
		<span>마감일자 : <%-- <fmt:formatDate value="${detail.trd_enddate}" pattern="yyyy년M월d일  h시mm분 까지"/>  --%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  추천 : ${detail.art_good }  비추천 : ${detail.art_bad }  조회수 : ${detail.art_read } </span>
	</div>

<table class="JoinList" > 
	<tr><th>공동구매 참여하기
			<input type="button" value="신청하기" id="JF" >
			<input type="submit" value="신청취소" formaction="${pageContext.request.contextPath }/dutchpay/applyCancel">
	</th></tr>
	<tr><td>작성자 : <strong>${detail.mem_image} ${detail.mem_nickname} (${detail.mem_username})</strong></td></tr>
	<tr><td>모집인원 : <strong>${detail.trd_max }명</strong> (작성자 제외)</td></tr>
	<tr><th>현재참가자</th></tr>
		<c:forEach var="JL" items="${joinList}" varStatus="status">
			<tr><td>
				<c:out value="${status.count}"/>.
				<c:out value="${JL.mem_image}"/>
				<c:out value="${JL.mem_nickname}"/>
			   (<c:out value="${JL.mem_username}"/>)
			</td></tr> 
		</c:forEach>
</table> 

<table class="WaitApply">
	<tr><th colspan="6">(작성자로 로그인하면 보임). (작성자 권한으로 수락/거부)</th></tr>
	<tr><th colspan="6">참가 대기자 명단</th></tr>
	<tr>
		<th>순서</th>
		<th colspan="3">대기자</th>
		<th>신청일자</th>
	</tr>
	<c:forEach var="WP" items="${waitList}" varStatus="status">
		<tr><td><input type="hidden" value="mem_id"></td></tr>
		<tr> <!--  listemp ajaxform 65라인참조delete -->
			<td><c:out value="${status.count}"/>.</td>
			<td><c:out value="${WP.mem_image}"/></td>
			<td><c:out value="${WP.mem_nickname}"/></td>
			<td>(<c:out value="${WP.mem_username}"/>)</td>
			<td><fmt:formatDate value="${WP.wait_date}" pattern="M월d일  h시mm분"/></td>
			<td><input type="button" value="수락" onclick="goJoinAccept(${detail.trd_id },${WP.mem_id},${detail.brd_id },${detail.art_id })">
				<input type="button" value="거부" onclick="goJoinDeny(${detail.trd_id },${WP.mem_id},${detail.brd_id },${detail.art_id })">
			</td>
		</tr> 
	</c:forEach>
</table>

</form>
	
	
<div class="container2">
		<span>내용 : ${detail.art_content }</span>
</div>
	
<h3>댓 글</h3>
<div class ="RepInsert">
	<textarea rows="3" cols="50" placeholder="댓글을 입력해주세요"></textarea>
	<input type="submit" value="등록" formaction="">
</div>	
	
<form method="post">
	<a>댓글수 : ${detail.reply_count }개</a> 
	<table class="repList" border="1">
		<tr>
			<th>댓글 순서</th>
			<th>작성자</th>
			<th>내용</th>	
			<th>작성일자</th>
			<th>추천수</th>
			<th>비추천수</th>
		</tr>
 			<c:forEach var="Rep" items="${repList}">
		<tr>
			<td><c:out value="${Rep.rep_id }"/></td>  
			<td><c:out value="${Rep.mem_nickname }"/></td>
			<td><c:out value="${Rep.rep_content }"/></td>
			<td><fmt:formatDate value="${Rep.rep_regdate}" pattern="yyyy년M월d일  hh시mm분"/>
			<td><input type="submit" value="추천" formaction="">${Rep.rep_good }</td>
			<td><input type="submit" value="비추천"   formaction="">${Rep.rep_bad }</td>
			<td><input type="submit" value="답글"    formaction=""></td>
			<td><input type="submit" value="댓글삭제" formaction=""></td>
		</tr>
			</c:forEach> 
	</table>
</form>
</body>
</html>