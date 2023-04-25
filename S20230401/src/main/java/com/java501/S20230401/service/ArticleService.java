package com.java501.S20230401.service;

import java.util.List;

import com.java501.S20230401.model.Article;
import com.java501.S20230401.model.ArticleMember;
import com.java501.S20230401.util.SummaryType;

public interface ArticleService {
	int allTotalArt(Article article);
	List<Article> allArticleList(Article article);
	
	// 댓글
	Article detailShareArticle(Article article);
	int readShareArticle(Article article);
	// 글쓰기
	int writeShareArticle(Article article);
	
	
	// 로그인
	public List<ArticleMember> getArticleSummary(int boardNum, SummaryType summaryType);
	public int insertArticle(Article article);
	public Article getArticleById(Article searcher);
}
