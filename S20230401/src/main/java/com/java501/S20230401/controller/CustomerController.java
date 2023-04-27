package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;

// 고객센터 페이지 계열 컨트롤러 : 최승환
@Controller
@RequiredArgsConstructor
public class CustomerController {
	
	private final ArticleService as;
	@Autowired
	private final ReplyService rs;
	
	@RequestMapping(value = "/board/customer")
	public String customerList(@AuthenticationPrincipal MemberDetails mD,
								Article article, Integer category, String currentPage, Model model) {
		System.out.println("CustomerController Start customerList..." );
		
		if (mD != null) {
			model.addAttribute("memberInfo", mD.getMemberInfo());
		}
		
		article.setBrd_id(category);
		int brd_id = category;
		
		// 전체 게시글 갯수
		int totalCustomer = 0;
		totalCustomer =  as.totalCustomer(article);
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
	
	@GetMapping(value = "/board/customer/detailCustomer")
	public String detailCustomer(Article article, Integer category, Model model) {
		System.out.println("CustomerController Start detailCustomer...");

//		1. ArticleService안에 detailCustomer method 선언
//		   1) parameter : brd_id
//		   2) Return      Article
//
		Article customerDetail = as.detailCustomer(article);
		
//		2. ArticleDao   detailCustomer method 선언 
////		                    mapper ID   ,    Parameter
//		article = session.selectOne("shCustomerDetail",    brd_id);
		System.out.println("댓글 갯수세기 시작");
		// 댓글 총갯수세기
		System.out.println("아티클수"+article);
		Reply reply = new Reply();
		reply.setArt_id(article.getArt_id());
		reply.setBrd_id(article.getBrd_id());
		
		int replyCount = rs.shReplyCount(reply);
		// 댓글 목록
		List<Reply> replyList = rs.replyList(reply);
		
		model.addAttribute("article", customerDetail);
		model.addAttribute("replyCount", replyCount);
		model.addAttribute("replyList",replyList);
		model.addAttribute("category", category);
		
		System.out.println("댓글카운트"+replyCount);
		System.out.println("댓글리스트"+replyList);
		System.out.println("카테고리"+category);
		
		return "/customer/detailCustomer";
	}
	
	@RequestMapping(value = "/board/customer/customerWriteForm")
	public String customerWriteForm(@AuthenticationPrincipal MemberDetails mD,
									Article article, Integer category, Model model) {
		model.addAttribute("memberInfo", mD.getMemberInfo());
		model.addAttribute("category", category);
		return "/customer/customerWriteForm";
	}
	
	@PostMapping(value = "writeCustomer")
	public String writeCustomer(@AuthenticationPrincipal MemberDetails mD,
								Article article, Model model) {
		System.out.println("CustomerController Start writeCustomer...");

		return "forward:/board/customer/customerWriteForm";
		
	}
}