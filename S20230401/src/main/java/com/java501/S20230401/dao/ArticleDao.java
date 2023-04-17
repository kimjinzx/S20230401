package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;

@Repository
public interface ArticleDao {

	int allTotalArt(Article article);
	List<Article> allArticleList(Article article);
	int totalArt(Article article);
	List<Article> articleList(Article article);
	Article detailArticle(Article article);

}
