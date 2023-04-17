package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleDao {

	int totalArticle();

	int totalEatingArticle();
	
	int totalSportsArticle();

	int totalShoppingArticle();

	int totalCurtureArticle();

	int totalHobbyArticle();

	int totalEtcArticle();
	
	List<Article> listArticle(Article article);

	List<Article> listEatingArticle(Article article);
	
	List<Article> listSportsArticle(Article article);

	List<Article> listShoppingArticle(Article article);

	List<Article> listCurtureArticle(Article article);

	List<Article> listHobbyArticle(Article article);

	List<Article> listEtcArticle(Article article);

}
