package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "contextPath/board/listArticle")
	public String articleList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		
		// 전체 게시글 갯수 Count
		int totalArticle = as.totalArticle();
		System.out.println("EmpController totalArticle => " + totalArticle);
		
		// 게시글 별 댓글 수
	//	List<Article> totalRepCountList = as.totalRepCount(article);
		
		// Paging 작업
		Paging page = new Paging(totalArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listArticle = as.listArticle(article);

		//model.addAttribute("totalRepCountList", totalRepCountList);
		model.addAttribute("totalArticle", totalArticle);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("page", page);
		
		
		return "together/listArticle";
	}
	
	@RequestMapping(value = "contextPath/board/listEatingArticle")
	public String articleEatingList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		
		// 전체 게시글 갯수 Count
		int totalEatingArticle = as.totalEatingArticle();
		System.out.println("EmpController totalEatingArticle => " + totalEatingArticle);
		
		// Paging 작업
		Paging page = new Paging(totalEatingArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listEatingArticle = as.listEatingArticle(article);
		System.out.println("EmpController list listEmp.size()=> " + listEatingArticle.size());

		model.addAttribute("totalEatingArticle", totalEatingArticle);
		model.addAttribute("listArticle", listEatingArticle);
		model.addAttribute("page", page);
		
		
		return "together/listEatingArticle";
	}
	
	@RequestMapping(value = "contextPath/board/listSportsArticle")
	public String articleSportsList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		
		// 전체 게시글 갯수 Count
		int totalSportsArticle = as.totalSportsArticle();
		System.out.println("EmpController totalSportsArticle => " + totalSportsArticle);
		
		// Paging 작업
		Paging page = new Paging(totalSportsArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listSportsArticle = as.listSportsArticle(article);
		System.out.println("EmpController list listEmp.size()=> " + listSportsArticle.size());
		
		model.addAttribute("totalSportsArticle", totalSportsArticle);
		model.addAttribute("listArticle", listSportsArticle);
		model.addAttribute("page", page);
		
		
		return "together/listSportsArticle";
	}
	
	@RequestMapping(value = "contextPath/board/listShoppingArticle")
	public String articleShoppingList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		
		// 전체 게시글 갯수 Count
		int totalShoppingArticle = as.totalShoppingArticle();
		System.out.println("EmpController totalShoppingArticle => " + totalShoppingArticle);
		
		// Paging 작업
		Paging page = new Paging(totalShoppingArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listShoppingArticle = as.listShoppingArticle(article);
		System.out.println("EmpController list listEmp.size()=> " + listShoppingArticle.size());

		model.addAttribute("totalShoppingArticle", totalShoppingArticle);
		model.addAttribute("listArticle", listShoppingArticle);
		model.addAttribute("page", page);
		
		
		return "together/listShoppingArticle";
	}
	
	@RequestMapping(value = "contextPath/board/listCurtureArticle")
	public String articleCurtureList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		
		// 전체 게시글 갯수 Count
		int totalCurtureArticle = as.totalCurtureArticle();
		System.out.println("EmpController totalCurtureArticle => " + totalCurtureArticle);
		
		// Paging 작업
		Paging page = new Paging(totalCurtureArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listCurtureArticle = as.listCurtureArticle(article);
		System.out.println("EmpController list listEmp.size()=> " + listCurtureArticle.size());


		model.addAttribute("totalCurtureArticle", totalCurtureArticle);
		model.addAttribute("listArticle", listCurtureArticle);
		model.addAttribute("page", page);
		
		
		return "together/listCurtureArticle";
	}
	
	@RequestMapping(value = "contextPath/board/listHobbyArticle")
	public String articleHobbyList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		
		// 전체 게시글 갯수 Count
		int totalHobbyArticle = as.totalHobbyArticle();
		System.out.println("EmpController totalHobbyArticle => " + totalHobbyArticle);
		
		// Paging 작업
		Paging page = new Paging(totalHobbyArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listHobbyArticle = as.listHobbyArticle(article);
		System.out.println("EmpController list listEmp.size()=> " + listHobbyArticle.size());

		model.addAttribute("totalHobbyArticle", totalHobbyArticle);
		model.addAttribute("listArticle", listHobbyArticle);
		model.addAttribute("page", page);
		
		
		return "together/listHobbyArticle";
	}
	
	@RequestMapping(value = "contextPath/board/listEtcArticle")
	public String articleEtcList(Article article, String currentPage, Model model) {
		System.out.println("articleList controller Start");
		
		// 전체 게시글 갯수 Count
		int totalEtcArticle = as.totalEtcArticle();
		System.out.println("EmpController totalEtcArticle => " + totalEtcArticle);
		
		// Paging 작업
		Paging page = new Paging(totalEtcArticle, currentPage);
		article.setStart(page.getStart()); // 시작시 1
		article.setEnd(page.getEnd());
		
		List<Article> listEtcArticle = as.listEtcArticle(article);
		System.out.println("EmpController list listEmp.size()=> " + listEtcArticle.size());

		model.addAttribute("totalEtcArticle", totalEtcArticle);
		model.addAttribute("listArticle", listEtcArticle);
		model.addAttribute("page", page);
		
		
		return "together/listEtcArticle";
	}
}
