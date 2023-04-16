package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 고객센터 페이지 계열 컨트롤러 : 최승환
@Controller
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
	private final ArticleService as;
	
	@RequestMapping(value = "/board/customer")
	public String customerList(Article article, String currentPage, Model model) {
		System.out.println("CustomerController Start /board/customer..." );
		int totalArticle =  as.totalArticle();
		System.out.println("CustomerController totalArticle=>" + totalArticle);
		// Paging 작업
		Paging page = new Paging(totalArticle, currentPage);
		// Parameter article --> Page만 추가 Setting
		article.setStart(page.getStart());	// 시작시 1
		article.setEnd(page.getEnd());		// 시작시 10
		
		List<Article> listArticle = as.listArticle(article);
		System.out.println("CustomerController list listArticle.size()->"+listArticle.size());
		
		model.addAttribute("totalArticle", totalArticle);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("page", page);
		// 설정해둔 view resolver로 리턴
		return "CustomerList";
	}
}
