package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleDao {
	Integer			totalCustomer();
	List<Article> 	listCustomer(Article article);
	Integer			totalNotice();
	Article 		detailCustomer(Article article);
	
	
}
