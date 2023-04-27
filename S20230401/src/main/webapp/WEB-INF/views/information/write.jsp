<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<script type="text/javascript">
function writeCheck()
  {
   var form = document.write;
   
   if( !form.title.value )   
   {
    alert( "제목을 적어주세요" ); 
    form.title.focus();   
    return;
   
  if( !form.tag.value )
   {
    alert( "태그을 적어주세요" );
    form.tag.focus();
    return;
   }
 
  if( !form.memo.value )
   {
    alert( "내용을 적어주세요" );
    form.memo.focus();
    return;
   }
 
  form.submit();
  }
 </script>
</head>
<body>
<form action = "${pageContext.request.contextPath }/board/information/insert" method = "post" id = "write">
<table>
  <tr>
   <td>
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
     <tr style="background:url('img/table_mid.gif') repeat-x; text-align:center;">
      <td width="5"><img src="img/table_left.gif" width="5" height="30" /></td>
      <td>게시판 작성</td>
      <td width="5"><img src="img/table_right.gif" width="5" height="30" /></td>
     </tr>
    </table>
    	<select name="brd_id">
    		<option disabled = "disabled">=====선택=====</option>
    		<option value="1410">동네정보</option>
    		<option value="1420">구매정보</option>
    		<option value="1430">신규정보</option>
    		<option value="1440">지역정보</option>
    	</select>
   <table>
     <tr>
      <td>&nbsp;</td>
      <td align="center">제목</td>
      <td><input name="art_title" size="50" maxlength="100"></td>
      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">태그</td>
      <td><input name="art_tag1" size="50" maxlength="50"></td>
      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
     
     
     
    <tr>
      <td>&nbsp;</td>
      <td align="center">내용</td>
      <td><textarea name="art_content" cols="50" rows="13"></textarea></td>
      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
     <tr align="center">
      <td>&nbsp;</td>
      <td colspan="2">
      <td>&nbsp;</td>
     </tr>
    </table>
   </td>
  </tr>
 </table>
</form>
<button type = "submit" form = "write">등록</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath }/board/information?category=1400';">취소</button>
</body>
</html>
