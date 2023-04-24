package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.CommService;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;
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
	public String totalPage(Article article, Integer category, String currentPage, Model model) {
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
		
		// 게시글 뿌리기
//		if(category % 100 == 0)
//			articleList = articleService.allArticleList(article); // 나눔해요 전체 글
//		else
//			articleList = articleService.articleList(article); // 카테고리 글
		
		// 며칠전?
//		for(Article art : articleList) {
//			long diffMin = new Date().getTime() - art.getArt_regdate().getTime();
//			art.setArt_regdate(new Date(diffMin));
//		}
		
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
	public String writeForm(Article article, Model model) {
		int category = article.getBrd_id();
		model.addAttribute("category", category);
		return "share/writeForm";
	}
	// 글쓰기
	@PostMapping(value = "board/share/writeArticle")
	public String writeArticle(Article article, Model model) {

		//int writeResult = articleService.writeArticle(article);
		
		
		log.info("제목은 나옴? [ {} ] 비었으면 안나옴", article.getArt_title());
		
		log.info("유저 이름 나옴? [ {} ] 비었으면 안나옴", article.getMember().getMem_username());
		
		model.addAttribute("article", article);
		return "redirect:share/total";
	}
	
	// 게시글, 댓글 조회
	@RequestMapping(value = "board/share/article")
	public String detailArticle(Article article, Model model, Integer category) {
		// 조회수 증가
		int result = articleService.readPlusArticle(article);
		System.out.println("조회수 증가 : "+result);
		// 글 조회
		Article detailArticle = articleService.detailArticle(article);
		// 댓글 조회
		List<Reply> replyList = replyService.replyList(article);
		
		
		model.addAttribute("article", detailArticle);
		model.addAttribute("replyList", replyList);
		model.addAttribute("category", category);
		
		return "share/article";
	}
	
	// 게시글 - 댓글 쓰기
	@PostMapping(value = "board/share/replyForm")
	public String replyForm(Reply reply, Model model, Integer category) {
		//RedirectAttributes redirectAttributes, 
		//redirectAttributes.addFlashAttribute("article", article);
		
		
		reply.setMem_id(21);
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
		
		
		return "redirect:/board/share/article?art_id="+reply.getArt_id()+"&brd_id="+reply.getBrd_id()+"&category="+category;
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
