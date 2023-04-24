package com.java501.S20230401.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleDao {

	int allTotalArt(Article article);
	List<Article> allArticleList(Article article);
	int totalArt(Article article);
	List<Article> articleList(Article article);
	Article detailArticle(Article article);
	int readPlusArticle(Article article);

	// 로그인
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);
	public int insertArticle(Article article);
	public Article getArticleById(Article searcher);
}
