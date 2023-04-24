package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Comm;
import com.java501.S20230401.model.Region;

public interface ArticleService {

	int 			totalArticle(Article article);

	List<Article> 	listArticle(Article article);
	Article 		detailArticle(Article article);

	List<Region> 	regionName();
	List<Comm> 		categoryName();

//	int 			favoriteCount(Article a);

	List<Article> 	replyList(Article article);

	void 			writeArticle(Article article);
	int 			deleteArticle(Article article);
	int 			updateArticle(Article article);
	
	

	



	

}
