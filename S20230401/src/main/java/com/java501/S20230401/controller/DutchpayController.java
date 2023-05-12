package com.java501.S20230401.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.MemberInfo;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 같이사요 페이지 계열 컨트롤러 : 김진현
@Controller
@RequiredArgsConstructor
@Slf4j
public class DutchpayController {
	private final ArticleService as;
	
	@RequestMapping(value = "/board/dutchpay") // 대분류 같이사요 + 중분류 하위카테고리 이동 및 List조회  
	public String dutchpayList(Article article,int category, String currentPage, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		String viewName;
		 String boardName;
	      switch(category) { //카테고리 이동
	      case 1110: viewName = "dutchpayFoodList"; boardName = "FoodList"; break;
	      case 1120: viewName = "dutchpayClothesList"; boardName = "ClothesList"; break;
	      case 1130: viewName = "dutchpayHouseStuffList"; boardName = "HouseStuffList"; break;
	      case 1140: viewName = "dutchpayOverseasList"; boardName = "OverseasList"; break;
	      case 1150: viewName = "dutchpayEtcList"; boardName = "EtcList"; break;
	      default: viewName = "dutchpayList"; boardName = "List"; break;
	      }
	      List<Article> dutchpayList = as.JHgetDutchpayList(boardName);
	      System.out.println("controller dutchpayList size() -> "+dutchpayList.size());
	      model.addAttribute("dutchpayList", dutchpayList);
	      
	      // 페이징
//	      System.out.println("아티클 -> "+article);
//	      int totalArticle = as.totalArticle1(article);
//	      System.out.println("컨트롤러 페이징 totalArticle -> "+totalArticle);
//	      
//	      Paging page = new Paging(totalArticle, currentPage);
//	      article.setStart(page.getStart());
//	      article.setEnd(page.getEnd());
//	      
//	      System.out.println("controller pageStart -> "+page.getStart());
//	      System.out.println("controller pageEnd -> "+page.getEnd());
	      
	      
	      
	      return "dutchpay/" + viewName;
	}
	
	@RequestMapping(value ="/dutchpay/dutchpayDetail") //상세 게시글    
	public String dutchpayDetail(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails, HttpServletRequest request, HttpServletResponse response) {
		
		Integer art_id = null;
		Integer brd_id = null;
		Integer mem_id = null;
		
		
		System.out.println("controller article para brd_id -> "+article.getBrd_id());
		System.out.println("controller article para art_id -> "+article.getArt_id());
		System.out.println("controller article para trd_id -> "+article.getTrd_id());

		if (memberDetails != null) {
			MemberInfo memberinfo = memberDetails.getMemberInfo();
			model.addAttribute("memberInfo", memberinfo);
			mem_id = memberinfo.getMem_id();
		}
		Article detail = as.JHdetail1(article); //상세페이지
		model.addAttribute("detail", detail); 
		System.out.println("controller detail brd_id -> "+detail.getBrd_id());
		System.out.println("controller detail art_id -> "+detail.getArt_id());
		System.out.println("controller detail trd_id -> "+detail.getTrd_id());
		
		
		
		 // 쿠키를 이름(name) 중복 확인
	      Cookie oldCookie = null;   // oldCookie 객체 생성
	       Cookie[] cookies = request.getCookies();   // cookies 배열에 모든 쿠키를 담는다.
	       if (cookies != null) {         // 쿠키가 있으면?
	            for (Cookie cookie : cookies) {   // 쿠키들로 반복문을 돌려서
	               if (cookie.getName().equals("artRead")) {   // 이름이 JHDeatilRead인 쿠키가 있으면?
	                  oldCookie = cookie;   // 쿠키를 내 oldCookie에 담는다.
	               }
	            }
	         } 
	       // 쿠키 값(value) 중복 확인
	        if (oldCookie != null) {            // oldCookie가 있으면 그걸 가져와서 값을 설정해준다.
	             if (!oldCookie.getValue().contains(String.format("[%s_%s_%s]", art_id, brd_id, mem_id))) {
	            	 detail.setArt_read(detail.getArt_read() + 1); // 조회수 증가
	              int read = as.JHDeatilRead1(article); // 조회수
	              
	               oldCookie.setValue(oldCookie.getValue() + String.format("[%s_%s_%s]", art_id, brd_id, mem_id));
	               oldCookie.setPath("/");
	               oldCookie.setMaxAge(60 * 60); // 1시간 (초 * 분 * 시간)
	               response.addCookie(oldCookie);
	             } 
	         } else {                        // oldCookie가 없으면 newCookie를 새로 만든다.
	        	 detail.setArt_read(detail.getArt_read() + 1); // 조회수 증가
	               int read = as.JHDeatilRead1(article); // 조회수
	               Cookie newCookie = new Cookie("artRead", String.format("[%s_%s_%s]", art_id, brd_id, mem_id));
	                 
	               newCookie.setPath("/"); 
	               newCookie.setMaxAge(60 * 60); // 1시간 (초 * 분 * 시간)   
	               response.addCookie(newCookie);
	         }
	       
		
		
		  
		

		
		 
		article.setTrd_id(detail.getTrd_id()); // 공구 참가자(수락 확정된) 명단 보여주기
		List<Article> joinList  = as.JHjoinList1(article); 
		model.addAttribute("joinList", joinList);
		System.out.println("joinList.trd_id -> "+detail.getTrd_id());
		
		
		article.setTrd_id(detail.getTrd_id()); // 공구 참가자(수락 미확정) 대기열 명단 보여주기
		List<Article> waitList = as.JHwaitList1(article);
		model.addAttribute("waitList", waitList);
		System.out.println("waitList.trd_id -> "+detail.getTrd_id());
		
		
		List<Article> repList = as.JHrepList1(article); // 댓글리스트 조회
		model.addAttribute("repList", repList);
		
		List<Comm> payStatus = as.JHpayStatus1(); // 거래 상태 보기 및 수정 
		model.addAttribute("payStatus",payStatus);
		System.out.println("Detail payStatus.size()- >"+payStatus.size());
		
		return "/dutchpay/dutchpayDetail";
	}
	
	@ResponseBody
	@RequestMapping(value ="/dutchpay/dutchpayDetailYN") // 신청하기 버튼을 눌렀을 때 유저의 신청유무 확인
	public String dutchpayDetailYN(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		 
		// 일단 없다는 가정하에 진행	
		String resultStr = "0";
		
		System.out.println("controller article ListYNPara brd_id -> "+article.getBrd_id());
		System.out.println("controller article ListYNPara art_id -> "+article.getArt_id());
		System.out.println("controller article ListYNPara trd_id -> "+article.getTrd_id());

		
		 
		// 조건은 article에 mem_id와 trd_id끼리 매칭되는 
//		// join테이블에 올라간 사람들의	count 가져오기
		int joinListCount = as.JHJoinListYN(article); 
		System.out.println("joinListCount-> "+joinListCount);
		
		// 조건은 article에 mem_id와 trd_id끼리 매칭되는 
//		// waiting테이블에 올라간 사람들의	count 가져오기
		int waitListCount = as.JHWaitListYN(article); 
		System.out.println("waitListCount-> "+waitListCount);

		
//		 직접 검증을 해서 1,0을 리턴
		 if(joinListCount > 0 ||  waitListCount > 0 ) {
			 resultStr = "1";
		 } else {
			 resultStr = "0"; 
		 }
		
		return resultStr;
	}
	
	@PostMapping(value = "/dutchpay/payStatusPro") // 거래상태 변경하기 (거래모집중 or 거래취소)
	public String payStatusPro(Model model, Article article , @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)  
			model.addAttribute("memberInfo", memberDetails.getMemberInfo()); 

		Article payStatusPro = as.JHpayStatusPro1(article);
		System.out.println("현재 거래상태 -> "+article.getComm_id()+":"+article.getStatus_name());
		System.out.println("trd_id -> "+article.getTrd_id());
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@PostMapping(value = "/dutchpay/replyInsert") // 댓글 입력
	public String replyInsert(Article article, RedirectAttributes ra, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)  
			model.addAttribute("memberInfo", memberDetails.getMemberInfo()); 
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		as.JHreplyInsert1(article);
		ra.addFlashAttribute("article",article);
		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@GetMapping(value = "/dutchpay/replyDelete") // 댓글 삭제
	public String replyDelete(Article article, RedirectAttributes ra, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)  
			model.addAttribute("memberInfo", memberDetails.getMemberInfo()); 
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		as.JHreplyDelete1(article);
		ra.addFlashAttribute("article",article);
		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@GetMapping(value = "dutchpay/dutchpayWriteForm") //글쓰기 폼 
	public String dutchpayWriteForm(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());

		System.out.println("dutchpay/dutchpayWriteForm start..");
		
		List<Comm> category = as.JHcategory1();
		for (Comm c : category) System.out.println(c.getComm_id() + " : " + c.getComm_value());
		System.out.println("dutchpay/dutchpayWriteForm category.size()- >"+category.size());
		List<Region> loc = as.JHloc1();
		//for (Region l : loc) System.out.println(l.getReg_id() + " : " + l.getReg_name());
		System.out.println("dutchpay/dutchpayWriteForm loc.size() ->"+loc.size());
	
		model.addAttribute("categories", category);
		model.addAttribute("loc", loc);
		
		return "dutchpay/dutchpayWriteForm";
	}
	
	

	@RequestMapping(value = "dutchpay/dutchpayUpdateForm") //업데이트(수정) 폼 + 드롭다운 
	public String dutchpayUpdateForm(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		System.out.println("dutchpay/dutchpayUpdateForm start..");
		System.out.println("controller updateForm brd_id  -> "+article.getBrd_id());
		System.out.println("controller updateForm art_id  -> "+article.getArt_id());
		Article updateForm = as.JHupdateForm1(article);
		model.addAttribute("updateForm", updateForm);
		
		List<Region> loc_ud = as.JHloc_ud1();
		//for (Region l : loc_ud) System.out.println(l.getReg_id() + " : " + l.getReg_name());
		System.out.println("dutchpay/dutchpayUpdateForm loc_ud.size()- >"+loc_ud.size());
		
		model.addAttribute("loc_ud", loc_ud);
		
		return "dutchpay/dutchpayUpdateForm";
	}
	
	@PostMapping(value = "dutchpay/dutchpayWritePro") // 글내용 삽입 (insert) 
	public String insert(Article article ,RedirectAttributes ra, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)  // 로그인한 mem_id를 가지고 글쓰기
			model.addAttribute("memberInfo", memberDetails.getMemberInfo()); 
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		System.out.println("start insert button");
		System.out.println("writePro controller article -> "+article);
		System.out.println("controller insert brd_id  -> "+article.getBrd_id());
		as.JHdutchpayInsert1(article);
		ra.addFlashAttribute("article", article);  //model.addAttribute와 다른점은 컨트롤러 내에서 매핑할 시 이렇게 사용하는게 좋음
		
		String saveEnddate = "";
		if( article.getTrd_saveEnddate() != null ) {
			saveEnddate = article.getTrd_saveEnddate().substring(0,10);
			article.setTrd_saveEnddate(saveEnddate);
		}
		
		int brd_id = article.getBrd_id(); //확인 버튼 누르면 드롭다운(카테고리) 에서 고른 해당카테고리로 이동 
		return "redirect:/board/dutchpay?category="+brd_id;
	}
	
	//detail에서 쓰던 brd_id,article_id,trd_id들을 가져온 updateForm에서 그것들을 사용해 update
	@PostMapping(value = "dutchpay/dutchpayUpdatePro") //글내용 수정(update)
	public String update(Article article, RedirectAttributes ra, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		System.out.println("start update button");
		System.out.println(article);
		System.out.println("controller update brd_id -> "+article.getBrd_id());
		System.out.println("controller update art_id -> "+article.getArt_id());
		
		as.JHdutchpayUpdate1(article);
		ra.addFlashAttribute("article", article);  
		
		int brd_id = article.getBrd_id();
		return "redirect:/board/dutchpay?category="+brd_id;
	}
	@ResponseBody
	@PostMapping(value = "/dutchpay/dutchpayDelete") //게시글 삭제
	public String delete(Article article, RedirectAttributes ra, Model model , @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		System.out.println("start delete button");
		System.out.println("controller delete brd_id -> "+article.getBrd_id());
		System.out.println("controller delete isdelete -> "+article.getIsdelete());
		as.JHdutchpayDelete1(article);
		ra.addFlashAttribute("article",article);
		int brd_id = article.getBrd_id();
		return "";
	}

	@RequestMapping(value = "/joinForm") // 상세게시글의 신청하기 버튼 (동의서 새창 띄우기)
	public String joinForm(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null) {
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		}
		
		Article detail = as.JHdetail1(article); 
		model.addAttribute("detail", detail);
		System.out.println("controller confirm brd_id -> "+detail.getBrd_id());
		System.out.println("controller confirm art_id -> "+detail.getArt_id());
		System.out.println("controller confirm trd_id -> "+detail.getTrd_id());
	    return "dutchpay/JoinForm";
	}
	
	@PostMapping("/dutchpay/ApplyInsert") // 신청서의 신청하기 버튼 (waiting테이블에 insert)
	@ResponseBody
	public String Apply(@RequestBody Article article, Model model,  @AuthenticationPrincipal MemberDetails memberDetails) {
		
		
		if (memberDetails != null) 
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
			article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		as.JHapplyInsert1(article);
		System.out.println("insert finish ");
		
		System.out.println("리턴");
		return "";
	}
	
	@GetMapping(value = "dutchpay/applyCancel") // 신청취소하기 버튼 (참가대기열에 대기중인 개인의 참가취소버튼)
	public String applyCancel(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());

		
		Article applyCancel = as.JHapplyCancel1(article);
		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@GetMapping(value = "dutchpay/joinCancel") // 참가취소 버튼 (참가자가 확정 된 개인의 참가취소버튼)
	public String joinCancel(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		
		Article joinCancel = as.JHjoinCancel1(article);
		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@GetMapping(value = "/dutchpay/JoinDeny") // 거절 버튼 (mem_id)가 따라다니도록 Detail.jsp에서 function설정
	public String joinDeny(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		System.out.println("Controller article -> "+article);
		
		Article joinDeny = as.JHjoinDeny1(article);
		model.addAttribute("waitList",joinDeny);
		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@GetMapping(value = "/dutchpay/JoinAccept") // 수락 버튼 (mem_id)가 따라다니도록 Detail.jsp에서 function설정
	public String joinAccept(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		System.out.println("Controller article -> "+article);
		
		Article joinAccept = as.JHjoinAccept1(article);
		model.addAttribute("waitList",joinAccept);
		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@PostMapping(value = "/dutchpay/payCompleted") // 거래 완료 버튼
	public String payStatusPro(Model model, Article article , int trd_id, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)  
			model.addAttribute("memberInfo", memberDetails.getMemberInfo()); 

		int payCompleted = as.JHpayCompleted1(trd_id);
		System.out.println("trd_id -> "+article.getTrd_id());

		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	
	@RequestMapping(value = "/reportForm") // 신고하기 양식창 띄우기
	public String reportForm(Article article, Model model
						   , @AuthenticationPrincipal MemberDetails memberDetails
						   , @RequestParam(required = false, value="report_id") Integer report_id) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		article.setReport_id(report_id);
		Article reportForm = as.JHdetail1(article);
		model.addAttribute("reportForm", reportForm);
		
		return "dutchpay/reportForm";
	}
	
    
	@RequestMapping (value = "/dutchpay/favoriteInsert", method = {RequestMethod.POST}) // 관심목록에 추가
	public String favoriteInsert(Article article, Model model, RedirectAttributes ra, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		as.JHfavoriteInsert1(article);
		ra.addFlashAttribute("article", article);
		
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id(); 
		return "redirect:/dutchpay/dutchpayDetail?brd_id="+brd_id+"&art_id="+art_id;
	}
	
	@ResponseBody
	@PostMapping(value ="/dutchpay/favoriteInsertYN") // 관심등록 버튼을 눌렀을 때 유저의 목록유무 확인
	public String favoriteInsertYN(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		 
		// 일단 없다는 가정하에 진행	
		String resultStr = "0";
		
		System.out.println("controller article favoriteYNPara brd_id -> "+article.getBrd_id());
		System.out.println("controller article favoriteYNPara art_id -> "+article.getArt_id());
		System.out.println("controller article favoriteYNPara mem_id -> "+article.getMem_id());
		 
		// 조건은 article에 mem_id,brd_id,art_id끼리 매칭되는 
//		// favorite 테이블에 올라간 사람들의	count 가져오기
		int favoriteListCount = as.JHfavoriteInsertYN1(article); 
		System.out.println("favoriteListCount -> "+favoriteListCount);

		
//		 직접 검증을 해서 1,0을 리턴
		 if(favoriteListCount > 0 ) {
			 resultStr = "1";
		 } else {
			 resultStr = "0"; 
		 }
		
		return resultStr;
	}
	
	@RequestMapping(value = "/dutchpay/articleSearch") // 게시글 검색
	public String articleSearch(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
		
		List<Article> articleSearch = as.JHarticleSearch1(article);
		model.addAttribute("articleSearch", articleSearch);
	
	return "/dutchpay/articleSearch";
	}
		
	@ResponseBody
	@PostMapping(value = "/dutchpay/artGood") //게시글 추천
	public String ArtGood(Article article, Model model , @AuthenticationPrincipal MemberDetails memberDetails) {

		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		System.out.println("controller Good brd_id -> "+article.getBrd_id());
		System.out.println("controller Good art_id -> "+article.getArt_id());
		as.JHartGood1(article);
		
		return "";
	}	
	
	@ResponseBody
	@PostMapping(value = "/dutchpay/artBad") //게시글 추천
	public String ArtBad(Article article, Model model , @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		System.out.println("controller Bad brd_id -> "+article.getBrd_id());
		System.out.println("controller Bad art_id -> "+article.getArt_id());
		as.JHartBad1(article);
		
		return "";
	}	
	
	@ResponseBody
	@PostMapping(value = "/dutchpay/repGood") //게시글 추천
	public String repGood(Article article, Model model , @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		System.out.println("controller Bad brd_id -> "+article.getBrd_id());
		System.out.println("controller Bad art_id -> "+article.getArt_id());
		as.JHrepGood1(article);
		
		return "";
	}	
	
	@ResponseBody
	@PostMapping(value = "/dutchpay/repBad") //게시글 추천
	public String repBad(Article article, Model model , @AuthenticationPrincipal MemberDetails memberDetails) {
		
		if (memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
		
		
		
		
		
		System.out.println("controller Bad brd_id -> "+article.getBrd_id());
		System.out.println("controller Bad art_id -> "+article.getArt_id());
		as.JHrepBad1(article);
		
		return "";
	}	
		
//	@PostMapping(value = "/dutchpay/replyUpdate") // 댓글 수정
//	public String replyUpdate(Article article, Model model, @AuthenticationPrincipal MemberDetails memberDetails) {
//		
//		if (memberDetails != null)  
//			model.addAttribute("memberInfo", memberDetails.getMemberInfo()); 
//		article.setMem_id(memberDetails.getMemberInfo().getMem_id());
//		
//		as.replyUpdate1(article);
//		
//		return "";
//	}
//	
	
	
}
