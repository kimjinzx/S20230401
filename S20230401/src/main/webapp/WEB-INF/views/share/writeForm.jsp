<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	
	// 첫번째 select박스 선택시 실행
	function firstSelect(){
		let locSelect = $('#location-firstSelect').val();
		console.log(locSelect);
		
		$.ajax({
			url:'${pageContext.request.contextPath}/board/share/selectRegion',
			data:{'reg_id':locSelect},
			success: function(data){
				console.log(data);
				
				let options = '';
				for(let i=0; i<data.length; i++){
					options += '<option value="'+data[i].reg_id+'">'+data[i].reg_name+'</option>';
				}
				// 지역 소분류의 옵션을 추가하고, name 속성 부여
				$('#location-secondSelect').append(options);
			}
		});
	}
	
	// 두번째 select박스 선택시
	function secondSelect(){
		// 지역 대분류의  name 속성 제거 소분류 name 추가
		$('#location-firstSelect').removeAttr('name');
		$('#location-secondSelect').attr('name', 'trade.reg_id');
	}
</script>
</head>
<body>
	<div class="container">
		<h1>글쓰기</h1>

		<div style="text-align: center;">
			<form action="${pageContext.request.contextPath}/board/share/writeArticleForm" method="post">
				<input type="hidden" 	name="category" 		value="${category}">
				<input type="hidden" 	name="brd_id" 			value="${category}">
			<!-- 임시 기본값 저장 -->
				<input type="hidden" 	name="trade.trd_status" value="401">
				<input type="hidden" 	name="trade.trd_cost" 	value="0">
				<input type="hidden" 	name="art_good" 		value="0">
				<input type="hidden" 	name="art_bad" 			value="0">
				<input type="hidden" 	name="art_read" 		value="0">
				<input type="hidden" 	name="isdelete" 		value="0">
				
				<div class="artigle_tag">
					<span>
						태그1 <input type="text" 	name="art_tag1" value="태그1">
						태그2 <input type="text" 	name="art_tag2" value="태그2">
						태그3 <input type="text" 	name="art_tag3" value="태그3">
						태그4 <input type="text" 	name="art_tag4" value="태그4">
						태그5 <input type="text" 	name="art_tag5" value="태그5">
					</span>
				</div>
				
				<div class="article_location">
					<!-- 대분류 -->
					지역제한
					<select id="location-firstSelect" name="trade.reg_id" onchange="firstSelect()">
						<option value="">지역을 선택하세요</option>
						<c:forEach var="region" items="${regionList}">
							<option id="region-parent" value="${region.reg_id}">${region.reg_name}</option>
						</c:forEach>
					</select>
					
					<!-- 소분류 -->
					<select id="location-secondSelect" onchange="secondSelect()">
						<option value="">선택</option>
					</select>
				</div>
				<br>

				거래지역<input type="text" name="trade.trd_loc" value="이대" required="required"><br>
				최대 거래인원<input type="number" name="trade.trd_max" value="2" required="required"><br>
 				마감일<input type="date" name="trd_endDate" required="required"><br>
				
				성별제한
				<select name="trade.trd_gender">
					<option value="">제한 없음</option>
					<option value="201">남자</option>
					<option value="202">여자</option>
				</select>
				<br>
				최소나이<input type="number" name="trade.trd_minage" value="10"><br>
				최대나이<input type="number" name="trade.trd_maxage" value="30"><br>
				
				제목<input type="text" 	name="art_title" placeholder="제목 입력" required="required"><br>
				내용<textarea name="art_content" rows="30" cols="50" required="required">
				</textarea><br>
				

				<label>공지 여부<input type="checkbox" name="art_isnotice" value="1"></label><br>
				
				<input type="submit" value="작성">
				<input type="reset" value="취소">
			</form>
		</div>
	</div>
</body>
</html>