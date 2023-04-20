package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleService {

	int allTotalArt(Article article);
	List<Article> allArticleList(Article article);
	int totalArt(Article article);
	List<Article> articleList(Article article);
	Article detailArticle(Article article);
	int readPlusArticle(Article article);
}
