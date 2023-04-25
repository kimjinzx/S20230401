package com.java501.S20230401.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;

// 커뮤니티 페이지 계열 컨트롤러 : 백준
@Controller
@RequiredArgsConstructor
public class CommunityController {
	private final ArticleService as;
	private final ReplyService rs;
	
	@RequestMapping(value = "board/community")
	public String communityList(Article article, int category, String currentPage, Model model) {
		article.setBrd_id(category);
		int brd_id = category;
		System.out.println("CommunityController 시작");
		int totalArticle = as.totalArticle(category);
		System.out.println("CommunityController totalArticle->"+totalArticle);
		
		model.addAttribute("totalArticle", totalArticle);
		Paging page = new Paging(totalArticle, currentPage);
		System.out.println("page"+page);
		article.setStart(page.getStart());
		article.setEnd(page.getEnd());
		
		Map<Integer, String> boardMap = new HashMap<>();
	    boardMap.put(1310, "일상수다");
	    boardMap.put(1320, "자랑하기");
	    boardMap.put(1330, "홍보하기");
	    boardMap.put(1340, "질문/요청");
	    model.addAttribute("boardMap", boardMap);
	    model.addAttribute("totalArticle", totalArticle);
	    model.addAttribute("page", page);
	    model.addAttribute("brd_id", brd_id);
	    model.addAttribute("category", category);
	    
	    article.setBrd_id(brd_id);
		if(brd_id == 1300) {
			List<Article> listArticle = as.articleTotal(article);
			model.addAttribute("listArticle", listArticle);
		}else {
			List<Article> menuArticle = as.articleMenu(article);
		    model.addAttribute("listArticle", menuArticle);
		}
		
		
		return "community/communityIndex";
	}
	
	@GetMapping(value = "board/community/detailContent")
	public String detailContent(Article article, int category, Model model) {
		System.out.println("CommunityController detail 시작");
		Integer readCount = as.upreadCount(article);
		System.out.println("업데이트 결과  =>"+readCount);
		Article detailCon = as.detailContent(article);
		Reply reply = new Reply();
		reply.setArt_id(article.getArt_id());
		reply.setBrd_id(article.getBrd_id());
		// 댓글 갯수
		Reply countReply = rs.replyCount(reply);
		// 댓글 정보
		List<Reply> replyMain = rs.replyMain(reply);
		System.out.println("detailContent art_id 값"+article);
		
		model.addAttribute("article", detailCon);
		model.addAttribute("reply", countReply);
		model.addAttribute("replyMain", replyMain);
		model.addAttribute("category", category);
		
		return "community/detailContent";
	}
	
	
	@RequestMapping(value = "board/community/communityWrite")
	public String communityFormWrite(Model model) {
		System.out.println("커뮤니티 폼 라이트 시작");
		List<Article> articleList = as.listMagnager();
		model.addAttribute("listMagnager", articleList);
		
		
		return "community/communityWrite";
	}
	
	@PostMapping(value = "bjcommunitywrite")
	public String communityWrite(Article article, Model model) {
		System.out.println("커뮤니티 라이트 시작");
		
		int writeResult = as.writeArticle(article);
		if(writeResult > 0 ) return "redirect:board/community";
		else {
			model.addAttribute("msg", "입력 실패 확인하세요");
			return "foward:bjCommunityFormWrite";
		}
		
		
	    
	}
}

