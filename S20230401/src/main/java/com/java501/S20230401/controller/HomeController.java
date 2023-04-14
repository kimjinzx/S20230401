package com.java501.S20230401.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.service.ArticleService;
import com.java501.S20230401.util.SummaryType;

import lombok.RequiredArgsConstructor;

// 메인 페이지 계열 컨트롤러 : 유현규
@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ArticleService as;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		List<Article> togetherRecentList = as.getArticleSummary(1000, SummaryType.RECENT);
		model.addAttribute("togetherRecentList", togetherRecentList);
		return "index";
	}
}
