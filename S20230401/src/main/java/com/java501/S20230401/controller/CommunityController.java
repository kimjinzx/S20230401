package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;

import lombok.RequiredArgsConstructor;

// 커뮤니티 페이지 계열 컨트롤러 : 백준
@Controller
@RequiredArgsConstructor
public class CommunityController {
	private final ArticleService as;
	
	@RequestMapping(value = "/bj_communityTotalList")
	public String communityList(Article article , String currentPage, Model model) {
		System.out.println("CommunityController 시작");
		int totalArticle = as.totalArticle();
		System.out.println("CommunityController totalArticle->"+totalArticle);
		
		model.addAttribute("totalArticle", totalArticle);
		
		Paging page = new Paging(totalArticle, currentPage);
		article.setStart(page.getStart());
		article.setEnd(page.getEnd());
		
		List<Article> listArticle = as.articleTotal(article);
		
		model.addAttribute("totalArticle", totalArticle);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("page", page);
		
		return "community/communityIndex";
	}
}
