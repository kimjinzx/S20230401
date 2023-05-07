package com.java501.S20230401.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.MemberDetails;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.service.CommService;
import com.java501.S20230401.service.MemberService;
import com.java501.S20230401.service.Paging;
import com.java501.S20230401.service.ReplyService;

import lombok.RequiredArgsConstructor;

// 관리자 페이지 계열 컨트롤러 : 유현규
@Controller
@RequiredArgsConstructor
public class AdminController {
	private final ArticleService as;
	private final MemberService ms;
	private final ReplyService rs;
	private final CommService cs;
	
	@RequestMapping(value = "/admin")
	public String adminHome(@AuthenticationPrincipal MemberDetails memberDetails,
							HttpServletRequest request, HttpServletResponse response,
							Model model) {
		if (memberDetails == null)
			try { response.sendRedirect("/"); }
			catch (Exception e) { e.printStackTrace(); }
		model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		
		
		return "admin/adminIndex";
	}
	
	@RequestMapping(value = "/admin/{type}")
	public String adminPage(@AuthenticationPrincipal MemberDetails memberDetails,
							@PathVariable String type, @RequestBody(required = false) Map<String, Object> data,
							HttpServletRequest request, HttpServletResponse response,
							Model model) {
		if (memberDetails == null)
			try { response.sendRedirect("/"); }
			catch (Exception e) { e.printStackTrace(); }
		else model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		Integer page = 1;
		if (data != null && data.containsKey("page")) page = (int)data.get("page");
		model.addAttribute("page", page);
		switch(type) {
			case "notice":
				Article searcher = new Article();
				searcher.setBrd_id(1510);
				Paging paging = new Paging(as.allTotalArt(searcher), Integer.toString(page));
				//paging.setPageRow(10);
				//paging.setPageBlock(10);
				searcher.setStart(paging.getStart());
				searcher.setEnd(paging.getEnd());
				List<Article> articles = as.allArticleList(searcher);
				model.addAttribute("start", paging.getStart());
				model.addAttribute("end", paging.getEnd());
				model.addAttribute("startPage", paging.getStartPage());
				model.addAttribute("endPage", paging.getEndPage());
				model.addAttribute("total", paging.getTotal());
				model.addAttribute("totalPage", paging.getTotalPage());
				model.addAttribute("articles", articles);
				break;
		}
		
		return "admin/" + type + "Template";
	}
	
	@RequestMapping(value = "/admin/{type}/view")
	public String adminViewPage(@AuthenticationPrincipal MemberDetails memberDetails,
								@PathVariable String type, @RequestBody(required = false) Map<String, Object> data,
								HttpServletRequest request, HttpServletResponse response,
								Model model) {
		if (memberDetails == null)
			try { response.sendRedirect("/"); }
			catch (Exception e) { e.printStackTrace(); }
		else model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		switch(type) {
			case "notice":
				Integer art_id = (int)data.get("art_id");
				Article searcher = new Article();
				searcher.setArt_id(art_id);
				searcher.setBrd_id(1510);
				Article article = as.getArticleById(searcher);
				model.addAttribute("article", article);
				break;
		}
		
		return "admin/" + type + "ViewTemplate";
	}
	
	@RequestMapping(value = "/admin/{type}/update")
	public String adminUpdatePage(@AuthenticationPrincipal MemberDetails memberDetails,
											@PathVariable String type, @RequestBody(required = false) Map<String, Object> data,
											HttpServletRequest request, HttpServletResponse response,
											Model model) {
		if (memberDetails == null)
			try { response.sendRedirect("/"); }
			catch (Exception e) { e.printStackTrace(); }
		else model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		switch(type) {
		case "notice":
			Integer art_id = (int)data.get("art_id");
			Article searcher = new Article();
			searcher.setArt_id(art_id);
			searcher.setBrd_id(1510);
			Article article = as.getArticleById(searcher);
			model.addAttribute("article", article);
			break;
		}
		
		return "admin/" + type + "UpdateTemplate";
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/{type}/updateProc")
	public String adminUpdateProcessPage(@AuthenticationPrincipal MemberDetails memberDetails,
												     @PathVariable String type, @RequestBody(required = false) Article article,
												     HttpServletRequest request, HttpServletResponse response,
												     Model model) {
		if (memberDetails == null)
			try { response.sendRedirect("/"); }
		catch (Exception e) { e.printStackTrace(); }
		else model.addAttribute("memberInfo", memberDetails.getMemberInfo());
		
		JSONObject jsonObj = new JSONObject();
		switch(type) {
		case "notice":
			int result = as.hgCompressedUpdateArticle(article);
			jsonObj.append("result", result);
			jsonObj.append("art_id", article.getArt_id());
			break;
		}
		
		//response.setCharacterEncoding("UTF-8");
		return jsonObj.toString();
	}
}
