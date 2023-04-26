package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleDao {

	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	public int insertArticle(Article article);

	public Article getArticleById(Article searcher);


	// 여기서부터 내꺼
	int 			totalArticle(Article article);
	List<Article> 	listArticle(Article article);
	Article 		detailArticle(Article article);

	List<Article> 	replyList(Article a);
	void 			writeArticle(Article article);
	void 			updateArticle(Article article);
	int 			deleteArticle(Article article);
	
	



}