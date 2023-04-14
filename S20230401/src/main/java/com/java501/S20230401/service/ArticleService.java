package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleService {

	int 			totalArticle();
	List<Article> 	listArticle(Article article);
	

}
