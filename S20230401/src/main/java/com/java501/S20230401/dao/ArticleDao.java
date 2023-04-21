package com.java501.S20230401.dao;

import java.util.List;

import com.java501.S20230401.model.Article;

public interface ArticleDao {
	Integer			totalNotice();
	List<Article>	listNotice(Article article);
	Article 		detailNotice(Article article);
	
}
