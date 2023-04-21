package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleDao {


	int 			totalArticle(Article article);
	List<Article> 	listArticle(Article article);
	Article 	detailArticle(Article article);
//	int 			favoriteCount(Article a);
	List<Article> 	replyList(Article a);
	int 			writeArticle(Article article);
//	int 			insertTrade(Article article);
	int 			deleteArticle(Article article);
	
	



}
