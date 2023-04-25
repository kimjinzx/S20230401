package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;

@Repository
public interface ArticleDao {

	Integer 			totalArticle(int brd_id);
	List<Article> 		articleTotal(Article article);
	Article 			detailContent(Article article);
	Integer				replyCount(int art_id);
	List<Article> 		artcleMenu(Article article);
	Integer 			upreadCount(Article article);
	List<Article> listMagnager();
	int writeArticle(Article article);

}
