package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	int				totalArticle();
	List<Article> 	listArticle(Article article);
	Article			cyArticlereadDetail(Article article);
	Article 		cyArticlereadupdate(Article article);
	Article 		detailArticle(int art_title);
	int				cyArticleinsert(Article article);
	int				cyArticlemodify(Article article);

	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);

	public int insertArticle(Article article);

	public Article getArticleById(Article searcher);

}
