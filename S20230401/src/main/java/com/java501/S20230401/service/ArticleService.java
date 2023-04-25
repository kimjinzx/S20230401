package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.Reply;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	Integer 			totalArticle(int brd_id);
	List<Article> 		articleTotal(Article article);
	Article 			detailContent(Article article);
	List<Article>		articleMenu(Article article);
	Integer 			upreadCount(Article article);
	List<Article> listMagnager();
	int writeArticle(Article article);

	public int insertArticle(Article article);

	public Article getArticleById(Article searcher);
}
