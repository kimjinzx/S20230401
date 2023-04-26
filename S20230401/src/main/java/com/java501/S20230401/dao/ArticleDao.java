package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.util.SummaryType;

@Repository
public interface ArticleDao {

	Integer 						totalArticle(int brd_id);
	List<Article> 					articleTotal(Article article);
	Article 						detailContent(Article article);
	Integer							replyCount(int art_id);
	List<Article> 					artcleMenu(Article article);
	Integer 						upreadCount(Article article);
	List<Article> 					listMagnager();
	int 							writeArticle(Article article);
	public List<ArticleMember>	 	getArticleSummary(int boardNum, SummaryType summaryType);
	public int 						insertArticle(Article article);
	public Article 					getArticleById(Article searcher);
	int 							updateArticle(Article article);
	int								delete(Article article);
	int 							replyWrite(Reply reply);
	int 							replyDelete(Reply reply);

}
