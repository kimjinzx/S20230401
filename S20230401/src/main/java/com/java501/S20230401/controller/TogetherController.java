package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;

import lombok.RequiredArgsConstructor;

// 함께해요 페이지 계열 컨트롤러 : 임동빈
@Controller
@RequiredArgsConstructor
public class TogetherController {
	
	private final ArticleService as;

	@RequestMapping(value = "/board/together")
	public String articleList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		System.out.println("articleList controller getBrd_id->"+article.getBrd_id() );
		
		// 전체 게시글 개수 Count
		int totalArticle = as.totalArticle(article);
		System.out.println("ArticleController totalArticle => " + totalArticle);
		
		// 게시글 리스트 작업
		List<Article> listArticle = as.listArticle(article);
		
		// 게시글 별 관심목록 개수 Count
		for (Article a : listArticle) {
			int favoriteCount = as.favoriteCount(a);
			a.setFavoriteCount(favoriteCount);
		}
		
		// Paging 작업
		Paging page = new Paging(totalArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		model.addAttribute("article", article);
		model.addAttribute("totalArticle", totalArticle);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("page", page);
		
		
		return "together/listArticle";
	}
	
	@GetMapping(value = "/board/detailArticle")
	public String detailEmp(Article article, Model model) {
		System.out.println("ArticleController Start detailArticle...");

		List<Article> detailArticle = as.detailArticle(article);
		for (Article a : detailArticle) {
			int favoriteCount = as.favoriteCount(a);
			a.setFavoriteCount(favoriteCount);
		}
		System.out.println("ArticleController detailArticle => " + detailArticle);
		
		model.addAttribute("article", detailArticle);

		return "together/detailArticle";
	}
	
	@GetMapping(value = "/board/writeFormArticle")
	public String writeFormArticle(Model model) {
		System.out.println("ArticleController Start writeFormArticle...");

		List<Comm> categoryList = as.categoryName();
		System.out.println("ArticleController category => " + categoryList);
		model.addAttribute("category", categoryList);
		
		List<Region> regionList = as.regionName();
		System.out.println("ArticleController category => " + regionList);
		model.addAttribute("region", regionList);
		
		return "together/writeFormArticle";
	}

}
