package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 정보공유 페이지 계열 컨트롤러 : 김찬영
@Controller  
@RequiredArgsConstructor
@Slf4j
public class InformationController {
		
	private final ArticleService as;
	private final ReplyService rs;
	
	
	// 리스트 조회 
	@RequestMapping(value="/board/information")
	public String articleList(@AuthenticationPrincipal MemberDetails memberDetails, Article article, int category, String currentPage, Model model) {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		String viewName = "infoindex";
		System.out.println("ArticleController Start listArticle...");
		article.setBrd_id(category);
		//전체 게시글
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

		return "information/" + viewName;
		
	} 
	
	
	// 상세페이지 확인
	@RequestMapping(value="/board/information/detail")
	public String detail(@AuthenticationPrincipal MemberDetails memberDetails,Article article, Reply reply, int category, Model model) {
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		//조회수
		int view = as.updateView(article);
		System.out.println("art_read Start...");
		Article result = as.cyArticlereadDetail(article);	//DB에서 불러오기
		
		List<Reply> listReply = rs.listReply(reply);
		System.out.println("ArticleController list listReply.size()=>" + listReply.size());

	
		model.addAttribute("listReply", listReply);
		model.addAttribute("category", category);
		model.addAttribute("article", result);
		
		System.out.println("controller detai; Article result"+result);		
		System.out.println("detail Start...");
		return "/information/detail";
	}
	
	//댓글 작성
	@PostMapping(value="/board/information/replyWrite")
	public String write(@AuthenticationPrincipal MemberDetails memberDetails, Reply reply, Model model, Integer category) throws Exception {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		System.out.println("reply write Start...");
		
		int result = rs.cywriteReply(reply);
		System.out.println("댓글달기");
//		log.info("값 확인 {}", reply);
		return "redirect:/board/information/detail?art_id=" +reply.getArt_id()+ "&brd_id=" +reply.getBrd_id()+ "&category=" + category;
	}
	
	
	
	// 추천
	@RequestMapping(value="/board/information/updategood")
	public String updategood(@AuthenticationPrincipal MemberDetails memberDetails, Article article, int category, Model model) {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());

		int result = as.updateGood(article);
		model.addAttribute("category", category);
		model.addAttribute("article", article);
		System.out.println("art_good Start...");
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id();
		return "redirect:/board/information/detail?art_id=" + art_id + "&brd_id=" + brd_id + "&category=" + category;
	}
	// 비추천
	@RequestMapping(value="/board/information/updatebad")
	public String updatebad(@AuthenticationPrincipal MemberDetails memberDetails, Article article, int category, Model model) {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		int result = as.updateBad(article);
		model.addAttribute("category", category);
		model.addAttribute("article", result);
		System.out.println("art_bad Start...");
		int brd_id = article.getBrd_id();
		int art_id = article.getArt_id();
		return "redirect:/board/information/detail?art_id=" + art_id + "&brd_id=" + brd_id + "&category=" + category;
	}
		
	
	
	
	
	// 상세페이지에서 지우기	
	@RequestMapping(value="/board/information/delete")
	public String delete(MemberDetails memberDetails, Article article, int category, Model model) {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		Article result = as.cyArticledelete(article);
		System.out.println("delete Start...");
		return "redirect:/board/information?category=1400";
	}
	
	
	// 수정 상세페이지 update로 이동 
	@RequestMapping(value="/board/information/update", method = RequestMethod.GET)
	public String update(MemberDetails memberDetails, Article article, int category, Model model) {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
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
	public String updateForm(MemberDetails memberDetails, Article article, int category, Model model)throws Exception{
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		System.out.println("updateform Start..");
		
		model.addAttribute("category", category);
		int result = as.cyArticlemodify(article);
		
		return "redirect:/board/information/detail?art_id="+article.getArt_id()+"&brd_id="+article.getBrd_id()+"&category="+category; 
	}
	
	
	//게시물 작성 GET
	@RequestMapping(value = "/board/information/write", method = RequestMethod.GET)
	public String getwrite(MemberDetails memberDetails, Model model) throws Exception {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		System.out.println("write Start...");
		return "information/write";
	}
	
	//게시물 작성POST
	@RequestMapping(value = "/board/information/insert", method = RequestMethod.POST)
	public String insert(MemberDetails memberDetails, Article article, Model model) throws Exception {
		// 유저 정보를 다시 리턴  //memberDetails.getMemberInfo() DB의 유저와 대조 & 권한 확인
		if(memberDetails != null)
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		System.out.println("writeform Start...");
		int result = as.cyArticleinsert(article);
		return "redirect:/board/information?category=1400";
	}
	









}