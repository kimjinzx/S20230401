<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../preset.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>신청서</title>
 <style>
      body {
        margin: 0px;
      }
      div {
        margin: 10px;
        padding: 10px;
        border: 10px solid #D9E5FF;
        width: 500px;
      }
      .box1 {
        box-sizing: content-box;
      }
      .box2 {
        box-sizing: border-box;
      }
</style>
<script type="text/javascript">
/* 	if(document.getElementsByName("agree").checked!=true){
		alert("해당 내용에 동의하셔야 신청 및 참여가능합니다.");
		f.agree.focus();
		return false;
	} */
	
	/* if(!$('input[name="agree"]').attr('checked')){
		alert("해당 내용에 동의하셔야 신청 및 참여가능합니다.");
		$('input[name="agree"]').focus();
		return false;
	} */	

	
</script>    
</head>
<body>
<form method="post"></form>
	
	<div class="box1">
    	<h3>주의사항</h3>
    	회원간의 범죄 및 사기에 대한 신고처리를 담당해 해당유저의 권한을 제어합니다
    	⊙법률 제18572호
		소비자생활협동조합법 
		제1조(목적) 이 법은 상부상조의 정신을 바탕으로 한 소비자들의 상호 간 협동에 기반하여 물품ㆍ용역ㆍ시설 등의 공동구매와 이용, 
		판매를 자주ㆍ자립ㆍ자치적으로 수행하는 생활협동조합활동을 촉진함으로써 조합원의 소비생활 향상과 국민의 복지 및 생활문화 향상에 이바지함을 목적으로 한다. (개정 2021. 12. 7.)
		“조합”이란 제1조의 목적을 달성하기 위하여 이 법에 따라 설립된 소비자생활협동조합을 말한다.<p>
		<strong>아래의 약관 내용을 동의하시면 신청하기를 눌러주세요</strong> 
    	<ul>
    		<li>ShareGo는 범죄 및 사기에 대한 손해배상을 청구하지 않습니다</li>
    		<li>불법 거래 및 사기행위를 행하거나 동참할 시 </li>
    		<li>신청내용에 대한 개인정보 수집 동의</li>
    	</ul>
	</div>
    
    <div class="box2">
    	<strong>상품 확인</strong><br>
    	작성자 : ${detail.mem_image} ${detail.mem_nickname }(${detail.mem_username })<br>
    	가격 : ${detail.trd_cost }원<br>
    	지역 : ${detail.reg_name } ${detail.trd_loc }<br>   <!-- + 상품사진 -->
    	인원 : ${detail.trd_max }명 (작성자 제외)<br>
    </div>
	
	<div>
<form name="frm" method="post">
		<span>위 내용에 모두 동의하십니까?</span>
		<input type="checkbox" id="agree" name="agree" required="required">
		
	

	<input type="hidden" name="trd_id" value="${article.trd_id }">
	<input type="hidden" name="brd_id" value="${article.brd_id }">
	<input type="hidden" name="art_id" value="${article.art_id }">
	
	<input type="submit" value="취소" 	onclick="javascript:self.close();">
	<input type="submit" value="신청하기" formaction="${pageContext.request.contextPath }/dutchpay/ApplyInsert">
</form>
	</div>
</body>
</html>
