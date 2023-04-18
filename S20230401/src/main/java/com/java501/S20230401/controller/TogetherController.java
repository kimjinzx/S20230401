package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;

import lombok.RequiredArgsConstructor;

// 함께해요 페이지 계열 컨트롤러 : 임동빈
@Controller
@RequiredArgsConstructor
public class TogetherController {
	
	private final ArticleService as;

	@RequestMapping(value = "listArticle")
	public String articleList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		System.out.println("articleList controller getBrd_id->"+article.getBrd_id() );
		
		// 전체 게시글 갯수 Count
		int totalArticle = as.totalArticle(article);
		System.out.println("ArticleController totalArticle => " + totalArticle);

		
		// Paging 작업
		Paging page = new Paging(totalArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listArticle = as.listArticle(article);
		
		model.addAttribute("article", article);
		model.addAttribute("totalArticle", totalArticle);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("page", page);
		
		
		return "together/listArticle";
	}
	
	@GetMapping(value = "detailArticle")
	public String detailEmp(Article article, Model model) {
		System.out.println("ArticleController Start detailArticle...");

		List<Article> detailArticle = as.detailArticle(article);
		System.out.println("ArticleController detailArticle => " + detailArticle);
		
		model.addAttribute("article", detailArticle);

		return "together/detailArticle";
	}

}
