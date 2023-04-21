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
	public String articleList(Article article, int category, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		System.out.println("articleList controller getBrd_id->" + article.getBrd_id());
		article.setBrd_id(category);

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

	@RequestMapping(value = "/board/detailArticle")
	public String detailEmp(Article article, Model model) {
		System.out.println("ArticleController Start detailArticle...");

		// 상세게시글 요소 구현
		List<Article> detailArticle = as.detailArticle(article);

		// 게시글 별 댓글 갯수 (상세 게시글)
		for (Article a : detailArticle) {
			int favoriteCount = as.favoriteCount(a);
			a.setFavoriteCount(favoriteCount);
		}
		model.addAttribute("detailArticle", detailArticle);

		// 게시글 별 댓글 리스트
		List<Article> replyList = as.replyList(article);
		model.addAttribute("replyList", replyList);

		System.out.println("ArticleController detailArticle => " + detailArticle.size());

		return "together/detailArticle";
	}

	@RequestMapping(value = "/board/writeFormArticle")
	public String writeFormArticle(Model model) {
		System.out.println("ArticleController Start writeFormArticle...");

		// 카테고리별 콤보박스
		List<Comm> categoryList = as.categoryName();
		System.out.println("ArticleController category => " + categoryList.size());
		model.addAttribute("category", categoryList);

		// 지역별 콤보박스
		List<Region> regionList = as.regionName();
		System.out.println("ArticleController category => " + regionList.size());
		model.addAttribute("regions", regionList);

		return "together/writeFormArticle";
	}

	@RequestMapping(value = "/board/writeArticle")
	// 생성
	// fetch? 수정
	// delete? 삭제
	public String writeEmp(Article article, Model model) {
		System.out.println("ArticleController Start writeEmp...");
		model.addAttribute("article", article);
		
		// Service, Dao, Mapper명[insertEmp]까지 => insert
		int insertTrade   = as.insertTrade(article);
		int insertArticle = as.insertArticle(article);
		if (insertArticle > 0)
			return "redirect:/board/together?category=1000";
		//
		else {
			model.addAttribute("msg", "입력실패");
			return "forward:/board/writeFormArticle";
		}
	}
}
