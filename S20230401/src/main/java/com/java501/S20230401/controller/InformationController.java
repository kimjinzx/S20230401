package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;

// 정보공유 페이지 계열 컨트롤러 : 김찬영
@Controller  
@RequiredArgsConstructor
public class InformationController {
		
	private final ArticleService as;
	private final ReplyService rs;
	
	
	// 리스트 조회 
	@RequestMapping(value="/board/information")
	public String articleList(Article article, int category, String currentPage, Model model) {
		System.out.println("ArticleController Start listArticle...");
		article.setBrd_id(category);
		int totalArticle = as.totalArticle();
		System.out.println("ArticleController totalArticle=>" + totalArticle);
		
		//페이징
		Paging page = new Paging(totalArticle, currentPage);		//전통방식
		article.setStart(page.getStart());
		article.setEnd(page.getEnd());
		
		List<Article> listArticle = as.listArticle(article);
		System.out.println("ArticleController list listArticle.size()=>" + listArticle.size());
		
		model.addAttribute("article", article);
		model.addAttribute("totalArticle", totalArticle);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("page", page);
		model.addAttribute("category", category);
		String viewName;
		switch(category) {
		case 1410: viewName = "infovillage"; break;
		case 1420: viewName = "infodeal"; break;
		case 1430: viewName = "infostore"; break;
		case 1440: viewName = "infoarea"; break;
		default: viewName = "infoindex"; break;
		}
	
		
		return "information/" + viewName;
		
	} 
	
	
	// 상세페이지 확인
	@RequestMapping(value="/board/information/detail")
	public String detail(Article article,int category, Model model) {
		
		Article result = as.cyArticlereadDetail(article);	//DB에서 불러오기
		
		model.addAttribute("category", category);
		model.addAttribute("aticle", result);
		System.out.println("controller detai; Article result"+result);		
		System.out.println("detail Start...");
		return "information/detail";
	}
	
		
	// 수정 상세페이지 update로 이동 
	@RequestMapping(value="/board/information/update", method = RequestMethod.GET)
	public String update(Article article,int category, Model model) {
		System.out.println("controller updateform Start.. >" + article);
		
		Article result = as.cyArticlereadDetail(article);	//DB에서 불러오기
		
		System.out.println("result = " + result.toString());
		
		model.addAttribute("category", category);
		model.addAttribute("article", result);
		
		
		
		
		System.out.println("update Start...");
		return "information/update";
		
	}
	
	//게시물 수정 	
	@RequestMapping(value="/board/information/modify", method = RequestMethod.POST)
	public String updateForm(Article article, int category, Model model)throws Exception{
		System.out.println("updateform Start..");
		
		model.addAttribute("category", category);
		int result = as.cyArticlemodify(article);
		
		return "redirect:/board/information/detail?art_id="+article.getArt_id()+"&brd_id="+article.getBrd_id()+"&category="+category; 
	}
			
	//게시물 작성 GET
	@RequestMapping(value = "/board/information/write", method = RequestMethod.GET)
	public String getwrite() throws Exception {
		System.out.println("write Start...");
		return "information/write";
	}
	
	//게시물 작성POST
	@RequestMapping(value = "/board/information/insert", method = RequestMethod.POST)
	public String insert(Article article, Model model) throws Exception {
		System.out.println("writeform Start...");

		int result = as.cyArticleinsert(article);
	
		Article newArticle = new Article();
		articleList(newArticle, 1400, "1", model);
		return "redirect:/board/information?category=1400";
	}
	

	
	
	
	
//	@RequestMapping(value="information/infovillage")
//	public String infovillage() {
//		return "information/infovillage";
//	}
//	@RequestMapping(value="information/infodeal")
//	public String infodeal() {
//		return "information/infodeal";
//	}
//	@RequestMapping(value="information/infostore")
//	public String infostore() {
//		return "information/infostore";
//	}
//	@RequestMapping(value="information/infoarea")
//	public String infoarea() {
//		return "information/infoarea";
//	}
}