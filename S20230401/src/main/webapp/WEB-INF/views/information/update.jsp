<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<form action="${pageContext.request.contextPath }/board/information/modify" method = "post" id = "modify">
</form>
<%-- <table>
  <tr>
   <td>
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
     <tr style="background:url('img/table_mid.gif') repeat-x; text-align:center;">
      <td width="5"><img src="img/table_left.gif" width="5" height="30" /></td>
      <td>게시글 수정</td>
      <td width="5"><img src="img/table_right.gif" width="5" height="30" /></td>
     </tr>
    </table>
   <table>
     <tr>
	      <td>&nbsp;</td>
	      <td align="center">제목</td>
	      <td>
	      	<input name="art_title" size="50" maxlength="100" value="${art_title}">
	      </td>
	      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     		<td colspan="4"></td>
     </tr>
    
    <tr>
      <td>&nbsp;</td>
      <td align="center">태그1</td>
       <td>
      	<input name="art_tag1" size="15" maxlength="15" value="${art_tag1 }">
      </td>      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">태그2</td>
       <td>
      	<input name="art_tag2" size="15" maxlength="15" value="${art_tag2 }">
      </td>      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">태그3</td>
       <td>
      	<input name="art_tag3" size="15" maxlength="15" value="${art_tag3 }">
      </td>      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">태그4</td>
       <td>
      	<input name="art_tag4" size="15" maxlength="15" value="${art_tag4 }">
      </td>      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">태그5</td>
       <td>
      	<input name="art_tag5" size="15" maxlength="15" value="${art_tag5 }">
      </td>      <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
     
     
     <tr>
	      <td>&nbsp;</td>
	      <td align="center">내용</td>
      <td>
      	  <textarea name="art_content" cols="50" rows="13">${art_content}</textarea>
      </td>
      	  <td>&nbsp;</td>
     </tr>
     <tr height="1" bgcolor="#dddddd">
     	<td colspan="4"></td>
     </tr>
     <tr align="center">
      <td>&nbsp;</td>
      <td colspan="2">
 <td>&nbsp;</td>
     </tr>
    </table> --%>
      <div id="wrap" >
        <h1>게시글 수정</h1>
            <table>
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="art_title" value="${art_title}"></td>
                </tr>
                <tr>
                    <th>태그1</th>
                    <td><input type="text" name="art_tag1" value="${art_tag1}"></td>
                </tr>
                <tr>
                    <th>태그2</th>
                    <td><input type="text" name="art_tag2" value="${art_tag2}"></td>
                </tr>
                <tr>
                    <th>태그3</th>
                    <td><input type="text" name="art_tag3" value="${art_tag3}"></td>
                </tr>
                <tr>
                    <th>태그4</th>
                    <td><input type="text" name="art_tag4" value="${art_tag4}"></td>
                </tr>
                <tr>
                    <th>태그5</th>
                    <td><input type="text" name="art_tag5" value="${art_tag5}"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea rows="15" cols="70" name="art_content">${art_content}</textarea></td>
                </tr>
            </table>
    	<button type="submit" form="modify">수정하기</button>
    	<button onclick="window.history.back()">뒤로가기</button>
<!-- </table> -->
</div>
</body>
</html>