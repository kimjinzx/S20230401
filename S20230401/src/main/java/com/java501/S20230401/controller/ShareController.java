package com.java501.S20230401.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.CommService;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.service.Paging;

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
	
	// 나눔해요
	@RequestMapping(value = "board/share")
	public String totalPage(Article article, String currentPage, Model model) {
		//article.setBrd_id(1200);
		int category = article.getBrd_id();
		int totalArt = 0;
		List<Article> articleList = null;
		
		// 현재 카테고리 이름 식별
		System.out.println(category);
		String categoryName = commService.categoryName(category);
		
		// 현재 카테고리 목록 가져오기
		int comm_id = ((int)category / 100) * 100;
		List<Comm> commList = commService.commList(comm_id);
		
		
		// 페이징
		if(category % 100 == 0)
			totalArt = articleService.allTotalArt(article); // 나눔해요 전체 글 갯수
		else
			totalArt = articleService.totalArt(article); // 카테고리 글 갯수
		
		Paging page = new Paging(totalArt, currentPage);
		article.setStart(page.getStart());
		article.setEnd(page.getEnd());
		
		log.info("시작 : {}",article.getStart());
		log.info("끝 : {}",article.getEnd());
		
		if(category % 100 == 0)
			articleList = articleService.allArticleList(article); // 나눔해요 전체 글
		else
			articleList = articleService.articleList(article); // 카테고리 글
		
		// 며칠전?
		for(Article art : articleList) {
			long diffMin = new Date().getTime() - art.getArt_regdate().getTime();
			art.setArt_regdate(new Date(diffMin));
		}
		
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
		
		model.addAttribute("article", article);
		return "redirect:share/total";
	}
	
	// 나눔해요 - 식품
	@RequestMapping(value = "board/food/share")
	public String foodPage(Article article, String currentPage, Model model) {
		
		//int totalArt = articleService.totalArt(article);
		//Paging page = new Paging(totalArt, currentPage);
		//article.setStart(page.getStart());
		//article.setEnd(page.getEnd());
		
		//List<Article> articleList = articleService.articleList(article);
		return "share/food";
	}
	// 나눔해요 - 패션/잡화
	@RequestMapping(value = "board/fashion/share")
	public String fashionPage(Article article, String currentPage, Model model) {
		return "share/fashion";
	}
	// 나눔해요 - 가전/가구
	@RequestMapping(value = "board/appliances/share")
	public String appliancesPage(Article article, String currentPage, Model model) {
		return "share/appliances";
	}
	// 나눔해요 - 기타
	@RequestMapping(value = "board/etc/share")
	public String etcPage(Article article, String currentPage, Model model) {
		return "share/etc";
	}
	// 게시글 조회
	@RequestMapping(value = "article/share")
	public String detailArticle(Article article, Model model) {
		Article detailArticle = articleService.detailArticle(article);
		model.addAttribute("article", detailArticle);
		return "share/article";
	}
}
