package com.java501.S20230401.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.MemberInfo;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.CommService;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

// 나눔해요 페이지 계열 컨트롤러 : 양동균
@Controller
@RequiredArgsConstructor
@Slf4j
public class ShareController {
	private final ArticleService articleService;
	private final MemberService memberService;
	private final CommService commService;
	private final ReplyService replyService;
	
	// 나눔해요
	@RequestMapping(value = "board/share")
	public String totalPage(@AuthenticationPrincipal
							MemberDetails memberDetails,	// 세션의 로그인 유저 정보
							Article article,
							Integer category,
							String currentPage,
							Model model) {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		article.setBrd_id(category);
		int totalArt = 0;
		List<Article> articleList = null;
		
		// 접속한 게시판의 카테고리 목록 가져오기
		int comm_id = ((int)category / 100) * 100;
		List<Comm> commList = commService.commList(comm_id);

		// 접속한 카테고리 이름 식별
		String categoryName = commService.categoryName(category);
		
		// 페이징 작업 (게시글 수)
		totalArt = articleService.allTotalArt(article);
		Paging page = new Paging(totalArt, currentPage);
		article.setStart(page.getStart());
		article.setEnd(page.getEnd());
		
		// 게시글 조회
		articleList = articleService.allArticleList(article);
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("page", page);
		model.addAttribute("totalArt", totalArt);
		model.addAttribute("category", category);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("commList", commList);
		
		return "share/total";
	}
	
	// writeForm 이동
	@RequestMapping(value = "board/share/write")
	public String writeForm(@AuthenticationPrincipal MemberDetails memberDetails, Article article, Model model, Integer category) {
		if(memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		model.addAttribute("category", category);
		return "share/writeForm";
	}
	
	
	
	// 글쓰기
	@PostMapping(value = "board/share/writeArticleForm")
	public String writeArticleForm(	@AuthenticationPrincipal
									MemberDetails memberDetails,
									@RequestParam("trd_enddate")
									@DateTimeFormat(pattern="yyyy-MM-dd")
									Date trd_enddate,
									Article article, 
									RedirectAttributes redirectAttributes, 
									Integer category) {
		MemberInfo memberInfo = null;
		redirectAttributes.addFlashAttribute("category", category);
		
		// 마감 날짜 저장
		article.getTrade().setTrd_enddate(trd_enddate);
		
		if(memberDetails != null) {
			memberInfo = memberDetails.getMemberInfo(); 					// 유저 정보 저장
			redirectAttributes.addFlashAttribute("memberInfo", memberInfo);	// 유저 정보 리턴
			article.setMem_id(memberInfo.getMem_id());
		}else {
			return "redirect:/board/share?category="+category;
		}
		
		
		log.info("제목은 나옴? [ {} ] 비었으면 안나옴", article.getArt_title());
		log.info("글쓰기 안에 있음 {}", article);
		
		// 글쓰기 실행
		int result = articleService.writeShareArticle(article);
		
		redirectAttributes.addFlashAttribute("article", article);
		return "redirect:/board/share?category="+category;
	}
	
	// 게시글, 댓글 조회
	@RequestMapping(value = "board/share/{commStr}")
	public String detailShareArticle(	@PathVariable("commStr")
										String commStr,
										@AuthenticationPrincipal
										MemberDetails memberDetails,
										Article article,
										Integer category,
										Model model) {
		// 유저 정보
		if(memberDetails != null)
			model.addAttribute("memberInfo", memberDetails.getMemberInfo());	// 유저 정보 리턴
		
		// url 데이터 변환
		Integer brd_id = Integer.parseInt(commStr.substring(0, 4));
		Integer art_id = Integer.parseInt(commStr.substring(4));
		article.setArt_id(art_id);
		article.setBrd_id(brd_id);
		
		// 조회수 증가
		int result = articleService.readShareArticle(article);
		System.out.println("조회수 증가 : "+result);
		// 글 조회
		Article detailArticle = articleService.detailShareArticle(article);
		// 댓글 조회
		List<Reply> replyList = replyService.replyShareList(article);
		
		model.addAttribute("article", detailArticle);
		model.addAttribute("replyList", replyList);
		model.addAttribute("category", category);
		
		return "share/article";
	}
	
	// 게시글 - 댓글 쓰기
	@PostMapping(value = "board/share/replyForm")
	public String replyForm(@AuthenticationPrincipal MemberDetails memberDetails, Reply reply, Model model, Integer category, RedirectAttributes redirectAttributes) {
		MemberInfo memberInfo = null;
		redirectAttributes.addFlashAttribute("category", category);
		
		if(memberDetails != null) {
			memberInfo = memberDetails.getMemberInfo(); 					// 유저 정보 저장
			redirectAttributes.addFlashAttribute("memberInfo", memberInfo);	// 유저 정보 리턴
			reply.setMem_id(memberInfo.getMem_id());
		}else {
			// 로그인 안했을때 수정필요
			return String.format("redirect:/board/share/%s%s?category=%s", reply.getBrd_id(), reply.getArt_id(), category);
		}
		
		System.out.println("게시판 아이디 : "+reply.getBrd_id());
		System.out.println("글번호 : "+reply.getArt_id());
		System.out.println("댓글 번호(PK) : "+reply.getRep_id());
		System.out.println("                              댓글 번호(PK)의 댓글 : "+reply.getRep_parent());
		System.out.println("내용 : "+reply.getRep_content());
		System.out.println("댓글 순서 : "+reply.getRep_step());
		
		// 댓글 작성
		int result = replyService.writeReply(reply);
		
		if(result > 0)
			System.out.println("성공");
		else
			System.out.println("실패");
		return String.format("redirect:/board/share/%s%s?category=%s", reply.getBrd_id(), reply.getArt_id(), category);
	}
	
	// 게시글 삭제
	@RequestMapping(value = "board/share/delete")
	public String deleteReply(	@AuthenticationPrincipal
								MemberDetails memberDetails,
								Reply reply,
								RedirectAttributes redirectAttributes) {
		MemberInfo memberInfo = null;
		if(memberDetails != null) {
			memberInfo = memberDetails.getMemberInfo(); 					// 유저 정보 저장
			redirectAttributes.addFlashAttribute("memberInfo", memberInfo);	// 유저 정보 리턴
			reply.setMem_id(memberInfo.getMem_id());
		}else {
			System.err.println("응 로그인 안했어");
			return String.format("redirect:/board/share/%s%s?category=%s", reply.getBrd_id(), reply.getArt_id(), reply.getBrd_id());
		}
		
		int result = replyService.deleteReply(reply);
		
		if(result > 0)
			System.out.println("성공");
		else
			System.out.println("실패");
		
		return String.format("redirect:/board/share/%s%s?category=%s", reply.getBrd_id(), reply.getArt_id(), reply.getBrd_id());
	}
	
	
	
	
	// 메인
//	@RequestMapping(value = "/")
//	public String indexPage() {
//		return "redirect:/board/share?category=999";
//	}
	// Share외의 다른 카테고리 연결
	@RequestMapping(value = "board/{categoryConnect}")
	public String togetherPage(	@PathVariable("categoryConnect")
								String 	categoryConnect, 
								Article article, 
								String 	currentPage, 
								Model 	model, 
								Integer category,
								RedirectAttributes redirectAttributes) {
		log.info("현재 {}에 접속 감지 카테고리 번호 : {}", categoryConnect, category);
		redirectAttributes.addFlashAttribute("article", article);
		redirectAttributes.addFlashAttribute("currentPage", currentPage);
		return "redirect:/board/share?category="+category;
	}
}
