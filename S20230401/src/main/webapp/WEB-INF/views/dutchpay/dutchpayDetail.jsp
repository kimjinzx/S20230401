<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/preset.jsp" %>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지 ▒ ShareGo</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/initializer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/layout.js"></script>
<script type="text/javascript">
   

</script>
<link href="https://unpkg.com/sanitize.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/preference.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/presets.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/layout.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share/article.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	
	$(() => {
		$('.article_content').find('img').error(e => {
			$(e.target).closest('img').attr('src', `${contextPath}/image/ShareGo_Not_Found_Image.png`);
		});
		$('.board-toggle').click(e => {
			let parent = $(e.target).closest('.board-summary');
			let children = parent.find('.board-summary-part');
			children.toggle();
		});
	});
	
	$(document).ready(()=>{
	    $('#btns-show, #btns-hide').click(e=>{
	        $(e.target).closest('.reply-list')
	        .find('#btns-show').toggle().end()
	        .find('#btns-hide').toggle().end()
	        .find('.reply-detail').toggle().end();
	    });
	});
	
	
	
	
	function goApplyBtn(my_mem_id, trd_id, brd_id, art_id,p_mem_id) {
  	  
  	  $.ajax({
  				url:"<%=context%>/board/dutchpay/dutchpayDetailYN",
  				data: {mem_id : my_mem_id,
  					   trd_id : trd_id,
  					   brd_id : brd_id,
  					   art_id : art_id},
  				type:'POST',
  				dataType:'text',
  				success:function(data){
  					if(my_mem_id == p_mem_id){
  						alert('작성자는 신청할 수 없습니다.');
  					}else if(data == '1'){
  						alert('이미 신청하셨습니다.');
  						}else{
	  						var popUrl = "/board/joinForm?brd_id=" + brd_id + "&art_id=" + art_id;
	  				        var popOption = "width=700px,height=700px,top=100px,left=400px";
	  				          window.open(popUrl, "신청서", popOption);
	  					}
	  				},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  				}
	  	  		});
	  		  } 

	function goDelete(p_brd_id, p_art_id) {
		  
		  $.ajax({
					url:"<%=context%>/board/dutchpay/dutchpayDelete",
					data: {brd_id : p_brd_id,
						   art_id : p_art_id},
					type:'POST',
					dataType:'text',
					success:function(data){
						if(confirm("게시글을 삭제하시겠습니까?")){
							alert('삭제되었습니다.');
							location.href="/board/dutchpay?category="+p_brd_id;
						}
					}
		  		});
			  }
	
	function goApplyCancel(p_brd_id, p_art_id, p_trd_id, p_mem_id) {
		 if (confirm("신청을 취소하시겠습니까?") == true){    
			location.href="/board/dutchpay/applyCancel?brd_id="+p_brd_id+"&art_id="+p_art_id+"&trd_id="+p_trd_id+"&mem_id="+p_mem_id;
		 }else{   
		     return false;
		 }
		}
	
	function goJoinCancel(p_brd_id, p_art_id, p_trd_id, p_mem_id) {
		 if (confirm("현재 참가중인 활동을 취소하시겠습니까?") == true){    
				location.href="/board/dutchpay/joinCancel?brd_id="+p_brd_id+"&art_id="+p_art_id+"&trd_id="+p_trd_id+"&mem_id="+p_mem_id;
		 }else{   
		     return false;
		 }
		}
	
	function goJoinAccept(p_trd_id, p_mem_id, p_brd_id, p_art_id) {
		if (confirm("신청을 수락 하시겠습니까?") == true){    
			location.href="/board/dutchpay/JoinAccept?trd_id="+p_trd_id+"&mem_id="+p_mem_id+"&brd_id="+p_brd_id+"&art_id="+p_art_id;
			 }else{   
			     return false;
			 }
	} 
	
	function goJoinDeny(p_trd_id, p_mem_id, p_brd_id, p_art_id) {
		if (confirm("신청을 거절 하시겠습니까?") == true){    
		location.href="/board/dutchpay/JoinDeny?trd_id="+p_trd_id+"&mem_id="+p_mem_id+"&brd_id="+p_brd_id+"&art_id="+p_art_id;
		 }else{   
		     return false;
		 }
	}
	
	function goReport(p_brd_id, p_art_id, p_report_id) {
		if (confirm("해당 게시글을 신고하시겠습니까?") == true){    
			window.open("/board/reportForm?brd_id=" + p_brd_id + "&art_id=" + p_art_id + "&report_id=" + p_report_id, "신고양식", "width=700px,height=500px,top=100px,left=400px");
		}else{   
		     return false;
		 }
	}
	
	function goreplyDelete(p_brd_id, p_art_id, p_rep_id) {
		if (confirm("댓글을 삭제하시겠습니까?") == true){    
		location.href="/board/dutchpay/replyDelete?brd_id="+p_brd_id+"&art_id="+p_art_id+"&rep_id="+p_rep_id;
		 }else{   
		     return false;
		 }
	}
	
	
	<%-- function goreplyUpdate(p_brd_id, p_art_id, p_rep_id) {
	  
	  $.ajax({
				url:"<%=context%>/board/dutchpay/replyUpdate",
				data: {mem_id : my_mem_id,
					   trd_id : trd_id,
					   brd_id : brd_id,
					   art_id : art_id},
				type:'POST',
				dataType:'text',
				success:function(data){
					if(confirm("댓글을 수정하시겠습니까?")){
						alert('수정되었습니다.');
					},
					error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				}
	  		});
		  } --%>
	
	function goFavorite(p_brd_id, p_art_id, p_mem_id) {
	
		$.ajax({
			url:"<%=context%>/board/dutchpay/favoriteInsertYN",
			data: {brd_id : p_brd_id,
				   art_id : p_art_id,
				   mem_id : p_mem_id},
			type:'POST',
			dataType:'text',
			success:function(data){
				if(data == '1'){
					
					alert('이미 등록하셨습니다.');
					
				}else if(confirm("해당 게시글을 찜 하시겠습니까?")){  
					
					$.ajax({
						url:"<%=context%>/board/dutchpay/favoriteInsert",
						data: {brd_id : p_brd_id,
							   art_id : p_art_id,
							   mem_id : p_mem_id},
						type:'POST',
						dataType:'text',
						success:function(data){
							alert("찜 목록 추가되었습니다.");	
						},
						error:function(request,status,error){
					        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					    }
					});
					
				}else {
			        return false;	
				}
			}
		});
		}
	
	
	function goArtGood(p_brd_id, p_art_id) {
	 	  
	 	  $.ajax({
	 				url:"<%=context%>/board/dutchpay/artGood",
	 				data: {brd_id : p_brd_id,
	 					   art_id : p_art_id},
	 				type:'POST',
	 				dataType:'text',
	 				success:function(data){
	 						alert('추천되었습니다.');
	 				}
	 	  		});
	 		  } 
	
	function goArtBad(p_brd_id, p_art_id) {
	 	  
	 	  $.ajax({
	 				url:"<%=context%>/board/dutchpay/artBad",
	 				data: {brd_id : p_brd_id,
	 					   art_id : p_art_id},
	 				type:'POST',
	 				dataType:'text',
	 				success:function(data){
	 						alert('비추천되었습니다.');
	 				}
	 	  		});
	 		  }
	
	function goreplyGood(p_brd_id, p_art_id, p_rep_id) {
	 	  
	 	  $.ajax({
	 				url:"<%=context%>/board/dutchpay/repGood",
	 				data: {brd_id : p_brd_id,
	 					   art_id : p_art_id,
	 					   rep_id : p_rep_id},
	 				type:'POST',
	 				dataType:'text',
	 				success:function(data){
	 						alert('추천되었습니다.');
	 				}
	 	  		});
	 		  }
	
	function  goreplyBad(p_brd_id, p_art_id, p_rep_id) {
	 	  
	 	  $.ajax({
	 				url:"<%=context%>/board/dutchpay/repBad",
	 				data: {brd_id : p_brd_id,
	 					   art_id : p_art_id,
	 					   rep_id : p_rep_id},
	 				type:'POST',
	 				dataType:'text',
	 				success:function(data){
	 						alert('비추천되었습니다.');
	 				}
	 	  		});
	 		  }
	
	
	function goCompleted(p_trd_id) {
		
		$.ajax({
				url:"<%=context%>/board/dutchpay/payCompleted",
				data: {trd_id : p_trd_id},
				type:'POST',
				dataType:'text',
				success:function(data){
					if (confirm("거래를 완료 하시겠습니까?") == true){    
					 }else{   
					     return false;
					 }
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
  				}
	  		});
		  } 

/* 	function completed() {
		 if (confirm("거래를 완료 하시겠습니까?")){    
		     document.dutchpay.submit();
		 }else{   
		     return false;
		 }
		}
 */		

</script>
</head>
<body>
	<header>
		<div id="usernav">
			<a href="">이용 약관</a>
			<a href="">개인정보 취급 방침</a>
		</div>
		<div id="topbar">
			<div id="logo-div" class="full-height" style="display: flex; justify-content: flex-start; align-items: center; padding: 0 10px;">
				<a id="logo" class="full-height" href="${pageContext.request.contextPath}/">
					<div style="width: auto; display: flex; flex-direction: column; justify-content: center; align-items: flex-end;">
						<img src="${pageContext.request.contextPath}/image/ShareGo_Img.png" style="height: 30px;">
						<span style="font-size: 24px; font-weight: 900; margin: -5px 0 0 0;">ShareGo</span>
					</div>
				</a>
			</div>
			<div id="topmenu">
				<a class="adv-hover menuitem" href="${pageContext.request.contextPath}/board/together?category=1000">함께해요</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="${pageContext.request.contextPath}/board/dutchpay?category=1100">같이사요</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="${pageContext.request.contextPath}/board/share?category=1200">나눔해요</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="${pageContext.request.contextPath}/board/community?category=1300">커뮤니티</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="${pageContext.request.contextPath }/board/information?category=1400">정보공유</a>
				<div class="menu-separator"></div>
				<a class="adv-hover menuitem" href="${pageContext.request.contextPath}/board/customer?category=1500">고객센터</a>
				<div id="dropdown">
					<div style="width: 200px;"></div>
					<div class="submenu-items">
						<div class="submenu">
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/together?category=1010">밥 / 카페</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/together?category=1020">스포츠 / 운동</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/together?category=1030">쇼핑</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/together?category=1040">문화생활</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/together?category=1050">취미생활</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/together?category=1060">기타</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/dutchpay?category=1110">식료품</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/dutchpay?category=1120">의류 / 잡화</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/dutchpay?category=1130">생활용품</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/dutchpay?category=1140">해외배송</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/dutchpay?category=1150">기타</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/share?category=1210">식품</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/share?category=1220">패션 / 잡화</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/share?category=1230">가전 / 가구</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/share?category=1240">기타</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/community?category=1310">일상수다</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/community?category=1320">자랑하기</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/community?category=1330">홍보하기</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/community?category=1340">질문 / 요청</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath }/board/information?category=1410">동네정보</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath }/board/information?category=1420">구매정보</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath }/board/information?category=1430">신규점포</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath }/board/information?category=1440">지역활동</a>
						</div>
						<div class="submenu">
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/customer?category=1510">공지</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/customer?category=1520">Q&A</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/customer?category=1530">이벤트</a>
							<a class="submenuitem adv-hover" href="${pageContext.request.contextPath}/board/customer?category=1540">문의 / 건의</a>
						</div>
					</div>
					<div style="width: 200px;"></div>
				</div>
			</div>
			<div id="top-right">
				<!-- <button id="viewMode">
					<div id="viewModeButton"></div>
				</button> -->
				<div></div>
				<div class="popup-group">
					<c:choose>
						<c:when test="${memberInfo == null }">
							<button id="login" class="togglePopup init-button">
								<svg width="40" height="40" viewBox="0 0 512 512" style="margin: 5px;">
									<path d="M258.9 48C141.92 46.42 46.42 141.92 48 258.9c1.56 112.19 92.91 203.54 205.1 205.1 117 1.6 212.48-93.9 210.88-210.88C462.44 140.91 371.09 49.56 258.9 48zm126.42 327.25a4 4 0 01-6.14-.32 124.27 124.27 0 00-32.35-29.59C321.37 329 289.11 320 256 320s-65.37 9-90.83 25.34a124.24 124.24 0 00-32.35 29.58 4 4 0 01-6.14.32A175.32 175.32 0 0180 259c-1.63-97.31 78.22-178.76 175.57-179S432 158.81 432 256a175.32 175.32 0 01-46.68 119.25z"/>
									<path d="M256 144c-19.72 0-37.55 7.39-50.22 20.82s-19 32-17.57 51.93C191.11 256 221.52 288 256 288s64.83-32 67.79-71.24c1.48-19.74-4.8-38.14-17.68-51.82C293.39 151.44 275.59 144 256 144z"/>
								</svg>
							</button> <!--  1 -->
							<div id="login-popup" class="popup-window">
								<button id="login-button" class="subtheme-button adv-hover" onclick="location.href = '${pageContext.request.contextPath }/login';">
									로그인
								</button>
							</div>
						</c:when>
						<c:otherwise>
							<button id="userInfo" class="togglePopup adv-hover">
								<div id="user-profile">
									<img src="${pageContext.request.contextPath }/uploads/profile/${memberInfo.mem_image }" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/abstract-user.svg';">
								</div>
							</button>
							<div id="userInfo-popup" class="popup-window">
								<div>
									<div style="display: flex; justify-content: flex-start; align-items: center; margin-bottom: 10px; padding: 10px; border-bottom: 2px solid var(--subtheme);">
										<div style="width: 75px; height: 75px; border-radius: 50%; margin-right: 10px; overflow: hidden; box-shadow: 0 2.5px 2.5px var(--theme-font); background-color: white;">
											<img style="object-fit: cover;" class="full-width full-height" src="${pageContext.request.contextPath }/uploads/profile/${memberInfo.mem_image }" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/anonymous.png';">
										</div>
										<div style="display: flex; flex-direction: column; justify-content: space-evenly; align-items: flex-start;">
											<h2 style="margin: 0; margin-left: 5px;">${memberInfo.mem_nickname }</h2>
											<div style="display: flex; justify-content: flex-start; align-items: center;">
												<div style="display: flex; justify-content: center; align-items: center; flex-grow: 1;">
													<svg viewBox="596.9167 0.0194 1565.0834 1639.8086" style="fill: var(--confirm); width: 16px; height: 16px; margin: 0 5px;">
														<!-- https://freesvg.org/thumbs-up-silhouette -->
														<path d="M1520.5629,60.7246c9.2529,0,22.9001-2.2927,31.0228,1.2784c4.0228,1.7685,10.0901,1.592,14.4762,2.7188 c5.8456,1.5018,11.6852,2.95,17.423,4.8376c19.8309,6.524,38.4539,17.5962,54.3069,31.1189 c15.4873,13.2107,28.811,28.8823,38.5651,46.7913c10.3126,18.9348,15.6564,39.2017,20.9418,59.9346 c5.438,21.3307,6.3975,44.4503,6.5536,66.3782c0.1575,22.0935-1.3568,45.1182-6.2789,66.6433 c-4.7166,20.626-9.5502,40.6268-17.2726,60.4215c-8.1235,20.8223-19.2517,40.6216-29.2167,60.5945 c-9.6312,19.3036-18.8459,38.775-28.1715,58.2219c-9.0332,18.8382-17.9552,38.7832-24.1283,58.7702 c-6.0906,19.7205-11.4923,40.3191-10.5479,61.155c0.3005,6.6298,1.0413,21.0134,7.0928,25.2928 c3.0446,2.1529,8.7776,0.9438,12.2129,0.8727c6.3768-0.1321,12.7501-0.3911,19.1221-0.6643 c46.1556-1.9804,92.4683-1.7981,138.6613-1.7421c45.6135,0.0554,91.168,0.327,136.7052,3.2466 c22.5062,1.4431,45.473,2.3863,67.7657,5.9072c20.1079,3.1758,39.5485,8.5963,55.7,21.558 c15.6963,12.5963,29.2531,29.2695,40.9388,45.5461c11.8752,16.5402,23.2302,34.9225,30.1526,54.1568 c3.2183,8.9421,6.8928,18.6378,7.8779,28.1332c1.1016,10.6202-0.9648,19.2577-6.2202,28.4807 c-10.1318,17.7805-24.4211,32.9326-35.489,50.126c-5.583,8.6729-9.6936,17.2821-8.1272,27.8564 c1.5747,10.6291,6.9783,20.636,12.1709,29.8923c9.8293,17.5203,21.5195,34.1235,30.4695,52.1269 c4.1145,8.2762,7.9856,17.6038,7.1602,27.0314c-0.873,9.9706-6.8962,18.98-12.7485,26.7786 c-12.3955,16.5182-27.3953,30.9061-39.7224,47.4825c-5.7537,7.7368-11.4421,16.1678-11.7715,26.1475 c-0.349,10.5802,2.4435,20.917,6.0121,30.7833c7.3593,20.3463,17.4034,40.3459,23.2218,61.1707 c6.0962,21.8181-0.4746,39.7147-13.5889,57.3821c-12.5927,16.9652-28.8911,30.9794-43.8359,45.7869 c-14.4075,14.2753-27.1533,28.6671-27.001,50.0262c0.078,10.9647,0.1594,21.9293,0.2654,32.8938 c0.1068,11.0503,1.4434,22.9386-1.6632,33.6997c-5.4886,19.0134-21.4039,36.1284-34.7365,50.1152 c-14.4594,15.1687-30.6368,28.7886-47.7722,40.8331c-17.229,12.1102-34.8148,20.5139-55.1942,25.6375 c-20.9213,5.2599-42.9412,7.8329-64.4313,9.6827c-90.5679,7.7957-181.6688,2.4065-272.3201-0.8159 c-44.7479-1.5908-89.6749-4.4669-134.2426-8.8455c-10.2728-1.0093-20.5367-1.4325-30.8199-2.1771 c-10.1438-0.7345-20.5139-3.3689-30.5206-5.2036
																	 c-21.0529-3.8597-42.129-7.6986-63.1381-11.7871 c-42.7938-8.328-85.7592-15.8619-128.62-23.8524c-21.6165-4.0298-43.25-7.9535-64.874-11.9396 c-10.5157-1.9385-21.0328-3.881-31.521-5.9642c-3.2131-0.6381-6.5777-1.077-7.1765-4.8949 c-0.9216-5.8757-0.113-12.4637-0.1102-18.4089c0.0895-185.7694,0.0862-371.5388,0.087-557.3082 c0.0001-11.146,0.0001-22.2924,0.0002-33.4384c0-2.7115-0.8728-7.2691,0.6964-9.6804c1.8351-2.8201,9.785-1.3683,12.4819-1.3834 c9.8492-0.0552,19.8955,0.8073,29.6094-1.1854c9.9312-2.0372,19.0519-6.6622,27.5282-12.1067 c34.0913-21.8972,59.651-57.7686,81.01-91.4886c23.2795-36.7521,43.9276-75.4404,62.1334-114.9429 c18.3373-39.788,34.3848-80.5327,51.8765-120.6971c17.0248-39.0923,48.2561-65.2728,80.8578-91.2554 c16.4827-13.1361,32.6666-26.548,46.3193-42.7003c13.8022-16.3293,24.9845-34.8267,33.7615-54.2959 c18.1166-40.1859,30.1417-84.3115,36.2227-127.9681c6.2136-44.6116,12.6005-89.2005,14.9655-134.2462 c0.3915-7.4563,1.6073-14.5817,7.3704-19.8748c2.4473-2.2476,5.3876-3.8947,8.4872-5.0599 C1513.9449,63.4004,1519.2778,63.2971,1520.5629,60.7246C1530.4342,60.7246,1519.7723,62.3071,1520.5629,60.7246z M654.1641,898.2689c0,113.5108,0,227.0218,0,340.5325c0,46.5209,0,93.0417,0,139.5626c0,23.2604,0,46.5209,0,69.7813 c0,12.0956,0,24.1909,0,36.2863c0,5.5826,0,11.165,0,16.7476c2.9518,0.6001,3.0689,7.4297,3.9023,9.6901 c5.8673,15.9106,20.7537,29.5836,36.6451,35.1024c9.34,3.2439,18.8117,3.2965,28.5798,3.3029 c11.0439,0.0074,22.0878,0.012,33.1318,0.0145c46.0164,0.0112,92.0328-0.0095,138.0493,0.0101 c19.0814,0.0082,36.9496-6.9194,49.56-21.6554c13.4414-15.7072,14.5669-34.699,14.5618-54.4558 c-0.012-46.6202-0.0196-93.2406-0.025-139.8608c-0.0109-93.2407-0.0132-186.4812-0.0248-279.7219 c-0.0057-46.3734,1.1533-92.9179-0.0865-139.2733c-0.5342-19.9727-7.4376-38.3519-23.3443-51.1132 c-15.4163-12.3679-33.5844-13.338-52.5065-13.3804c-46.3998-0.1041-92.7999-0.1375-139.1995-0.0057 c-20.9935,0.0595-42.7304-1.9652-61.1382,10.0648c-7.4174,4.8474-13.9482,11.0446-18.7111,18.5484 c-2.1867,3.4451-3.9904,7.1271-5.4059,10.9533C657.395,891.446,656.8574,897.8079,654.1641,898.2689 C654.1641,1099.2389,655.7422,897.9987,654.1641,898.2689z"/>
													</svg>
													<span style="font-size: 16px;">${memberInfo.his_good }</span>
												</div>
												<div style="display: flex; justify-content: center; align-items: center; flex-grow: 1;">
													<div style="width: 8px; height: 2px; background-color: var(--theme-font); margin: 0 4px;"></div>
													<span style="font-size: 16px;">${memberInfo.his_normal }</span>
												</div>
												<div style="display: flex; justify-content: center; align-items: center; flex-grow: 1;">
													<svg viewBox="596.9167 0.0194 1565.0834 1639.8086" style="fill: var(--warning); width: 16px; height: 16px; transform: rotate(180deg); transform-origin: 50% 50%; margin: 0 5px;">
														<!-- https://freesvg.org/thumbs-up-silhouette -->
														<path d="M1520.5629,60.7246c9.2529,0,22.9001-2.2927,31.0228,1.2784c4.0228,1.7685,10.0901,1.592,14.4762,2.7188 c5.8456,1.5018,11.6852,2.95,17.423,4.8376c19.8309,6.524,38.4539,17.5962,54.3069,31.1189 c15.4873,13.2107,28.811,28.8823,38.5651,46.7913c10.3126,18.9348,15.6564,39.2017,20.9418,59.9346 c5.438,21.3307,6.3975,44.4503,6.5536,66.3782c0.1575,22.0935-1.3568,45.1182-6.2789,66.6433 c-4.7166,20.626-9.5502,40.6268-17.2726,60.4215c-8.1235,20.8223-19.2517,40.6216-29.2167,60.5945 c-9.6312,19.3036-18.8459,38.775-28.1715,58.2219c-9.0332,18.8382-17.9552,38.7832-24.1283,58.7702 c-6.0906,19.7205-11.4923,40.3191-10.5479,61.155c0.3005,6.6298,1.0413,21.0134,7.0928,25.2928 c3.0446,2.1529,8.7776,0.9438,12.2129,0.8727c6.3768-0.1321,12.7501-0.3911,19.1221-0.6643 c46.1556-1.9804,92.4683-1.7981,138.6613-1.7421c45.6135,0.0554,91.168,0.327,136.7052,3.2466 c22.5062,1.4431,45.473,2.3863,67.7657,5.9072c20.1079,3.1758,39.5485,8.5963,55.7,21.558 c15.6963,12.5963,29.2531,29.2695,40.9388,45.5461c11.8752,16.5402,23.2302,34.9225,30.1526,54.1568 c3.2183,8.9421,6.8928,18.6378,7.8779,28.1332c1.1016,10.6202-0.9648,19.2577-6.2202,28.4807 c-10.1318,17.7805-24.4211,32.9326-35.489,50.126c-5.583,8.6729-9.6936,17.2821-8.1272,27.8564 c1.5747,10.6291,6.9783,20.636,12.1709,29.8923c9.8293,17.5203,21.5195,34.1235,30.4695,52.1269 c4.1145,8.2762,7.9856,17.6038,7.1602,27.0314c-0.873,9.9706-6.8962,18.98-12.7485,26.7786 c-12.3955,16.5182-27.3953,30.9061-39.7224,47.4825c-5.7537,7.7368-11.4421,16.1678-11.7715,26.1475 c-0.349,10.5802,2.4435,20.917,6.0121,30.7833c7.3593,20.3463,17.4034,40.3459,23.2218,61.1707 c6.0962,21.8181-0.4746,39.7147-13.5889,57.3821c-12.5927,16.9652-28.8911,30.9794-43.8359,45.7869 c-14.4075,14.2753-27.1533,28.6671-27.001,50.0262c0.078,10.9647,0.1594,21.9293,0.2654,32.8938 c0.1068,11.0503,1.4434,22.9386-1.6632,33.6997c-5.4886,19.0134-21.4039,36.1284-34.7365,50.1152 c-14.4594,15.1687-30.6368,28.7886-47.7722,40.8331c-17.229,12.1102-34.8148,20.5139-55.1942,25.6375 c-20.9213,5.2599-42.9412,7.8329-64.4313,9.6827c-90.5679,7.7957-181.6688,2.4065-272.3201-0.8159 c-44.7479-1.5908-89.6749-4.4669-134.2426-8.8455c-10.2728-1.0093-20.5367-1.4325-30.8199-2.1771 c-10.1438-0.7345-20.5139-3.3689-30.5206-5.2036
																	 c-21.0529-3.8597-42.129-7.6986-63.1381-11.7871 c-42.7938-8.328-85.7592-15.8619-128.62-23.8524c-21.6165-4.0298-43.25-7.9535-64.874-11.9396 c-10.5157-1.9385-21.0328-3.881-31.521-5.9642c-3.2131-0.6381-6.5777-1.077-7.1765-4.8949 c-0.9216-5.8757-0.113-12.4637-0.1102-18.4089c0.0895-185.7694,0.0862-371.5388,0.087-557.3082 c0.0001-11.146,0.0001-22.2924,0.0002-33.4384c0-2.7115-0.8728-7.2691,0.6964-9.6804c1.8351-2.8201,9.785-1.3683,12.4819-1.3834 c9.8492-0.0552,19.8955,0.8073,29.6094-1.1854c9.9312-2.0372,19.0519-6.6622,27.5282-12.1067 c34.0913-21.8972,59.651-57.7686,81.01-91.4886c23.2795-36.7521,43.9276-75.4404,62.1334-114.9429 c18.3373-39.788,34.3848-80.5327,51.8765-120.6971c17.0248-39.0923,48.2561-65.2728,80.8578-91.2554 c16.4827-13.1361,32.6666-26.548,46.3193-42.7003c13.8022-16.3293,24.9845-34.8267,33.7615-54.2959 c18.1166-40.1859,30.1417-84.3115,36.2227-127.9681c6.2136-44.6116,12.6005-89.2005,14.9655-134.2462 c0.3915-7.4563,1.6073-14.5817,7.3704-19.8748c2.4473-2.2476,5.3876-3.8947,8.4872-5.0599 C1513.9449,63.4004,1519.2778,63.2971,1520.5629,60.7246C1530.4342,60.7246,1519.7723,62.3071,1520.5629,60.7246z M654.1641,898.2689c0,113.5108,0,227.0218,0,340.5325c0,46.5209,0,93.0417,0,139.5626c0,23.2604,0,46.5209,0,69.7813 c0,12.0956,0,24.1909,0,36.2863c0,5.5826,0,11.165,0,16.7476c2.9518,0.6001,3.0689,7.4297,3.9023,9.6901 c5.8673,15.9106,20.7537,29.5836,36.6451,35.1024c9.34,3.2439,18.8117,3.2965,28.5798,3.3029 c11.0439,0.0074,22.0878,0.012,33.1318,0.0145c46.0164,0.0112,92.0328-0.0095,138.0493,0.0101 c19.0814,0.0082,36.9496-6.9194,49.56-21.6554c13.4414-15.7072,14.5669-34.699,14.5618-54.4558 c-0.012-46.6202-0.0196-93.2406-0.025-139.8608c-0.0109-93.2407-0.0132-186.4812-0.0248-279.7219 c-0.0057-46.3734,1.1533-92.9179-0.0865-139.2733c-0.5342-19.9727-7.4376-38.3519-23.3443-51.1132 c-15.4163-12.3679-33.5844-13.338-52.5065-13.3804c-46.3998-0.1041-92.7999-0.1375-139.1995-0.0057 c-20.9935,0.0595-42.7304-1.9652-61.1382,10.0648c-7.4174,4.8474-13.9482,11.0446-18.7111,18.5484 c-2.1867,3.4451-3.9904,7.1271-5.4059,10.9533C657.395,891.446,656.8574,897.8079,654.1641,898.2689 C654.1641,1099.2389,655.7422,897.9987,654.1641,898.2689z"/>
													</svg>
													<span style="font-size: 16px;">${memberInfo.his_bad }</span>
												</div>
											</div>
										</div>
									</div>
									<button style="width: 240px; height: 32px; font-size: 16px; font-weight: bold; border-radius: 5px; margin: 5px 10px;" class="theme-button" onclick="location.href = '${pageContext.request.contextPath }/user/mypage';">
										마이 페이지
									</button>
									<button style="width: 240px; height: 32px; font-size: 16px; font-weight: bold; border-radius: 5px; margin: 5px 10px; margin-bottom: 10px;" class="subtheme-button" onclick="location.href = '${pageContext.request.contextPath }/logout';">
										로그아웃
									</button>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</header>
	<aside id="leftside">
		
	</aside>
	<main>
		<div class="view-content">
			<!-- 게시글 -->
			<div class="view-article">
				<!-- 게시글의 정보 -->
				<div class="article-header" class="padding-0">
					<input type="hidden" id="article_id"name="art_id"    value="${detail.art_id}">
					<input type="hidden" id="brd_id"    name="brd_id"    value="${detail.brd_id}">
					<input type="hidden" id="mem_id"    name="mem_id"    value="${detail.mem_id}">
					
						<input type="hidden" name="art_id" value="${detail.art_id }">
						<input type="hidden" name="brd_id" value="${detail.brd_id }">
						<input type="hidden" name="mem_id" value="${memberInfo.mem_id}">
						<input type="hidden" name="brd_id" id="brd_id" value="${detail.brd_id }">
						<input type="hidden" name="art_id" id="art_id" value="${detail.art_id }">
						<input type="hidden" name="trd_id" value="${detail.trd_id }">
						<input type="hidden" name="mem_id" value="${detail.mem_id }">
						<input type="hidden" name="report_id" value="${detail.report_id }">
						<input type="hidden" id="login_authority"    name="login_authority"    value="${memberInfo.mem_authority}">
<%--  						<input type="hidden" name="category" value="${category}">
 --%> 						
						
						
					
					<!-- 카테고리 표시 -->
					<div class="article-category display-flex justify-content-space-between align-items-center">
						<span class="category-name">
							<a href="${pageContext.request.contextPath}/board/dutchpay?category=${detail.brd_id - (detail.brd_id % 100) }"><span style="color: rgba(var(--theme-font-rgb), 0.5);">같이사요</span></a>
							<span class="margin-hor-2_5px" style="color: rgba(var(--theme-font-rgb), 0.5);">&gt;</span>
							<a class="font-weight-bolder" style="color: var(--subtheme)" href="${pageContext.request.contextPath}/board/dutchpay?category=${detail.brd_id}">${detail.brd_name}</a>
							
						</span>
						<span class="only-for-member display-flex justify-content-flex-end align-items-center">
							<!-- 글 수정 삭제 -->
							<c:if test="${detail.mem_id == memberInfo.mem_id || memberInfo.mem_authority >= 108}">
								<button id="btns-artUpdate" class="adv-hover" onclick="location.href='${pageContext.request.contextPath }/board/dutchpay/dutchpayUpdateForm?art_id=${detail.art_id}&brd_id=${detail.brd_id}'">수정</button>
								<button id="btns-artDelete" class="adv-hover" onclick="goDelete(${detail.brd_id},${detail.art_id })">삭제</button>
							</c:if>
 							<button class="adv-hover" onclick="location.href='${pageContext.request.contextPath}/board/dutchpay?category=${detail.brd_id}'">목록</button>
						</span>
					</div>
				</div>
	         <!-- <form method="post"> -->
	         			<c:choose> 
							<c:when test="${memberInfo.mem_id == detail.mem_id}"> 
								<c:choose> 
									<c:when test="${detail.comm_id == 402}"> <!-- 거래상태가 402(거래진행중)일 때만 나타나는 거래완료하기 버튼 -->
										<button class="btns-action"  id="btns-completed"	onclick="goCompleted(${detail.trd_id})">거래완료</button><p> 				
									 		<%-- <input type="submit" value="거래완료" formaction="${pageContext.request.contextPath }/board/dutchpay/payCompleted"  	onclick="return completed()"> --%>
									 </c:when>
								</c:choose>
							</c:when>
						</c:choose>
			<!-- </form> -->
	         
						<%-- <c:choose> 
							<c:when test="${memberInfo.mem_id == detail.mem_id && detail.comm_id != 403  && detail.comm_id != 404}"> 
								<div class="CurrentStatus">
									<p><span>거래 상태 수정하기
										<select name="comm_id">
										<c:forEach var="PS" items="${payStatus }">
											<option value="${PS.comm_id }" ${PS.comm_id == detail.comm_id ? 'selected' : '' }>${PS.comm_value }</option>
										</c:forEach>
										</select>
									</span>
										<button class="btns-action"  id="btns-completed"	onclick="">거래중지</button><p>
										<input type="submit" value="확인"  formaction="${pageContext.request.contextPath }/board/dutchpay/payStatusPro"><br>
								</div>
							</c:when>
						</c:choose>   --%>
	         
	         
	         
	         
	         
	         
				<!-- 글 제목 및 상태 -->
				<div class="article-title" style="border: 2px solid rgba(128, 128, 128, 0.5); border-width: 2px 0;">
					<div class="title-status">
						<c:if test="${detail.comm_value != null}"><button class="btn font-weight-bold">${detail.comm_value}</button></c:if>
					</div>
					<div class="title-subject">
						<span class="font-size-20px font-weight-bold">${detail.art_title}</span>
					</div>
					<!-- 게시글 신고 -->
					<div class="modal-report">
						<c:if test="${not empty memberInfo}">
							<svg onclick="goReport(${detail.brd_id },${detail.art_id },${detail.report_id })" id="article-report" viewBox="0 0 512 512" width="30" height="30" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
						</c:if>
					</div>
				</div>
				
				<!-- 작성자 -->
				<div class="article-memberRow display-flex align-items-center" style="border-bottom: 1px solid rgba(128, 128, 128, 0.5);">
					<div class="user-profile-image-in-list">
						<img src="${pageContext.request.contextPath}/uploads/profile/${detail.mem_image}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/abstract-user.svg';"></span>
					</div>
					<div class="article-member" style="display: flex; align-items: center;">
						<div class="modal-report display-flex justify-content-flex-start align-items-center padding-0">
						<span id="member_nickname" class="font-weight-bolder margin-right-10px">${detail.mem_nickname}</span>
						<c:if test="${not empty memberInfo}">
							<svg id="member-report" viewBox="0 0 512 512" width="24" height="24" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
							<input type="hidden" id="member_id" value="${detail.mem_id}">
							<input type="hidden" id="memReport_id" value="${detail.report_id}">
						</c:if>
					   </div>
					</div>
					<div class="article-memberInfo" style="font-size: 14px; flex-grow: 1; text-align: right;">
						<span>추천 ${detail.art_good}</span>
						<span>비추천 ${detail.art_bad}</span>
						<span>댓글 ${detail.reply_count == null ? 0:detail.reply_count}</span>
						<span>조회수 ${detail.art_read}</span>
						<span>작성일 <fmt:formatDate value="${detail.art_regdate}" pattern="yy.MM.dd HH:mm:ss"/></span>
					</div>
				</div>
				
				<!-- 태그 출력 및 검색 -->
				<div class="article-info">
					<div class="view-tag padding-0">
						<form action="${pageContext.request.contextPath}/board/share/searchForm">
							<input type="hidden" name="category" value="${category}">
							<input type="hidden" name="brd_id" value="${brd_id}">
							<input type="hidden" name="search" value="articleTag">
							<c:forEach begin="1" end="5" varStatus="status">
								<c:set var="art_tag" value="art_tag${status.index}"/>
									<c:if test="${detail[art_tag] != null}">
										<button class="btns-tag" name="keyWord" value="${detail[art_tag]}">${detail[art_tag]}</button>
									</c:if>
							</c:forEach>
						</form>
					</div>
					
					<!-- 본문 내용 -->
					<div class="article_content" style="padding: 20px 10px;">
						${detail.art_content }
					</div>
					
					<!-- 거래 관련 내용 -->
					<div class="article-trade">
						<div class="share-trdHeader display-flex justify-content-space-between align-items-center">
							<span class="color-subtheme font-size-20px font-weight-bolder">${detail.trd_cost} 원 [배송비 포함]</span>
							<div class="display-flex justify-content-flex-end align-items-center">
								<button class="btn" type="button">개시일 : <fmt:formatDate value="${detail.art_regdate}" pattern="yy-MM-dd"/></button>
								<span class="color-theme-font font-weight-bold margin-hor-2_5px" style="color: rgba(var(--theme-font-rgb), 0.5);">~</span>
								<button>마감일 : <fmt:formatDate value="${detail.trd_enddate}" pattern="yy-MM-dd"/></button>
							</div>
						</div>
						<div class="share-trdContent display-flex flex-direction-column justify-content-flex-start" style="border-bottom: 1px solid rgba(128, 128, 128, 0.5);">
							<div class="display-flex justify-content-space-between align-items-center">
								<span><span class="color-subtheme font-weight-bolder margin-right-5px">지역제한</span>${detail.reg_name}</span>
								<span><span class="color-subtheme font-weight-bolder">상세장소</span>${detail.trd_loc}</span>
							</div>
							<div class="display-flex justify-content-space-between align-items-center">
								<span><span class="color-subtheme font-weight-bolder margin-right-5px">최대 인원</span>${detail.trd_max}명</span>
							</div>
						</div>
						
						<!-- 참가중인 회원 목록 -->
						<div class="share-userList">
							<h2>거래 참가자 명단</h2><hr />
							<c:forEach var="JL" items="${joinList}">
								<div class="userList-memberInfo">
									<div class="user-profile-image-in-list">
										<img src="${pageContext.request.contextPath}/uploads/profile/${JL.mem_image}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/abstract-user.svg';">
									</div>
									<div class="userList-details">
										<div class="display-flex justify-content-flex-start align-items-center padding-0">
											<h3 class="font-weight-bolder margin-0">${JL.mem_nickname}</h3>
											<span class="margin-left-10px" style="color: rgba(var(--theme-font-rgb), 0.5);"><span class="font-weight-bolder margin-right-5px" style="color: rgba(var(--subtheme-rgb), 0.5);">참여일</span><fmt:formatDate value="${JL.join_date}" pattern="yy-MM-dd HH:mm:ss"/></span>
										</div>
										<div class="modal-report display-flex justify-content-flex-start align-items-center padding-0">
											<span id="member_nickname" style="color: rgba(var(--theme-font-rgb), 0.5);">${JL.mem_username}</span>
											<c:if test="${not empty memberInfo}">
												<svg id="member-report" viewBox="0 0 512 512" width="24" height="24" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
											</c:if>
											<input type="hidden" id="member_id" name="mem_id" value="${JL.mem_id}">
											<input type="hidden" id="memReport_id" value="${JL.report_id}">
										</div>
									</div>
									<div class="userList-btns">
										<c:choose>
											<%-- <c:when test="${detail.mem_id == memberInfo.mem_id}">
												<button class="btns-action adv-hover" id="btns-drop">추방</button>
											</c:when> --%>
											<c:when test="${JL.mem_id == memberInfo.mem_id}">
												<button class="btns-action adv-hover" id="btns-joinCancel"  onclick="goJoinCancel(${detail.brd_id},${detail.art_id},${detail.trd_id},${memberInfo.mem_id})">취소</button>
											</c:when>
										</c:choose>
									</div>
								</div>
								<hr />
							</c:forEach>
							<c:if test="${empty joinList}"><p style="text-align: center;">아직 참가자가 없어요</p></c:if>
						</div>
						
						<!-- 거래 대기열 회원 목록 -->
						<div class="share-userList">
							<h2>거래 신청자 명단</h2><hr />
							<c:forEach var="WL" items="${waitList}" varStatus="status">
								<div class="userList-memberInfo">
									<div class="user-profile-image-in-list">
										<img src="${pageContext.request.contextPath}/uploads/profile/${WL.mem_image}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/abstract-user.svg';">
									</div>
									<div class="userList-details">
										<div class="display-flex justify-content-flex-start align-items-center padding-0">
											<h3 class="font-weight-bolder margin-0">${WL.mem_nickname}</h3>
											<span class="margin-left-10px" style="color: rgba(var(--theme-font-rgb), 0.5);"><span class="font-weight-bolder margin-right-5px" style="color: rgba(var(--subtheme-rgb), 0.5);">신청일</span><fmt:formatDate value="${WL.wait_date}" pattern="yy-MM-dd HH:mm:ss"/></span>
										</div>
										<div class="modal-report display-flex justify-content-flex-start align-items-center padding-0">
											<span id="member_nickname" style="color: rgba(var(--theme-font-rgb), 0.5);">${WL.mem_username}</span>
											<c:if test="${not empty memberInfo}">
												<svg id="member-report" viewBox="0 0 512 512" width="24" height="24" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
											</c:if>
											<input type="hidden" id="member_id" name="mem_id" value="${WL.mem_id}">
											<input type="hidden" id="memReport_id" value="${WL.report_id}">
										</div>
									</div>
									<div class="userList-btns">
										<c:choose>
											<c:when test="${detail.mem_id == memberInfo.mem_id}">
												<c:if test="${detail.trd_max ne joinList.size()}">
													<button class="btns-action" id="btns-accept" onclick="goJoinAccept(${detail.trd_id },${WL.mem_id},${detail.brd_id },${detail.art_id })">승인</button>
												</c:if>
												<button class="btns-action" id="btns-refuse" onclick="goJoinDeny(${detail.trd_id },${WL.mem_id},${detail.brd_id },${detail.art_id })">거절</button>
											</c:when>
											<c:when test="${WL.mem_id == memberInfo.mem_id}">
												<button class="btns-action" id="btns-waitCancel"  onclick="goApplyCancel(${detail.brd_id},${detail.art_id},${detail.trd_id},${memberInfo.mem_id})">취소</button>
											</c:when>
										</c:choose>
									</div>
								</div><hr />
							</c:forEach>
							<c:if test="${empty waitList}"><p style="text-align: center;">아직 신청자가 없어요</p></c:if>
						</div>
						
						<!-- 거래 신청, 찜 -->
						<div class="share-btns">
							<c:if test="${memberInfo != null}">
								<div class="btns-favorite">
									<c:choose>
										<c:when test="${favoriteListCount > 0}">
											<button class="btns-action" id="btns-favoriteDel">찜 취소</button>
										</c:when>
										<c:when test="${memberInfo.mem_id != null }"> 
										<%-- <c:when test="${favoriteListCount == 0}"> --%>
											<button class="btns-action" id="btns-favorite" onclick="goFavorite(${detail.brd_id },${detail.art_id },${memberInfo.mem_id})">찜</button>
										</c:when>
									</c:choose>
								</div>
								<div class="btns-trade">
									<c:choose>
 										 <c:when test="${memberInfo.mem_id != null }"> 
  										 <%-- <c:when test="${waitListCount == 0 && joinListCount == 0 && joinList.size() < detail.trd_max}"> --%> 
											<button class="btns-action" id="btns-apply"  onclick="goApplyBtn(${memberInfo.mem_id},${detail.trd_id},${detail.brd_id},${detail.art_id},${detail.mem_id})">신청</button>
										</c:when>
										<c:when test="${joinList.size() == detail.trd_max}">
											<button class="btns-action" id="btns-end">모집 완료</button>
										</c:when>
									</c:choose>
								</div>
							</c:if>
							<c:if test="${memberInfo == null}"><div class="login display-flex justify-content-center align-items-center"><a href="${pageContext.request.contextPath}/login"><h3 class="color-subtheme">로그인</h3></a><span> 후 이용 가능합니다</span></div></c:if>
						</div>
					</div>
				</div>
				
				<div class="article-body" style="border-bottom: 1px solid rgba(128, 128, 128, 0.5);">
					<!-- 추천 비추천 -->
					<div class="share-btns" id="btns-vote">
						<button class="btns-good" id="btns-good" onclick="goArtGood(${detail.brd_id },${detail.art_id })">추천 <span>${detail.art_good}</span></button>
						<button class="btns-good" id="btns-goodcancel" style="display: none; background-color: #0193F8;">추천 <span>${detail.art_good}</span></button>
						<button class="btns-bad" id="btns-bad" onclick="goArtBad(${detail.brd_id },${detail.art_id })">비추천 <span>${detail.art_bad}</span></button>
						<button class="btns-bad" id="btns-badcancel" style="display: none; background-color: red;">비추천 <span>${detail.art_bad}</span></button>
					</div>
				</div>
				
				<!-- 댓글 부분 -->      
				<div class="reply-list padding-hor-0">
					<div class="list-toggle display-flex justify-content-flex-start align-items-center padding-hor-0" style="padding-bottom: 10px; border-bottom: 1px solid rgba(128, 128, 128, 0.5);">
						<c:choose>
							<c:when test="${detail.reply_count > 0}">
								<span class="font-size-24px font-weight-bolder color-subtheme">댓글</span>
								<span class="font-size-14px font-weight-bold color-theme-font margin-left-10px" style="color: rgba(var(--theme-font-rgb), 0.5);">(${detail.reply_count})</span>
								<button id="btns-show" style="display: none;">▶ 펼치기</button>
								<button id="btns-hide">▼ 접기</button>
							</c:when>
							<c:otherwise>
								<p style="text-align: center; flex-grow: 1;">이 글은 아직 댓글이 없어요<br> 첫 댓글을 남겨주세요</p>
							</c:otherwise>
						</c:choose>
					</div>
					
					<!-- 댓글 리스트 -->
					<c:forEach var="Rep" items="${repList}" varStatus="status">
						<div class="reply-detail display-flex flex-direction-column justify-content-flex-start align-items-stretch">
							<input type="hidden" id="reply_id" name="rep_id" value="${Rep.rep_id}">
							<input type="hidden" id="reply_nickname" value="${Rep.mem_nickname}">
							<input type="hidden" id="repReport_id"    value="${Rep.report_id}">
							<div class="reply-view display-flex flex-direction-column justify-content-flex-start align-items-stretch" style="${(Rep.rep_id != Rep.rep_parent) ? 'margin-left: 32px; background-color: rgba(var(--subtheme-rgb), 0.125);' : ''}">
								<div class="reply-header display-flex justify-content-flex-start align-items-center">
								<div class="user-profile-image-in-list">
									<img src="${pageContext.request.contextPath}/uploads/profile/${Rep.mem_image}" onerror="this.onerror=null; this.src='${pageContext.request.contextPath }/image/abstract-user.svg'; $(this).removeAttr('onerror');">
								</div>
								<div class="reply-header-info modal-report display-flex justify-content-flex-start align-items-center">
									<span id="member_nickname" class="font-weight-bolder">${Rep.mem_nickname}</span>
									<c:if test="${not empty memberInfo}">
										<svg id="member-report" class="margin-hor-5px" viewBox="0 0 512 512" width="24" height="24" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
										<input type="hidden" id="member_id" value="${Rep.mem_id}">
										<input type="hidden" id="memReport_id" value="${Rep.report_id}">
									</c:if>
									<span class="color-theme-font font-size-14px" style="color: rgba(var(--theme-font-rgb), 0.5);">(<fmt:formatDate value="${Rep.rep_regdate}" pattern="yy-MM-dd :HH:mm:ss"/>)</span>
								</div>
								<div class="flex-grow-1 display-flex justify-content-flex-end align-items-center">
									<c:if test="${memberInfo != null }">
										<button class="btns-repWrite font-weight-bolder">댓글 달기</button>
									</c:if>
									<c:if test="${Rep.mem_id == memberInfo.mem_id || memberInfo.mem_authority > 108}">
										<button class="btns-repUpdate font-weight-bolder">수정</button>
										<button class="btns-repComplete font-weight-bolder" style="display: none;" onclick="rep_Update(${status.index})">완료</button>
										<button class="btns-delete font-weight-bolder" onclick="goreplyDelete(${detail.brd_id},${detail.art_id },${Rep.rep_id })">삭제</button>
										<button class="btns-cancel font-weight-bolder" style="display: none;">취소</button>
									</c:if>
									<div class="modal-report padding-0" style="width: 24px; height: 24px;">
										<c:if test="${not empty memberInfo}">
											<svg id="reply-report" viewBox="0 0 512 512" width="24" height="24" style="fill: none; stroke: var(--warning); stroke-width: 32px; stroke-linecap: round; stroke-linejoin: round; cursor: pointer;"><path d="M448 256c0-106-86-192-192-192S64 150 64 256s86 192 192 192 192-86 192-192z"/><path d="M250.26 166.05L256 288l5.73-121.95a5.74 5.74 0 00-5.79-6h0a5.74 5.74 0 00-5.68 6z"/><path d="M256 367.91a20 20 0 1120-20 20 20 0 01-20 20z" style="stroke: none; fill: var(--warning);"/></svg>
										</c:if>
									</div>
								</div>
							   </div>
							   <!-- 댓글 본문 -->
							   <div class="reply-inner padding-0" style="flex-grow: 1">
									<!-- 댓글 수정 -->
									<div class="reply-content padding-0">
										<textarea class="rep-content full-width full-height" id="rep-content${status.index}" disabled="disabled" autofocus="autofocus">${Rep.rep_content}</textarea>
									</div>
							   </div>
							</div>
							
							<!-- 댓글의 댓글 작성 -->
							<div class="reply-replyWrite" style="display: none; margin-left: 10%">
								<form action="${pageContext.request.contextPath}" method="post">
									<div class="form-box display-flex flex-direction-column justify-content-flex-start align-items-stretch" style="border: 2px solid var(--subtheme); border-radius: 5px;">
										<input type="hidden" id="brd_id${status.index}" name="brd_id"    value="${detail.brd_id}">
										<input type="hidden" id="art_id${status.index}" name="art_id"    value="${detail.art_id}">
										<input type="hidden" id="rep_id${status.index}" name="rep_id"    value="${Rep.rep_id}">
										<input type="hidden" name="category"    value="${category}">
										<input type="hidden" name="rep_parent"value="${Rep.rep_parent}">
										<input type="hidden" name="rep_step"   value="${Rep.rep_step}">
										<div class="display-flex flex-direction-column justify-content-flex-start align-items-stretch">
										<div class="display-flex justify-content-space-between align-items-center" style="border-bottom: 1px solid rgba(var(--subtheme-rgb), 0.5);">
											<span class="font-size-18px font-weight-bolder">${memberInfo.mem_nickname }</span>
											<input class="reply-primitive-submit" type="submit" value="등록">
										</div>
										<textarea class="reply-primitive-write" style="margin-top: 10px;" placeholder="댓글을 입력하세요" name="rep_content"></textarea>
										</div>
									</div>
								</form>
							</div>
						</div>
					</c:forEach>
					
					<!-- 새로운 댓글 작성 -->
					<div class="reply-write">
						<c:choose>
							<c:when test="${memberInfo != null}">
								<form action="${pageContext.request.contextPath}/board/dutchpay/replyInsert" method="post">
									<div class="form-box display-flex flex-direction-column justify-content-flex-start align-items-stretch" style="border: 2px solid var(--subtheme); border-radius: 5px;">
										<span><input type="hidden" name="brd_id"    value="${detail.brd_id}"></span>
										<span><input type="hidden" name="art_id"    value="${detail.art_id}"></span>
											<div class="display-flex flex-direction-column justify-content-flex-start align-items-stretch">
											<div class="display-flex justify-content-space-between align-items-center" style="border-bottom: 1px solid rgba(var(--subtheme-rgb), 0.5);">
												<span class="font-size-18px font-weight-bolder">${memberInfo.mem_nickname }</span>
												<input class="reply-primitive-submit" type="submit" value="등록">
											</div>
											<textarea class="reply-primitive-write" style="margin-top: 10px;" placeholder="댓글을 입력하세요" name="rep_content"></textarea>
										</div>
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<div class="reply-login padding-10px display-flex flex-direction-column justify-content-center align-items-center" style="border-radius: 5px; background-color: rgba(var(--subtheme-rgb), 0.25);">
									<span>본 게시물에 댓글을 작성하실 권한이 없습니다. 로그인 하신 후 댓글을 다실 수 있습니다.</span>
									<span>ShareGo <a href="${pageContext.request.contextPath }/login"><span class="color-subtheme font-weight-bolder">로그인</span></a></span>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		
		<button id="scrollToTop" class="adv-hover">
			<svg style="fill: var(--subtheme); stroke: var(--subtheme); stroke-width: 2px; stroke-linecap: round; stroke-linejoin: round;" width="20" height="10" viewBox="0 0 32 16">
				<path d="M 15 1 L 1 15 31 15 Z"/>
			</svg>
		</button>
		<button id="scrollToBottom" class="adv-hover">
			<svg style="fill: var(--subtheme); stroke: var(--subtheme); stroke-width: 2px; stroke-linecap: round; stroke-linejoin: round;" width="20" height="10" viewBox="0 0 32 16">
				<path d="M 15 15 L 1 1 31 1 Z"/>
			</svg>
		</button>
	</main>
	<aside id="rightside">
		
	</aside>
	<footer>
		<div id="footer-info" class="full-height">
			<span style="font-size: 40px; font-weight: 900; margin: 0 10px; text-align: left; opacity: 0.5;">ShareGo</span>
			<div style="font-size: 12px; flex-grow: 1;">
				<p>서울특별시 마포구 신촌로 176</p>
				<p>02-313-1711</p>
				<p>choongang@naver.com</p>
			</div>
			<div>
				<a href="" style="margin: 10px;">
					<svg style="fill: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M480 257.35c0-123.7-100.3-224-224-224s-224 100.3-224 224c0 111.8 81.9 204.47 189 221.29V322.12h-56.89v-64.77H221V208c0-56.13 33.45-87.16 84.61-87.16 24.51 0 50.15 4.38 50.15 4.38v55.13H327.5c-27.81 0-36.51 17.26-36.51 35v42h62.12l-9.92 64.77H291v156.54c107.1-16.81 189-109.48 189-221.31z" fill-rule="evenodd"/>
					</svg>
				</a>
				<a href="" style="margin: 10px;">
					<svg style="stroke: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M349.33 69.33a93.62 93.62 0 0193.34 93.34v186.66a93.62 93.62 0 01-93.34 93.34H162.67a93.62 93.62 0 01-93.34-93.34V162.67a93.62 93.62 0 0193.34-93.34h186.66m0-37.33H162.67C90.8 32 32 90.8 32 162.67v186.66C32 421.2 90.8 480 162.67 480h186.66C421.2 480 480 421.2 480 349.33V162.67C480 90.8 421.2 32 349.33 32z"/>
						<path d="M377.33 162.67a28 28 0 1128-28 27.94 27.94 0 01-28 28zM256 181.33A74.67 74.67 0 11181.33 256 74.75 74.75 0 01256 181.33m0-37.33a112 112 0 10112 112 112 112 0 00-112-112z"/>
					</svg>
				</a>
				<a href="" style="margin: 10px;">
					<svg style="fill: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M508.64 148.79c0-45-33.1-81.2-74-81.2C379.24 65 322.74 64 265 64h-18c-57.6 0-114.2 1-169.6 3.6C36.6 67.6 3.5 104 3.5 149 1 184.59-.06 220.19 0 255.79q-.15 53.4 3.4 106.9c0 45 33.1 81.5 73.9 81.5 58.2 2.7 117.9 3.9 178.6 3.8q91.2.3 178.6-3.8c40.9 0 74-36.5 74-81.5 2.4-35.7 3.5-71.3 3.4-107q.34-53.4-3.26-106.9zM207 353.89v-196.5l145 98.2z"/>
					</svg>
				</a>
				<a href="" style="margin: 10px;">
					<svg style="fill: var(--theme-font);" width="30" height="30" viewBox="0 0 512 512">
						<path d="M496 109.5a201.8 201.8 0 01-56.55 15.3 97.51 97.51 0 0043.33-53.6 197.74 197.74 0 01-62.56 23.5A99.14 99.14 0 00348.31 64c-54.42 0-98.46 43.4-98.46 96.9a93.21 93.21 0 002.54 22.1 280.7 280.7 0 01-203-101.3A95.69 95.69 0 0036 130.4c0 33.6 17.53 63.3 44 80.7A97.5 97.5 0 0135.22 199v1.2c0 47 34 86.1 79 95a100.76 100.76 0 01-25.94 3.4 94.38 94.38 0 01-18.51-1.8c12.51 38.5 48.92 66.5 92.05 67.3A199.59 199.59 0 0139.5 405.6a203 203 0 01-23.5-1.4A278.68 278.68 0 00166.74 448c181.36 0 280.44-147.7 280.44-275.8 0-4.2-.11-8.4-.31-12.5A198.48 198.48 0 00496 109.5z"/>
					</svg>
				</a>
			</div>
		</div>
	</footer>
</body>
</html>