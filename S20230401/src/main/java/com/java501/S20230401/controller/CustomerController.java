package com.java501.S20230401.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 고객센터 페이지 계열 컨트롤러 : 최승환
@Controller
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
	
	private final ArticleService as;
	@Autowired
	private final ReplyService rs;
	
	
	// 고객센터 목록
	@RequestMapping(value = "/board/customer")
	public String customerList(@AuthenticationPrincipal MemberDetails mD, 
								Article article, Integer category, String currentPage, Model model ) {
		System.out.println("CustomerController Start customerList..." );
		//System.err.println(article.getSearch());
		//System.err.println(article.getSearch_keyword());
		// 유저 권한 확인
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		
		int brd_id = category;
		article.setBrd_id(category);
		
		// 전체 게시글 갯수
		int totalCustomer =  as.totalCustomer(article);
		System.out.println("CustomerController totalCustomer=>" + totalCustomer);
		
		// Paging 작업
		Paging page = new Paging(totalCustomer, currentPage);
		article.setStart(page.getStart());	// 시작시 1
		article.setEnd(page.getEnd());		// 시작시 10
		
		
		// 설정해둔 view resolver로 리턴
		model.addAttribute("category", category);
		model.addAttribute("brd_id", brd_id);
		model.addAttribute("totalCustomer", totalCustomer);
		model.addAttribute("page", page);
		
		// 게시글 목록
		if(brd_id == 1500) {
			List<Article> listCustomer = as.listCustomer(article);
			model.addAttribute("listCustomer", listCustomer);
		}else {
			List<Article> listCustomerMenu = as.listCustomerMenu(article);
			System.out.println("컨트롤러 리스트커스터머메뉴"+ listCustomerMenu);
			model.addAttribute("listCustomer", listCustomerMenu);
		}
			
		return "/customer/CustomerIndex";
	}
	
	// 게시글, 댓글
//	@GetMapping(value = "/board/customer/detailCustomer")
	@GetMapping(value = "/board/customer/{art_id}")
	public String detailCustomer(@AuthenticationPrincipal MemberDetails mD, @PathVariable("art_id") String art_id,
								HttpServletRequest request, HttpServletResponse response,
								Article article, Integer category, Model model) {
		
		// url
		article.setArt_id(Integer.parseInt(art_id));
		
		// 유저 권한 확인
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
	
		System.out.println("CustomerController Start detailCustomer...");
		
		
		
		
//		////////
//		// 쿠키
//		Cookie oldCookie = null;
//	    Cookie[] cookies = request.getCookies();
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	        	log.info("cookie.getName " + cookie.getName());
//                log.info("cookie.getValue " + cookie.getValue());
//	            if (cookie.getName().equals("readcookie")) {
//	                oldCookie = cookie;
//	                log.info("\n쿠키 이름 {} 쿠키 값 {}",cookie.getName(), cookie.getValue());
//	            }
//	        }
//	    }
//
//	    if (oldCookie == null) {
//	        as.customerViewCount(article); // 조회수 업데이트
//	        Cookie newCookie = new Cookie("readcookie", "[" + article.getArt_id() + "_" + article.getBrd_id() + "]");
//	        newCookie.setPath("/");
//	        newCookie.setMaxAge(60 * 60 * 24);  // 60초  60분  24시간
//	        response.addCookie(newCookie); 		// 쿠키 저장
//	    }else if (!oldCookie.getValue().contains("[" + article.getArt_id() + "_" + article.getBrd_id() + "]")) {
//	    	as.customerViewCount(article); 			// 조회수 업데이트
//	    	oldCookie.setValue(oldCookie.getValue()+("[" + article.getArt_id() + "_" + article.getBrd_id() + "]"));
//	    	oldCookie.setPath("/");
//	    	oldCookie.setMaxAge(60 * 60 * 24);	// 60초  60분  24시간
//	    	response.addCookie(oldCookie); 		// 쿠키 저장
//	    }
//		
//		///////
		
		
		as.customerViewCount(article);
		
		Article customerDetail = as.detailCustomer(article);
		

		System.out.println("댓글 갯수세기 시작");
		// 댓글 총갯수세기
		System.out.println("아티클수"+article);
		Reply reply = new Reply();
		reply.setArt_id(article.getArt_id());
		reply.setBrd_id(article.getBrd_id());
		
		category = article.getBrd_id();
		int replyCount = rs.shReplyCount(reply);
		// 댓글 목록
		List<Reply> replyList = rs.replyList(reply);
		
		model.addAttribute("article", customerDetail);
		model.addAttribute("replyCount", replyCount);
		model.addAttribute("replyList",replyList);
		model.addAttribute("category", category);
		
		return "/customer/detailCustomer";
	}
	
	//게시글쓰기
	
	@RequestMapping(value = "/board/customer/customerWriteForm")
	public String customerWriteForm(@AuthenticationPrincipal MemberDetails mD,
									Article article, Integer category, Model model) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		
		System.out.println("CustomerController customerWriteForm start");
		
		model.addAttribute("category", category);
		return "/customer/customerWriteForm";
	}
	
	@PostMapping(value = "/board/customer/writeCustomer")
	public String writeCustomer(@AuthenticationPrincipal MemberDetails mD,
								Article article, Integer category, Model model) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		
		System.out.println("CustomerController writeCustomer start");
		
		model.addAttribute("category", category);
		int insertResult = as.insertCustomer(article);
		if(insertResult > 0) return "redirect:/board/customer?category="+article.getBrd_id();
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:/board/customer/writeCustomer";
		}	
	}
	
	//댓글작성
	
	@PostMapping(value = "/board/customer/shcustomerWriteReply")
	public String customerWriteReply(@AuthenticationPrincipal MemberDetails mD,
									Reply reply, Model model) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}		
		
		System.out.println("CustomerController customerWriteReply start");
		int cReplyWrite = rs.customerWriteReply(reply);
		model.addAttribute("cReplyW", cReplyWrite);
		
		return "redirect:/board/customer/"+reply.getArt_id()+"?brd_id="+reply.getBrd_id()+"&category="+reply.getBrd_id();   
	}
	
	@RequestMapping(value = "board/customer/customerDeleteReply")
	public String customerDeleteReply(@AuthenticationPrincipal MemberDetails mD,
									Reply reply, Model model) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}	
		System.out.println("customerDeleteReply start");
		int deleteResult = rs.customerDeleteReply(reply);
		model.addAttribute("deleteResult", deleteResult);
		
		return "redirect:/board/customer/"+reply.getArt_id()+"?brd_id="+reply.getBrd_id()+"&category="+reply.getBrd_id();
	}
	
	@RequestMapping(value = "board/customer/customerUpdateReply")
	public String customerUpdateReply(@AuthenticationPrincipal MemberDetails mD,
									  Integer category, Article article, Reply reply, Model model) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		
		System.out.println("CustomerController customerUpdateReply start");
		int upRResult = rs.customerUpdateReply(reply);
		model.addAttribute("upRResult", upRResult);
		
		return "redirect:/board/customer/"+article.getArt_id()+"?brd_id="+article.getBrd_id()+"&category="+category;
	}
	
	
	@GetMapping(value = "/board/customer/updateFormC")
	public String updateFormC(@AuthenticationPrincipal MemberDetails mD,
							  Article article, Integer category, Model model) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		System.out.println("CustomerController updateFormC start");
		
		Article customFormUpdate = as.detailCustomer(article);
		
		model.addAttribute("category", category);
		model.addAttribute("article", customFormUpdate);
		
		return "/customer/updateFormC";
	}
	
	//글 수정
	
	@PostMapping(value = "/board/customer/updateCustomer")
	public String updateCustomer(@AuthenticationPrincipal MemberDetails mD, //@PathVariable("art_id") String art_id,
								Article article, Integer category, Model model) {
		
//		article.setArt_id(Integer.parseInt(art_id));
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		System.out.println("CustomerController updateCustomer start");
		
		int update = as.updateCustomer(article);
		
		model.addAttribute("category", category);
		model.addAttribute("upCnt", update);
		
		
		return "redirect:/board/customer/"+article.getArt_id()+"?brd_id="+article.getBrd_id()+"&category="+category;
	}
	
	//글 삭제
	
	@RequestMapping(value = "/board/customer/deleteCustomer")
	public String deleteCustomer(@AuthenticationPrincipal MemberDetails mD,
								 Article article, Integer category, Model model) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		
		System.out.println("CustomerController deleteCustomer start");
		int dresult = as.deleteCustomer(article);
		model.addAttribute("category", category);
		model.addAttribute("dresult", dresult);
		
		return "redirect:/board/customer?category="+category;
	}
	
	//글 검색
	
	@RequestMapping(value = "/board/customer/shSearch")
	public String shSearch(@AuthenticationPrincipal MemberDetails mD,
			 			   Article article, String currentPage, Integer category, RedirectAttributes redirectAttributes) {
//		if (mD != null) {
//			model.addAttribute("memberInfo", mD.getMemberInfo());
//		}
//		
//		System.out.println("CustomerController shSearch start");
//		
//		int totalCustomer = as.totalCustomer(article);
//		Paging page = new Paging(totalCustomer, currentPage);
//		article.setStart(page.getStart());
//		article.setEnd(page.getEnd());
//		
//		List<Article> shSearchCustomer = as.shSearchCustomer(article);
//		
//		model.addAttribute("totalCustomer", totalCustomer);
//		model.addAttribute("listCustomer", shSearchCustomer);
//		model.addAttribute("page", page);
//		model.addAttribute("category", category);
		redirectAttributes.addFlashAttribute("article", article);
		return "redirect:/board/customer?category="+category;
	}
	
	@RequestMapping(value = "/board/customer/recomm")
	public int customRecomm (@AuthenticationPrincipal MemberDetails mD,
							Article article, Integer category, Model model,		
							RedirectAttributes redirectAttributes,
							HttpServletRequest request, HttpServletResponse response) {
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		
		int result=0;
		
		return result;
	}

}




























