package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	int 				totalCustomer();
	List<Article> 		listCustomer(Article article);
	Article 			detailCustomer(Article article);
	List<Article> 		listManager();
	List<Article> listCustomerMenu(Article article);
	
	

	public int insertArticle(Article article);

	public Article getArticleById(Article searcher);
}
