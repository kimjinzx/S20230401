package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleDao {
	int				totalArticle();
	List<Article>	listArticle(Article article);
}
