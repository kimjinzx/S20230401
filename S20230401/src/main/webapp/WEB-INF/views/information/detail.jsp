<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>
	#art-tags > li {
	  float:left;
	  margin:1px;
	  display:block;
	  padding:2px;
	}
</style>
</head>
<script type="text/javascript">

//작동 안함 신고하기 버튼 클릭시 예 아니요 구현
/* $(function() {
	$("input[id$=report]").click(function(){
		if(!confirm('신고하시겠습니까?')== false) return false;
			return true;
	});
}); */
function functionAlert(){
	alert("신청완료 되었습니다");
}

function functionAlert2(){
	alert("관심 등록 되었습니다");
}


 </script>
<body>

<table>
  <tr>
   <td>
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
     <tr style="background:url('img/table_mid.gif') repeat-x; text-align:center;">
      <td width="5"><img src="img/table_left.gif" width="5" height="30" /></td>
      <td>게시글</td>
      <td width="5"><img src="img/table_right.gif" width="5" height="30" /></td>
     </tr>
    </table>
	<button type="button" onclick="location.href='${pageContext.request.contextPath}/board/information/update?art_id=${art_id }&brd_id=${brd_id }';">수정하기</button>
	<button onclick="location.href='${pageContext.request.contextPath }/board/information?category=1400';">목록</button>
	<table>
     <tr>
	      <td>&nbsp;</td>
	      <td align="center">글번호</td>
	      <td>
	      	<input name="artId" size="50" maxlength="100" value="${art_id}">
	      </td>
	      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     		<td colspan="4"></td>
     </tr>
     
     
     <tr>
	      <td>&nbsp;</td>
	      <td align="center">게시판 번호</td>
	      <td>
	      	<input name="brdId" size="50" maxlength="100" value="${brd_id}">
	      </td>
	      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     		<td colspan="4"></td>
     </tr>
     
     
     <tr>
	      <td>&nbsp;</td>
	      <td align="center">제목</td>
	      <td>
	      	<input name="title" size="50" maxlength="100" value="${art_title}">
	      </td>
	      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     		<td colspan="4"></td>
     </tr>


	<tr>
	  <td>&nbsp;</td>
	  <td align="center">작성일</td>
	  <td>
	    <fmt:formatDate value="${art_regdate}" pattern="yyyy-MM-dd"/>
	  </td>
	  <td>&nbsp;</td>
	</tr>
	<tr height="1" bgcolor="#dddddd">
	  <td colspan="4"></td>
	</tr>
     
     
     <tr>
      <td>&nbsp;</td>
      <td align="center">작성자</td>
      <td>
      	<input name="nickname" size="50" maxlength="50" value="${mem_nickname }">
      </td>
      <td>&nbsp;</td>
     </tr>
      <tr height="1" bgcolor="#dddddd">
      		<td colspan="4"></td>
      </tr>
      
      
    <tr>
      <td>&nbsp;</td>
      <td align="center">태그</td>
      <td>
      	<ul id="art-tags">
      		<c:if test="${art_tag1 != null}"><li>#${art_tag1}</li></c:if>
      		<c:if test="${art_tag2 != null}"><li>#${art_tag2}</li></c:if>
      		<c:if test="${art_tag3 != null}"><li>#${art_tag3}</li></c:if>
      		<c:if test="${art_tag4 != null}"><li>#${art_tag4}</li></c:if>
      		<c:if test="${art_tag5 != null}"><li>#${art_tag5}</li></c:if>
      	</ul>
      </td>
      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
     
     
     <tr>
	      <td>&nbsp;</td>
	      <td align="center">내용</td>
      <td>
      	  <textarea name="content" cols="50" rows="13">${art_content}</textarea>
      </td>
      	  <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>

     <tr align="center">
      <td>&nbsp;</td>
      <td colspan="2">
       <input type=button onclick="report" style="float:right" value="신고하기">
       <input type=button onclick="functionAlert2()" style="float:right" value="관심">
       <input type=button onclick="functionAlert()" style="float:right" value="신청">
      <td>&nbsp;</td>
     </tr>
    </table>
   </td>
  </tr>
 </table>
</body>
</html>