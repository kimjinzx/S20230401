package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleDao {
	int				totalCustomer();
	List<Article> 	listCustomer(Article article);
	Article 		detailCustomer(Article article);
	List<Article> 	listCustomerMenu(Article article);
	int 			insertCustomer(Article article);
	
	
	
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	public int insertArticle(Article article);

	public Article getArticleById(Article searcher);

}
