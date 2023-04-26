package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleDao {


	int 			totalArticle(Article article);
	List<Article> 	listArticle(Article article);
	Article 		detailArticle(Article article);

	List<Article> 	replyList(Article a);
	
	void 			writeArticle(Article article);
	void 			updateArticle(Article article);
	int 			deleteArticle(Article article);
	
	



}